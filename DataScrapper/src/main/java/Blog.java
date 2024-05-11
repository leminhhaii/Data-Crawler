import java.io.IOException;
import java.util.List;

public class Blog {
    public final String url;

    public Blog(String url) {
        this.url = url;
    }

    
    public static SingleArticle getSingleArticleBlog101(String url) throws IOException {
        Blog101Article blogs = new Blog101Article(url);

        String creationDate = blogs.getCreationDate();
        String title = blogs.getTitle();
        String author = blogs.getAuthor();
        String content = blogs.getContent();
        String websiteSource = blogs.getWebsiteSource();
        String type = blogs.getType();
        String category = blogs.getCategory();
        List<String> referenceLink = blogs.getReference();

        if (title != null & creationDate != null & author != null & type!= null & category != null & content != null) {
            return new SingleArticle(websiteSource, type, title, category, author, url, creationDate, content, referenceLink);
        }
        return null;
    }
    public static SingleArticle getSingleArticleBCN(String url) throws IOException {
        BCNArticle blogs = new BCNArticle(url);

        String creationDate = blogs.getCreationDate();
        String title = blogs.getTitle();
        String author = blogs.getAuthor();
        String content = blogs.getContent();
        String websiteSource = blogs.getWebsiteSource();
        String type = blogs.getType();
        String category = blogs.getCategory();
        List<String> referenceLink = blogs.getReference();

        if (title != null & creationDate != null & author != null & type!= null & category != null & content != null) {
            return new SingleArticle(websiteSource, type, title, category, author, url, creationDate, content, referenceLink);
        }
        return null;
    }
    public static SingleArticle getSingleArticleCN(String url) throws IOException {
        CNArticle blogs = new CNArticle(url);

        String creationDate = blogs.getCreationDate();
        String title = blogs.getTitle();
        String author = blogs.getAuthor();
        String content = blogs.getContent();
        String websiteSource = blogs.getWebsiteSource();
        String type = blogs.getType();
        String category = blogs.getCategory();
        List<String> referenceLink = blogs.getReference();

        if (title != null & creationDate != null & author != null & type!= null & category != null & content != null) {
            return new SingleArticle(websiteSource, type, title, category, author, url, creationDate, content, referenceLink);
        }
        return null;
    }
}
