package ke.co.zeno.bukuapp.ui.main;


import android.app.Activity;
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

import java.util.List;

import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.data.DatabaseHelper;
import ke.co.zeno.bukuapp.data.local.StreamDataHelper;
import ke.co.zeno.bukuapp.model.Stream;
import ke.co.zeno.bukuapp.ui.main.adapter.StreamHelperAdapter;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StreamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StreamFragment extends Fragment implements StreamHelperAdapter.ClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "dbHelper";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private StreamHelperAdapter mStreamHelperAdapter;
    private View rootView;
    private DatabaseHelper dbHelper;

    public StreamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StreamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StreamFragment newInstance(String param1, String param2, DatabaseHelper dbHelper) {
        StreamFragment fragment = new StreamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_stream, container, false);
        rootView.setTag(TAG);
        setUpRecyclerView();

        return rootView;
    }
    private void setUpRecyclerView() {
        Activity mActivity = getActivity();
        LinearLayoutManager layoutManagerCenter
                = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView streamsRecycler = (RecyclerView)rootView.findViewById(R.id.streamsRecycler);
        streamsRecycler.setLayoutManager(layoutManagerCenter);
        mStreamHelperAdapter = new StreamHelperAdapter(mActivity);
        mStreamHelperAdapter.setClickListener(this);

        List<Stream> streamList = new StreamDataHelper().getItemList();

        mStreamHelperAdapter.updateList(streamList);
        streamsRecycler.setAdapter(mStreamHelperAdapter);
        SnapHelper snapHelperCenter = new LinearSnapHelper();
        snapHelperCenter.attachToRecyclerView(streamsRecycler);

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
        position +=1; // add 1 to the position to get the stream
        String pos = Integer.toString(position);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SubjectFragment subjectFragment = new SubjectFragment();
        subjectFragment.newInstance("stream",pos);

        fragmentTransaction.replace(R.id.fragment_container, subjectFragment, "subjects").
                addToBackStack(null);
        fragmentTransaction.commit();

    }
}
