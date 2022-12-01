package com.example.madt11162022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
    private Switch swAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvItems = findViewById(R.id.lv_Items);
        this.tvStatus = findViewById(R.id.tv_status);
        this.swAsyncTask = findViewById(R.id.asyncTask);

        this.listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
        this.lvItems.setAdapter(this.listAdapter);
    }

    public void onBtnGetData(View view){
        this.tvStatus.setText("Loading data...");
        if(this.swAsyncTask.isChecked()){
            Toast.makeText(this, "Please switch the button", Toast.LENGTH_LONG).show();
        }else{
            getDataByAsyncTask();
            Toast.makeText(this, "Using AsyncTask for FloatRates", Toast.LENGTH_LONG).show();

        }
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