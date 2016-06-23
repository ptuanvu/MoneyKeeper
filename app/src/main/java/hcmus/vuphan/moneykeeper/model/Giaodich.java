package hcmus.vuphan.moneykeeper.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

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
    public Giaodich()
    {

    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public Giaodich(String ID_HinhAnh,String ID_Thang,String ID_Catalog,String TenGiaoDich,String MoTaGiaoDich,String DiaDiem,Date ThoiGian,String GhiChu,String GiaoDichCoDinh)
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
