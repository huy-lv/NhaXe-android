package com.bich.hp.nhaxe.View.TrangChu.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bich.hp.nhaxe.Adapter.LTGVAdapter;
import com.bich.hp.nhaxe.Model.ObjLichTrinhGiaVe.ObjLTGV;
import com.bich.hp.nhaxe.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.bich.hp.nhaxe.View.TrangChu.MainActivity.SERVER_NAME;

/**
 * Created by hp on 2/14/2017.
 */

public class FragmentLichTrinhGiaVe extends Fragment   {
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mrc;
    private LTGVAdapter mad;
    public static final int CONNECTION_TIMEOUT = 10000;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_lichtrinhgiave, container, false);
        mrc = (RecyclerView) view.findViewById(R.id.recyclerView1);


        new AsyncFetch().execute();
        return view;
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(getContext());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL(SERVER_NAME+"/listgiavelotrinh.php");
               // url = new URL("http://192.168.130.2/webQLNX/dist/listgiavelotrinh.php");

            } catch (MalformedURLException e) {

                e.printStackTrace();
                return e.toString();
            }
            try {


                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
            List<ObjLTGV> data = new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    ObjLTGV obj = new ObjLTGV();
                    obj.DIEMDI= json_data.getString("DIEMDI");
                    obj.DIEMDEN= json_data.getString("DIEMDEN");
                    obj.TENTUYENXE= json_data.getString("TENTUYENXE");

                    obj.GIA= json_data.getInt("GIA");
                    obj.TONGKM= json_data.getString("TONGKM");
                    obj.THOIGIANLOTRINH= json_data.getString("THOIGIANLOTRINH");
                    obj.SOTRAMDUNG= json_data.getInt("SOTRAMDUNG");
                    data.add(obj);
                }
                mad = new LTGVAdapter(getContext(), R.layout.list_item_lichgiave, data);
                mrc.setAdapter(mad);
                mrc.setLayoutManager(new LinearLayoutManager(getContext()));

            } catch (JSONException e) {
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}