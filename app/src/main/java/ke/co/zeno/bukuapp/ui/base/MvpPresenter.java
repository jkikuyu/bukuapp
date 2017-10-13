package ke.co.zeno.bukuapp.ui.base;

/**
 *
 * https://blog.mindorks.com/using-snaphelper-in-recyclerview-fc616b6833e8
 * @author amitshekhar
 *  date: 13/01/17.
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();

}
