package com.jsm.cashierone.config

import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    Database.connect(
        url = "jdbc:postgresql://ep-patient-grass-a6pkoyeq.us-west-2.aws.neon.tech/mi_restaurante?user=aguilaj10&password=CKuogV7JYBy2&sslmode=require",
        driver = "org.postgresql.Driver",
        user = "aguilaj10",
        password = "CKuogV7JYBy2",
    )
}