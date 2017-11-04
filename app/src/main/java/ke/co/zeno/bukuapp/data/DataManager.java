package ke.co.zeno.bukuapp.data;

import android.database.Cursor;

import java.util.List;

/**
 *
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */
public interface DataManager {

    public List <?> getItemList();
    public void insert(String name, String desc) ;
    public Cursor fetch(String qry);
    public int update(long _id, String name, String desc);
    public void delete(long _id);

}

