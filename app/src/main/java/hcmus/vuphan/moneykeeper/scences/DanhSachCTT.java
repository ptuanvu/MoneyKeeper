package hcmus.vuphan.moneykeeper.scences;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.adapters.CTTAdapter;
import hcmus.vuphan.moneykeeper.adapters.CatalogAdapter;
import hcmus.vuphan.moneykeeper.dialogs.CUChiTieuThang;
import hcmus.vuphan.moneykeeper.dialogs.CreateCatalogDialog;
import hcmus.vuphan.moneykeeper.model.Catalog;
import hcmus.vuphan.moneykeeper.model.ChiTieuThang;

/**
 * Created by monster on 24/06/2016.
 */
public class DanhSachCTT extends Fragment implements View.OnClickListener {
    static Context context;
    //Cac view elements
    FloatingActionButton fabCreate;
    static RecyclerView rcvCTT;
    static FragmentManager fragmentManager;
    RecyclerView.LayoutManager layoutManager;

    public void setContext(Context context) {
        this.context = context;
    }

    public static DanhSachCTT createFragment(Context context) {
        DanhSachCTT catalogFragment = new DanhSachCTT();
        catalogFragment.setContext(context);
        return catalogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalog_layout, container, false);
        rcvCTT = (RecyclerView) view.findViewById(R.id.rcvCatalogs);
        fabCreate = (FloatingActionButton) view.findViewById(R.id.fabCreate);
        rcvCTT.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rcvCTT.setLayoutManager(layoutManager);

        RefreshRecyclerView();

        fabCreate.setOnClickListener(this);
        fragmentManager = getFragmentManager();
        return view;
    }

    public static void RefreshRecyclerView() {
        List<ChiTieuThang> chiTieuThangs = ChiTieuThang.listAll(ChiTieuThang.class);
        CTTAdapter cttAdapter = new CTTAdapter(chiTieuThangs, context);
        rcvCTT.setAdapter(cttAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabCreate:
                ShowCreateDialog(null);
                break;
        }
    }

    public static void ShowCreateDialog(ChiTieuThang chiTieuThang) {
        CUChiTieuThang cuChiTieuThang = CUChiTieuThang.concreateInstance(chiTieuThang, context);
        cuChiTieuThang.show(fragmentManager, "dialog");
    }
}
