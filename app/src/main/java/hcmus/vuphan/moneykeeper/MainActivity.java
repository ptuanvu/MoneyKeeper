package hcmus.vuphan.moneykeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.orm.SugarContext;

import hcmus.vuphan.moneykeeper.scences.AddGdFragment;
import hcmus.vuphan.moneykeeper.scences.CameraFragment;
import hcmus.vuphan.moneykeeper.scences.CapnhatgdFragment;
import hcmus.vuphan.moneykeeper.scences.CatalogFragment;
import hcmus.vuphan.moneykeeper.scences.ListwalletFragment;
import hcmus.vuphan.moneykeeper.scences.LoginFragment;
import hcmus.vuphan.moneykeeper.scences.ShowListGdFragment;
import hcmus.vuphan.moneykeeper.scences.ShowwalletFragment;
import hcmus.vuphan.moneykeeper.scences.SignupFragment;

public class MainActivity extends AppCompatActivity {


    FrameLayout contentFrameLayout;
    CameraFragment cameraFragment;
    CatalogFragment catalogFragment;
    LoginFragment loginFragment;
    SignupFragment signupFragment;
    ListwalletFragment listwalletFragment;
    ShowwalletFragment showwalletFragment;
    ShowListGdFragment showListGdFragment;
    AddGdFragment addGdFragment;
    CapnhatgdFragment capnhatgdFragment;

    public final static String KEY = "camera_instance_restore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);

        // Khoi tao ID cho cac thanh phan
        InitID();

        // Khoi tao ham su kien
        CreateListenerEvent();

        getFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, showListGdFragment).commit();
    }

    private void CreateListenerEvent() {

    }

    private void InitID() {
        contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrameLayout);
        catalogFragment = CatalogFragment.createFragment(this);
        loginFragment = LoginFragment.createFragment(this);
        signupFragment = SignupFragment.createFragment(this);
        listwalletFragment = ListwalletFragment.createFragment(this);
        showwalletFragment = ShowwalletFragment.createFragment(this);
        showListGdFragment = ShowListGdFragment.createInstance(this);
        addGdFragment = AddGdFragment.createInstance(this);
        capnhatgdFragment = CapnhatgdFragment.createInstance(this);
    }


}
