package hcmus.vuphan.moneykeeper.scences;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.GraphViewXML;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
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
    GraphView graphViewXML;

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
        graphViewXML = (GraphView) view.findViewById(R.id.gvGraph);


        Refresh();

        //graphViewXML.getViewport().setXAxisBoundsManual(true);

        return view;
    }

    private String GetCurrentStatus(Date time) {
        int month = time.getMonth();
        int year = time.getYear();
        //List<ChiTieuThang> chiTieuThangs = ChiTieuThang.find(ChiTieuThang.class, "strftime(%m, thoi_gian) = ?", String.valueOf(month) );
        List<ChiTieuThang> chiTieuThangs = ChiTieuThang.listAll(ChiTieuThang.class);

        ChiTieuThang curCTT = null;
        for (ChiTieuThang chiTieuThang :
                chiTieuThangs) {
            if (chiTieuThang.getThoiGian().getMonth() == month) {
                curCTT = chiTieuThang;
                break;
            }
        }

        if (curCTT.getSoTienToiDa() == 0)
            return "";

        List<Giaodich> giaodiches = Giaodich.find(Giaodich.class, "idthang = ?", String.valueOf(curCTT.getId()));
        int soTienDaGiaoDich = 0;
        for (Giaodich giaodich :
                giaodiches) {
            soTienDaGiaoDich -= giaodich.getTongtien();
        }

        int soTienHienTai = curCTT.getSoTienToiDa() - soTienDaGiaoDich;

        String gioiHan = MoneyHelper.MoneyParserWithoutVND(curCTT.getSoTienToiDa());
        String hienTai = MoneyHelper.MoneyParserWithoutVND(soTienHienTai);
        return hienTai + "/" + gioiHan;
    }


    public void Refresh() {
        Calendar calendar = Calendar.getInstance();
        curWallet = MoneyHelper.GetCurWallet();
        tvName.setText(curWallet.getName());
        tvCurMoney.setText(MoneyHelper.MoneyParser(Integer.valueOf(curWallet.getTienhientai())));
        tvSaveMoney.setText(MoneyHelper.MoneyParser(Integer.valueOf(curWallet.getTiendutru())));
        tvBankMoney.setText(MoneyHelper.MoneyParser(Integer.valueOf(curWallet.getTientietkiem())));

        String currentStatus = GetCurrentStatus(calendar.getTime());
        tvStatus.setText(currentStatus);

        ChiTieuThang curCTT = MoneyHelper.GetChiTieuThangByMonth(MoneyHelper.GetCurrentMonth());
        List<Giaodich> giaodiches = MoneyHelper.GetGiaoDichByChiTieuThang(curCTT.getId());

        int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

        for (int i = minDay; i <= maxDay; i++) {
            int sum = 0;
            for (Giaodich gd :
                    giaodiches) {
                if (gd.getThoiGian().getDate() == i)
                    sum++;
            }
            DataPoint dp = new DataPoint(i, sum);
            series.appendData(dp, true, maxDay);
        }
        series.setColor(Color.GREEN);
        series.setThickness(8);
        graphViewXML.addSeries(series);
        graphViewXML.setTitle("Chi tiêu tháng này");


        graphViewXML.getGridLabelRenderer().setNumHorizontalLabels(16); // only 4 because of the space
        graphViewXML.getGridLabelRenderer().setHorizontalAxisTitle("Ngày");
        graphViewXML.getGridLabelRenderer().setVerticalAxisTitle("Số lượng giao dịch");

        // set manual x bounds to have nice steps
        graphViewXML.getViewport().setMinX(minDay);
        graphViewXML.getViewport().setMaxX(maxDay);
    }
}
