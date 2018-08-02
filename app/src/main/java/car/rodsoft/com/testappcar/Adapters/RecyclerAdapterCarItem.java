package car.rodsoft.com.testappcar.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import car.rodsoft.com.testappcar.Model.Car;
import car.rodsoft.com.testappcar.R;

public class RecyclerAdapterCarItem extends RecyclerView.Adapter<RecyclerAdapterCarItem.ViewHolder> {

    private static final String TAG = "RecyclerAdapterCarItem";

    private List<Car> cars = new ArrayList<>();
    private Context mContext;


    public RecyclerAdapterCarItem( Context mContext,List<Car> cars) {
        this.cars = cars;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cars_item_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(cars.get(position).getImg())
                .into(holder.carImg);

        holder.carName.setText(cars.get(position).getName());
        holder.carPrice.setText("US$" + cars.get(position).getPrice().toString());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on:" + cars.get(position).getName());
                Toast.makeText(mContext, cars.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView carName;
        TextView carPrice;
        ImageView carImg;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            carName = itemView.findViewById(R.id.carName);
            carPrice = itemView.findViewById(R.id.carPrice);
            carImg = itemView.findViewById(R.id.carImg);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
