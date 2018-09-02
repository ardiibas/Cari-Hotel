package id.gifood.carihotel.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import id.gifood.carihotel.R;
import id.gifood.carihotel.fragment.FragmenHistory;
import id.gifood.carihotel.fragment.FragmentList;
import id.gifood.carihotel.fragment.FragmentMaps;

public class MainActivity extends AppCompatActivity implements
        FragmentMaps.OnFragmentInteractionListener,
        FragmentList.OnFragmentInteractionListener,
        FragmenHistory.OnFragmentInteractionListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    // view
    private BottomNavigationView bottomNavigationMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, FragmentMaps.newInstance());
        fragmentTransaction.commit();

        bottomNavigationMenu = findViewById(R.id.main_bottom_menu);
        bottomNavigationMenu.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.menu_maps :
                fragment = FragmentMaps.newInstance();
                break;
            case R.id.menu_topsis :
                fragment = FragmentList.newInstance();
                break;
            case R.id.menu_favorite :
                fragment = FragmenHistory.newInstance();
                break;
        }

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_frame_layout, fragment);
            fragmentTransaction.commit();
        }

        return true;
    }
}
