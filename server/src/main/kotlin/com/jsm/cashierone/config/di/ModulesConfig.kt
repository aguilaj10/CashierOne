package com.jsm.cashierone.config.di

import com.jsm.cashierone.controller.JWTController
import com.jsm.cashierone.repository.user.UserRepository
import com.jsm.cashierone.repository.user.UserRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import java.time.Clock

object ModulesConfig {
    private val securityModule = DI.Module("SECURITY") {
        bindSingleton { JWTController(instance()) }
    }

    private val repositoryModule = DI.Module("REPOSITORY") {
        bindProvider<UserRepository> { UserRepositoryImpl() }

    }

    private val controllerModule = DI.Module("CONTROLLER") {

    }

    private val commonModule = DI.Module("COMMON") {
        bindSingleton { Clock.systemDefaultZone() }
    }

    val di = DI {
        importAll(securityModule, repositoryModule, controllerModule, commonModule)
    }
}