package ma.enset.news_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ma.enset.news_app.models.Article;

public class CustomAdapter extends RecyclerView.Adapter<ArticleActivity> {
    private Context context;
    private List<Article> articleList;
    private SelectListener listener;

    public CustomAdapter(Context context, List<Article> articleList,SelectListener listener) {
        this.context = context;
        this.articleList = articleList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ArticleActivity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleActivity(LayoutInflater.from(context).inflate(R.layout.list_header_items,parent,false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleActivity holder, int position) {
        holder.textTitre.setText(articleList.get(position).getTitle());
        holder.textAuthor.setText(articleList.get(position).getAuthor());
        if(articleList.get(position).getUrlToImage()!=null)
        {
            Picasso.get().load(articleList.get(position).getUrlToImage()).into(holder.imageView);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnNewsClicked(articleList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
