package id.gifood.carihotel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.List;

import id.gifood.carihotel.R;
import id.gifood.carihotel.model.list.DataHotels;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<DataHotels> dataHotels;
    private List<DataHotels> dataHotelsFiltered;

    public Adapter(Context context, List<DataHotels> dataHotels) {
        this.context = context;
        this.dataHotels = dataHotels;
        this.dataHotelsFiltered = dataHotels;
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

    public DataHotels removeItem(int position){
        final DataHotels DataHotels = dataHotels.remove(position);
        notifyItemRemoved(position);
        return DataHotels;
    }

    public void addItem(int position, DataHotels DataHotels){
        dataHotels.add(position, DataHotels);
    }

    public void moveItem(int fromPosition, int toPosition){
        final DataHotels DataHotels = dataHotels.remove(fromPosition);
        dataHotels.add(toPosition, DataHotels);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(List<DataHotels> DataHotels){
        applyAndAnimateRemovals(DataHotels);
        applyAndAnimateAdditions(DataHotels);
        applyAndAnimateMovedItems(DataHotels);
    }

    private void applyAndAnimateRemovals(List<DataHotels> newModels) {
        for (int i = dataHotels.size() - 1; i >= 0; i--) {
            final DataHotels model = dataHotels.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<DataHotels> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final DataHotels model = newModels.get(i);
            if (!dataHotels.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<DataHotels> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final DataHotels model = newModels.get(toPosition);
            final int fromPosition = dataHotels.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }
}

