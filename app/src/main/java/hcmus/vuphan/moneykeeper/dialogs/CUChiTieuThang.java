package hcmus.vuphan.moneykeeper.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.ChiTieuThang;

/**
 * Created by monster on 23/06/2016.
 */
public class CUChiTieuThang extends DialogFragment
{
    private static final String CHI_TIEU_THANG_AGRS = "chi_tieu_thang";
    Button btnOK, btnCancel;
    EditText edtChiTieuToiDa, edtThang;


    public static CUChiTieuThang concreateInstance(ChiTieuThang cur) {
        CUChiTieuThang cuChiTieuThang = new CUChiTieuThang();
        if (cur != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(CHI_TIEU_THANG_AGRS, cur);
            cuChiTieuThang.setArguments(bundle);
        }
        return cuChiTieuThang;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cu_chi_tieu_thang, container, false);
        btnOK = (Button) view.findViewById(R.id.btnOK);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        edtThang = (EditText) view.findViewById(R.id.edtThang);
        edtChiTieuToiDa = (EditText) view.findViewById(R.id.edtChiTieuToiDa);

        final Bundle bundle = getArguments();
        if (bundle != null) {
            ChiTieuThang chiTieuThang = (ChiTieuThang) bundle.getSerializable(CHI_TIEU_THANG_AGRS);
            edtThang.setText(chiTieuThang.getThoiGian().toString());
            edtChiTieuToiDa.setText(chiTieuThang.getSoTienToiDa());

        }

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChiTieuThang chiTieuThang = null;
                if (bundle != null) {
                    chiTieuThang = (ChiTieuThang) bundle.getSerializable(CHI_TIEU_THANG_AGRS);
                } else {
                    chiTieuThang = new ChiTieuThang();
                }

                chiTieuThang.setSoTienToiDa(Integer.valueOf(edtChiTieuToiDa.getText().toString()));
                chiTieuThang.setThoiGian(null);
                chiTieuThang.save();
                dismiss();
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
