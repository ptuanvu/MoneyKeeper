package hcmus.vuphan.moneykeeper.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hcmus.vuphan.moneykeeper.MainActivity;
import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.dialogs.CreateCatalogDialog;
import hcmus.vuphan.moneykeeper.model.Catalog;
import hcmus.vuphan.moneykeeper.scences.CatalogFragment;

/**
 * Created by monster on 17/06/2016.
 */
public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> implements View.OnClickListener {
    ArrayList<Catalog> items;
    Context context;

    public CatalogAdapter(ArrayList<Catalog> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public CatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item_layout, parent, false);
        CatalogViewHolder vh = new CatalogViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(CatalogViewHolder holder, int position) {

        final Catalog c = items.get(position);
        holder.tvTitle.setText(c.getTitle());
        holder.tvDescription.setText(c.getDescription());
        holder.imbDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AlertDialog.Builder(context)
                        .setTitle("Xác nhận xóa catalog")
                        .setMessage("Bạn vẫn muốn xóa catalog này?")
                        .setIcon(R.drawable.ic_warning_24dp)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                DeleteCatalogItem(c);
                                CatalogFragment.RefreshRecyclerViewCatalogs();
                            }})
                        .setNegativeButton("Không", null).show();
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CatalogFragment.ShowCreateDialog(c);
            }
        });
    }

    private void DeleteCatalogItem(Catalog c) {
        c.delete();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(View v) {

    }

    public static class CatalogViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvDescription;
        public ImageButton imbIcon, imbDelete;
        public CardView cardView;

        public CatalogViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            imbIcon = (ImageButton) itemView.findViewById(R.id.imbIcon);
            imbDelete = (ImageButton) itemView.findViewById(R.id.imbDelete);
            cardView = (CardView) itemView.findViewById(R.id.cvCatalogItem);

        }


    }
}
