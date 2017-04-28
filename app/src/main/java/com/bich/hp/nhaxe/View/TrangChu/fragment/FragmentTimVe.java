package com.bich.hp.nhaxe.View.TrangChu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.bich.hp.nhaxe.ConnectInternet.LoTrinhResponse;
import com.bich.hp.nhaxe.ConnectInternet.PhanCongResponse;
import com.bich.hp.nhaxe.ConnectInternet.RetrofitClient;
import com.bich.hp.nhaxe.Model.Lo_Trinh;
import com.bich.hp.nhaxe.R;
import com.bich.hp.nhaxe.Utils;
import com.bich.hp.nhaxe.View.TimVe.TimVeActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp on 2/14/2017.
 */

public class FragmentTimVe extends Fragment {

    @BindView(R.id.timve_diemdi_sp)
    Spinner timve_diemdi_sp;
    @BindView(R.id.timve_diemden_sp)
    Spinner timve_diemden_sp;
    @BindView(R.id.timve_chonngay_sp)
    Spinner timve_chonngay_sp;
    @BindView(R.id.timve_chongio_sp)
    Spinner timve_chongio_sp;

    ArrayList<String> cacdiemdi;
    Lo_Trinh tuyenXeHienTai;
    private ArrayAdapter<String> adapter_diemdi;
    private ArrayList<String> cacdiemden;
    private ArrayAdapter<String> adapter_diemden;
    private ArrayList<Lo_Trinh> lotrinh;
    private ArrayList<String> cacngay;
    private ArrayAdapter<String> adapter_cacngay;
    private ArrayList<PhanCongResponse.PhanCong> phancong;
    private ArrayList<String> cacgio;
    private ArrayAdapter<String> adapter_cacgio;
    private int currentDiemDi;
    private String currentMaTuyenXe;
    private int currentDiemDen;
    private String ngayHienTai;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_timve, container, false);
        ButterKnife.bind(this, view);

        cacdiemdi = new ArrayList<>();
        cacdiemden = new ArrayList<>();
        cacngay = new ArrayList<>();
        cacgio = new ArrayList<>();
        //spinner


        adapter_diemdi = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, cacdiemdi);
        timve_diemdi_sp.setAdapter(adapter_diemdi);
        adapter_diemden = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, cacdiemden);
        timve_diemden_sp.setAdapter(adapter_diemden);
        adapter_cacngay = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, cacngay);
        timve_chonngay_sp.setAdapter(adapter_cacngay);
        adapter_cacgio = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, cacgio);
        timve_chongio_sp.setAdapter(adapter_cacgio);

        RetrofitClient.getAPIInterface().getPhanCong().enqueue(new Callback<PhanCongResponse>() {
            @Override
            public void onResponse(Call<PhanCongResponse> call, Response<PhanCongResponse> response) {
                if(response.isSuccessful()) {
                    phancong = response.body().getPhanCongs();
                    RetrofitClient.getAPIInterface().getLoTrinh().enqueue(new Callback<LoTrinhResponse>() {
                        @Override
                        public void onResponse(Call<LoTrinhResponse> call, Response<LoTrinhResponse> response) {
                            lotrinh = response.body().getLotrinh();
                            cacdiemdi.clear();
                            cacdiemdi.add("Chon diem di");
                            for (Lo_Trinh l : response.body().getLotrinh()) {
                                if (!cacdiemdi.contains(l.getDiemdi()))
                                    cacdiemdi.add(l.getDiemdi());
                            }
                            adapter_diemdi.notifyDataSetChanged();

                        }

                        @Override
                        public void onFailure(Call<LoTrinhResponse> call, Throwable t) {
                            Utils.showInfoDialog(getContext(), t.getMessage());
                        }
                    });
                }else{
                    Utils.showInfoDialog(getActivity(),response.message());
                }
            }

            @Override
            public void onFailure(Call<PhanCongResponse> call, Throwable t) {
                Utils.showInfoDialog(getContext(),t.getMessage());
            }
        });



        timve_diemdi_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                cacdiemden.clear();
                cacdiemden.add("Chon diem den");
                currentDiemDi = i;
                String diemdi = cacdiemdi.get(i);
                //generate diem den
                for(Lo_Trinh ll : lotrinh){
                    if(ll.getDiemdi().equals(diemdi)){
                        if(!cacdiemden.contains(ll.getDiemden())){
                            cacdiemden.add(ll.getDiemden());
                        }
                    }
                }
                adapter_diemden.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        timve_diemden_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentDiemDen = i;
                currentMaTuyenXe = getMaTuyenXeFromDiemDi(currentDiemDi,currentDiemDen);
                Log.e("Cxz","tuyen xe hien tai: "+currentMaTuyenXe);

                if(currentMaTuyenXe!=null){
                    cacngay.clear();
                    cacngay.add("Chon ngay");
                    for(PhanCongResponse.PhanCong pc : phancong){
                        if(pc.getMatuyenxe().equals(currentMaTuyenXe)){
                            cacngay.add(pc.getNgaylamviec());
                        }
                    }
                    adapter_cacngay.notifyDataSetChanged();
                    timve_chonngay_sp.setSelection(0);
                }else{
                    Log.e("cxz","ma tuyen xe null" + currentDiemDi);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        timve_chonngay_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ngayHienTai = cacngay.get(i);
                cacgio.clear();
                cacgio.add("Chon gio");
                adapter_cacgio.notifyDataSetChanged();
                for(Lo_Trinh ll : lotrinh){
                    if(ll.getMatuyenxe().equals(currentMaTuyenXe)){
                        if(!cacgio.contains(ll.getThoigianbatdau())){
                            cacgio.add(ll.getThoigianbatdau());
                        }
                    }
                }
                adapter_cacgio.notifyDataSetChanged();
                timve_chongio_sp.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        timve_chongio_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tuyenXeHienTai = getTuyenxe(currentMaTuyenXe,cacgio.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private Lo_Trinh getTuyenxe(String currentMaTuyenXe, String s) {
        for(Lo_Trinh ll : lotrinh){
            if(currentMaTuyenXe.equals(ll.getMatuyenxe()) && s.equals(ll.getThoigianbatdau())){
                return ll;
            }
        }
        return null;
    }

    @OnClick(R.id.timve_tieptuc_bt)
    void tiectuc(){
        if(tuyenXeHienTai!=null && ngayHienTai!=null) {
            Gson gson = new Gson();
            Intent i = new Intent(getActivity(), TimVeActivity.class);
            Utils.loTrinhDaChon = tuyenXeHienTai;
            Utils.ngayDaChon = ngayHienTai;
            startActivity(i);
        }else{
            Toast.makeText(getActivity(),"Ban chua chon tuyen xe nao!",Toast.LENGTH_SHORT).show();
        }
    }

    private String getMaTuyenXeFromDiemDi(int currentDiemDi,int currentDiemDen) {
        for(Lo_Trinh lt : lotrinh){
            if(lt.getDiemdi().equals(cacdiemdi.get(currentDiemDi))  && lt.getDiemden().equals(cacdiemden.get(currentDiemDen))){
                return lt.getMatuyenxe();
            }
        }
        return null;
    }


}
