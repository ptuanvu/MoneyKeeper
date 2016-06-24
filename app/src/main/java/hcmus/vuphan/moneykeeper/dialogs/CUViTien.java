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
import android.widget.Toast;

import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.Wallet;

/**
 * Created by Tam Le on 24/06/2016.
 */
public class CUViTien extends DialogFragment {
    EditText tenvi ;
    EditText hientai;
    EditText dutru;
    EditText tietkiem;
    Button upd;
    Button back;
    Button btnruttien, btnguitien;
    List<Wallet> temp;

    public static CUViTien concreateInstance(Wallet cur, Context context) {
        CUViTien cuViTien = new CUViTien();

        return cuViTien;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.showwallet_layout, container, false);
        tenvi = (EditText) view.findViewById(R.id.namewallet);
        hientai = (EditText) view.findViewById(R.id.curmoneyitem);
        dutru = (EditText) view.findViewById(R.id.resmoneyitem);
        tietkiem = (EditText) view.findViewById(R.id.savingmoneyitem);
        upd = (Button) view.findViewById(R.id.updatebt);
        back = (Button)view.findViewById(R.id.btbacklistwallet);
        btnruttien=(Button)view.findViewById(R.id.btnruttien);
        btnguitien=(Button)view.findViewById(R.id.btnguitien);

        Wallet temp= Wallet.first(Wallet.class);
        if(temp!=null)
        {
            tenvi.setText(temp.getName());
            hientai.setText(temp.getTienhientai());
            dutru.setText(temp.getTiendutru());
            tietkiem.setText(temp.getTientietkiem());
        }

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upname = tenvi.getText().toString();
                String uphientai = hientai.getText().toString();
                String updutru = dutru.getText().toString();
                String uptietkiem = tietkiem.getText().toString();
                if (!tryParseInt(uphientai)||!tryParseInt(updutru)||!tryParseInt(uptietkiem))
                {
                    Toast.makeText(getDialog().getContext(), "Giá trị nhập vào phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }
                Wallet temp= Wallet.first(Wallet.class);
                if(temp!=null)
                {
                    tenvi.setText(temp.getName());
                    hientai.setText(temp.getTienhientai());
                    dutru.setText(temp.getTiendutru());
                    tietkiem.setText(temp.getTientietkiem());
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnguitien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        btnruttien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        return  view;
    }
    public boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
