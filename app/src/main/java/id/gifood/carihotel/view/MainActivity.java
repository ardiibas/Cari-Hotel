package id.gifood.carihotel.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
    private Toolbar toolbarMain;
    private BottomNavigationView bottomNavigationMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarMain = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbarMain);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, FragmentMaps.newInstance());
        fragmentTransaction.commit();

        bottomNavigationMenu = findViewById(R.id.main_bottom_menu);
        bottomNavigationMenu.setOnNavigationItemSelectedListener(this);
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_about:
                startActivity(new Intent(this, About.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

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
