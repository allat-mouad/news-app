package ma.enset.news_app;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import ma.enset.news_app.models.ListArticleResponce;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit=new Retrofit.Builder().
            baseUrl("https://newsapi.org/v2/").
            addConverterFactory(GsonConverterFactory.
                    create())
            .build();

    public  void  getNews(OnfetchDataListener listener,String category,String titre){
        RestServiceAPI restServiceAPI=retrofit.create(RestServiceAPI.class);
        Call<ListArticleResponce> call =restServiceAPI.listArticles(category,titre,context.getString(R.string.api_key));
        try
        {
            call.enqueue(new Callback<ListArticleResponce>() {
                @Override
                public void onResponse(Call<ListArticleResponce> call, Response<ListArticleResponce> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();

                    }
                    listener.onFetchData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<ListArticleResponce> call, Throwable t) {
                    listener.onError("request failed");
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public RequestManager(Context context) {
        this.context = context;
    }
    public  interface RestServiceAPI{
        @GET("top-headlines")
        Call<ListArticleResponce> listArticles(
                @Query("category") String category,
                @Query("q") String key,
                @Query("apiKey") String api_key
        );
        @GET("everything")
        Call<ListArticleResponce> listArticles2(
                @Query("from") String dateQuery,
                @Query("q") String key,
                @Query("apiKey") String api_key
        );

    }
}
