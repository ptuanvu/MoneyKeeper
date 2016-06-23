package hcmus.vuphan.moneykeeper.scences;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hcmus.vuphan.moneykeeper.MoneyHelper;
import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.ChiTieuThang;
import hcmus.vuphan.moneykeeper.model.Giaodich;
import hcmus.vuphan.moneykeeper.model.Wallet;

/**
 * Created by monster on 23/06/2016.
 */
public class TinhTrangHienTai extends Fragment {
    Context context;
    TextView tvName, tvCurMoney, tvSaveMoney, tvBankMoney, tvStatus;
    Wallet curWallet;

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
        tvSaveMoney = (TextView) view.findViewById(R.id.tvSaveMoney);
        tvBankMoney = (TextView) view.findViewById(R.id.tvBankMoney);
        tvStatus = (TextView) view.findViewById(R.id.tvStatus);

        curWallet = MoneyHelper.GetCurWallet();
        tvCurMoney.setText(MoneyHelper.MoneyParser(Integer.valueOf(curWallet.getTienhientai())));
        tvSaveMoney.setText(MoneyHelper.MoneyParser(Integer.valueOf(curWallet.getTiendutru())));
        tvBankMoney.setText(MoneyHelper.MoneyParser(Integer.valueOf(curWallet.getTientietkiem())));

        Calendar calendar = Calendar.getInstance();

        String currentStatus = GetCurrentStatus(calendar.getTime());
        tvStatus.setText(currentStatus);

        return view;
    }

    private String GetCurrentStatus(Date time) {
        int month = time.getMonth();
        //List<ChiTieuThang> chiTieuThangs = ChiTieuThang.find(ChiTieuThang.class, "strftime(%m, thoi_gian) = ?", String.valueOf(month) );
        List<ChiTieuThang> chiTieuThangs = ChiTieuThang.listAll(ChiTieuThang.class);

        ChiTieuThang curCTT = null;
        for (ChiTieuThang chiTieuThang :
                chiTieuThangs) {
            if (chiTieuThang.getThoiGian().getMonth() == month)
            {
                curCTT = chiTieuThang;
                break;
            }
        }

        List<Giaodich> giaodiches = Giaodich.find(Giaodich.class, "idthang = ?", String.valueOf(curCTT.getId()));
        int soTienDaGiaoDich = 0;
        for (Giaodich giaodich :
                giaodiches) {
            soTienDaGiaoDich += 20000;
        }
        int soTienHienTai = curCTT.getSoTienToiDa() - soTienDaGiaoDich;

        String gioiHan = MoneyHelper.MoneyParserWithoutVND(curCTT.getSoTienToiDa());
        String hienTai = MoneyHelper.MoneyParserWithoutVND(soTienHienTai);
        return hienTai + "/" + gioiHan;
    }


}
