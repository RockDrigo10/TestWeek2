package com.example.admin.testweek2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String TAG = "List";
    ListView list;
    Button btnAdd;
    String[] item;
    String[] id;
    DataBase databaseHelper;
    List<Cars> carsList;
    RecyclerView rvAnimalList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    CarListAdapter carListAdapter;

    private OnFragmentInteractionListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //btnAdd = (Button) view.findViewById(R.id.btnAdd);
        databaseHelper = new DataBase(getActivity());
        list = (ListView) view.findViewById(R.id.list);
        NotificationChanged();
        list.setOnItemClickListener(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction("-1");
                Log.d(TAG, "onClick: " );
            }
        });

        rvAnimalList = (RecyclerView) view.findViewById(R.id.rvAnimalList);
        layoutManager = new LinearLayoutManager(view.getContext());
        itemAnimator =  new DefaultItemAnimator();
        rvAnimalList.setLayoutManager(layoutManager);
        rvAnimalList.setItemAnimator(itemAnimator);
        ////initialize
        initCarList();
        carListAdapter = new CarListAdapter(carsList);
        rvAnimalList.setAdapter(carListAdapter);
        carListAdapter.notifyDataSetChanged();

    }
    private void initCarList() {
        databaseHelper = new DataBase(getContext());
        carsList = databaseHelper.getCars();


    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String string) {
        if (mListener != null) {
            mListener.onFragmentInteraction(string);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mListener.onFragmentInteraction(id[i]);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String string);
    }
    public void NotificationChanged(){
        String val = mParam1;
        if(val == null)
            val = "-1";
        Log.d(TAG, "onActivityCreated: " + mParam1 + val);
        try {
            ArrayList<Cars> cars = databaseHelper.getCars();

            item = new String[cars.size()];
            id = new String[cars.size()];
            for (int i = 0; i < cars.size(); i++) {
                item[i] = "Model: " + cars.get(i).getModel() + "\n\n\n" +
                        "Type: " +cars.get(i).getTypes() + "\n\n\n" +
                        "Yar: " + cars  .get(i).getYear();
                id[i] = "" + cars.get(i).getId();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, item);
            list.setAdapter(adapter);
        }catch(Exception ex){}
    }
}
