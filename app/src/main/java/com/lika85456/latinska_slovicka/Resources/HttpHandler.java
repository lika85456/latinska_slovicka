package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lika85456 on 24.10.2017.
 */

/***
 * This class is helper for httprequests
 */
public class HttpHandler {
    public String response = null;
    public Context context;

    /***
     * @param urls
     * @param ctx
     * @return response from server
     */
    public String getRequest(String urls,Context ctx)
    {
        this.context = ctx;
        try {
            new GetUrlContentTask(this).execute(urls);
            int totalWaitTime = 5000;
            int time = 0;
            while (response == null && time < totalWaitTime) {
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

    public void saveImage(String imageUrl, String destinationFile, Context ctx) {
        this.context = ctx;
        try {
            new GetDrawableContentTask(this, destinationFile).execute(imageUrl);
            int totalWaitTime = 5000;
            int time = 0;
            while (response == null && time < totalWaitTime) {
                time += 10;
                Thread.sleep(10);
            }
            Log.i("JE TO TU", response);
        } catch (Exception e) {
            Log.d("Erol", e.toString());
        }
    }

    public void ddo(String s)
    {
        //if(s==null)
        this.response = s;
    }




}

class GetDrawableContentTask extends AsyncTask<String, Integer, String> {
    private HttpHandler h;
    private String filename;

    public GetDrawableContentTask(HttpHandler h, String filename) {
        super();
        this.h = h;
        this.filename = filename;
    }

    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            InputStream is = url.openStream();
            OutputStream os = this.h.context.openFileOutput(this.filename, Context.MODE_PRIVATE);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (Exception e) {
            Log.e("Error,", e.toString());
        }

        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(String result) {
        //h.ddo(result);
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




