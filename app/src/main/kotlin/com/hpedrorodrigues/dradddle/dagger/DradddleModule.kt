package com.hpedrorodrigues.dradddle.dagger

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.net.ConnectivityManager
import android.view.LayoutInflater

import javax.inject.Singleton

import dagger.Module;
import dagger.Provides;

import com.hpedrorodrigues.dradddle.application.BaseApplication
import com.hpedrorodrigues.dradddle.constant.DradddleConstants
import com.hpedrorodrigues.dradddle.network.DradddleNetwork
import com.hpedrorodrigues.dradddle.util.VersionInfo
import retrofit.RestAdapter

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

    Provides fun provideMovieNetwork(restAdapter: RestAdapter): DradddleNetwork {
        return restAdapter.create<DradddleNetwork>(javaClass<DradddleNetwork>())
    }

    Provides fun provideResources(): Resources {
        return application.getResources()
    }

    Provides fun provideLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }
}