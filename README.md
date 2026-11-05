# EmergencyConnect_3
# EmergencyConnect – GitHub Pages Prototype

EmergencyConnect is a South African emergency and disaster assistance prototype based on the Part 1 design. It provides weather information, emergency alerts, emergency service searches, emergency contacts, safety information, offline-information guidance and settings.

## Part 2 prototype features

- Responsive Home/Dashboard
- Registration and Login user flow
- Input validation and error messages
- Weather REST API integration
- Emergency alerts
- Emergency contacts
- Emergency service map links
- Safety information
- Offline information guidance
- Settings stored in browser local storage
- Dark mode
- English/isiZulu preference placeholder
- GitHub Pages compatible static front end

## Important authentication note

GitHub Pages is a static hosting service. The demonstration login in this prototype uses browser local storage so the UI can be tested without a server. **Do not use this local-storage password mechanism as a production authentication system.**

For the final Part 2 requirement that data be stored in an online authentication service and that passwords be securely handled, connect the registration/login forms to **Firebase Authentication**. Firebase should manage passwords; the application should never store plain-text passwords in its own database.

## REST API

The weather prototype uses the Open-Meteo REST API from JavaScript. The application sends an HTTP request and processes JSON weather data before displaying it.

## GitHub Pages

1. Create a GitHub repository, for example `EmergencyConnect`.
2. Upload `index.html`, `login.html`, `register.html`, `css/` and `js/`.
3. Go to **Settings → Pages**.
4. Select **Deploy from a branch**.
5. Select the `main` branch and `/root`.
6. Save and wait for GitHub Pages to publish.

## Recommended Part 2 evidence

Capture screenshots of:
1. Home dashboard
2. Registration validation
3. Successful registration
4. Login validation
5. Successful login
6. Weather REST API result
7. Emergency alerts
8. Emergency contacts
9. Emergency service map links
10. Safety information
11. Settings
12. GitHub repository
13. GitHub Actions workflow
14. Firebase Authentication users
15. API/database data where applicable

## Android/PoE alignment

The original Part 1 proposal identified Android Studio, Kotlin, REST APIs/JSON, Room/SQLite, Google Maps SDK, Location Services and notifications. This website is a browser prototype for GitHub Pages; it should not be presented as replacing the required Android implementation where the rubric specifically requires an Android application.

## AI-use statement

If AI assistance is used for code generation, debugging, documentation, design ideas or other development tasks, disclose those uses according to the institution's submission requirements. Keep the final statement within the required 500-word limit.
