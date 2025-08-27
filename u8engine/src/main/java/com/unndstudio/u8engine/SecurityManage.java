package com.unndstudio.u8engine;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecurityManage {
    private String secureData;
    public void setSecureData(String secureData) {
        this.secureData = secureData;
    }
    public String getSecureData() {
        return secureData;
    }

    public SecurityManage() {
    }

    public String secureDataInstanceCallback() throws Exception{

        String title = getSecureData();
        byte[] setTitle = title.getBytes("UTF-8");

        String pass = "G7v!q9Rk#2Lp8zXw";
        byte[] enPass = pass.getBytes("UTF-8");

        SecretKeySpec secretKey = new SecretKeySpec(enPass,"AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] encoded = cipher.doFinal(setTitle);

        String encodedData = Base64.encodeToString(encoded,Base64.DEFAULT);
        return encodedData;

    }

    public String setImageByteFormat(){

        ImageView ima = null;

        BitmapDrawable drawable = (BitmapDrawable) ima.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);


        return null;
    }

}
