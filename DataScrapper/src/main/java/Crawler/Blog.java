package Crawler;

import Crawler.BCN.BCNArticle;
import Crawler.BLog101.Blog101Article;
import Crawler.CN.CNArticle;
import Crawler.ETHNews.ETHNewsArticle;
import Crawler.PotatoNews.PotatoNewsArticle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.List;

public class Blog {
    public final String url;

    public final String pictureLink;

    public Blog(String url, String pictureLink) {
        this.url = url;
        this.pictureLink = pictureLink;
    }

    public static SingleArticle getSingleArticleBlog101(String url, String pictureLink) throws IOException {
        Article blogs = new Blog101Article(url, pictureLink);
        return getSingleArticle(blogs);
    }

    public static SingleArticle getSingleArticleBCN(String url, String pictureLink) throws IOException {
        Article blogs = new BCNArticle(url, pictureLink);
        return getSingleArticle(blogs);
    }

    public static SingleArticle getSingleArticleCN(String url, String pictureLink) throws IOException {
        Article blogs = new CNArticle(url, pictureLink);
        return getSingleArticle(blogs);
    }

    public static SingleArticle getSingleArticleETHNews(String url, String pictureLink) throws IOException {
        Article blogs = new ETHNewsArticle(url, pictureLink);
        return getSingleArticle(blogs);
    }

    public static SingleArticle getSingleArticlePotatoNews(String url, String pictureLink) throws IOException {
        Article blogs = new PotatoNewsArticle(url, pictureLink);
        return getSingleArticle(blogs);
    }

    public static SingleArticle getSingleArticle(Article article) throws IOException {

        String url = article.getUrl();
        String creationDate = article.getCreationDate();
        String title = article.getTitle();
        String author = article.getAuthor();
        String content = article.getContent();
        String websiteSource = article.getWebsiteSource();
        String type = article.getType();
        String category = article.getCategory();
        List<String> referenceLink = article.getReference();
        String pictureLink = article.getPicture();

        if (title != null & creationDate != null & author != null & type != null & category != null & content != null & pictureLink != null) {
            return new SingleArticle(url, pictureLink, websiteSource, type, title, category, author, creationDate, content,referenceLink);
        }
        return null;
    }

    public static void WriteToJson(List<SingleArticle> articleList, String filename) throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(articleList);

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(filename), true))) {
            writer.println(json);
        }
    }
}
