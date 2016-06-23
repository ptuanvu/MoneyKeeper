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

import java.util.List;

import hcmus.vuphan.moneykeeper.R;
import hcmus.vuphan.moneykeeper.model.User;

/**
 * Created by Join on 23/06/2016.
 */
public class SignupFragment extends Fragment implements View.OnClickListener{
    static FragmentActivity context;
    EditText editTextuser;
    EditText editTextpassword;
    Button btncreate;
    public void setContext(FragmentActivity context) {
        this.context = context;
    }
    public static SignupFragment createFragment(FragmentActivity context) {
        SignupFragment SF = new SignupFragment();
        SF.setContext(context);

        return SF;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_layout, container, false);
        editTextuser=(EditText)view.findViewById(R.id.editTextusersignup);
        editTextpassword=(EditText)view.findViewById(R.id.editTextpasswordsignup);
        btncreate = (Button)view.findViewById(R.id.btncreate);

        btncreate.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btncreate:
                if (editTextuser.getText().toString().equals("") || editTextpassword.getText().toString().equals("")) {
                    Toast.makeText(context, "Vui lòng điền đầy đủ tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
            List<User> alluser = User.listAll(User.class);
            boolean kttrung = false;
            for (User user : alluser) {
                if (user.getUsername().equals(editTextuser.getText().toString())) {
                    kttrung = true;
                    break;
                }
            }
            if (kttrung) {
                Toast.makeText(context, "Tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            } else {

                User temp = new User(editTextuser.getText().toString(), editTextpassword.getText().toString());
                temp.save();
                LoginFragment lf = new LoginFragment();
                FragmentTransaction ft = context.getFragmentManager().beginTransaction();
                ft.replace(R.id.contentFrameLayout, lf);
                ft.addToBackStack(null);
                ft.commit();
            }
        }
    }
}
