package hcmus.vuphan.moneykeeper.model;

import com.orm.SugarRecord;

/**
 * Created by monster on 23/06/2016.
 */
public class Wallet extends SugarRecord {
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

    public Wallet() {
    }

    public Wallet(String ten, String hientai, String dutru, String tietkiem) {
        name = ten;
        tienhientai= hientai;
        tiendutru = dutru;
        tientietkiem = tietkiem;
    }
    public boolean rutTiendutru(int sotien)
    {

        if(Integer.parseInt(this.tiendutru) < sotien){
            return  false;
        }
        int tempdutru = (Integer.parseInt(this.tiendutru) - sotien);
        this.setTiendutru(Integer.toString(tempdutru));
        int temptienhientai = Integer.parseInt(this.tienhientai)+sotien;
        this.setTienhientai(Integer.toString(temptienhientai));
        return true;
    }
    public boolean themTiendutru(int sotien)
    {

        if(Integer.parseInt(this.tienhientai) < sotien){
            return  false;
        }
        int temptienhientai = Integer.parseInt(this.tienhientai)-sotien;
        this.setTienhientai(Integer.toString(temptienhientai));
        int tempdutru = (Integer.parseInt(this.tiendutru) + sotien);
        this.setTiendutru(Integer.toString(tempdutru));
        return true;
    }
    public void Thanhtoan(int sotien){
        int temp = Integer.parseInt(this.tienhientai) + sotien;
        this.setTienhientai(Integer.toString(temp));
    }

    @Override
    public String toString() {
        return "hello";
    }
}