package hcmus.vuphan.moneykeeper.dialogs;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
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

import java.text.DateFormat;
import java.text.ParseException;
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
    EditText edtChiTieuToiDa, edtThang;
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

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        edtThang.setText(global.df.format(myCalendar.getTime()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cu_chi_tieu_thang, container, false);
        btnOK = (Button) view.findViewById(R.id.btnOK);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        edtThang = (EditText) view.findViewById(R.id.edtThang);
        edtChiTieuToiDa = (EditText) view.findViewById(R.id.edtChiTieuToiDa);
        dpThang = (DatePicker) view.findViewById(R.id.dpThang);

        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpThang.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpThang);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
        }

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
                try {
                    chiTieuThang.setThoiGian(global.df.parse(edtThang.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
