package ma.enset.news_app;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleActivity extends RecyclerView.ViewHolder {
    TextView textTitre,textAuthor;
    ImageView imageView;
    CardView cardView;
    public ArticleActivity(@NonNull View itemView) {
        super(itemView);
        textTitre=itemView.findViewById(R.id.textViewTitre);
        textAuthor=itemView.findViewById(R.id.textViewAuthor);
        imageView=itemView.findViewById(R.id.imageView);
        cardView=itemView.findViewById(R.id.main_container);


    }
}
