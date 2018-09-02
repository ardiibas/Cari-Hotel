package id.gifood.carihotel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.gifood.carihotel.R;

/**
 * Created by ibas on 21/03/2018.
 */

public class FragmenHistory extends Fragment{
    private OnFragmentInteractionListener listener;

    private Toolbar toolbar;


    public static FragmenHistory newInstance(){
        return new FragmenHistory();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        toolbar = view.findViewById(R.id.fragment_history_toolbar);
        toolbar.setTitle(R.string.app_name);

        return view;
    }

    public interface OnFragmentInteractionListener {
    }
}
