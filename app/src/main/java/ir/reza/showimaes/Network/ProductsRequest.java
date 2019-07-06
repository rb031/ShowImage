package ir.reza.showimaes.Network;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

import ir.reza.showimaes.Model.Image;


public class ProductsRequest extends Request<ArrayList<Image>> {

    private Response.Listener<ArrayList<Image>> mListener;
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private final String mRequestBody;


    public ProductsRequest(String url, JSONObject jsonRequest, Response.Listener<ArrayList<Image>> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, url, errorListener);
        this.mListener = listener;
        this.mRequestBody = jsonRequest.toString();
    }

    @Override
    public byte[] getBody()
    {
        try
        {
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        }
        catch (UnsupportedEncodingException uee)
        {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }

    @Override
    protected Response<ArrayList<Image>> parseNetworkResponse(NetworkResponse response)
    {
        ArrayList<Image> commentItems = new ArrayList<>();
        try
        {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JSONArray array = new JSONArray(json);
            try
            {
                array.getJSONObject(0).getString("state");
                commentItems = null;
            }
            catch (Exception e)
            {
                Gson gson = new Gson();
                Image[] posts = gson.fromJson(array.toString(), Image[].class);
                Collections.addAll(commentItems, posts);
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            commentItems = null;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            commentItems = null;
        }


        return Response.success(commentItems, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(ArrayList<Image> response)
    {
        mListener.onResponse(response);
    }

}
