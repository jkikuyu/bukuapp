package ke.co.zeno.bukuapp.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.model.Book;

/**
 * @author  Jude Kikuyu
 * date: 30/11/2017
 * ref:http://www.viralandroid.com/2016/02/android-listview-with-image-and-text.html
 */

public class BookSelectedActivity extends AppCompatActivity {

    @BindView(R.id.book_image)
    ImageView book_Image;

       @Override
        protected void onCreate(Bundle savedInstanceState) {

           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_book_selected);
           List<Book> bookList = Collections.emptyList();
           Bundle extra = getIntent().getExtras();
           // get book object and extract details
           Book book = (Book) extra.get("book");
           String title = book.getTitle();
           String price = book.getPrice().toString();

           String image = book.getImage();
           TextView bookTitle = (TextView) findViewById(R.id.book_title);
           bookTitle.setText(title);
           TextView bookPrice = (TextView) findViewById(R.id.book_price);
           bookPrice.setText(price);
           bookPrice.setText("KES: " + book.getPrice().toString());

           Uri uri = Uri.fromFile(new File(book.getImage()));
           Glide.with(this).load(Uri.parse("file:///android_asset/"+image)).into(book_Image);
           //ListView  selectedBooks = (ListView) findViewById(R.id.selectedBooks);
           //selectedBooks.setAdapter(adapter);
        }

}
