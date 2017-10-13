package ke.co.zeno.bukuapp.ui.main;


import ke.co.zeno.bukuapp.ui.base.MvpPresenter;

/**
 * https://blog.mindorks.com/using-snaphelper-in-recyclerview-fc616b6833e8
 * @author amitshekhar
 *  date: 13/01/17.
 *  modified by
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void getStreamList();

}
