package id.gifood.carihotel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.gifood.carihotel.R;

/**
 * Created by ibas on 21/03/2018.
 */

public class FragmentList extends Fragment{

    private OnFragmentInteractionListener listener;

    public static FragmentList newInstance(){
        return new FragmentList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        return view;
    }

    public interface OnFragmentInteractionListener {
    }
}
