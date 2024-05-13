package Crawler;

import Crawler.BCN.BCNArticle;
import Crawler.BLog101.Blog101Article;
import Crawler.CN.CNArticle;
import Crawler.ETHNews.ETHNewsArticle;
import Crawler.PotatoNews.PotatoNewsArticle;

import java.io.IOException;
import java.util.List;

public class Blog {
    public final String url;

    public final String pictureLink;

    public Blog(String url, String pictureLink) {
        this.url = url;
        this.pictureLink = pictureLink;
    }

    public static SingleArticle getSingleArticleBlog101(String url, String pictureLink) throws IOException {
        Blog101Article blogs = new Blog101Article(url, pictureLink);
        return getSingleArticle(blogs);
    }
    public static SingleArticle getSingleArticleBCN(String url, String pictureLink) throws IOException {
        BCNArticle blogs = new BCNArticle(url, pictureLink);
        return getSingleArticle(blogs);
    }
    public static SingleArticle getSingleArticleCN(String url, String pictureLink) throws IOException {
        CNArticle blogs = new CNArticle(url, pictureLink);
        return getSingleArticle(blogs);
    }
    public static SingleArticle getSingleArticleETHNews(String url, String pictureLink) throws IOException {
        Article blogs = new ETHNewsArticle(url, pictureLink);
        return getSingleArticle(blogs);
    }

    public static SingleArticle getSingleArticlePotatoNews(String url, String pictureLink) throws IOException {
        PotatoNewsArticle blogs = new PotatoNewsArticle(url, pictureLink);
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

        if (title != null & creationDate != null & author != null & type!= null & category != null & content != null & pictureLink != null) {
            return new SingleArticle(websiteSource, type, title, category, author, url, creationDate, content, referenceLink, pictureLink);
        }
        return null;
    }

}
