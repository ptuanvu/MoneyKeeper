package hcmus.vuphan.moneykeeper.scences;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.User;

/**
 * Created by Join on 23/06/2016.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    static FragmentActivity context;
    EditText editTextuserlogin;
    EditText editTextpasswordlogin;
    Button btnlogin;
    Button btnCancel;
    Button btsignupnew;
    public void setContext(FragmentActivity context) {
        this.context = context;
    }
    public static LoginFragment createFragment(FragmentActivity context) {
        LoginFragment LF = new LoginFragment();
        LF.setContext(context);

        return LF;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout, container, false);
        SugarContext.init(context);
        editTextuserlogin = (EditText) view.findViewById(R.id.editTextuserlogin);
        editTextpasswordlogin = (EditText) view.findViewById(R.id.editTextpasswordlogin);
        btnlogin = (Button) view.findViewById(R.id.btnlogin);
        btnCancel=(Button) view.findViewById(R.id.btncancel);
        btsignupnew = (Button) view.findViewById(R.id.btnsignupnew);
        btnlogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btsignupnew.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnlogin:
                loginfunc();
                break;
            case R.id.btncancel: {
                editTextpasswordlogin.setText("");
                editTextuserlogin.setText("");
                break;
            }
            case R.id.btnsignupnew:
                SignupFragment sf = new SignupFragment();
                FragmentTransaction ft = context.getFragmentManager().beginTransaction();
                ft.replace(R.id.contentFrameLayout, sf);
                ft.addToBackStack(null);
                ft.commit();
                break;

        }
    }
    private void loginfunc()
    {
        List<User> alluser = User.listAll(User.class);
        boolean kt = false;
        for (User user:alluser) {
            if(user.getUsername().equals(editTextuserlogin.getText().toString()) && user.getPassword().equals(editTextpasswordlogin.getText().toString()))
            {
                kt=true;
                ListwalletFragment lwf = new ListwalletFragment();
                FragmentTransaction ft = context.getFragmentManager().beginTransaction();
                ft.replace(R.id.contentFrameLayout, lwf);
                ft.addToBackStack(null);
                ft.commit();
            }

        }
        if(kt==false)
        {
            Toast.makeText(context, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
        }
    }
}
