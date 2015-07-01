package com.hpedrorodrigues.dradddle.dagger

import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.location.LocationManager
import android.net.ConnectivityManager
import android.view.LayoutInflater
import com.squareup.okhttp.OkHttpClient

import javax.inject.Singleton

import dagger.Module;
import dagger.Provides;

import com.hpedrorodrigues.dradddle.application.BaseApplication
import com.hpedrorodrigues.dradddle.constant.DradddleConstants
import com.hpedrorodrigues.dradddle.network.DradddleNetwork
import retrofit.RestAdapter
import java.util.concurrent.TimeUnit

Module public class DradddleModule(private val application: BaseApplication) {

    Provides fun provideContext(): Context {
        return application.getBaseContext()
    }

    Provides fun providePreferenceManager(): SharedPreferences {
        return application.getSharedPreferences(DradddleConstants.PREFERENCES, 0)
    }

    Provides fun provideConnectivityManager(): ConnectivityManager {
        return application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    Provides Singleton fun provideRestAdapter(): RestAdapter {
        return RestAdapter.Builder().setEndpoint(DradddleConstants.DRIBBBLE_ENDPOINT).build()
    }

    Provides
    fun provideMovieNetwork(restAdapter: RestAdapter): DradddleNetwork {
        return restAdapter.create<DradddleNetwork>(javaClass<DradddleNetwork>())
    }

    Provides Singleton fun provideLocationManager(): LocationManager {
        return application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    Provides fun provideContentResolver(): ContentResolver {
        return application.getContentResolver()
    }

    Provides fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient()
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS)
        okHttpClient.setReadTimeout(1, TimeUnit.MINUTES)
        okHttpClient.setWriteTimeout(1, TimeUnit.MINUTES)
        return okHttpClient
    }

    Provides fun provideResources(): Resources {
        return application.getResources()
    }

    Provides fun provideLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }
}