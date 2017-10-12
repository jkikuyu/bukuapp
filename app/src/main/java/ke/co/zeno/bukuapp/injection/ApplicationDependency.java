package ke.co.zeno.bukuapp.injection;

import android.app.Application;
import android.content.Context;

import ke.co.zeno.bukuapp.data.DataManager;
import ke.co.zeno.bukuapp.data.local.StreamDataHelper;


/**
 * Created by amitshekhar on 13/01/17.
 */

public class ApplicationDependency {

    private DataManager mDataManager;

    private ApplicationDependency(Context context) {
        StreamDataHelper streamDataHelper = new StreamDataHelper();
        mDataManager = new DataManager(streamDataHelper);
    }

    public static ApplicationDependency inject(Application application) {
        return new ApplicationDependency(application);
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}
