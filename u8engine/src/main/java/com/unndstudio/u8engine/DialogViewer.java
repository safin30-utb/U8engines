package com.unndstudio.u8engine;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;

public class DialogViewer extends Dialog {

    private String title;
    private int setImageResource;
    private String setImageFromUrl;
    private String setButtonTwo;
    private String setButtonOne;
    private int setBackgroundColor;
    private DialogCallback cd;
    private ButtonCustomizedCallback dc;

    public void setButtonOne(String setButtonOne) {
        this.setButtonOne = setButtonOne;
    }

    public String getButtonOne() {
        return setButtonOne;
    }

    public void setButtonCustomizedListener(ButtonCustomizedCallback bd) {
        this.dc = bd;
    }

    public void setOnClickListener(DialogCallback cd) {
        this.cd = cd;
    }

    public void setBackgroundColor(int setBackgroundColor) {
        this.setBackgroundColor = setBackgroundColor;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setImageResource(int setImageResource) {
        this.setImageResource = setImageResource;
    }

    public void setImageFromUrl(String setImageFromUrl) {
        this.setImageFromUrl = setImageFromUrl;
    }

    public void setButtonTwo(String setButtonTwo) {
        this.setButtonTwo = setButtonTwo;
    }

    public DialogViewer(@NonNull Context context) {
        super(context);
    }

    public int getBackgroundColor() {
        return setBackgroundColor;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResource() {
        return setImageResource;
    }

    public String getImageFromUrl() {
        return setImageFromUrl;
    }

    public String getButtonTwo() {
        return setButtonTwo;
    }

    public interface DialogCallback{
        void onClick();
    }

    public interface ButtonCustomizedCallback{
        void onButtonOne(AppCompatButton button);
        void onButtonTwo(AppCompatButton button);

    }

    public void onDialogView(){

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialogviewer);
        setCancelable(false);
        show();

        ImageView image = findViewById(R.id.image);
        TextView title = findViewById(R.id.title);
        AppCompatButton button = findViewById(R.id.cancel);
        AppCompatButton progress = findViewById(R.id.progress);
        LinearLayout addBackground = findViewById(R.id.addBackground);

        title.setText(getTitle());
        button.setText(getButtonTwo());
        progress.setText(getButtonOne());

        dc.onButtonOne(progress);
        dc.onButtonTwo(button);

        addBackground.setBackgroundColor(getContext().getResources().getColor(getBackgroundColor()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cd.onClick();
                } catch (Exception e) {
                    Log.d("TAGS",e.toString());
                }
            }
        });

        if (setImageFromUrl != null){
            Glide.with(getContext())
                    .load(getImageFromUrl())
                    .into(image);
        }else {
            image.setImageResource(getImageResource());
        }
    }


}
