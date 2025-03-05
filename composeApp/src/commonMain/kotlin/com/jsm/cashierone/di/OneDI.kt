package com.jsm.cashierone.di

import com.jsm.cashierone.api.createHttpClient
import com.jsm.cashierone.features.login.LoginRepository
import com.jsm.cashierone.features.login.LoginRepositoryImpl
import com.jsm.cashierone.features.login.LoginViewModel
import org.kodein.di.DI
import org.kodein.di.bindInstance
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val loginModule = DI.Module("LoginModule") {
    bindInstance<LoginRepository> { LoginRepositoryImpl(createHttpClient()) }
    bindSingleton { LoginViewModel(instance()) }
}
