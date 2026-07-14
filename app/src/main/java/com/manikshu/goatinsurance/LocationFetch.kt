package com.manikshu.goatinsurance

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import java.util.Locale

/**
 * Returns a lambda that fetches the device's current GPS coordinates from the
 * phone's location provider and reports them back as a "lat, lng" string via
 * [onResult]. On first use it requests the runtime location permission.
 * [onStatus] receives short user-facing status/error messages (also shown as a
 * Toast) — useful for driving a "fetching…" indicator in the caller.
 *
 * Call at the top level of a @Composable, then invoke the returned lambda from a
 * click handler (e.g. a "use my location" icon on a location field).
 */
@Composable
fun rememberGpsFetcher(
    onResult: (String) -> Unit,
    onStatus: (String) -> Unit = {},
): () -> Unit {
    val context = LocalContext.current
    val fused = remember { LocationServices.getFusedLocationProviderClient(context) }

    fun notify(msg: String) {
        onStatus(msg)
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { grants ->
        if (grants.values.any { it }) {
            fetchCurrentLocation(context, fused, onResult, ::notify)
        } else {
            notify("Location permission denied")
        }
    }

    return {
        val fine = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val coarse = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        if (fine || coarse) {
            fetchCurrentLocation(context, fused, onResult, ::notify)
        } else {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                )
            )
        }
    }
}

@SuppressLint("MissingPermission") // permission is verified by the caller before this runs
private fun fetchCurrentLocation(
    context: Context,
    fused: FusedLocationProviderClient,
    onResult: (String) -> Unit,
    notify: (String) -> Unit,
) {
    // Make sure location is switched on at the OS level.
    val lm = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
    val enabled = lm?.let {
        it.isProviderEnabled(LocationManager.GPS_PROVIDER) || it.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    } ?: false
    if (!enabled) {
        notify("Turn on location (GPS) to fetch coordinates")
        return
    }

    notify("Fetching location…")
    val cts = CancellationTokenSource()
    @Suppress("DEPRECATION")
    fused.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, cts.token)
        .addOnSuccessListener { loc: Location? ->
            if (loc != null) {
                onResult(formatCoords(loc))
            } else {
                // Current fix unavailable — fall back to the last known location.
                fused.lastLocation
                    .addOnSuccessListener { last ->
                        if (last != null) onResult(formatCoords(last))
                        else notify("Could not get location, please try again")
                    }
                    .addOnFailureListener { notify("Could not get location, please try again") }
            }
        }
        .addOnFailureListener { notify("Failed to fetch location") }
}

private fun formatCoords(loc: Location): String =
    String.format(Locale.US, "%.6f, %.6f", loc.latitude, loc.longitude)
