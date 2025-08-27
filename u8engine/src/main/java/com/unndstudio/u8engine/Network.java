package com.unndstudio.u8engine;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network {
    private Context context;

    public Network(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public interface Callback{
        void onSuccess(boolean network);

    }

    public void getNetworkView(Callback cd){

        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null){
            cd.onSuccess(true);
        }else {
            cd.onSuccess(false);
        }

    }



}
