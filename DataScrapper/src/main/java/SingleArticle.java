import java.util.List;

public class SingleArticle {

    private final String websiteSource;
    private final String type;
    private final String title;
    private final String category;

    private final String author;
    private final String link;
    private final String creationDate;
    private final String content;
    private final List<String> referenceLinks;

    public SingleArticle(String websiteSource, String type, String title, String category, String author, String link, String creationDate, String content, List<String> referenceLinks) {
        this.websiteSource = websiteSource;
        this.type = type;
        this.title = title;
        this.category = category;
        this.author = author;
        this.link = link;
        this.creationDate = creationDate;
        this.content = content;
        this.referenceLinks = referenceLinks;
    }

    public String getWebsiteSource() {
        return websiteSource;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor() {
        return author;
    }

    public String getLink() {
        return link;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getContent() {
        return content;
    }

    public List<String> getReferenceLinks() {
        return referenceLinks;
    }

}
