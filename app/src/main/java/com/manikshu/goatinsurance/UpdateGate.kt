package com.manikshu.goatinsurance

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SystemUpdate

/**
 * Gates the app behind a version check. While checking, shows a brief splash;
 * if the installed build is below the backend's min_version_code, shows a
 * blocking update screen; otherwise renders [content]. Fails open on network error.
 */
@Composable
fun UpdateGate(content: @Composable () -> Unit) {
    val vm: UpdateGateViewModel = hiltViewModel()
    val state by vm.state.collectAsState()

    when (val s = state) {
        is UpdateState.Checking -> UpdateSplash()
        is UpdateState.UpdateRequired -> UpdateRequiredScreen(s.updateUrl, s.latestVersion)
        is UpdateState.Ok -> content()
    }
}

@Composable
private fun UpdateSplash() {
    Box(
        modifier = Modifier.fillMaxSize().background(PrimaryGreen),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painterResource(R.drawable.ic_logo_custom),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(140.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
        }
    }
}

@Composable
private fun UpdateRequiredScreen(updateUrl: String, latestVersion: String) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF8F9F5)).padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(96.dp)
                    .background(Color(0xFFE8F5E9), RoundedCornerShape(48.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.SystemUpdate, null, tint = PrimaryGreen, modifier = Modifier.size(52.dp))
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Update Required",
                fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                if (latestVersion.isNotBlank())
                    "A newer version ($latestVersion) is available. Please update to continue using the app."
                else
                    "A newer version is available. Please update to continue using the app.",
                fontSize = 15.sp, color = Color.Gray, textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    if (updateUrl.isNotBlank()) {
                        try {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl)))
                        } catch (_: Exception) { /* no browser / bad url */ }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen)
            ) {
                Text("Update Now", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}
