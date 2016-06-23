package hcmus.vuphan.moneykeeper.model;

import com.orm.SugarRecord;

/**
 * Created by Join on 23/06/2016.
 */
public class wallet extends SugarRecord {
    private  Long id;
    String name;
    String tienhientai;
    String tiendutru;
    String tientietkiem;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTienhientai() {
        return tienhientai;
    }

    public void setTienhientai(String tienhientai) {
        this.tienhientai = tienhientai;
    }

    public String getTiendutru() {
        return tiendutru;
    }

    public void setTiendutru(String tiendutru) {
        this.tiendutru = tiendutru;
    }

    public String getTientietkiem() {
        return tientietkiem;
    }

    public void setTientietkiem(String tientietkiem) {
        this.tientietkiem = tientietkiem;
    }

    public Long getId() {
        return id;
    }

    public wallet() {
    }

    public wallet(String ten, String hientai, String dutru, String tietkiem) {
        name = ten;
        tienhientai= hientai;
        tiendutru = dutru;
        tientietkiem = tietkiem;
    }
    @Override
    public String toString() {
        return "hello";
    }
}
