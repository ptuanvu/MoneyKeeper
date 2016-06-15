package hcmus.vuphan.moneykeeper;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by monster on 15/06/2016.
 */
public class Book extends SugarRecord {
    @Unique
    String isbn;
    String title;
    String edition;

    // Default constructor is necessary for SugarRecord
    public Book() {

    }

    public Book(String isbn, String title, String edition) {
        this.isbn = isbn;
        this.title = title;
        this.edition = edition;
    }

    @Override
    public String toString() {
        return title;
    }
}
