package ma.enset.news_app;

import java.util.List;

import ma.enset.news_app.models.Article;

public interface OnfetchDataListener<ListArticleResponce> {
     void onFetchData(List<Article> list,String message);
     void onError(String message);
}
