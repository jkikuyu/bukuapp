package ke.co.zeno.bukuapp.ui.main;


import java.util.List;

import ke.co.zeno.bukuapp.model.Stream;
import ke.co.zeno.bukuapp.ui.base.MvpView;

/**
 * Created by amitshekhar on 13/01/17.
 */

public interface MainMvpView extends MvpView {

    void showStreams(List<Stream> streamList);

}
