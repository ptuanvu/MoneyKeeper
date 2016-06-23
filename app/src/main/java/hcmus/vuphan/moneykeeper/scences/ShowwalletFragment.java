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
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.wallet;

/**
 * Created by Join on 23/06/2016.
 */
public class ShowwalletFragment extends Fragment implements View.OnClickListener {
    static FragmentActivity context;
     EditText tenvi ;
     EditText hientai;
     EditText dutru;
     EditText tietkiem;
     Button upd;
     Button back;
    List<wallet> temp;

    public void setContext(FragmentActivity context) {
        this.context = context;
    }
    public static ShowwalletFragment createFragment(FragmentActivity context) {
        ShowwalletFragment LF = new ShowwalletFragment();
        LF.setContext(context);

        return LF;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.showwallet_layout, container, false);
        SugarContext.init(context);
         tenvi = (EditText) view.findViewById(R.id.namewallet);
         hientai = (EditText) view.findViewById(R.id.curmoneyitem);
         dutru = (EditText) view.findViewById(R.id.resmoneyitem);
         tietkiem = (EditText) view.findViewById(R.id.savingmoneyitem);
         upd = (Button) view.findViewById(R.id.updatebt);
         back = (Button)view.findViewById(R.id.btbacklistwallet);
        Bundle b = getArguments();
        String name = b.getString("name");
        temp = wallet.find(wallet.class,"name = ? ",name);
        if (temp.size()!=0)
        {
            tenvi.setText(temp.get(0).getName());
            hientai.setText(temp.get(0).getTienhientai());
            dutru.setText(temp.get(0).getTiendutru());
            tietkiem.setText(temp.get(0).getTientietkiem());
        }
        upd.setOnClickListener(this);
        back.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.updatebt:
            String upname = tenvi.getText().toString();
            String uphientai = hientai.getText().toString();
            String updutru = dutru.getText().toString();
            String uptietkiem = tietkiem.getText().toString();
            List<wallet> tempup = wallet.find(wallet.class, "name = ? ", upname);
            if (tempup.size() == 0 || temp.get(0).getId().equals(tempup.get(0).getId())) {
                if (!tryParseInt(uphientai)||!tryParseInt(updutru)||!tryParseInt(uptietkiem))
                {
                    Toast.makeText(context, "Giá trị nhập vào phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }
                temp.get(0).setName(upname);
                temp.get(0).setTienhientai(uphientai);
                temp.get(0).setTiendutru(updutru);
                temp.get(0).setTientietkiem(uptietkiem);
                temp.get(0).save();
            }
            if (tempup.size() != 0 && !temp.get(0).getId().equals(tempup.get(0).getId())) {
                Toast.makeText(context, "Tên đã tồn tại", Toast.LENGTH_SHORT).show();
                return;
            }
            ListwalletFragment sf = new ListwalletFragment();
            FragmentTransaction ft = context.getFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrameLayout, sf);
            ft.addToBackStack(null);
            ft.commit();
                break;
            case R.id.btbacklistwallet:
                ListwalletFragment lf = new ListwalletFragment();
                FragmentTransaction ft1 = context.getFragmentManager().beginTransaction();
                ft1.replace(R.id.contentFrameLayout, lf);
                ft1.addToBackStack(null);
                ft1.commit();
                break;
        }
    }
    public boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
