package ke.co.zeno.bukuapp;

import android.app.Application;
import android.content.Context;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.EntypoModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.joanzapata.iconify.fonts.TypiconsModule;

import ke.co.zeno.bukuapp.data.DataManager;
import ke.co.zeno.bukuapp.injection.ApplicationDependency;

/**
 * Created by nimda on 05/10/17.
 */

public class BukuApp extends Application {
    private ApplicationDependency applicationDependency;

    public static BukuApp get(Context context) {

        return (BukuApp) context.getApplicationContext();
    }


    @Override
        public void onCreate() {
            super.onCreate();
            Iconify.with(new FontAwesomeModule())
                    .with(new EntypoModule())
                    .with(new TypiconsModule())
                    .with(new MaterialModule())
                    .with(new IoniconsModule());
            applicationDependency = ApplicationDependency.inject(this);

        }
    public DataManager getDataManager() {
        return applicationDependency.getDataManager();
    }

}

