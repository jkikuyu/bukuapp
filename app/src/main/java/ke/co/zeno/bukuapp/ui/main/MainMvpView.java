package ke.co.zeno.bukuapp.ui.main;


import java.util.List;

import ke.co.zeno.bukuapp.model.Stream;
import ke.co.zeno.bukuapp.ui.base.MvpView;

/**
 *
 * https://blog.mindorks.com/using-snaphelper-in-recyclerview-fc616b6833e8
 * @author amitshekhar
 *  date: 13/01/17.
 *  modified by
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */

public interface MainMvpView extends MvpView {

    void showStreams(List<Stream> streamList);

}
