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
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.adapters.GiaoDichItemAdapter;
import hcmus.vuphan.moneykeeper.model.Giaodich;

/**
 * Created by Tam Le on 24/06/2016.
 */
public class LichSuGiaoDichFragment extends Fragment{
    static Spinner spinner;
    static ListView listView;

    static Context context;
    public static void setContext(Context context) {
        LichSuGiaoDichFragment.context = context;
    }


    public static LichSuGiaoDichFragment createInstance(Context context) {
        LichSuGiaoDichFragment lichSuGiaoDichFragment = new LichSuGiaoDichFragment();
        lichSuGiaoDichFragment.setContext(context);
        return lichSuGiaoDichFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.lichsu_giaodich,container,false);
        listView=(ListView)view.findViewById(R.id.listView2);
        spinner=(Spinner)view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("January");
        list.add("February");
        list.add("March");
        list.add("April");
        list.add("May");
        list.add("June");
        list.add("July");
        list.add("August");
        list.add("September");
        list.add("October");
        list.add("November");
        list.add("December");

        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String thang = spinner.getSelectedItem().toString();
                String month="";

                switch (thang) {
                    case "January": {
                        month = "Jan";
                        break;
                    }
                    case "February": {
                        month = "Feb";
                        break;
                    }
                    case "March": {
                        month = "Mar";
                        break;
                    }
                    case "April": {
                        month = "Apr";
                        break;
                    }
                    case "May": {
                        month = "May";
                        break;
                    }
                    case "June": {
                        month = "Jun";
                        break;
                    }
                    case "July": {
                        month = "Jul";
                        break;
                    }
                    case "August": {
                        month = "Aug";
                        break;
                    }
                    case "September": {
                        month = "Sep";
                        break;
                    }
                    case "October": {
                        month = "Oct";
                        break;
                    }
                    case "November": {
                        month = "Nov";
                        break;
                    }
                    case "December": {
                        month = "Dec";
                        break;
                    }
                }

                List<Giaodich> list = getArrayByMonth(getArr(), month);
                listView.setAdapter(new GiaoDichItemAdapter(context, list));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
    public static List<Giaodich> getArrayByMonth(List<Giaodich> arr,String month)
    {
        List<Giaodich> arrayList=new ArrayList<Giaodich>();
        if (arr.size()==0)return null;
        for (Giaodich c:arr) {
            String value=(String) android.text.format.DateFormat.format("MMM", c.getThoiGian());

            if(value.equals(month))
            {
                arrayList.add(c);
            }
        }

        return arrayList;
    }
    public static List<Giaodich> getArr()
    {
        List<Giaodich> arr=Giaodich.listAll(Giaodich.class);
        return arr;
    }
}
