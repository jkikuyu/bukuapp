package ke.co.zeno.bukuapp.utils;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 *
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */

public class RecyclerTouchListener implements  RecyclerView.OnItemTouchListener{

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}