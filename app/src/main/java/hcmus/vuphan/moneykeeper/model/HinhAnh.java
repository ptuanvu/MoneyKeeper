package hcmus.vuphan.moneykeeper.model;

import com.orm.SugarRecord;

/**
 * Created by monster on 15/06/2016.
 */
public class HinhAnh extends SugarRecord {
    String URL;

    public HinhAnh() {
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public HinhAnh(String URL) {
        this.URL = URL;
    }
}
