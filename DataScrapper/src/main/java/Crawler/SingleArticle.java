package Crawler;

import java.util.List;

public class SingleArticle {

    private final String url;
    private final String pictureLink;
    private final String websiteSource;
    private final String type;
    private final String title;
    private final String category;
    private final String author;
    private final String creationDate;
    private final String content;
    private final List<String> referenceLinks;

    public SingleArticle(String url, String pictureLink, String websiteSource, String type, String title, String category, String author, String creationDate, String content, List<String> referenceLinks) {
        this.url = url;
        this.pictureLink = pictureLink;
        this.websiteSource = websiteSource;
        this.type = type;
        this.title = title;
        this.category = category;
        this.author = author;
        this.creationDate = creationDate;
        this.content = content;
        this.referenceLinks = referenceLinks;
    }
}
