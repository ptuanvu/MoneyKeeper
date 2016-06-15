package hcmus.vuphan.moneykeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.orm.SugarContext;

import hcmus.vuphan.moneykeeper.scences.CameraFragment;
import hcmus.vuphan.moneykeeper.scences.CatalogFragment;

public class MainActivity extends AppCompatActivity {


    FrameLayout contentFrameLayout;
    CameraFragment cameraFragment;
    CatalogFragment catalogFragment;

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



        catalogFragment = CatalogFragment.createFragment(this);
        getFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, catalogFragment).commit();
    }

    private void CreateListenerEvent() {

    }

    private void InitID() {
        contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrameLayout);

    }


}
