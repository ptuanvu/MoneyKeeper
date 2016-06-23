package hcmus.vuphan.moneykeeper.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.Catalog;
import hcmus.vuphan.moneykeeper.scences.CatalogFragment;

/**
 * Created by monster on 15/06/2016.
 */
public class CreateCatalogDialog extends DialogFragment implements View.OnClickListener {
    Button btnOK, btnCancel;
    EditText edtTitle, edtDescription;
    Spinner spnParrent;

    private final static String INPUT_CATALOG = "input_catalog";

    public static CreateCatalogDialog createInstance(Catalog catalog) {
        CreateCatalogDialog createCatalogDialog = new CreateCatalogDialog();
        if (catalog != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(INPUT_CATALOG, catalog);
            createCatalogDialog.setArguments(bundle);
        }
        return createCatalogDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_catalog_layout, container, false);

        btnOK = (Button) view.findViewById(R.id.btnOK);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);

        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        edtDescription = (EditText) view.findViewById(R.id.edtDescription);
        edtTitle = (EditText) view.findViewById(R.id.edtTitle);
        spnParrent = (Spinner) view.findViewById(R.id.spnParrent);

        List<Catalog> catalogs = Catalog.find(Catalog.class, "id_parrent = ?", "-1");
        List<String> list = new ArrayList<>();
        list.add("Thuộc catalog");
        for (Catalog c: catalogs
             ) {
            list.add(c.getTitle());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
        spnParrent.setAdapter(dataAdapter);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Catalog c = (Catalog) bundle.getSerializable(INPUT_CATALOG);
            edtTitle.setText(c.getTitle());
            //edtParrent.setText(c.getIdParrent().toString());
            spnParrent.setEnabled(false);
            edtDescription.setText(c.getDescription());
            getDialog().setTitle("Chỉnh sửa lại catalog");
        } else {
            getDialog().setTitle("Tạo mới dialog");
        }

        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOK:
                Long idParrent = -1L;

                if (String.valueOf(spnParrent.getSelectedItem()) == "Thuộc catalog") {
                    idParrent = -1L;
                } else {
                    Catalog c = Catalog.find(Catalog.class, "title = ?", String.valueOf(spnParrent.getSelectedItem())).get(0);
                    idParrent = c.getIdParrent();
                }
                OnCreateCatalogOK(edtTitle.getText().toString(), edtDescription.getText().toString(), idParrent);
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }

    private void OnCreateCatalogOK(String s, String s1, Long s3) {


        Bundle bundle = getArguments();

        Catalog catalog = null;
        if (bundle != null) {
            catalog = (Catalog) bundle.getSerializable(INPUT_CATALOG);
            catalog.setTitle(s);
            catalog.setDescription(s1);
        } else {
            catalog = new Catalog(s, s1, s3);
        }
        catalog.save();
        CatalogFragment.RefreshRecyclerViewCatalogs();

    }
}
