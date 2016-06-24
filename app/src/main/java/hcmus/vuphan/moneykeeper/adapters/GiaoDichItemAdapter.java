package hcmus.vuphan.moneykeeper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.Giaodich;

/**
 * Created by Tam Le on 24/06/2016.
 */
public class GiaoDichItemAdapter extends BaseAdapter {
    List<Giaodich> listData;
    LayoutInflater layoutInflater;
    Context context;
    public GiaoDichItemAdapter(Context context,List<Giaodich> listData)
    {
        this.context=context;
        this.listData=listData;
        layoutInflater= LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.lichsu_giaodich_item,null);
            holder=new ViewHolder();
            holder.Ten=(TextView)convertView.findViewById(R.id.txtTenItem);
            holder.Ngay=(TextView)convertView.findViewById(R.id.txtNgayItem);
            holder.MoTa=(TextView)convertView.findViewById(R.id.txtMoTaItem);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }
        Giaodich giaodich=this.listData.get(position);
        holder.Ten.setText(giaodich.getTenGiaoDich());
        holder.Ngay.setText((String) android.text.format.DateFormat.format("dd MMM", giaodich.getThoiGian()));
        holder.MoTa.setText(giaodich.getMoTaGiaoDich());
        return convertView;
    }
    static class ViewHolder
    {
        TextView Ngay,Ten,MoTa;
    }
}
