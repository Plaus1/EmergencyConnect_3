# EmergencyConnect — Kotlin web application

A responsive community emergency-support website with an HTML/CSS/JavaScript frontend and a Kotlin + Ktor backend. It includes an emergency incident form, a volunteer-interest form, safety resources, and JSON API routes.

> **Safety note:** This is a student/demo project. It does not contact emergency services and must not be relied on for real dispatch. In immediate danger, call the official emergency number for your location.

## Requirements
- JDK 21
- VS Code
- Gradle (the project uses the Gradle Kotlin DSL; if you do not have Gradle installed, install it or generate a Gradle wrapper using `gradle wrapper` from this folder)
- VS Code extensions: Extension Pack for Java and Kotlin Language

## Run in VS Code
1. Extract the ZIP file.
2. Open the `EmergencyConnectKotlin` folder in VS Code (File → Open Folder).
3. Ensure JDK 21 and Gradle are installed and available in the VS Code terminal.
4. In the terminal, run:
   - Windows: `gradle run`
   - macOS/Linux: `gradle run`
5. Open `http://localhost:8080` in your browser.
6. Check the backend at `http://localhost:8080/api/health`.

If you have a Gradle wrapper, use `gradlew.bat run` on Windows or `./gradlew run` on macOS/Linux.

## API routes
- `GET /api/health` — basic health check
- `GET /api/resources` — sample safety resources in JSON
- `GET /api/reports` — reports received during the current server session
- `POST /api/reports` — submit an emergency report
- `POST /api/volunteers` — validate a volunteer-interest submission

## Important limitations
- Reports are held in memory and disappear when the server stops. Add a database (for example PostgreSQL or SQL Server) before using this beyond a classroom demo.
- Volunteer submissions are validated but are not persisted or emailed.
- There is no authentication, role-based access, moderation workflow, SMS integration, maps/geolocation, or real emergency-service connection yet.
- Do not publish real people's personal or medical details in this demo. Add privacy controls, secure storage, HTTPS, validation, rate limiting and access controls before any real deployment.

## Project structure
```
EmergencyConnectKotlin/
├── build.gradle.kts
├── settings.gradle.kts
├── README.md
└── src/main/
    ├── kotlin/com/emergencyconnect/Application.kt
    └── resources/static/
        ├── index.html
        ├── styles.css
        └── app.js
```
