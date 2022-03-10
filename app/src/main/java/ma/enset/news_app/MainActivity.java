package ma.enset.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import ma.enset.news_app.models.Article;
import ma.enset.news_app.models.ListArticleResponce;

public class MainActivity extends AppCompatActivity implements  SelectListener {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView=findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestManager manager=new RequestManager(MainActivity.this);
                manager.getNews(listener,"general",query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        RequestManager manager =new RequestManager(this);
        manager.getNews(listener,"general",null);
    }
    private  final  OnfetchDataListener<ListArticleResponce> listener=new OnfetchDataListener<ListArticleResponce>(){

        @Override
        public void onFetchData(List<Article> list, String message) {
           if(list.isEmpty()){
               Toast.makeText(MainActivity.this,"No data found",Toast.LENGTH_SHORT).show();
           }
           else{
               showNews(list);

           }
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<Article> list) {
        recyclerView=findViewById(R.id.recyclerViewArticles);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter= new CustomAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(Article article) {
        startActivity(new Intent(MainActivity.this,DetailsActivity.class)
        .putExtra("data",article));

    }
}