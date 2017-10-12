package ke.co.zeno.bukuapp.data.local;

import java.util.ArrayList;
import java.util.List;

import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.model.Stream;

/**
 * Created by nimda on 09/10/17.
 */

public class StreamDataHelper {
    public List<Stream> getAppList() {

        List<Stream> StreamList = new ArrayList<>();

        appList.add(new Stream("Std 1", R.drawable.ic_std1));
        appList.add(new App("Skype", R.drawable.ic_skype));
        appList.add(new App("Facebook", R.drawable.ic_facebook));
        appList.add(new App("Google+", R.drawable.ic_gplus));
        appList.add(new App("Instagram", R.drawable.ic_instagram));
        appList.add(new App("LinkedIn", R.drawable.ic_linkedin));
        appList.add(new App("Quora", R.drawable.ic_quora));
        appList.add(new App("Twitter", R.drawable.ic_twitter));
        appList.add(new App("Tumblr", R.drawable.ic_tumblr));
        appList.add(new App("Email", R.drawable.ic_email));
        appList.add(new App("Gallery", R.drawable.ic_gallery));


        return appList;
    }

}
