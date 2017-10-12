package ke.co.zeno.bukuapp.ui.main;


import ke.co.zeno.bukuapp.ui.base.MvpPresenter;

/**
 * Created by amitshekhar on 13/01/17.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void getStreamList();

}
