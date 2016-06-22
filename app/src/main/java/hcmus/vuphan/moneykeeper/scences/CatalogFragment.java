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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmus.vuphan.moneykeeper.MainActivity;
import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.adapters.CatalogAdapter;
import hcmus.vuphan.moneykeeper.dialogs.CreateCatalogDialog;
import hcmus.vuphan.moneykeeper.model.Catalog;

/**
 * Created by monster on 15/06/2016.
 */
public class CatalogFragment extends Fragment implements View.OnClickListener {
    static Context context;

    //Cac view elements
    FloatingActionButton fabCreate;
    static RecyclerView rcvCatalogs;
    static FragmentManager fragmentManager;

    RecyclerView.LayoutManager layoutManager;

    public void setContext(Context context) {
        this.context = context;
    }

    public static CatalogFragment createFragment(Context context) {
        CatalogFragment catalogFragment = new CatalogFragment();
        catalogFragment.setContext(context);

        return catalogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalog_layout, container, false);

        rcvCatalogs = (RecyclerView) view.findViewById(R.id.rcvCatalogs);
        fabCreate = (FloatingActionButton) view.findViewById(R.id.fabCreate);

        rcvCatalogs.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rcvCatalogs.setLayoutManager(layoutManager);

        RefreshRecyclerViewCatalogs();
        fabCreate.setOnClickListener(this);
        fragmentManager = getFragmentManager();

        return view;
    }

    public static void RefreshRecyclerViewCatalogs() {
        ArrayList catalogs = GetAllCatalogs();
        CatalogAdapter catalogAdapter = new CatalogAdapter(catalogs, context);
        rcvCatalogs.setAdapter(catalogAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabCreate:
                OnCreateCatalog();
                break;
        }
    }

    private void OnReadCatalog() {


    }

    private void OnUpdateCatalog(String s) {
        Long id = Long.valueOf(s);
        Catalog catalog = Catalog.findById(Catalog.class, id);
        CreateCatalogDialog createCatalogDialog = CreateCatalogDialog.createInstance(catalog);
        createCatalogDialog.show(getFragmentManager(), "dialog");
    }

    private void OnDeleteCatalog(String s) {

    }

    private void OnCreateCatalog() {
        CreateCatalogDialog createCatalogDialog = CreateCatalogDialog.createInstance(null);
        createCatalogDialog.show(getFragmentManager(), "Create Catalog Dialog");

        Toast.makeText(context, "Create Catalog Success", Toast.LENGTH_SHORT).show();
    }

    public static void ShowCreateDialog(Catalog c) {
        CreateCatalogDialog createCatalogDialog = CreateCatalogDialog.createInstance(c);
        createCatalogDialog.show(fragmentManager, "dialog");
    }

    public static ArrayList<Catalog> GetAllCatalogs() {
        ArrayList<Catalog> result = new ArrayList<Catalog>();

        List<Catalog> parents = Catalog.find(Catalog.class, "id_parrent = ?", "-1");
        for (Catalog parent: parents
             ) {
            List<Catalog> childs = Catalog.find(Catalog.class, "id_parrent = ?" , String.valueOf(parent.getId()));
            result.add(parent);
            result.addAll(childs);
        }
        return result;
    }
}
