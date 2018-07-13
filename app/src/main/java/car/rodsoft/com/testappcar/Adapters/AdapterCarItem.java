package car.rodsoft.com.testappcar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import car.rodsoft.com.testappcar.Model.Car;
import car.rodsoft.com.testappcar.R;

public class AdapterCarItem extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context context;
    List<Car> cars;

    public AdapterCarItem(Context context, List<Car> cars) {
        this.context = context;
        this.cars = cars;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View view = inflater.inflate(R.layout.cars_item_adapter,null);

        final TextView carName = view.findViewById(R.id.carName);
        final TextView carPrice = view.findViewById(R.id.carPrice);
        final ImageView carImg = view.findViewById(R.id.carImg);

        carName.setText(cars.get(position).getName());
        carPrice.setText("US$" + cars.get(position).getPrice().toString());
        carImg.setImageResource(cars.get(position).getImg());

        return view;
    }
}
