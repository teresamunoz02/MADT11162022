package com.example.madt11162022.utilities;

import android.os.AsyncTask;

import java.io.IOException;

public class AsyncDataLoader extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... params){
        try{
            return ApiDataReader.getValuesFromApi(params[0]);
        }catch (IOException ex){
            return String.format("ERROR => %s", ex.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){super.onPostExecute(result);}
}
