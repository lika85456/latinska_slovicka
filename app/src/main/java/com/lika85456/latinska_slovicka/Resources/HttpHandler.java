package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lika85456 on 24.10.2017.
 */
public class HttpHandler {
    public String response = null;
    public Context context;

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    public String getRequest(String urls,Context ctx)
    {
        this.context = ctx;
        try {
            new GetUrlContentTask(this).execute(urls);
            int totalWaitTime = 5000;
            int time = 0;
            while(response==null || time<totalWaitTime) {
                time+=10;
                Thread.sleep(10);
            }
            Log.i("JE TO TU",response);
            return response;
        }
        catch(Exception e)
        {
            Log.d("Erol",e.toString());
        }
    return null;
    }

    public void ddo(String s)
    {
        //if(s==null)
        this.response = s;
    }




}

class GetUrlContentTask extends AsyncTask<String, Integer, String> {
    private HttpHandler h;
    public GetUrlContentTask(HttpHandler h)
    {
        super();
        this.h = h;
    }

    protected String doInBackground(String... urls) {
        try
        {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-16LE"));
            String content = "", line;
            while ((line = rd.readLine()) != null) {
                content += line + "\n";
            }
            this.h.ddo(content);
            connection.getInputStream().close();
            return content;
        }
        catch(Exception e)
        {
            return e.toString();
        }

    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(String result) {
        //h.ddo(result);
    }
}