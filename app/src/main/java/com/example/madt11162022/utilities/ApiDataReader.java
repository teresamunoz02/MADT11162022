package com.example.madt11162022.utilities;

import static com.example.madt11162022.utilities.Constants.FLOAT_RATES_API_URL;

import com.example.madt11162022.parser.FloatRatesXmlParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiDataReader {
    public static String getValuesFromApi(String apiCode) throws IOException {
        InputStream apiContentStream = null;
        String result = "";
        try{
            apiContentStream = downloadUrlContent(FLOAT_RATES_API_URL);
            result = FloatRatesXmlParser.getCurrencyRatesBaseUsd(apiContentStream);
        }
        finally{
            if(apiContentStream != null){
                apiContentStream.close();
            }
        }
        return result;
    }

    private static InputStream downloadUrlContent(String urlString) throws IOException{
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
}
