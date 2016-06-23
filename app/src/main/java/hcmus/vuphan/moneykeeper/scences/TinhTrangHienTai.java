package hcmus.vuphan.moneykeeper.scences;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hcmus.vuphan.moneykeeper.MoneyHelper;
import hcmus.vuphan.moneykeeper.R;

/**
 * Created by monster on 23/06/2016.
 */
public class TinhTrangHienTai extends Fragment {
    Context context;
    TextView tvName, tvCurMoney;

    public void setContext(Context context) {
        this.context = context;
    }

    public static TinhTrangHienTai createInstance(Context context) {
        TinhTrangHienTai tinhTrangHienTai = new TinhTrangHienTai();
        tinhTrangHienTai.setContext(context);
        return tinhTrangHienTai;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tinh_trang_hien_tai, container, false);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvCurMoney = (TextView) view.findViewById(R.id.tvCurMoney);

        tvCurMoney.setText(MoneyHelper.MoneyParser(500000));

        return view;
    }
}
