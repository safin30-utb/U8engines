package com.unndstudio.u8engine;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class Callback {
    public interface onJsonArrayManagerListener{
        void JsonArray(JSONArray jsonArray);
        void ErrorListener(Exception e);
        Map<String, String> setHeader();

    }

    /**
     * what is this for
     * it called when the object is well protected
     * like the api is have a key and the key will appear here is
     * the passkey add your api key in it and the setpass is the pass holder called it something
     * like app-application host
     *
     */
    public interface onRunWithJsonArrayPass{
        String setPass();
        String setPassKey();

    }

    public interface onJsonObjectRequest{
        void onCalled(JSONObject jsonObject);
        void onError(Exception error);
        Map<String, String> setHeader();

    }
    public interface onStringRequest{
        void onCalled(String stringRequest);
        void onError(Exception error);

    }

}
