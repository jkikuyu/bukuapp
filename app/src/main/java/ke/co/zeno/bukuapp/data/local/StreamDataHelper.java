package ke.co.zeno.bukuapp.data.local;

import java.util.ArrayList;
import java.util.List;

import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.data.DataManager;
import ke.co.zeno.bukuapp.model.Stream;

/**
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */

public class StreamDataHelper implements DataManager {
    public List<Stream> getStreamList() {

        List<Stream> streamList = new ArrayList<>();

        streamList.add(new Stream("Std 1", R.drawable.ic_std1));
        streamList.add(new Stream("Std 2", R.drawable.ic_std2));
        streamList.add(new Stream("Std 3", R.drawable.ic_std3));
        streamList.add(new Stream("Std 4", R.drawable.ic_std4));
        streamList.add(new Stream("Std 5", R.drawable.ic_std5));
        streamList.add(new Stream("Std 6", R.drawable.ic_std6));
        streamList.add(new Stream("Std 7", R.drawable.ic_std7));
        streamList.add(new Stream("Std 8", R.drawable.ic_std8));


        return streamList;
    }

}
