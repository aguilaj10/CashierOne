plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "com.jsm.cashierone"
version = "1.0.0"
application {
    mainClass.set("com.jsm.cashierone.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.logback)
    implementation(libs.kodein.di)
    implementation(libs.ktor.server.auth.jvm)
    implementation(libs.ktor.server.auth.jwt.jvm)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.resources)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.postgres)

    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}