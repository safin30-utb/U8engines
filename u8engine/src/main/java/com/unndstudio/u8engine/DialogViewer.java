package com.unndstudio.u8engine;

import android.app.Dialog;
import android.content.Context;
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
    private String setButtonText;
    private int setBackgroundColor;
    private DialogCallback cd;

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

    public void setButtonText(String setButtonText) {
        this.setButtonText = setButtonText;
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

    public String getButtonText() {
        return setButtonText;
    }

    public interface DialogCallback{
        void onClick();
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
        button.setText(getButtonText());

        addBackground.setBackgroundColor(getContext().getResources().getColor(getBackgroundColor()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                cd.onClick();
            }
        });

        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                cd.onClick();
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
