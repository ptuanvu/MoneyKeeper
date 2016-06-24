package hcmus.vuphan.moneykeeper.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hcmus.vuphan.moneykeeper.MoneyHelper;
import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.dialogs.CUGiaoDich;
import hcmus.vuphan.moneykeeper.model.Giaodich;
import hcmus.vuphan.moneykeeper.scences.CatalogFragment;
import hcmus.vuphan.moneykeeper.scences.LichSuGiaoDich;

/**
 * Created by Monster on 6/25/2016.
 */
public class DanhSachGiaoDichAdapter extends RecyclerView.Adapter<DanhSachGiaoDichAdapter.GiaoDichViewHolder> {
    List<Giaodich> gds;
    Context context;

    public DanhSachGiaoDichAdapter(Context context, List<Giaodich> gds) {
        this.context = context;
        this.gds = gds;
    }

    @Override
    public GiaoDichViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.giao_dich_item_layout, parent, false);
        GiaoDichViewHolder vh = new GiaoDichViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(GiaoDichViewHolder holder, int position) {
        final Giaodich gd = gds.get(position);
        holder.tvTitle.setText(gd.getTenGiaoDich());
        holder.tvPrice.setText(MoneyHelper.MoneyParser(gd.getTongtien()));
        holder.tvTime.setText(gd.getThoiGian().toString());
        holder.imbDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Xác nhận xóa catalog")
                        .setMessage("Bạn vẫn muốn xóa catalog này?")
                        .setIcon(R.drawable.ic_warning_24dp)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                DeleteGiaoDich(gd);
                                LichSuGiaoDich.RefreshRecyclerView();
                            }
                        })
                        .setNegativeButton("Không", null).show();
            }
        });
        holder.cvWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LichSuGiaoDich.ShowCreateDialog(gd);
            }
        });
    }

    private void DeleteGiaoDich(Giaodich gd) {
        gd.delete();
        try {
            Toast.makeText(context, "Xóa thành công giao dịch", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return gds.size();
    }

    public class GiaoDichViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvPrice, tvTime;
        ImageButton imbDelete;
        CardView cvWraper;

        public GiaoDichViewHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvTitle = (TextView) itemView.findViewById(R.id.tvName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            imbDelete = (ImageButton) itemView.findViewById(R.id.imbDelete);
            cvWraper = (CardView) itemView.findViewById(R.id.cvWraper);
        }
    }
}
