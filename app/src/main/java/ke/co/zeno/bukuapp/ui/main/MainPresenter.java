package ke.co.zeno.bukuapp.ui.main;


import ke.co.zeno.bukuapp.data.DataManager;
import ke.co.zeno.bukuapp.ui.base.BasePresenter;

/**
 *
 * https://blog.mindorks.com/using-snaphelper-in-recyclerview-fc616b6833e8
 * @author amitshekhar
 *  date: 13/01/17.
 *  modified by
 *  @author Jude Kikuyu
 *  date: 10/10/2017
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
