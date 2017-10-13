package ke.co.zeno.bukuapp.injection;

import android.content.Context;

import ke.co.zeno.bukuapp.BukuApp;
import ke.co.zeno.bukuapp.data.DataManager;
import ke.co.zeno.bukuapp.ui.base.MvpPresenter;
import ke.co.zeno.bukuapp.ui.base.MvpView;
import ke.co.zeno.bukuapp.ui.main.MainActivity;
import ke.co.zeno.bukuapp.ui.main.MainMvpPresenter;
import ke.co.zeno.bukuapp.ui.main.MainMvpView;
import ke.co.zeno.bukuapp.ui.main.MainPresenter;


/**
 *
 * https://blog.mindorks.com/using-snaphelper-in-recyclerview-fc616b6833e8
 * @author amitshekhar
 *  date: 13/01/17.
 */
public abstract class ActivityDependency<P extends MvpPresenter<? extends MvpView>> {

    public static MainActivityDependency inject(MainActivity activity) {
        return new MainActivityDependency(activity);
    }

    public abstract P getMvpPresenter();

    protected DataManager getDataManager(Context context) {
        return BukuApp.get(context.getApplicationContext()).getDataManager();
    }


    public static class MainActivityDependency extends ActivityDependency<MainMvpPresenter<MainMvpView>> {

        private MainMvpPresenter<MainMvpView> mvpPresenter;

        protected MainActivityDependency(MainActivity activity) {
            mvpPresenter = new MainPresenter<>(getDataManager(activity));
        }

        @Override
        public MainMvpPresenter<MainMvpView> getMvpPresenter() {
            return mvpPresenter;
        }
    }

}
