package com.emergencyconnect

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.time.Instant
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger

@Serializable
data class EmergencyReport(
    val id: Int,
    val name: String,
    val phone: String,
    val type: String,
    val location: String,
    val details: String,
    val urgency: String,
    val createdAt: String
)

@Serializable
data class ReportRequest(
    val name: String,
    val phone: String,
    val type: String,
    val location: String,
    val details: String,
    val urgency: String
)

@Serializable
data class VolunteerRequest(val name: String, val email: String, val phone: String, val skills: String)

@Serializable
data class ApiMessage(val message: String, val id: Int? = null)

private val reports = CopyOnWriteArrayList<EmergencyReport>()
private val nextId = AtomicInteger(1)

fun main() {
    embeddedServer(Netty, port = (System.getenv("PORT") ?: "8080").toInt(), host = "0.0.0.0") {
        install(ContentNegotiation) { json(Json { prettyPrint = true; ignoreUnknownKeys = true }) }
        routing {
            get("/api/health") { call.respond(mapOf("status" to "ok", "app" to "EmergencyConnect")) }
            get("/api/reports") { call.respond(reports.sortedByDescending { it.id }) }
            post("/api/reports") {
                val input = call.receive<ReportRequest>()
                if (input.name.isBlank() || input.phone.isBlank() || input.location.isBlank() || input.details.isBlank()) {
                    call.respond(io.ktor.http.HttpStatusCode.BadRequest, ApiMessage("Please complete all required fields."))
                    return@post
                }
                val report = EmergencyReport(
                    id = nextId.getAndIncrement(), name = input.name.trim(), phone = input.phone.trim(),
                    type = input.type, location = input.location.trim(), details = input.details.trim(),
                    urgency = input.urgency, createdAt = Instant.now().toString()
                )
                reports.add(report)
                call.respond(io.ktor.http.HttpStatusCode.Created, ApiMessage("Emergency report received. Reference #${report.id}.", report.id))
            }
            post("/api/volunteers") {
                val input = call.receive<VolunteerRequest>()
                if (input.name.isBlank() || input.email.isBlank() || input.phone.isBlank()) {
                    call.respond(io.ktor.http.HttpStatusCode.BadRequest, ApiMessage("Please complete your name, email and phone number."))
                    return@post
                }
                // Demo project: volunteer details are validated but not persisted.
                call.respond(io.ktor.http.HttpStatusCode.Created, ApiMessage("Thank you, ${input.name.trim()}! Your volunteer interest has been recorded for this demo."))
            }
            get("/api/resources") {
                call.respond(listOf(
                    mapOf("title" to "Emergency services", "description" to "For immediate danger, call your local emergency number."),
                    mapOf("title" to "Medical help", "description" to "Move to a safe place and provide first aid only if safe and trained."),
                    mapOf("title" to "Disaster updates", "description" to "Follow official municipal and emergency-service announcements."),
                    mapOf("title" to "Family check-in", "description" to "Agree on a meeting point and keep your phone charged.")
                ))
            }
            staticResources("/") { }
        }
    }.start(wait = true)
}
