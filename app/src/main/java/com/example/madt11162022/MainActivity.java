package com.example.madt11162022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madt11162022.utilities.AsyncDataLoader;
import com.example.madt11162022.utilities.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvItems;
    private TextView tvStatus;
    private ArrayAdapter listAdapter;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvItems = findViewById(R.id.lv_Items);
        this.tvStatus = findViewById(R.id.tv_status);
        this.btn = findViewById(R.id.btnGetData);

        this.listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
        this.lvItems.setAdapter(this.listAdapter);
    }

    public void onBtnGetDataClick(View view){
        this.tvStatus.setText("Loading data...");
        getDataByAsyncTask();
        Toast.makeText(this, "Using AsyncTask for FloatRates", Toast.LENGTH_LONG).show();
    }

    public void getDataByAsyncTask(){
        new AsyncDataLoader(){
            @Override
            public void onPostExecute(String result){
                tvStatus.setText("Data Loaded: " + result);
            }
        }.execute(Constants.FLOAT_RATES_API_URL);
    }
}