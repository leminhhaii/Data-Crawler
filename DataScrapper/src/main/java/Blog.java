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

        String creationDate = blogs.getCreationDate();
        String title = blogs.getTitle();
        String author = blogs.getAuthor();
        String content = blogs.getContent();
        String websiteSource = blogs.getWebsiteSource();
        String type = blogs.getType();
        String category = blogs.getCategory();
        List<String> referenceLink = blogs.getReference();

        if (title != null & creationDate != null & author != null & type!= null & category != null & content != null & pictureLink != null) {
            return new SingleArticle(websiteSource, type, title, category, author, url, creationDate, content, referenceLink, pictureLink);
        }
        return null;
    }
    public static SingleArticle getSingleArticleBCN(String url, String pictureLink) throws IOException {
        BCNArticle blogs = new BCNArticle(url, pictureLink);

        String creationDate = blogs.getCreationDate();
        String title = blogs.getTitle();
        String author = blogs.getAuthor();
        String content = blogs.getContent();
        String websiteSource = blogs.getWebsiteSource();
        String type = blogs.getType();
        String category = blogs.getCategory();
        List<String> referenceLink = blogs.getReference();

        if (title != null & creationDate != null & author != null & type!= null & category != null & content != null & pictureLink != null) {
            return new SingleArticle(websiteSource, type, title, category, author, url, creationDate, content, referenceLink, pictureLink);
        }
        return null;
    }
    public static SingleArticle getSingleArticleCN(String url, String pictureLink) throws IOException {
        CNArticle blogs = new CNArticle(url, pictureLink);

        String creationDate = blogs.getCreationDate();
        String title = blogs.getTitle();
        String author = blogs.getAuthor();
        String content = blogs.getContent();
        String websiteSource = blogs.getWebsiteSource();
        String type = blogs.getType();
        String category = blogs.getCategory();
        List<String> referenceLink = blogs.getReference();

        if (title != null & creationDate != null & author != null & type!= null & category != null & content != null & pictureLink != null) {
            return new SingleArticle(websiteSource, type, title, category, author, url, creationDate, content, referenceLink, pictureLink);
        }
        return null;
    }
    public static SingleArticle getSingleArticleETHNews(String url, String pictureLink) throws IOException {
        ETHNewsArticle blogs = new ETHNewsArticle(url, pictureLink);

        String creationDate = blogs.getCreationDate();
        String title = blogs.getTitle();
        String author = blogs.getAuthor();
        String content = blogs.getContent();
        String websiteSource = blogs.getWebsiteSource();
        String type = blogs.getType();
        String category = blogs.getCategory();
        List<String> referenceLink = blogs.getReference();

        if (title != null & creationDate != null & author != null & type!= null & category != null & content != null & pictureLink != null) {
            return new SingleArticle(websiteSource, type, title, category, author, url, creationDate, content, referenceLink, pictureLink);
        }
        return null;
    }

    public static SingleArticle getSingleArticlePotatoNews(String url, String pictureLink) throws IOException {
        PotatoNewsArticle blogs = new PotatoNewsArticle(url, pictureLink);

        String creationDate = blogs.getCreationDate();
        String title = blogs.getTitle();
        String author = blogs.getAuthor();
        String content = blogs.getContent();
        String websiteSource = blogs.getWebsiteSource();
        String type = blogs.getType();
        String category = blogs.getCategory();
        List<String> referenceLink = blogs.getReference();

        if (title != null & creationDate != null & author != null & type!= null & category != null & content != null & pictureLink != null) {
            return new SingleArticle(websiteSource, type, title, category, author, url, creationDate, content, referenceLink, pictureLink);
        }
        return null;
    }

}
