package edu.mssu.cis385.simpleasynctask;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(ProgressBar pb, TextView tv) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pb);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 200;

        try {
            Log.d("doInBackground","random number");
            publishProgress(0);
            Thread.sleep(s/4);
            publishProgress(25);
            Thread.sleep(s/4);
            publishProgress(50);
            Thread.sleep(s/4);
            publishProgress(75);
            Thread.sleep(s/4);
            publishProgress(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake at last after sleeping for " + s + " milliseconds!";

    }

    protected void onProgressUpdate(Integer... progress){
        mProgressBar.get().setProgress(progress[0]);
        Log.d("onProgressUpdate","progress");
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

}
