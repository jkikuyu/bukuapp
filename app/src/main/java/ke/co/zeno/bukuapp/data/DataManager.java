package ke.co.zeno.bukuapp.data;

import java.util.List;

import ke.co.zeno.bukuapp.data.local.StreamDataHelper;
import ke.co.zeno.bukuapp.model.Stream;

/**
 *
 *  @author Jude Kikuyu
 *  date: 10/10/2017
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
