package com.example.lavanya.imagedownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.imageView);

    }

    public void downloadimage(View view){
        Imagedownloader imagedownloader=new Imagedownloader();
        try {
            Bitmap myimage=imagedownloader.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
            imageView.setImageBitmap(myimage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class Imagedownloader extends AsyncTask<String,Void,Bitmap>{

        URL url;
        HttpURLConnection urlConnection;
        Bitmap bitmap;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                url=new URL(strings[0]);
                urlConnection= (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream= urlConnection.getInputStream();
              bitmap= BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}
