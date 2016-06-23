package hcmus.vuphan.moneykeeper.scences;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.adapters.GiaoDichAdapter;
import hcmus.vuphan.moneykeeper.model.Giaodich;

/**
 * Created by Join on 23/06/2016.
 */
public class ShowListGdFragment extends Fragment implements View.OnClickListener{
    static ListView listView;
    static FragmentActivity context;

    public static void setContext(FragmentActivity context) {
        ShowListGdFragment.context = context;
    }


    public static ShowListGdFragment createInstance(FragmentActivity context) {
        ShowListGdFragment ac = new ShowListGdFragment();
        ac.setContext(context);
        return ac;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listgd_layout, container, false);
        listView=(ListView)view.findViewById(R.id.listView);
        List<Giaodich> arr=getListData();
        listView.setAdapter(new GiaoDichAdapter(context,arr));
        return view;
    }

    public static void RefreshList()
    {
        List<Giaodich> arr=getListData();

        listView.setAdapter(new GiaoDichAdapter(context,arr));
    }
    public static List<Giaodich> getListData()
    {
        List<Giaodich> arr=Giaodich.listAll(Giaodich.class);
        return arr;
    }
    @Override
    public void onClick(View v) {

    }
}
