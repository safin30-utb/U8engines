package com.unndstudio.u8engine;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import java.util.Random;

public class HorizontalProgress {
    private int currentPro;
    private Context context;
    private ProgressBar intprogress;
    public void setIntprogress(ProgressBar intprogress) {
        this.intprogress = intprogress;
    }

    public ProgressBar getIntprogress() {
        return intprogress;
    }

    public HorizontalProgress(Context context) {
        this.context = context;
    }
    @NonNull
    public void onCreate(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Random ran = new Random();
                int ra = ran.nextInt(100 - 4) + 4;

                currentPro += ra;
                getIntprogress().setProgress(currentPro);
                getIntprogress().setMax(100);

                if (getIntprogress().getProgress() == 100){
                    getIntprogress().setVisibility(View.GONE);
                }

                handler.postDelayed(this,1000);
            }
        });
    }
}
