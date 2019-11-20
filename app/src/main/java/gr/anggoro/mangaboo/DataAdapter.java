package gr.anggoro.mangaboo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    private ArrayList<String> titleList = new ArrayList<>();
    private ArrayList<String> authorList = new ArrayList<>();
    private ArrayList<String> dateList = new ArrayList<>();

    private Activity activity;
    private int lastPos = -1;

    public DataAdapter(Activity activity, ArrayList<String> titleList, ArrayList<String> authorList, ArrayList<String> dateList){
        this.activity = activity;
        this.titleList = titleList;
        this.authorList = authorList;
        this.dateList = dateList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvBlogTitle.setText(titleList.get(position));
        holder.tvBlogAuthor.setText(authorList.get(position));
        holder.tvBlogDate.setText(dateList.get(position));
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvBlogTitle, tvBlogAuthor, tvBlogDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBlogTitle = itemView.findViewById(R.id.row_tv_blog_title);
            tvBlogAuthor = itemView.findViewById(R.id.row_tv_blog_author);
            tvBlogDate = itemView.findViewById(R.id.row_tv_blog_upload_date);
        }
    }
}
