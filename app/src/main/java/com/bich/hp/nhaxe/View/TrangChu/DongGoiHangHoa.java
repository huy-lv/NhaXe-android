package com.bich.hp.nhaxe.View.TrangChu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bich.hp.nhaxe.R;

public class DongGoiHangHoa extends AppCompatActivity {
TextView tv1,tv2,tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_goi_hang_hoa);

        tv1=(TextView)findViewById(R.id.tvChuThich);
        tv1.setText("Vật phẩm làm bằng thuỷ tinh hoặc những chất dễ vỡ phải đựng  vào hộp làm bằng kim loại, hoặc gỗ, chèn bằng giấy vụn, hoặc bất kỳ thứ gì khác có tác dụng bảo vệ, tránh gây cọ sát và va chạm trong quá trình vận chuyển.");

        tv2=(TextView)findViewById(R.id.tvTCChung);
        tv2.setText("- Bưu gửi phải được gói bọc chắc chắn, bao bì phải phù hợp với hình thức và tính chất của vật phẩm bên trong, phù hợp với điều kiện vận chuyển, đảm bảo không gây nguy hiểm cho nhân viên khi thực hiện nhiệm vụ, không làm ảnh hưởng đến các bưu gửi khác hoặc các trang thiết bị.");
        tv3=(TextView)findViewById(R.id.tvgoiboc);
        tv3.setText("- Vật phẩm làm bằng thuỷ tinh hoặc những chất dễ vỡ phải đựng vào hộp làm bằng kim loại, " +
                " hoặc gỗ, chèn bằng giấy vụn, hoặc bất kỳ thứ gì khác có tác dụng bảo vệ, tránh gây cọ sát và va chạm trong quá trình vận chuyển.\n \n - Chất lỏng phải đựng trong bình hoàn toàn kín, chất liệu bình phải đảm bảo an toàn, gói bọc trong nhiều lớp vật liệu bền chắc, có khả năng thấm hút để chất lỏng bên trong không chảy ra." +
                "\n \n - Các chất bột khô chỉ được chấp nhận nếu đựng vào hộp, túi gói kín, vật liệu bền, dai, chắc.\n\n-  Đối với hàng điện tử: Máy tính xách tay, máy in, điện thoại, màn hình … Sử dụng chất liệu đệm là xốp, bọt mềm gói bọc đựng trong thùng gỗ tránh gây cọ sát và va chạm trong quá trình vận chuyển.");
    }
}
