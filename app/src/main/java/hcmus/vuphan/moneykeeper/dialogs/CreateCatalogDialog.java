package hcmus.vuphan.moneykeeper.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.Catalog;

/**
 * Created by monster on 15/06/2016.
 */
public class CreateCatalogDialog extends DialogFragment implements View.OnClickListener {
    Button btnOK, btnCancel;
    EditText edtTitle, edtDescription, edtParrent;

    private final static String INPUT_CATALOG = "input_catalog";

    public static CreateCatalogDialog createInstance(Catalog catalog) {
        CreateCatalogDialog createCatalogDialog = new CreateCatalogDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(INPUT_CATALOG, catalog);
        createCatalogDialog.setArguments(bundle);
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
        edtParrent = (EditText) view.findViewById(R.id.edtParrent);
        edtTitle = (EditText) view.findViewById(R.id.edtTitle);

        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOK:
                OnCreateCatalogOK(edtTitle.getText().toString(), edtDescription.getText().toString(), edtParrent.getText().toString());
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }

    private void OnCreateCatalogOK(String s, String s1, String s2) {
        Long idParrent = s2 == "" ? Long.valueOf("-1") : Long.valueOf(s2);
        Bundle bundle = getArguments();
        Catalog catalog = null;
        if (bundle != null) {
            catalog = (Catalog) bundle.getSerializable(INPUT_CATALOG);
            catalog.setTitle(s);
            catalog.setDescription(s1);
            catalog.setIdParrent(idParrent);
        } else {
            catalog = new Catalog(s, s1,idParrent);
        }

        catalog.save();

    }
}
