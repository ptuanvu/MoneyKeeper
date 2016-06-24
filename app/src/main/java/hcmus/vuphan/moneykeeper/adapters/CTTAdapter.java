package hcmus.vuphan.moneykeeper.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hcmus.vuphan.moneykeeper.MoneyHelper;
import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.global;
import hcmus.vuphan.moneykeeper.model.Catalog;
import hcmus.vuphan.moneykeeper.model.ChiTieuThang;
import hcmus.vuphan.moneykeeper.model.Giaodich;
import hcmus.vuphan.moneykeeper.scences.CatalogFragment;
import hcmus.vuphan.moneykeeper.scences.DanhSachCTT;

/**
 * Created by monster on 24/06/2016.
 */
public class CTTAdapter extends RecyclerView.Adapter<CTTAdapter.CTTHolder> {

    List<ChiTieuThang> items;
    Context context;

    public CTTAdapter(List<ChiTieuThang> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public CTTHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item_layout, parent, false);
        CTTHolder vh = new CTTHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(CTTHolder holder, int position) {
        final ChiTieuThang chiTieuThang = items.get(position);

        Date date = chiTieuThang.getThoiGian();

        String thoiGian2 = global.dfMonthAndYear.format(date);
        thoiGian2 = MoneyHelper.ToFixedDate(thoiGian2);

        holder.tvTitle.setText(thoiGian2);
        holder.imbDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Xác nhận xóa catalog")
                        .setMessage("Bạn vẫn muốn xóa catalog này?")
                        .setIcon(R.drawable.ic_warning_24dp)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                DeleteChiTieuThang(chiTieuThang);
                                CatalogFragment.RefreshRecyclerViewCatalogs();
                            }
                        })
                        .setNegativeButton("Không", null).show();
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DanhSachCTT.ShowCreateDialog(chiTieuThang);
            }
        });
    }

    private void DeleteChiTieuThang(ChiTieuThang chiTieuThang) {
        List<Giaodich> giaodichList = Giaodich.find(Giaodich.class, "idthang = ?", String.valueOf(chiTieuThang.getId()));
        for (Giaodich gd :
                giaodichList) {
            gd.delete();
        }

        chiTieuThang.delete();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CTTHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvDescription;
        public ImageButton imbIcon, imbDelete;
        public CardView cardView;

        public CTTHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            imbIcon = (ImageButton) itemView.findViewById(R.id.imbIcon);
            imbDelete = (ImageButton) itemView.findViewById(R.id.imbDelete);
            cardView = (CardView) itemView.findViewById(R.id.cvCatalogItem);

        }
    }
}
