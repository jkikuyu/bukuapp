package ke.co.zeno.bukuapp.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.model.Stream;

/**
 * Created by nimda on 09/10/17.
 */

public class StreamHelperAdapter extends RecyclerView.Adapter<StreamHelperAdapter.StreamViewHolder> {
    Context context;
    List <Stream> streamList;
    public StreamHelperAdapter(Context context){
        this.context = context;
        streamList = new ArrayList<>();
    }
    public void updateList(List<Stream> streamList) {
        this.streamList = streamList;
        notifyDataSetChanged();
    }




    @Override
    public StreamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stream_list_row, parent, false);
        StreamViewHolder viewHolder = new StreamViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StreamViewHolder holder, int position) {

        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return streamList.size();
    }

    public class StreamViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.streamIcon)
        ImageView streamIcon;
        @BindView(R.id.streamName)
        TextView streamName;

        public StreamViewHolder(View streamView) {
            super(streamView);
            ButterKnife.bind(this, streamView);

        }
        public void onBind(final int position) {
            final Stream stream = streamList.get(position);
            streamName.setText(stream.name);
            Glide.with(context)
                    .load(stream.drawable)
                    .into(streamIcon);
        }
    }

}
