package ke.co.zeno.bukuapp.ui.main.adapter;

import android.content.Context;
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
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.model.Book;
import ke.co.zeno.bukuapp.ui.main.adapter.BookHelperAdapter.ClickListener;

/**
 * Created by nimda on 29/12/17.
 */

public class BookListHelperAdapter extends RecyclerView.Adapter<BookListHelperAdapter.BookListViewHolder> {
    List <Book> bookList = Collections.emptyList();
    Context context;
    ClickListener clickListener;


    public void updateList(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(BookListViewHolder holder, int position) {
        holder.onBind(position);

    }

    @Override
    public BookListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_list_row, parent, false);
        BookListViewHolder viewHolder = new BookListViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class BookListViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        @BindView(R.id.bookIcon)
        ImageView bookIcon;
        @BindView(R.id.bookTitle)
        TextView bookTitle;
        @BindView(R.id.bookPrice)
        TextView bookPrice;

        public BookListViewHolder(View bookListView) {
            super(bookListView);
            bookListView.setOnClickListener(this);
            ButterKnife.bind(this, bookListView);

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
}
