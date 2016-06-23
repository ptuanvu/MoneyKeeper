package hcmus.vuphan.moneykeeper.scences;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.Giaodich;

/**
 * Created by Join on 23/06/2016.
 */
public class AddGdFragment extends Fragment implements View.OnClickListener {
    static Button ThucHienGiaoDich;
    static EditText ID_HinhAnh,ID_Thang,ID_Catalog,TenGiaoDich,MoTaGiaoDich,DiaDiemGiaoDich,GhiChu;
    static RadioButton Co,Khong;
    static FragmentActivity context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.themgiaodich_layout, container, false);
        TenGiaoDich=(EditText)view.findViewById(R.id.txtTenGiaoDich);
        MoTaGiaoDich=(EditText)view.findViewById(R.id.txtMoTaGiaoDich);
        DiaDiemGiaoDich=(EditText)view.findViewById(R.id.txtDiaDiemGiaoDich);
        GhiChu=(EditText)view.findViewById(R.id.txtGhiChu);
        Co=(RadioButton)view.findViewById(R.id.rbtco);
        Khong=(RadioButton)view.findViewById(R.id.rbtkhong);
        ThucHienGiaoDich=(Button)view.findViewById(R.id.btnThucHienGiaoDich);
        ThucHienGiaoDich.setOnClickListener(this);
        return view;
    }
    public static void setContext(FragmentActivity context) {
        AddGdFragment.context = context;
    }


    public static AddGdFragment createInstance(FragmentActivity context) {
        AddGdFragment value = new AddGdFragment();
        value.setContext(context);
        return value;
    }

    @Override
    public void onClick(View v) {
        if(Co.isChecked()) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy / MM / dd ");
            String Date = format.format(calendar.getTime());
            Giaodich value = new Giaodich("1","2","3",TenGiaoDich.getText().toString(), MoTaGiaoDich.getText().toString(), DiaDiemGiaoDich.getText().toString(),Date, GhiChu.getText().toString(),"true");
            value.save();
        }
        if (Khong.isChecked())
        {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy / MM / dd ");
            String Date = format.format(calendar.getTime());
            Giaodich value = new Giaodich("1","2","3",TenGiaoDich.getText().toString(), MoTaGiaoDich.getText().toString(), DiaDiemGiaoDich.getText().toString(),Date, GhiChu.getText().toString(),"false");
            value.save();
        }
    }
}
