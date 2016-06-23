package hcmus.vuphan.moneykeeper;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.orm.SugarContext;

import hcmus.vuphan.moneykeeper.model.Catalog;
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

        //create database here
        SharedPreferences initialPref = getSharedPreferences("INITIAL", 0);
        boolean firsttimer = initialPref.getBoolean("INITIAL", false);
        if (!firsttimer) {
            InitCatalog();

            //get boolean preference to true
            SharedPreferences.Editor editorPref = initialPref.edit();
            editorPref.putBoolean("INITIAL", true);
            editorPref.commit();
        }



        cameraFragment = CameraFragment.createInstance(this);
        getFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, cameraFragment).commit();
    }

    public void InitCatalog() {
        Catalog c1 = new Catalog("Ăn uống", "Nhu cầu ăn uống hằng ngày.", -1L);
        Long id = c1.save();
        Catalog b11 = new Catalog("Nhà hàng", "", id);
        Catalog b12 = new Catalog("Cafe", "", id);
        b11.save();
        b12.save();

        Catalog c2 = new Catalog("Hóa đơn", "Thanh toán các loại hóa đơn.", -1L);
        id = c2.save();
        Catalog b21 = new Catalog("Điện thoại Cố định", "", id);
        Catalog b22 = new Catalog("Tiền Nước", "", id);
        Catalog b23 = new Catalog("Mạng internet", "", id);
        Catalog b24 = new Catalog("Tiền nhà", "", id);
        b21.save();
        b22.save();
        b23.save();
        b24.save();

        Catalog c3 = new Catalog("Cá nhân", "Các nhu cầu cá nhân.", -1L);
        id = c3.save();
        Catalog b31 = new Catalog("Điện thoại di động", "", id);

       // Catalog c4 = new Catalog("Liên lạc", "Các nhu cầu liên lạc", -1L);

        Catalog c5 = new Catalog("Giao thông", "Các nhu cầu về đi lại.", -1L);
        id = c5.save();
        Catalog b51 = new Catalog("Taxi", "", id);
        Catalog b52 = new Catalog("Xe ôm", "", id);
        Catalog b53 = new Catalog("Giữ xe", "", id);
        b51.save();
        b52.save();
        b53.save();


        Catalog c6 = new Catalog("Mua sắm", "Nhu cầu mua sắm đồ.", -1L);
        id = c6.save();
        Catalog b61 = new Catalog("Thời trang", "", id);
        Catalog b62 = new Catalog("Gia dụng", "", id);
        Catalog b63 = new Catalog("Đồ dùng", "", id);
        b61.save();
        b62.save();
        b63.save();

        Catalog c7 = new Catalog("Bạn bè và tình yêu", "Nhu cầu cho các mối quan hệ trong cuộc sống.", -1L);
        id = c7.save();


        Catalog c8 = new Catalog("Giải trí", "Các nhu cầu về giải trí", -1L);
        id = c8.save();
        Catalog b81 = new Catalog("Xem phim", "", id);
        Catalog b82 = new Catalog("Game", "", id);
        b81.save();
        b82.save();

        Catalog c9 = new Catalog("Sức khỏe", "Nhu cầu về sức khỏe.", -1L);
        id = c9.save();
        Catalog b91 = new Catalog("Thể thao", "", id);
        Catalog b92 = new Catalog("Bác sĩ", "", id);
        Catalog b93 = new Catalog("Thuốc bệnh", "", id);
        b91.save();
        b92.save();
        b93.save();

        Catalog c10 = new Catalog("Quà tặng", "Nhu cầu tặng quà cho người khác", -1L);
        id = c10.save();
        Catalog b101 = new Catalog("Đám cưới", "", id);
        Catalog b102 = new Catalog("Sinh nhật", "", id);
        b101.save();
        b102.save();

        Catalog c11 = new Catalog("Học tập", "Nhu cầu về học tập.", -1L);
        id = c11.save();
        Catalog b111 = new Catalog("Sách", "", id);
        Catalog b112 = new Catalog("Học phí", "", id);
        Catalog b113 = new Catalog("Đồ dùng học tập", "", id);
        b111.save();
        b112.save();
        b113.save();

        Catalog c12 = new Catalog("Các khoản chi khác", "", -1L);
        id = c12.save();

        Catalog c13 = new Catalog("Đầu tư", "Các khoản đầu tư", -1L);
        id = c13.save();

        Catalog c14 = new Catalog("", "", -1L);
        id = c14.save();

        Catalog c15 = new Catalog("", "", -1L);
        Catalog c16 = new Catalog("", "", -1L);
    }

    private void CreateListenerEvent() {

    }

    private void InitID() {
        contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrameLayout);

    }


}
