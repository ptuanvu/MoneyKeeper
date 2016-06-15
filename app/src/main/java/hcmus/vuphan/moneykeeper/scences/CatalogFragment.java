package hcmus.vuphan.moneykeeper.scences;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmus.vuphan.moneykeeper.MainActivity;
import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.dialogs.CreateCatalogDialog;
import hcmus.vuphan.moneykeeper.model.Catalog;

/**
 * Created by monster on 15/06/2016.
 */
public class CatalogFragment extends Fragment implements View.OnClickListener {
    Context context;

    // Cac view co trong layout
    Button btnCreate, btnRead, btnUpdate, btnDelete;
    EditText edtUserInput;

    //

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
        btnCreate = (Button) view.findViewById(R.id.btnCreate);
        btnDelete = (Button) view.findViewById(R.id.btnDelete);
        btnRead = (Button) view.findViewById(R.id.btnRead);
        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        edtUserInput = (EditText) view.findViewById(R.id.edtUserInput);

        btnCreate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnRead.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreate:
                OnCreateCatalog();
                break;
            case R.id.btnDelete:
                if (edtUserInput.getText().toString() != "")
                    OnDeleteCatalog(edtUserInput.getText().toString());
                break;
            case R.id.btnUpdate:
                if (edtUserInput.getText().toString() != "") {
                    OnUpdateCatalog(edtUserInput.getText().toString());
                }
                break;
            case R.id.btnRead:
                OnReadCatalog();
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
}
