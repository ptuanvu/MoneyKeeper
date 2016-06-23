package hcmus.vuphan.moneykeeper.adapters;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.Wallet;
import hcmus.vuphan.moneykeeper.scences.ShowwalletFragment;

/**
 * Created by Join on 23/06/2016.
 */
public class WalletAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private FragmentActivity context;


    public WalletAdapter(ArrayList<String> list, FragmentActivity context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listwalletitem, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.tenvi);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.xoabt);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Xác nhận xóa")
                        .setMessage("Bạn vẫn muốn xóa ?")
                        .setIcon(R.drawable.ic_warning_24dp)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                List<Wallet> temp = Wallet.find(Wallet.class, "name = ?", list.get(position));
                                temp.get(0).delete();
                                list.remove(position); //or some other task
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Không", null).show();

            }
        });
        Button showbt = (Button)view.findViewById(R.id.xembt);
        showbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = list.get(position);
                ShowwalletFragment sf = new ShowwalletFragment();
                Bundle b = new Bundle();
                b.putString("name",name);
                sf.setArguments(b);
                FragmentTransaction ft = context.getFragmentManager().beginTransaction();
                ft.replace(R.id.contentFrameLayout, sf);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;
    }
}
