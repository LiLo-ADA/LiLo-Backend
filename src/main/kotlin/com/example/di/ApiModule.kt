package com.example.di

import com.example.data.api.AuthApi
import com.example.data.api.LockerApi
import org.koin.dsl.module

object ApiModule {
    val provide = module {
        single { AuthApi }
        single { LockerApi }
    }
}