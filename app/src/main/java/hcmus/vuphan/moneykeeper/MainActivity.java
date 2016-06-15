package hcmus.vuphan.moneykeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.orm.SugarContext;

import hcmus.vuphan.moneykeeper.scences.CameraFragment;

public class MainActivity extends AppCompatActivity {


    FrameLayout contentFrameLayout;
    CameraFragment cameraFragment;

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


        cameraFragment = CameraFragment.createInstance(this);
        getFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, cameraFragment).commit();
    }

    private void CreateListenerEvent() {

    }

    private void InitID() {
        contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrameLayout);

    }


}
