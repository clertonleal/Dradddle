package com.hpedrorodrigues.dradddle.application;

import android.app.Application;

import com.hpedrorodrigues.dradddle.dagger.DaggerDradddleComponent;
import com.hpedrorodrigues.dradddle.dagger.DradddleComponent;
import com.hpedrorodrigues.dradddle.dagger.DradddleModule;

public abstract class BaseApplication extends Application {

    protected DradddleComponent buildDaggerComponent() {
        return DaggerDradddleComponent
                .builder()
                .dradddleModule(new DradddleModule(this))
                .build();
    }
}