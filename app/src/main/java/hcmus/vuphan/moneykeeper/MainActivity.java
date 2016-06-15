package hcmus.vuphan.moneykeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.orm.SugarContext;

public class MainActivity extends AppCompatActivity {

    TextView tvShowStatus;
    FrameLayout contentFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);

        // Khoi tao ID cho cac thanh phan
        InitID();

        // Khoi tao ham su kien
        CreateListenerEvent();

        tvShowStatus = (TextView) findViewById(R.id.tvShowStatus);
        Book a = new Book("ABC", "My Book Test", "Phan Vu");
        a.save();

        tvShowStatus.setText(Book.listAll(Book.class).get(0).toString());
    }

    private void CreateListenerEvent() {

        
    }

    private void InitID() {
        contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrameLayout);

    }
}
