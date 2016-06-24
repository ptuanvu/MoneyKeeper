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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.Catalog;
import hcmus.vuphan.moneykeeper.model.ChiTieuThang;
import hcmus.vuphan.moneykeeper.model.Giaodich;

/**
 * Created by Tam Le on 24/06/2016.
 */
public class ThongKeFragment extends Fragment{

    Spinner spinner ;
    TextView txtviewthongke;
    Button btnThongkequaylai;
    ListView listthongke;
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
        //txtviewthongke = (TextView) view.findViewById(R.id.txtviewThongke);
        btnThongkequaylai=(Button)view.findViewById(R.id.btnthongkequaylai);
        listthongke = (ListView)view.findViewById(R.id.listthongke);

        //final List<ChiTieuThang> chiTieuThangs = ChiTieuThang.listAll(ChiTieuThang.class);
        final List<String> list = new ArrayList<>();
        list.add("Catalog");
        list.add("Tháng/Năm");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String kieuthongke = spinner.getSelectedItem().toString();
                List<Giaodich> giaodichList = Giaodich.listAll(Giaodich.class);
                List<Integer> sothongke = new ArrayList<Integer>();
                List<Integer> tongtien = new ArrayList<Integer>();
                List<String> data = new ArrayList<String>();
                switch (kieuthongke) {
                    case "Catalog":
                        //List<Giaodich> giaodichList = Giaodich.listAll(Giaodich.class);
                        List<Catalog> catalogList = Catalog.listAll(Catalog.class);

                        for (int i = 0; i < catalogList.size(); i++) {
                            sothongke.add(0);
                            tongtien.add(0);
                        }
                        for (int i = 0; i < giaodichList.size(); i++) {
                            for (Catalog catalog : catalogList) {
                                if (catalog.getTitle().equals(giaodichList.get(i).getID_Catalog())) {
                                    sothongke.set(i, sothongke.get(i) + 1);
                                    tongtien.set(i, tongtien.get(i) + giaodichList.get(i).getTongtien());
                                    break;
                                }
                            }
                        }

                        for (int i = 0; i < catalogList.size(); i++) {
                            data.add(catalogList.get(i).getTitle() + "\t" + sothongke.get(i).toString() + "\t" + tongtien.get(i).toString());
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context,R.layout.thongkeitem_layout,data);
                        listthongke.setAdapter(arrayAdapter);

                        break;
                    case "Tháng/Năm":
                        List<ChiTieuThang> chiTieuThangs = ChiTieuThang.listAll(ChiTieuThang.class);

                        for (int i = 0; i < chiTieuThangs.size(); i++) {
                            sothongke.add(0);
                            tongtien.add(0);
                        }
                        for (int i = 0; i < giaodichList.size(); i++) {
                            String thoigiangiaodich = (String) android.text.format.DateFormat.format("MMM/yyyy", giaodichList.get(i).getThoiGian());
                            for (ChiTieuThang chiTieuThang : chiTieuThangs) {
                                String chitieuthoigian = (String) android.text.format.DateFormat.format("MMM/yyyy", chiTieuThang.getThoiGian());
                                if (thoigiangiaodich.equals(chitieuthoigian)) {
                                    sothongke.set(i, sothongke.get(i) + 1);
                                    tongtien.set(i, tongtien.get(i) + giaodichList.get(i).getTongtien());
                                    break;
                                }
                            }

                        }
                        data = new ArrayList<String>();
                        for (int i = 0; i < chiTieuThangs.size(); i++) {
                            String chitieuthoigian = (String) android.text.format.DateFormat.format("MMM/yyyy", chiTieuThangs.get(i).getThoiGian());
                            data.add(chitieuthoigian + "\t" + sothongke.get(i).toString() + "\t" + tongtien.get(i).toString());
                        }
                        //code o day
                        ArrayAdapter arrayAdapter1 = new ArrayAdapter(context,R.layout.thongkeitem_layout,data);
                        listthongke.setAdapter(arrayAdapter1);


                        break;

                }
                //txtviewthongke.append(Integer.toString(tonggiaodich)+ "\t" + chiTieuThangs.get(position).getSoTienToiDa() );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }
}
