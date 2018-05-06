package id.gifood.carihotel.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import id.gifood.carihotel.R;

public class Topsis extends AppCompatActivity {

    // view
    private Toolbar toolbarTopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topsis);

        toolbarTopsis = findViewById(R.id.topsis_toolbar);
        setSupportActionBar(toolbarTopsis);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTopsis.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }
}
