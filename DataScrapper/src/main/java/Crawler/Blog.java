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
        GetArticleInformation blogs = new Blog101Article(url, pictureLink);
        return getSingleArticle(blogs);
    }

    public static SingleArticle getSingleArticleBCN(String url, String pictureLink) throws IOException {
        GetArticleInformation blogs = new BCNArticle(url, pictureLink);
        return getSingleArticle(blogs);
    }

    public static SingleArticle getSingleArticleCN(String url, String pictureLink) throws IOException {
        GetArticleInformation blogs = new CNArticle(url, pictureLink);
        return getSingleArticle(blogs);
    }

    public static SingleArticle getSingleArticleETHNews(String url, String pictureLink) throws IOException {
        GetArticleInformation blogs = new ETHNewsArticle(url, pictureLink);
        return getSingleArticle(blogs);
    }

    public static SingleArticle getSingleArticlePotatoNews(String url, String pictureLink) throws IOException {
        GetArticleInformation blogs = new PotatoNewsArticle(url, pictureLink);
        return getSingleArticle(blogs);
    }

    public static SingleArticle getSingleArticle(GetArticleInformation getArticleInformation) throws IOException {

        String url = getArticleInformation.getUrl();
        String creationDate = getArticleInformation.getCreationDate();
        String title = getArticleInformation.getTitle();
        String author = getArticleInformation.getAuthor();
        String content = getArticleInformation.getContent();
        String websiteSource = getArticleInformation.getWebsiteSource();
        String type = getArticleInformation.getType();
        String category = getArticleInformation.getCategory();
        List<String> referenceLink = getArticleInformation.getReference();
        String pictureLink = getArticleInformation.getPicture();

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
