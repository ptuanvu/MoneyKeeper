package hcmus.vuphan.moneykeeper.adapters;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.Giaodich;
import hcmus.vuphan.moneykeeper.scences.CapnhatgdFragment;
import hcmus.vuphan.moneykeeper.scences.ShowListGdFragment;

/**
 * Created by Join on 23/06/2016.
 */
public class GiaoDichAdapter extends BaseAdapter {
    List<Giaodich> listData;
    LayoutInflater layoutInflater;
    FragmentActivity context;
    public GiaoDichAdapter(FragmentActivity context,List<Giaodich> listData)
    {
        this.context=context;
        this.listData=listData;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.item_gd_layout,null);
            holder=new ViewHolder();
            holder.Ten=(TextView)convertView.findViewById(R.id.txtTen);
            holder.Catalog=(TextView)convertView.findViewById(R.id.txtcatalog);
            holder.Del=(ImageButton)convertView.findViewById(R.id.imbDel);
            holder.Up=(Button)convertView.findViewById(R.id.btup);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }
        Giaodich giaodich=this.listData.get(position);
        holder.Ten.setText(giaodich.getTenGiaoDich());
        holder.Catalog.setText(giaodich.getID_Catalog());
        holder.Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Xác nhận xóa")
                        .setMessage("Bạn vẫn muốn xóa ?")
                        .setIcon(R.drawable.ic_warning_24dp)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Giaodich gd = Giaodich.findById(Giaodich.class, listData.get(position).getId());
                                gd.delete();
                                ShowListGdFragment.RefreshList();
                            }
                        })
                        .setNegativeButton("Không", null).show();
            }
        });
        holder.Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapnhatgdFragment lwf = new CapnhatgdFragment();
                Bundle b = new Bundle();
                b.putLong("ID",listData.get(position).getId());
                lwf.setArguments(b);
                FragmentTransaction ft = context.getFragmentManager().beginTransaction();
                ft.replace(R.id.contentFrameLayout, lwf);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return convertView;
    }
    static class ViewHolder
    {
        TextView Ten,Catalog;
        ImageButton Del;
        Button Up;
    }

}
