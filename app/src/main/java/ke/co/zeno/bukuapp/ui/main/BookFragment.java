package ke.co.zeno.bukuapp.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.SQLException;
import java.util.List;

import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.data.DatabaseHelper;
import ke.co.zeno.bukuapp.data.local.BookDataHelper;
import ke.co.zeno.bukuapp.model.Book;
import ke.co.zeno.bukuapp.ui.main.adapter.BookHelperAdapter;

import static android.content.ContentValues.TAG;

/**
 * @author Jude Kikuyu
 * date:19/10/2017
 * A simple {@link Fragment} subclass.
 * Use the {@link BookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookFragment extends Fragment implements BookHelperAdapter.ClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private BookHelperAdapter mBookHelperAdapter;
    private View rootView;
    private DatabaseHelper dbHelper;


    public BookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getActivity());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        try {
            dbHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_book, container, false);
        rootView.setTag(TAG);
        setUpRecyclerView();

        return rootView;
    }
    private void setUpRecyclerView() {

        LinearLayoutManager layoutManagerCenter
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView booksRecycler = (RecyclerView)rootView.findViewById(R.id.booksRecycler);
        booksRecycler.setLayoutManager(layoutManagerCenter);
        mBookHelperAdapter = new BookHelperAdapter(getActivity());
        mBookHelperAdapter.setClickListener(this);
        BookDataHelper bookDataHelper = new BookDataHelper(dbHelper);
        List<Book> bookList = bookDataHelper.getItemList();
        mBookHelperAdapter.updateList(bookList);

        booksRecycler.setAdapter(mBookHelperAdapter);
        SnapHelper snapHelperCenter = new LinearSnapHelper();
        snapHelperCenter.attachToRecyclerView(booksRecycler);

/*
        LinearLayoutManager layoutManagerStart
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        startSnapRecyclerView.setLayoutManager(layoutManagerStart);
        appListStartAdapter = new AppListAdapter(this);
        startSnapRecyclerView.setAdapter(appListStartAdapter);

        SnapHelper snapHelperStart = new StartSnapHelper();
        snapHelperStart.attachToRecyclerView(startSnapRecyclerView);
*/

    }

    @Override
    public void itemClicked(View view, int position) {
        Bundle bundle = new Bundle();
        position +=1; // add 1 to the position to get the book
        String pos = Integer.toString(position);
        bundle.putString("book",pos);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SubjectFragment subjectFragment = new SubjectFragment();
        subjectFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragment_container, subjectFragment, "subjects");
        fragmentTransaction.commit();

    }
    @Override
    public void onDestroy(){
            super.onDestroy();
            dbHelper.close();
    }
}