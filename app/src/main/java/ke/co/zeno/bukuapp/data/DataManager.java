package ke.co.zeno.bukuapp.data;

import java.util.List;

import ke.co.zeno.bukuapp.data.local.StreamDataHelper;
import ke.co.zeno.bukuapp.model.Stream;

/**
 * Created by Jude Kikuyu on 09/10/17.
 */

public class DataManager {
    private final StreamDataHelper mStreamDataHelper;

    public DataManager(StreamDataHelper dummyDataHelper) {
        this.mStreamDataHelper = dummyDataHelper;
    }

    public List<Stream> getStreamList() {
        return mStreamDataHelper.getStreamList();
    }

}
