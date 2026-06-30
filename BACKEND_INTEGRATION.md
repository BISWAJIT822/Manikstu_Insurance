# Backend Integration & Device Testing

This app talks to the FastAPI backend in `S:\Somu\Githubs\backend_goat`.

## Architecture (added)
- `Models.kt` — Kotlin DTOs matching every backend response (snake_case mapped via `@SerialName`).
- `ApiService.kt` — Retrofit interface for all 40 endpoints (auth / admin / sd / farmer / coordinator).
- `NetworkModule.kt` — Hilt-provided Retrofit + OkHttp, JWT `Authorization: Bearer` interceptor, `BuildConfig.BASE_URL`.
- `SessionManager.kt` — DataStore token + role + user storage; `AuthTokenHolder` gives the interceptor a synchronous token.
- `Repository.kt` — thin suspend wrappers over `ApiService`.
- `AuthViewModel.kt` + `ViewModels.kt` — per-feature Hilt ViewModels exposing `UiState<T>` (Idle/Loading/Success/Error).

## BASE_URL — switchable
Set in `app/build.gradle.kts` (`buildConfigField BASE_URL`). Default: `http://localhost:8000/`.

| Scenario | BASE_URL | Setup |
|---|---|---|
| USB device (default) | `http://localhost:8000/` | `adb reverse tcp:8000 tcp:8000` |
| Emulator | `http://10.0.2.2:8000/` | none |
| Any network (Wi-Fi/4G) | `https://<sub>.loca.lt/` | run localtunnel (below) |

Cleartext HTTP is allowed (`usesCleartextTraffic="true"` in the manifest).

## 1. Run the backend
```
cd S:\Somu\Githubs\backend_goat
python main.py            # serves on 0.0.0.0:8000
```
Seeds 3 pre-approved demo logins so you can sign in immediately:

| Role | Mobile | OTP |
|---|---|---|
| Suraksha Didi | 9000000010 | shown in app (dev mode) |
| Farmer | 9000000030 | shown in app |
| Coordinator | 9000000020 | shown in app |

The OTP is returned by the backend and shown as a Toast ("Dev OTP: ……") — type it in.
New sign-ups land **pending admin approval** (approve them from the web admin dashboard at
`http://localhost:8000/dashboard`, login `9999999999`).

## 2a. Test on a USB device (recommended) — adb reverse
```
adb devices                       # confirm device is listed
adb reverse tcp:8000 tcp:8000     # device localhost:8000 -> PC:8000
adb install -r app/build/outputs/apk/debug/app-debug.apk
```
Keep BASE_URL = `http://localhost:8000/`. Re-run `adb reverse` after replug/reboot.

## 2b. Test over any network — localtunnel (public URL)
```
npx localtunnel --port 8000       # prints https://<sub>.loca.lt
```
Put that URL in `BASE_URL`, rebuild, install. (loca.lt may show a one-time interstitial in a browser;
the tunnel password is your public IP from https://loca.lt/mytunnelpassword.)
Alternative: `cloudflared tunnel --url http://localhost:8000`.

## 3. Remote / wireless debugging (no cable)
Android 11+:
```
# once over USB:
adb tcpip 5555
adb shell ip route        # find the device IP (e.g. 192.168.1.42)
adb connect 192.168.1.42:5555
# then unplug USB; adb reverse also works over this connection:
adb reverse tcp:8000 tcp:8000
```
Android 12+ alternative: Developer Options → Wireless debugging → Pair with code, then
`adb pair <ip:port>` and `adb connect <ip:port>`.

> Note: on MIUI/HyperOS, enable Developer Options → **USB debugging (Security settings)**
> to allow installs and (if you want it) input simulation.

## What's wired to live data
- Auth: login + signup (real OTP, JWT stored, role-gated, server errors surfaced).
- Suraksha Didi dashboard (enrolled / pending / policies / premium).
- Goat list (tabs all/active/expired/claimed + search, live).
- Coordinator dashboard (enrollments / claims today / policies expiring).
- Farmer dashboard (policies + next vaccination).
- Profile logout calls `/auth/logout` and clears the token.

## Still on mock data (ViewModels ready in `ViewModels.kt`, UI binding pending)
Enrollment 7-step submit, record vaccination, vaccination list, mortality report + photos,
claim list/review (coordinator), goat detail, reports, team. Each has a matching ViewModel
(`EnrollmentViewModel`, `VaccinationViewModel`, `MortalityViewModel`, `ClaimViewModel`,
`CoordinatorViewModel`, `GoatViewModel.loadDetail`) — wire the screen's mock block to the
ViewModel `UiState` the same way `GoatListContent` / dashboards were done.
