package hcmus.vuphan.moneykeeper.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.Date;

/**
 * Created by monster on 23/06/2016.
 */
@Table
public class ChiTieuThang extends SugarRecord {
    int soTienToiDa;
    Date thoiGian;

    public ChiTieuThang() {
        this.soTienToiDa = 0;
    }

    public ChiTieuThang(int soTienToiDa, Date thoiGian) {
        this.soTienToiDa = soTienToiDa;
        this.thoiGian = thoiGian;
    }

    public int getSoTienToiDa() {
        return soTienToiDa;
    }

    public void setSoTienToiDa(int soTienToiDa) {
        this.soTienToiDa = soTienToiDa;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }
}
