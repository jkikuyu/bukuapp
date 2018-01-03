package ke.co.zeno.bukuapp.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.zeno.bukuapp.R;
import ke.co.zeno.bukuapp.model.Subject;

/**
 * @author Jude Kikuyu
 * date: 09/10/17.
 */

public class SubjectHelperAdapter extends RecyclerView.Adapter<SubjectHelperAdapter.SubjectViewHolder> {
    Context context;
    ClickListener clickListener;
    List <Subject> subjectList = Collections.emptyList();
    public SubjectHelperAdapter(Context context){
        this.context = context;
        subjectList = new ArrayList<>();
    }
    public void updateList(List<Subject> subjectList) {
        this.subjectList = subjectList;
        notifyDataSetChanged();
    }




    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subject_list_row, parent, false);
        SubjectViewHolder viewHolder = new SubjectViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {

        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        @BindView(R.id.subjectIcon)
        ImageView subjectIcon;
        @BindView(R.id.subjectName)
        TextView subjectName;

        public SubjectViewHolder(View subjectView) {
            super(subjectView);
            subjectView.setOnClickListener(this);
            ButterKnife.bind(this, subjectView);

        }
        public void onBind(final int position) {
            final Subject subject = subjectList.get(position);
            subjectName.setText(subject.name);
            Glide.with(context)
                    .load(subject.drawable)
                    .into(subjectIcon);
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
