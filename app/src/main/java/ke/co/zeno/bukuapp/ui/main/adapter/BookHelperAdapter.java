package ke.co.zeno.bukuapp.ui.main.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.model.Book;

/**
 * Created by nimda on 09/10/17.
 */

public class BookHelperAdapter extends RecyclerView.Adapter<BookHelperAdapter.BookViewHolder> {
    Context context;
    ClickListener clickListener;
    List <Book> bookList = Collections.emptyList();
    Bitmap bitmap;
    public BookHelperAdapter(Context context){
        this.context = context;
        bookList = new ArrayList<>();
    }
    public void updateList(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }




    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_list_row, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        @BindView(R.id.bookIcon)
        ImageView bookIcon;
        @BindView(R.id.bookTitle)
        TextView bookTitle;
        @BindView(R.id.bookPrice)
        TextView bookPrice;

        public BookViewHolder(View bookView) {
            super(bookView);
            bookView.setOnClickListener(this);
            ButterKnife.bind(this, bookView);

        }
        public void onBind(final int position) {
            final Book book = bookList.get(position);
            bookTitle.setText(book.getTitle());
            Uri uri = Uri.fromFile(new File(book.getImage()));
            Glide.with(context).load(Uri.parse("file:///android_asset/"+book.getImage())).into(bookIcon);
            bookPrice.setText("KES: " + book.getPrice().toString());

        }

        @Override
        public void onClick(View v) {
            if (clickListener !=null){
                clickListener.itemClicked(v, getAdapterPosition());
            }
        }
    }
    public interface ClickListener {
        void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

}
