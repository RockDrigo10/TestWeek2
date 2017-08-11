package com.example.admin.testweek2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/11/2017.
 */

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder>{
    private static final String TAG = "Adapter";
    List<Cars> cars =  new ArrayList<>();
    Context context;

    public CarListAdapter(List<Cars> carsList) {
        this.cars = carsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvModel,tvType,tvYear;
        Button btnSave;
        public ViewHolder(View itemView) {
            super(itemView);
            tvModel = (TextView) itemView.findViewById(R.id.etModel);
            tvType = (TextView) itemView.findViewById(R.id.etType);
            tvYear = (TextView) itemView.findViewById(R.id.etType);
            btnSave = (Button) itemView.findViewById(R.id.btnSave);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carlist_item,parent, false);
        Log.d(TAG, "onCreateViewHolder: " );
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cars car = cars.get(position);
        holder.tvModel.setText(car.getModel());
        holder.tvType.setText(car.getTypes());
        holder.tvYear.setText(car.getYear());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),CarFragment.class);
                intent.putExtra("animal",car);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }


}
