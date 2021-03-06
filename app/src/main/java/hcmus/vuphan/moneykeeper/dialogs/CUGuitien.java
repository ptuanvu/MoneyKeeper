package hcmus.vuphan.moneykeeper.dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.Wallet;

/**
 * Created by Tam Le on 24/06/2016.
 */
public class CUGuitien extends DialogFragment{
    EditText edttiengui;
    Button btnokguitien,btncancelguitien;
    public static CUGuitien concreateInstance(Wallet cur, Context context) {
        CUGuitien cuGuitien = new CUGuitien();

        return cuGuitien;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rutguitien_layout, container, false);
        edttiengui = (EditText)view.findViewById(R.id.edtTiengui);
        btnokguitien = (Button)view.findViewById(R.id.btnokguitien);
        btncancelguitien =(Button)view.findViewById(R.id.btncancelguitien);

        btnokguitien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wallet wallet = Wallet.first(Wallet.class);
                int sotien = Integer.parseInt(edttiengui.getText().toString());
                wallet.themTiendutru(sotien);
                wallet.save();
                dismiss();
            }
        });
        btncancelguitien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }
}
