package com.unndstudio.u8engine;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;

public class ServerManager extends Callback {

    protected Context context;
    private RequestQueue requestQueue;

    public ServerManager(Context context) {
        this.context = context;

    }

    public void onJsonArrayListener(String url,onJsonArrayManagerListener jb,onRunWithJsonArrayPass pass){

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            jsonObject.put(pass.setPass(),pass.setPassKey());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        jsonArray.put(jsonObject);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url,jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                jb.JsonArray(jsonArray);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                jb.ErrorListener(volleyError);
            }
        });

        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);

    }

    public void onJsonArrayWithHeaderListener(String url,onJsonArrayManagerListener jb){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                jb.JsonArray(jsonArray);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                jb.ErrorListener(volleyError);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return jb.setHeader();
            }
        };

        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);

    }

    public void onJsonObjectRequest(String url,int method,onJsonObjectRequest jb){

        JsonObjectRequest objectRequest = new JsonObjectRequest(method, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                jb.onCalled(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                jb.onError(error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return jb.setHeader();
            }
        };

        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);

    }
    public void onStringRequest(String url, int method,onStringRequest s){

        StringRequest stringRequest = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                s.onCalled(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                s.onError(error);
            }
        });

        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}
