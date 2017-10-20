package ke.co.zeno.bukuapp.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.data.local.StreamDataHelper;
import ke.co.zeno.bukuapp.model.Stream;
import ke.co.zeno.bukuapp.ui.main.adapter.StreamHelperAdapter;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubjectFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private StreamHelperAdapter mStreamHelperAdapter;

    private View rootView;


    public SubjectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubjectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubjectFragment newInstance(String param1, String param2) {
        SubjectFragment fragment = new SubjectFragment();
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

        LinearLayoutManager layoutManagerCenter
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView streamsRecycler = (RecyclerView)rootView.findViewById(R.id.streamsRecycler);
        streamsRecycler.setLayoutManager(layoutManagerCenter);
        mStreamHelperAdapter = new StreamHelperAdapter(getActivity());
        mStreamHelperAdapter.setClickListener(this);
        List<Stream> streamList = new StreamDataHelper().getStreamList();
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


}
