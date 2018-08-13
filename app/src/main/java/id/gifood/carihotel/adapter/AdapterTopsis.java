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
import id.gifood.carihotel.model.topsis.TopsisModel;

public class AdapterTopsis extends RecyclerView.Adapter<AdapterTopsis.ViewHolderTopsis> {

    private Context context;
    private List<TopsisModel> topses;

    public AdapterTopsis(Context context, List<TopsisModel> topses) {
        this.context = context;
        this.topses = topses;
    }

    @Override
    public ViewHolderTopsis onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel,null,false);
        return new ViewHolderTopsis(row);
    }

    @Override
    public void onBindViewHolder(ViewHolderTopsis holder, int position) {
        final TopsisModel topsisModel = this.topses.get(position);
        holder.title.setText(topsisModel.getName());
        holder.address.setText(topsisModel.getAddress());
        Glide.with(context).load(topsisModel.getImages().get(0)).centerCrop().into(holder.photo);

/*        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Ini hotel " + topsisModel.getName(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return topses.size();
    }

    public class ViewHolderTopsis extends RecyclerView.ViewHolder {
        TextView title, address;
        ImageView photo;
        public ViewHolderTopsis(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.list_hotel_photo);
            title   = itemView.findViewById(R.id.list_hotel_title);
            address = itemView.findViewById(R.id.list_hotel_address);
        }
    }
}
