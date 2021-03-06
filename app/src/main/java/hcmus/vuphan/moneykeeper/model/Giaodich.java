package hcmus.vuphan.moneykeeper.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by Join on 23/06/2016.
 */
public class Giaodich extends SugarRecord implements Serializable{



    private  Long ID;
    String ID_HinhAnh;
    String ID_Thang;
    String ID_Catalog;
    String TenGiaoDich;
    String MoTaGiaoDich;
    String DiaDiem;
    Date ThoiGian;
    String GhiChu;
    String GiaoDichCoDinh;
    String LoaiGiaodich;//gia tri = "Thu" và "Chi"
    int Tongtien;//= âm neu la chi và = dương nếu là thu.
    public Giaodich()
    {}

    public int getTongtien() {
        return Tongtien;
    }

    public void setTongtien(int tongtien) {
        Tongtien = tongtien;
    }

    public String getGiaoDichCoDinh() {
        return GiaoDichCoDinh;
    }

    public String getLoaiGiaodich() {
        return LoaiGiaodich;
    }

    public void setLoaiGiaodich(String loaiGiaodich) {
        LoaiGiaodich = loaiGiaodich;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public static List<Giaodich> GetRandomGiaoDich() {
        List<Giaodich> giaodiches = new ArrayList<Giaodich>();
        List<ChiTieuThang> chiTieuThangs = ChiTieuThang.listAll(ChiTieuThang.class);
        List<Catalog> catalogs = Catalog.listAll(Catalog.class);
        Random r = new Random();
        Calendar calendar = Calendar.getInstance();

        for(int i = 0 ; i < 200; i++) {
            Date thoiGian = calendar.getTime();
            int ngay = r.nextInt(30);
            thoiGian.setDate(ngay);
            int id_catalog = r.nextInt(catalogs.size());
            int id_thang = r.nextInt(chiTieuThangs.size());
            int tongTien = r.nextInt(100000);
            boolean isAm = r.nextBoolean();
            if (isAm) tongTien *= -1;
            Giaodich giaodich = new Giaodich("227 NVC", String.valueOf(catalogs.get(id_catalog).getId()),String.valueOf(chiTieuThangs.get(id_thang).getId()), "Giao dịch thử nghiệm", "Giao dịch thử" + String.valueOf(i),thoiGian, tongTien );
            giaodiches.add(giaodich);
        }

        return giaodiches;
    }

    public Giaodich(String diaDiem, String ID_Catalog, String ID_Thang, String moTaGiaoDich, String tenGiaoDich, Date thoiGian, int tongtien) {
        DiaDiem = diaDiem;
        this.ID_Catalog = ID_Catalog;
        this.ID_Thang = ID_Thang;
        MoTaGiaoDich = moTaGiaoDich;
        TenGiaoDich = tenGiaoDich;
        ThoiGian = thoiGian;
        Tongtien = tongtien;
        this.GhiChu ="";
        this.LoaiGiaodich = "Chi";
        this.ID_HinhAnh = "1";
    }

    public Giaodich(String ID_HinhAnh, String ID_Thang, String ID_Catalog, String TenGiaoDich, String MoTaGiaoDich, String DiaDiem, Date ThoiGian, String GhiChu, String GiaoDichCoDinh, String loaiGiaodich , int tongtien)
    {
        this.ID_HinhAnh=ID_HinhAnh;
        this.ID_Thang=ID_Thang;
        this.ID_Catalog=ID_Catalog;
        this.TenGiaoDich=TenGiaoDich;
        this.MoTaGiaoDich=MoTaGiaoDich;
        this.DiaDiem=DiaDiem;
        this.ThoiGian=ThoiGian;
        this.GhiChu=GhiChu;
        this.GiaoDichCoDinh=GiaoDichCoDinh;
        this.LoaiGiaodich = loaiGiaodich;
        //List<Wallet> walletList = Wallet.listAll(Wallet.class);
        this.Tongtien = tongtien;
        Wallet wallet = Wallet.first(Wallet.class);
        if(this.LoaiGiaodich.equals("Chi")){
            tongtien = -tongtien;
        }
        wallet.Thanhtoan(tongtien);
    }
    public String getID_HinhAnh() { return ID_HinhAnh; }
    public void setID_HinhAnh(String ID_HinhAnh) { this.ID_HinhAnh=ID_HinhAnh; }

    public String getID_Thang()
    {
        return ID_Thang;
    }
    public void setID_Thang(String ID_Thang)
    {
        this.ID_Thang=ID_Thang;
    }

    public String getID_Catalog()
    {
        return ID_Catalog;
    }
    public void setID_Catalog(String ID_Catalog)
    {
        this.ID_Catalog=ID_Catalog;
    }

    public String getTenGiaoDich()
    {
        return TenGiaoDich;
    }
    public void setTenGiaoDich(String TenGiaoDich)
    {
        this.TenGiaoDich=TenGiaoDich;
    }

    public String getMoTaGiaoDich()
    {
        return MoTaGiaoDich;
    }
    public void setMoTaGiaoDich(String MoTaGiaoDich)
    {
        this.MoTaGiaoDich=MoTaGiaoDich;
    }

    public String getDiaDiem()
    {
        return DiaDiem;
    }
    public void setDiaDiem(String DiaDiem)
    {
        this.DiaDiem=DiaDiem;
    }

    public Date getThoiGian()
    {
        return ThoiGian;
    }
    public void setThoiGian(Date ThoiGian)
    {
        this.ThoiGian=ThoiGian;
    }

    public String getGhiChu() { return GhiChu; }
    public void setGhiChu(String GhiChu) { this.GhiChu=GhiChu; }

    public String isGiaoDichCoDinh() { return GiaoDichCoDinh; }
    public void setGiaoDichCoDinh(String GiaoDichCoDinh) { this.GiaoDichCoDinh=GiaoDichCoDinh; }
}
