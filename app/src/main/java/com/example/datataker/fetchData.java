package com.example.datataker;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.xml.namespace.QName;

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data="";
    String datParsed ="";
    String singleParsed ="";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url= new URL("https://api.myjson.com/bins/18mduk");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONArray JA = new JSONArray(data);
            for(int i=0; i <JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed = "Name :" + JO.get("name") + "\n"+
                        "school :" + JO.get("school") + "\n"+
                        "State :" + JO.get("state") + "\n";
                 datParsed = datParsed + singleParsed +"\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setText(this.datParsed);
    }
}
