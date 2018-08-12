package id.gifood.carihotel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import id.gifood.carihotel.R;
import id.gifood.carihotel.model.list.DataHotels;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<DataHotels> dataHotels;

    public Adapter(Context context, List<DataHotels> dataHotels) {
        this.context = context;
        this.dataHotels = dataHotels;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel,null,false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        final DataHotels dataHotels = this.dataHotels.get(position);
        holder.title.setText(dataHotels.getName());
        holder.address.setText(dataHotels.getAddress());
        Glide.with(context).load(dataHotels.getImages().get(0)).centerCrop().into(holder.photo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Ini hotel " + dataHotels.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataHotels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, address;
        ImageView photo;
        public ViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.list_hotel_photo);
            title   = itemView.findViewById(R.id.list_hotel_title);
            address = itemView.findViewById(R.id.list_hotel_address);
        }
    }
}

