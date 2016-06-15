package hcmus.vuphan.moneykeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.orm.SugarContext;

public class MainActivity extends AppCompatActivity {

    TextView tvShowStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);

        tvShowStatus = (TextView) findViewById(R.id.tvShowStatus);
        Book a = new Book("ABC", "My Book Test", "Phan Vu");
        a.save();

        tvShowStatus.setText(Book.listAll(Book.class).get(0).toString());
    }
}
