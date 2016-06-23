package hcmus.vuphan.moneykeeper.scences;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.Giaodich;

/**
 * Created by Join on 23/06/2016.
 */
public class CapnhatgdFragment extends Fragment implements View.OnClickListener {
    static FragmentActivity context;
     EditText ten ;
     EditText mota;
     EditText diadiem;
     EditText chuthich;
     RadioButton upco;
      RadioButton upkhong;
      Button thuchienup;
    Giaodich temp;
    public  void setContext(FragmentActivity context) {
        this.context = context;
    }


    public static CapnhatgdFragment createInstance(FragmentActivity context) {
        CapnhatgdFragment ac = new CapnhatgdFragment();
        ac.setContext(context);
        return ac;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.capnhatgd_layout, container, false);
        Bundle b = getArguments();
        Long ID = b.getLong("ID");
         temp = Giaodich.findById(Giaodich.class, ID);
         ten = (EditText) view.findViewById(R.id.txtTenGiaoDichup);
         mota = (EditText)view.findViewById(R.id.txtMoTaGiaoDichup);
         diadiem = (EditText)view.findViewById(R.id.txtDiaDiemGiaoDichup);
         chuthich = (EditText)view.findViewById(R.id.txtGhiChuup);
         upco = (RadioButton) view.findViewById(R.id.rbtcoup);
         upkhong = (RadioButton) view.findViewById(R.id.rbtkhongup);
         thuchienup = (Button) view.findViewById(R.id.btnThucHienGiaoDichup);
        ten.setText(temp.getTenGiaoDich());
        mota.setText(temp.getMoTaGiaoDich());
        diadiem.setText(temp.getDiaDiem());
        chuthich.setText(temp.getGhiChu());
        if (temp.isGiaoDichCoDinh().equals("true")) upco.setChecked(true);
        if (temp.isGiaoDichCoDinh().equals("false")) upkhong.setChecked(true);
        thuchienup.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        temp.setTenGiaoDich(ten.getText().toString());
        temp.setMoTaGiaoDich(mota.getText().toString());
        temp.setDiaDiem(diadiem.getText().toString());
        temp.setGhiChu(chuthich.getText().toString());
        if (upco.isChecked()) temp.setGiaoDichCoDinh("true");
        if (upkhong.isChecked()) temp.setGiaoDichCoDinh("false");
        temp.save();
        ShowListGdFragment lwf = new ShowListGdFragment();
        FragmentTransaction ft = context.getFragmentManager().beginTransaction();
        ft.replace(R.id.contentFrameLayout, lwf);
        ft.addToBackStack(null);
        ft.commit();

    }
}
