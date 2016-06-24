package hcmus.vuphan.moneykeeper.dialogs;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hcmus.vuphan.moneykeeper.MainActivity;
import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.global;
import hcmus.vuphan.moneykeeper.model.ChiTieuThang;

/**
 * Created by monster on 23/06/2016.
 */
public class CUChiTieuThang extends DialogFragment
{
    private static final String CHI_TIEU_THANG_AGRS = "chi_tieu_thang";
    Button btnOK, btnCancel;
    EditText edtChiTieuToiDa;
    DatePicker dpThang;
    public Context context;


    public static CUChiTieuThang concreateInstance(ChiTieuThang cur, Context context) {
        CUChiTieuThang cuChiTieuThang = new CUChiTieuThang();
        if (cur != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(CHI_TIEU_THANG_AGRS, cur);
            cuChiTieuThang.setArguments(bundle);
            cuChiTieuThang.context = context;
        }
        return cuChiTieuThang;
    }

    public interface ChiTieuThangDiaglogListener {
        void OnChiTieuThangDialogFinish();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cu_chi_tieu_thang, container, false);
        btnOK = (Button) view.findViewById(R.id.btnOK);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        edtChiTieuToiDa = (EditText) view.findViewById(R.id.edtChiTieuToiDa);
        dpThang = (DatePicker) view.findViewById(R.id.dpThang);
        dpThang.findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        dpThang.setCalendarViewShown(false);
        dpThang.setSpinnersShown(true);


        final Bundle bundle = getArguments();
        if (bundle != null) {
            ChiTieuThang chiTieuThang = (ChiTieuThang) bundle.getSerializable(CHI_TIEU_THANG_AGRS);
            dpThang.updateDate(chiTieuThang.getThoiGian().getYear(), chiTieuThang.getThoiGian().getMonth(), chiTieuThang.getThoiGian().getDay());
            edtChiTieuToiDa.setText(String.valueOf(chiTieuThang.getSoTienToiDa()));
            getDialog().setTitle("Chỉnh sửa chi tiêu tháng");
        } else {
            getDialog().setTitle("Tạo mới chi tiêu tháng");
        }


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtChiTieuToiDa.getText().toString().length() >= 1) {
                    ChiTieuThang chiTieuThang = null;
                    if (bundle != null) {
                        chiTieuThang = (ChiTieuThang) bundle.getSerializable(CHI_TIEU_THANG_AGRS);
                    } else {
                        chiTieuThang = new ChiTieuThang();
                    }


                    chiTieuThang.setSoTienToiDa(Integer.valueOf(edtChiTieuToiDa.getText().toString()));
                    chiTieuThang.setThoiGian(new Date(dpThang.getYear(), dpThang.getMonth(), 1));

                    chiTieuThang.save();
                    ((ChiTieuThangDiaglogListener)getActivity()).OnChiTieuThangDialogFinish();
                    dismiss();
                } else {
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ các thông tin cần thiết.", Toast.LENGTH_SHORT).show();
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
