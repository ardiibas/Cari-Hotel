package id.gifood.carihotel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import id.gifood.carihotel.R;
import id.gifood.carihotel.model.Facility;

/**
 * id.gifood.carihotel.adapter
 * Created by ROFIE SAGARA on 8/2/2018.
 * Cari-Hotel
 */
public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.Holder>{
    private List<Facility> mData = new ArrayList<>();
    private Listener mListener;

    public FacilityAdapter(Listener listener){
        super();
        mListener = listener;
    }

    public void add(Facility facility){
        mData.add(facility);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_fasilitas,null,false);
        return new Holder(v, mListener);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        private CheckBox mCheckBoxMain;
        private Listener mListener;

        Holder(View itemView, Listener listener) {
            super(itemView);

            mListener = listener;
            mCheckBoxMain = itemView.findViewById(R.id.check_box_list_fasilitas);
        }

        void bind(final Facility facility){
            mCheckBoxMain.setText(facility.getName());
            mCheckBoxMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mListener.onClick(facility, ((CheckBox)view).isChecked());
                }
            });
        }
    }

    public interface Listener{
        void onClick(Facility facility, Boolean isChecked);
    }
}
