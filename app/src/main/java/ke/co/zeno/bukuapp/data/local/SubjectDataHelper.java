package ke.co.zeno.bukuapp.data.local;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.data.DataManager;
import ke.co.zeno.bukuapp.model.Subject;

/**
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */

public class SubjectDataHelper implements DataManager {
    public List<Subject> getItemList(Bundle bundle) {

        List<Subject> subjectList = new ArrayList<>();

        subjectList.add(new Subject("Maths", R.drawable.ic_math));
        subjectList.add(new Subject("English", R.drawable.ic_english));
        subjectList.add(new Subject("Kiswahili", R.drawable.ic_kiswahili));
        subjectList.add(new Subject("S. Studies", R.drawable.ic_sst));
        subjectList.add(new Subject("C.R.E", R.drawable.ic_cre));
        subjectList.add(new Subject("Science", R.drawable.ic_science));
        return subjectList;
    }

    @Override
    public void insert(String name, String desc) {
        
    }

    @Override
    public Cursor fetch(String qry) {
        return null;
    }

    @Override
    public int update(long _id, String name, String desc) {
        return 0;
    }

    @Override
    public void delete(long _id) {

    }

}
