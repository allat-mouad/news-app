package ma.enset.news_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ma.enset.news_app.models.Article;

public class DetailsActivity extends AppCompatActivity {
    Article article;
    TextView textViewTitle;
    TextView textViewAuthor;
    TextView textViewTime;
    TextView textViewDetail;
    TextView textViewContent;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        textViewTitle=findViewById(R.id.textDetailTitle);
        textViewAuthor=findViewById(R.id.textDetailAthor);
        textViewDetail=findViewById(R.id.textDetaildetail);
        textViewTime=findViewById(R.id.textDetailTIME);
        textViewContent=findViewById(R.id.textDetailContent);
        imageView=findViewById(R.id.imageDetail);

        article= (Article) getIntent().getSerializableExtra("data");

        textViewTitle.setText(article.getTitle());
        textViewAuthor.setText(article.getAuthor());
        textViewTime.setText(article.getPublishedAt());
        textViewContent.setText(article.getContent());
        textViewDetail.setText(article.getDescription());
        Picasso.get().load(article.getUrlToImage()).into(imageView);

    }
}