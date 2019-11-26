package com.aman.aman;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void>
{
    String data="";
    Interface interfaces;
    public fetchData(Interface interfaces)
    {
        this.interfaces=interfaces;
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        try {
            URL url=new URL("https://pascolan-config.s3.us-east-2.amazonaws.com/android/v1/prod/Category/hi/category.json");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while (line !=null)
            {
                line=bufferedReader.readLine();
                data=data + line;
            }

            JSONArray jsonArray=new JSONArray(data);
            for (int i=0;i<jsonArray.length();i++)
            {
                String a="";
                String p="";
                JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                if (jsonObject.has("a"))
                {
                    a=jsonObject.getString("a");
                    Log.d("DATA A ", a);
                }
                if (jsonObject.has("p"))
                {
                    p=jsonObject.getString("p");
                    Log.d("DATA P ", p);
                }
                MainActivity.listData.add(new Aman(a,p));
//                singlePass="P"+jsonObject.get("p")+"\n"+
//                        "A"+jsonObject.get("a")+"\n";

//                Log.d("DATA ", singlePass);
//                parsedData=parsedData + singlePass;
            }
            interfaces.onDataFetch();


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
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);

    }
}
