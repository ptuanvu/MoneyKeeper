package hcmus.vuphan.moneykeeper;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hcmus.vuphan.moneykeeper.dialogs.CUChiTieuThang;
import hcmus.vuphan.moneykeeper.dialogs.CUGiaoDich;
import hcmus.vuphan.moneykeeper.dialogs.CUViTien;
import hcmus.vuphan.moneykeeper.model.Catalog;
import hcmus.vuphan.moneykeeper.model.ChiTieuThang;
import hcmus.vuphan.moneykeeper.model.Giaodich;
import hcmus.vuphan.moneykeeper.model.Wallet;
import hcmus.vuphan.moneykeeper.scences.AboutFragment;
import hcmus.vuphan.moneykeeper.scences.CameraFragment;
import hcmus.vuphan.moneykeeper.scences.CatalogFragment;
import hcmus.vuphan.moneykeeper.scences.DanhSachCTT;
import hcmus.vuphan.moneykeeper.scences.LichSuGiaoDich;
import hcmus.vuphan.moneykeeper.scences.LichSuGiaoDichFragment;
import hcmus.vuphan.moneykeeper.scences.ListwalletFragment;
import hcmus.vuphan.moneykeeper.scences.LoginFragment;
import hcmus.vuphan.moneykeeper.scences.ShowListGdFragment;
import hcmus.vuphan.moneykeeper.scences.ShowwalletFragment;
import hcmus.vuphan.moneykeeper.scences.SignupFragment;
import hcmus.vuphan.moneykeeper.scences.ThongKeFragment;
import hcmus.vuphan.moneykeeper.scences.TinhTrangHienTai;

public class MainActivity extends AppCompatActivity implements CUChiTieuThang.ChiTieuThangDiaglogListener, NavigationView.OnNavigationItemSelectedListener , CUGiaoDich.CUGiaoDichListener {

    public final int TINHTRANG = 0;

    int modeFragment = TINHTRANG;

    ImageButton imbExit;
    FrameLayout contentFrameLayout;
    CatalogFragment catalogFragment;
    LoginFragment loginFragment;
    SignupFragment signupFragment;
    ListwalletFragment listwalletFragment;
    ShowwalletFragment showwalletFragment;
    ShowListGdFragment showListGdFragment;
    TinhTrangHienTai tinhTrangHienTai;
    LichSuGiaoDichFragment lichSuGiaoDichFragment;

    //Cac thanh phan giao dien chinh
    Toolbar toolbar = null;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView nvLeftMenu;

    public final static String KEY = "camera_instance_restore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);

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

        global.dfMonthAndYear.setCalendar(Calendar.getInstance());


        // Khoi tao ID cho cac thanh phan
        InitID();

        // Khoi tao ham su kien
        CreateListenerEvent();

        // Cai dat cac thanh phan giao dien
        SettingUpUI();



        Calendar calendar = Calendar.getInstance();
        Date curDate = calendar.getTime();
        int curMonth = curDate.getMonth();

        List<ChiTieuThang> chiTieuThangs = ChiTieuThang.listAll(ChiTieuThang.class);
        Boolean haveChiTieuThang = false;
        for (ChiTieuThang chiTieuThang :
                chiTieuThangs) {
            if (chiTieuThang.getThoiGian().getMonth() == curMonth) {
                haveChiTieuThang = true;
            }
        }

