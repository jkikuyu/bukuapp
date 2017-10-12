package ke.co.zeno.bukuapp.ui.base;

/**
 * Created by amitshekhar on 13/01/17.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();

}
