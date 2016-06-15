package hcmus.vuphan.moneykeeper.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by monster on 15/06/2016.
 */
public class Catalog extends SugarRecord implements Serializable{

    public Catalog() {}

    String title, description;
    Long idParrent;

    public Catalog(String title, String description) {
        this.title = title;
        this.description = description;
        idParrent = Long.parseLong("-1");
    }

    public Catalog(String title, String description, Long idParrent) {
        this.title = title;
        this.description = description;
        this.idParrent = idParrent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdParrent() {
        return idParrent;
    }

    public void setIdParrent(Long idParrent) {
        this.idParrent = idParrent;
    }
}
