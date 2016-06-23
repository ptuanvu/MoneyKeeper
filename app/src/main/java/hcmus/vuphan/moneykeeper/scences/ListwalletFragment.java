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
import android.widget.ListView;
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.adapters.WalletAdapter;
import hcmus.vuphan.moneykeeper.model.Wallet;

/**
 * Created by Join on 23/06/2016.
 */
public class ListwalletFragment extends Fragment implements View.OnClickListener {
    static FragmentActivity context;
    ArrayList<String> mylistwallet = new ArrayList<String>();
    WalletAdapter mydapter;
    Button add;
    EditText name;
    public void setContext(FragmentActivity context) {
        this.context = context;
    }
    public static ListwalletFragment createFragment(FragmentActivity context) {
        ListwalletFragment LF = new ListwalletFragment();
        LF.setContext(context);

        return LF;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.listwallet_layout, container, false);
        SugarContext.init(context);
        final ListView lv = (ListView) view.findViewById(R.id.listwallet);
        mydapter = new WalletAdapter(mylistwallet,context);
        InitList();
        lv.setAdapter(mydapter);
        SugarContext.init(context);
        add = (Button) view.findViewById(R.id.newbt);
        name = (EditText)view.findViewById(R.id.walletname);
        add.setOnClickListener(this);

        return view;
    }
    public void InitList()
    {
        List<Wallet> initwallet = Wallet.listAll(Wallet.class);

        for (int i = 0 ; i < initwallet.size(); i ++)
        {
            mylistwallet.add(initwallet.get(i).getName());
        }
        mydapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        if (name.getText().toString().equals("")) {
            Toast.makeText(context, "Nhập tên ví", Toast.LENGTH_SHORT).show();
            return;
        } else {
            for (int i = 0; i < mylistwallet.size(); i++) {
                if (name.getText().toString().equals(mylistwallet.get(i))) {
                    Toast.makeText(context, "Tên ví đã tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Wallet newwallet = new Wallet(name.getText().toString(), "", "", "");
            newwallet.save();
            mylistwallet.add(name.getText().toString());
            mydapter.notifyDataSetChanged();
            name.setText("");
        }
    }
}
