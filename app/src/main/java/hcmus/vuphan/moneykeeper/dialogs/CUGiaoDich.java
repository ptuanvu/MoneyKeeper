package hcmus.vuphan.moneykeeper.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hcmus.vuphan.moneykeeper.MoneyHelper;
import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.global;
import hcmus.vuphan.moneykeeper.model.Catalog;
import hcmus.vuphan.moneykeeper.model.ChiTieuThang;
import hcmus.vuphan.moneykeeper.model.Giaodich;

/**
 * Created by monster on 24/06/2016.
 */
public class CUGiaoDich extends DialogFragment {
    private static final String GIAO_DICH_PARAMS = "giao_dich";
    Button btnOK, btnCancel;
    EditText edtTitle, edtDescription, edtThoiGian, edtLocation, edtTotalPrice;
    Spinner spnCatalog, spnCTT;
    ImageButton imbTime, imbLocation;
    CheckBox cbGiaoDichCoDinh;
    List<String> ctts = new ArrayList<String>();
    List<Long> ids = new ArrayList<Long>();



    public Context context;


    public static CUGiaoDich concreateInstance(Giaodich cur, Context context) {
        CUGiaoDich cuGiaoDich = new CUGiaoDich();
        if (cur != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(GIAO_DICH_PARAMS, cur);
            cuGiaoDich.setArguments(bundle);
            cuGiaoDich.context = context;
        }
        return cuGiaoDich;
    }

    public interface CUGiaoDichListener {
        void OnCUGiaoDichFinish();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.them_giao_dich_layout2, container, false);
        btnOK = (Button) view.findViewById(R.id.btnOK);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        edtTitle = (EditText) view.findViewById(R.id.edtTitle);
        edtDescription = (EditText) view.findViewById(R.id.edtDescription);
        edtThoiGian = (EditText) view.findViewById(R.id.edtThoiGian);
        edtLocation = (EditText) view.findViewById(R.id.edtLocation);
        spnCatalog = (Spinner) view.findViewById(R.id.spnCatalog);
        imbTime = (ImageButton) view.findViewById(R.id.imbTime);
        imbLocation = (ImageButton) view.findViewById(R.id.imbCurrentLocation);
        cbGiaoDichCoDinh = (CheckBox) view.findViewById(R.id.cbGiaoDichCoDinh);
        spnCTT = (Spinner) view.findViewById(R.id.spnCTT);
        edtTotalPrice = (EditText) view.findViewById(R.id.edtTotalPrice);

        imbTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                Date now = calendar.getTime();
                SimpleDateFormat df = new SimpleDateFormat("hh:mm aaa MM/dd/yyyy");
                String nowTime = df.format(now);
                edtThoiGian.setText(nowTime);
            }
        });

        imbLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtLocation.setText("Trường Đại học Khoa học tự nhiên, 227 Nguyễn Văn Cừ");
            }
        });

        List<Catalog> catalogs = Catalog.listAll(Catalog.class);
        List<String> catalog_titles = new ArrayList<String>();

        for (int i = 0; i < catalogs.size(); i ++)
            catalog_titles.add(catalogs.get(i).getTitle());

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, catalog_titles);
        spnCatalog.setAdapter(dataAdapter);

        List<ChiTieuThang> chiTieuThangs = ChiTieuThang.listAll(ChiTieuThang.class);


        for (int i = 0; i < chiTieuThangs.size(); i ++)
        {
            String tg = global.dfMonthAndYear.format(chiTieuThangs.get(i).getThoiGian());
            ctts.add(tg);
            ids.add(chiTieuThangs.get(i).getId());
        }

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, ctts);
        spnCTT.setAdapter(dataAdapter1);


        final Bundle bundle = getArguments();
        if (bundle != null) {
            Giaodich giaodich = (Giaodich) bundle.getSerializable(GIAO_DICH_PARAMS);
            edtTitle.setText(giaodich.getTenGiaoDich());
            SimpleDateFormat df = new SimpleDateFormat("hh:mm aaa MM/dd/yyyy");
            String thoiGian = df.format(giaodich.getThoiGian());
            edtThoiGian.setText(thoiGian);
            edtLocation.setText(giaodich.getDiaDiem());
            spnCatalog.setSelection(MoneyHelper.FindOnList(catalogs, giaodich.getID_Catalog()));
            edtDescription.setText(giaodich.getMoTaGiaoDich());
            edtTotalPrice.setText(String.valueOf(giaodich.getTongtien()));
            getDialog().setTitle("Chỉnh sửa chi tiêu tháng");
        } else {
            getDialog().setTitle("Tạo mới chi tiêu tháng");
        }


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (edtTitle.getText().length() > 0 && edtThoiGian.getText().length() > 0 && edtTotalPrice.getText().length() > 0) {
                   Giaodich giaoDich = null;
                   if (bundle != null) {
                       giaoDich = (Giaodich) bundle.getSerializable(GIAO_DICH_PARAMS);
                   } else {
                       giaoDich = new Giaodich();
                   }

                   giaoDich.setTenGiaoDich(edtTitle.getText().toString());
                   giaoDich.setMoTaGiaoDich(edtDescription.getText().toString());

                   Catalog c = Catalog.find(Catalog.class, "title = ?", String.valueOf(spnCatalog.getSelectedItem())).get(0);
                   giaoDich.setID_Catalog(c.getId().toString());

                   giaoDich.setThoiGian(new Date(edtThoiGian.getText().toString()));
                   giaoDich.setDiaDiem(edtLocation.getText().toString());
                   giaoDich.setGhiChu("");
                   giaoDich.setID_HinhAnh("1");
                   giaoDich.setGiaoDichCoDinh(String.valueOf(cbGiaoDichCoDinh.isChecked()));

                   Long id = ids.get(ctts.indexOf(spnCTT.getSelectedItem()));
                   giaoDich.setID_Thang(String.valueOf(id));

                   giaoDich.setTongtien(Integer.valueOf(edtTotalPrice.getText().toString()));

                   giaoDich.save();
                   ((CUGiaoDichListener)getActivity()).OnCUGiaoDichFinish();
                   dismiss();
               } else {
                   Toast.makeText(getActivity(), "Vui lòng nhập các trường cần thiết", Toast.LENGTH_SHORT).show();
               }


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
