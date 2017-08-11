package com.example.admin.testweek2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class CarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String TAG = "Carsdetail";
    private EditText etModel, etType, etYear;
    private Button btnSave;
    String id;
    DataBase databaseHelper;

    private OnFragmentInteractionListener mListener;

    public CarFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CarFragment newInstance(String param1, String param2) {
        CarFragment fragment = new CarFragment();
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
        return inflater.inflate(R.layout.fragment_car, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etModel = (EditText) view.findViewById(R.id.etModel);
        etType = (EditText) view.findViewById(R.id.etType);
        etYear = (EditText) view.findViewById(R.id.etType);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        databaseHelper = new DataBase(getActivity());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        id = mParam1;
        if (id != "-1") {
            try {
                ArrayList<Cars> listCars = databaseHelper.getCars();
                if (listCars.size() > 0) {
                    etModel.setText(listCars.get(0).getModel());
                    etType.setText(listCars.get(0).getTypes());
                    etYear.setText(listCars.get(0).getYear());
                    Log.d(TAG, "onCreate: " + id + listCars.get(0).getModel());
                }
            } catch (Exception ex) {
            }
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etModel.getText().toString().equals("") || etType.getText().toString().equals("") || etYear.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d(TAG, "onClick: " +etType.getText().toString());
                    Cars cars = new Cars(-1, etModel.getText().toString(), etType.getText().toString(),Integer.parseInt(etYear.getText().toString()));
                    Log.d(TAG, "onClick: " + cars.getModel() + " " + cars.getTypes()+ " " + cars.getYear() +
                            " " + cars.getId() );
                    databaseHelper.saveNewCars(cars);
                        Toast.makeText(getActivity(), "Element created successfully", Toast.LENGTH_SHORT).show();
                    }
                    mListener.onFragmentInteraction(-1);

            }
        });
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int integer);
    }
}
