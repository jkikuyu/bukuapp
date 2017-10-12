package ke.co.zeno.bukuapp.ui.main;


import ke.co.zeno.bukuapp.data.DataManager;
import ke.co.zeno.bukuapp.ui.base.BasePresenter;

/**
 * Created by amitshekhar on 13/01/17.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    final DataManager mDataManager;

    public MainPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getStreamList() {
        getMvpView().showStreams(mDataManager.getStreamList());
    }


}
