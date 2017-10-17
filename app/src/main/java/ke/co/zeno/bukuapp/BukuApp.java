package ke.co.zeno.bukuapp;

import android.app.Application;
import android.content.Context;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.EntypoModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.joanzapata.iconify.fonts.TypiconsModule;

/**
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */
public class BukuApp extends Application {

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

        }

}

