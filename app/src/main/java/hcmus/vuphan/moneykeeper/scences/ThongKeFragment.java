package hcmus.vuphan.moneykeeper.scences;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.ChiTieuThang;
import hcmus.vuphan.moneykeeper.model.Giaodich;

/**
 * Created by Tam Le on 24/06/2016.
 */
public class ThongKeFragment extends Fragment{

    Spinner spinner ;
    TextView txtviewthongke;
    Button btnThongkequaylai;
    static Context context;
    public static void setContext(Context context) {
        ThongKeFragment.context = context;
    }

    public static ThongKeFragment createInstance(Context context) {
        ThongKeFragment thongKeFrament = new ThongKeFragment();
        thongKeFrament.setContext(context);
        return thongKeFrament;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.thongke_layout,container,false);
        spinner = (Spinner) view.findViewById(R.id.spinnerthongke);
        txtviewthongke = (TextView) view.findViewById(R.id.txtviewThongke);
        btnThongkequaylai=(Button)view.findViewById(R.id.btnthongkequaylai);

        final List<ChiTieuThang> chiTieuThangs = ChiTieuThang.listAll(ChiTieuThang.class);
        List<String> list = new ArrayList<>();
        for (ChiTieuThang chiTieuThang : chiTieuThangs){
            String value=(String) android.text.format.DateFormat.format("MMM/yyyy", chiTieuThang.getThoiGian());
            list.add(value);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String thangnam = spinner.getSelectedItem().toString();
                List<Giaodich> giaodichList = Giaodich.listAll(Giaodich.class);
                int tonggiaodich = 0;
                for (Giaodich giaodich : giaodichList) {
                    String value = (String) android.text.format.DateFormat.format("MMM/yyyy", giaodich.getThoiGian());
                    if (thangnam.equals(value)) {
                        tonggiaodich++;
                    }
                }
                txtviewthongke.append(Integer.toString(tonggiaodich)+ "\t" + chiTieuThangs.get(position).getSoTienToiDa() );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }
}
