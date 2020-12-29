package com.example.loader

import android.app.Application
import com.example.loader.data.Repository
import com.example.loader.data.network.RetrofitClient
import com.example.loader.ui.main.CourseViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        bind() from singleton { provideOkHttpClient() }
        bind() from singleton { RetrofitClient(instance()) }
        bind() from singleton { String() }
        bind() from singleton { Repository(instance()) }
        bind() from singleton { CourseViewModelFactory(instance()) }
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
    }
}