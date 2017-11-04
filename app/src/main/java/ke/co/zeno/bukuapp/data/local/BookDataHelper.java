package ke.co.zeno.bukuapp.data.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ke.co.zeno.bukuapp.data.DataManager;
import ke.co.zeno.bukuapp.data.DatabaseHelper;
import ke.co.zeno.bukuapp.model.Book;

/**
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */

public class BookDataHelper implements DataManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    public static final String TABLE_NAME = "Book";

    public BookDataHelper(DatabaseHelper dbHelper){
        this.dbHelper = dbHelper;
        db = dbHelper.getDb();
    }
    public List<Book> getItemList() {
            String sql = "select * from Book;";
            Cursor items = fetch(sql);
            List<Book> bookList = new ArrayList<>();

        do{
              Book book = new Book();

                book.setTitle(items.getString(1));
                book.setDescription(items.getString(2));
                book.setEdition(items.getInt(3));
                book.setLevel(items.getInt(4));
                book.setPrice(items.getDouble(5));
                book.setImage(items.getString(6));
                Log.d("test", book.getImage());
                bookList.add(book);

            } while (items.moveToNext()); //move to next row in the query result
        return bookList;
    }


    @Override
    public void insert(String fields, String values) {

    }

    @Override
    public Cursor fetch(String qry) {


        Cursor cursor = db.rawQuery(qry, null);

        if (cursor != null ) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    @Override
    public int update(long _id, String name, String desc) {
        return 0;
    }

    @Override
    public void delete(long _id) {

    }

}
