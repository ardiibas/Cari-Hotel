package id.gifood.carihotel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import javax.naming.Context;

import id.gifood.carihotel.R;
import id.gifood.carihotel.model.HotelModel;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private ArrayList<HotelModel> hotelModels;

    public Adapter(Context context){
        this.context = context;
        hotelModels = new ArrayList<>();
    }


    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel,null,false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        HotelModel hotelModel = hotelModels.get(position);
        holder.title.setText(hotelModel.getName());
        holder.address.setText(hotelModel.getAddress());
    }

    @Override
    public int getItemCount() {
        return hotelModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView address;
        public ViewHolder(View itemView) {
            super(itemView);
            title   = itemView.findViewById(R.id.list_hotel_title);
            address = itemView.findViewById(R.id.list_hotel_address);
        }
    }
}

