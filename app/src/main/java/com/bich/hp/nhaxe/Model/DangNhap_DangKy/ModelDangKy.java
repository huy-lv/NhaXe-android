package com.bich.hp.nhaxe.Model.DangNhap_DangKy;

import android.util.Log;

import com.bich.hp.nhaxe.ConnectInternet.DownloadJSON;
import com.bich.hp.nhaxe.Model.ObjectClass.KhachHang;
import com.bich.hp.nhaxe.View.TrangChu.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;



public class ModelDangKy {
    public Boolean DangKyThanhVien(KhachHang khachHang){
        String duongdan = MainActivity.SERVER_NAME + "/nhaxe.php";
        boolean kiemtra = false;
        List<HashMap<String,String>> attr = new ArrayList<>();
        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","DangKyThanhVien");

        HashMap<String,String> hsSDT = new HashMap<>();
        hsSDT.put("sdt",khachHang.getSoDT());

        HashMap<String,String> hsmapt = new HashMap<>();
        hsSDT.put("maphuongthuc", String.valueOf(khachHang.getMaPhuongThuc()));

        HashMap<String,String> hsEmail = new HashMap<>();
        hsEmail.put("email", khachHang.getEmail());



        HashMap<String,String> hsMatKhauKH = new HashMap<>();
        hsMatKhauKH.put("matkhaukh",khachHang.getMatKhauKH());

        HashMap<String,String> hsTenkhdv = new HashMap<>();
        hsTenkhdv.put("tendn", khachHang.getTenDN());

        attr.add(hsHam);
        attr.add(hsSDT);
        attr.add(hsEmail);
        attr.add(hsMatKhauKH);

        attr.add(hsTenkhdv);
         attr.add(hsmapt);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attr);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();


            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            Log.d("kiemtra",ketqua);
            kiemtra = ketqua.equals("true");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }
}