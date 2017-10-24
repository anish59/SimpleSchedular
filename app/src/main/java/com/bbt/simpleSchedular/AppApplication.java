package com.bbt.simpleSchedular;

import android.app.Application;

import com.bbt.simpleSchedular.dataBaseHelper.AppOpenHelper;
import com.bbt.simpleSchedular.dataBaseHelper.DatabaseManager;
import com.facebook.stetho.Stetho;

/**
 * Created by anish on 24-10-2017.
 */

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseManager.initialize(AppOpenHelper.getInstance((this)));
        AppOpenHelper.getInstance((this)).createDataBase(this);

        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