        if (!haveChiTieuThang) {
            CUChiTieuThang cuChiTieuThang = CUChiTieuThang.concreateInstance(null, this);
            cuChiTieuThang.show(getFragmentManager(), "dialog");
            Toast.makeText(MainActivity.this, "Vui lòng tạo kế hoạch chi tiêu cho tháng này!", Toast.LENGTH_SHORT).show();
        } else {
            tinhTrangHienTai = TinhTrangHienTai.createInstance(this);
            getFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, tinhTrangHienTai).commit();

        }

    }

    private void SettingUpUI() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nvLeftMenu.setItemIconTintList(null);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        boolean c = true;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            c = false;
        }
        if (modeFragment != TINHTRANG) {
            TinhTrangHienTai tinhTrangHienTai = TinhTrangHienTai.createInstance(this);
            getFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, tinhTrangHienTai).commit();
            modeFragment = TINHTRANG;
            c = false;
        }

        if (c) super.onBackPressed();
    }

    public void InitGiaoDich() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        for (int i = 0 ; i < 12; i ++) {
            Date ctt = new Date(now.getYear(), i, 1);
            if (i == now.getMonth()) continue;
            ChiTieuThang chiTieuThang = new ChiTieuThang(0, ctt);
            chiTieuThang.save();
        }

        List<Giaodich> gds = Giaodich.GetRandomGiaoDich();
        for (Giaodich gd :
                gds) {
            gd.save();
        }
    }


    private void CreateListenerEvent() {
        nvLeftMenu.setNavigationItemSelectedListener(this);
        imbExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void InitID() {
        contentFrameLayout = (FrameLayout) findViewById(R.id.contentFrameLayout);
        nvLeftMenu = (NavigationView) findViewById(R.id.nvLeftMenu);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        toolbar = (Toolbar) findViewById(R.id.tbMyToolbar);
        catalogFragment = CatalogFragment.createFragment(this);
        loginFragment = LoginFragment.createFragment(this);
        signupFragment = SignupFragment.createFragment(this);
        listwalletFragment = ListwalletFragment.createFragment(this);
        showwalletFragment = ShowwalletFragment.createFragment(this);
        showListGdFragment = ShowListGdFragment.createInstance(this);
        imbExit = (ImageButton) findViewById(R.id.imbExit);
    }

    @Override
    public void OnChiTieuThangDialogFinish() {
        tinhTrangHienTai = TinhTrangHienTai.createInstance(this);
        getFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, tinhTrangHienTai).commit();
        InitGiaoDich();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        modeFragment = 1;
        switch (item.getItemId()) {
            case R.id.mnThemGiaoDich:
                CUGiaoDich cuGiaoDich = CUGiaoDich.concreateInstance(null, this);
                cuGiaoDich.show(getFragmentManager(), "dialog");
                break;
            case R.id.mnCatalog:
                CatalogFragment catalogFragment = CatalogFragment.createFragment(this);
                transaction.replace(R.id.contentFrameLayout, catalogFragment).commit();
                break;
            case R.id.mnChiTieuThang:
                DanhSachCTT danhSachCTT = DanhSachCTT.createFragment(this);
                transaction.replace(R.id.contentFrameLayout, danhSachCTT).commit();
                break;
            case R.id.mnViTien:
                CUViTien cuViTien = CUViTien.concreateInstance(null,this);
                cuViTien.show(getFragmentManager(),"dialog");
                break;
            case R.id.mnLichSuThang:
                lichSuGiaoDichFragment= LichSuGiaoDichFragment.createInstance(this);
                getFragmentManager().beginTransaction().replace(R.id.contentFrameLayout, lichSuGiaoDichFragment).commit();
                break;
            case R.id.mnLichSuGiaoDich:
                LichSuGiaoDich lichSuGiaoDich = LichSuGiaoDich.createFragment(this);
                transaction.replace(R.id.contentFrameLayout, lichSuGiaoDich).commit();
                break;
            case R.id.mnThongKe:
                ThongKeFragment thongKeFrament = ThongKeFragment.createInstance(this);
                getFragmentManager().beginTransaction().replace(R.id.contentFrameLayout,thongKeFrament).commit();
                break;
            case R.id.mnAbout:
                AboutFragment aboutFragment = AboutFragment.concreateFragment(this);
                transaction.replace(R.id.contentFrameLayout, aboutFragment).commit();
                break;
        }

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        return false;
    }


    @Override
    public void OnCUGiaoDichFinish() {
        tinhTrangHienTai.Refresh();
    }


    public void InitCatalog() {
        Wallet newWallet = new Wallet("Ví tiền của tôi", "2000000", "500000", "0");
        newWallet.save();

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

        Catalog c15 = new Catalog("", "", -1L);
        Catalog c16 = new Catalog("", "", -1L);

    }
}
