package com.bich.hp.nhaxe.View.TrangChu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bich.hp.nhaxe.R;


public class VeFunBus extends Fragment {
    TextView tvGT,tvMT,tvSM;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_datvengayle,container,false);
        tvGT=(TextView)view.findViewById(R.id.tvGThieu);
        tvGT.setText("Công ty FunBus chuyên kinh doanh lĩnh vực vận tải hành khách,vận chuyển " +
                "hàng hóa và dịch vụ taxi,FunBus thành lập vào ngày 1-2-1993 đến nay có hơn 10 chi nhánh.Công " +
                "ty hiện có hơn 500 nhân viên,đội ngũ nhân viên có nhiều " +
                " kinh nghiệm trong lĩnh vực,với phương châm Chất Lượng Tạo Thành Công," +
                "thái độ làm việc nghiêm túc đã tạo nên FunBus thành công");
        tvMT=(TextView)view.findViewById(R.id.tvMucTieu);
        tvMT.setText("FunBus phấn đấu trở thành Công ty cung cấp dịch vụ hàng đầu,giảm chi phí," +
                "tạo nhiều dịch vu hơn để cung cấp cho khách hàng");
        tvSM=(TextView)view.findViewById(R.id.tvTamNHin);
        tvSM.setText("Đảm bảo lợi ích cao nhất cho khách hàng,luôn đặt khách hàng lên hàng đầu," +
                "với Sứ Mệnh tạo chất lượng tốt nhất để dất nước hội nhập và phát triển.");

        return view;
    }
}
