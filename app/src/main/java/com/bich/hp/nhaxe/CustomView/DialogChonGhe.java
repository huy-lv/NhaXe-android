package com.bich.hp.nhaxe.CustomView;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bich.hp.nhaxe.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huylv on 12-Apr-17.
 */

public class DialogChonGhe extends Dialog {
    Context context;
    OnClickOk onClickListener;
    @BindView(R.id.dialog_chonghe_ten)
    EditText dialog_chonghe_ten;
    @BindView(R.id.dialog_chonghe_ok)
    Button dialog_chonghe_ok;
    String ten;

    public DialogChonGhe(@NonNull Context context, OnClickOk onClickListener) {
        super(context);
        init();
    }

    public DialogChonGhe(@NonNull Context context, String ten) {
        super(context);
        this.ten = ten;
        init();
    }

    public void setClick(OnClickOk o) {
        onClickListener = o;
    }

    private void init() {
        setContentView(R.layout.dialog_chonghe);
        ButterKnife.bind(this);

        if (ten != null)
            if (!ten.equals("NULL"))
                dialog_chonghe_ten.setText(ten);
    }

    @OnClick(R.id.dialog_chonghe_ok)
    void okkk() {
        onClickListener.OnClickOk(dialog_chonghe_ten.getText().toString());
    }

    @OnClick(R.id.dialog_chonghe_cancel)
    void cancell() {
        dismiss();
    }

    public DialogChonGhe(@NonNull Context context) {
        super(context);
        init();
    }


    public interface OnClickOk {
        public void OnClickOk(String s);
    }

    public String getTenKhachHang() {
        return dialog_chonghe_ten.getText().toString();
    }

    public DialogChonGhe(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected DialogChonGhe(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}