package Crawler.BLog101;

import Crawler.ArticleInformation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Blog101Article implements ArticleInformation {
    private final String url;
    public final String pictureLink;

    public Blog101Article(String url, String pictureLink) {
        this.url = url;
        this.pictureLink = pictureLink;
    }

    @Override
    public Elements createFile(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        return document.getElementsByClass("col-md-9 col-sm-7");
    }

    @Override
    public String getCategory() throws IOException {
        Elements article = createFile(url);
        return article.select("a.blog-category-green").text();
    }

    @Override
    public String getAuthor() throws IOException {
        Elements article = createFile(url);
        return article.select("li a[href*='/author/']").text();
    }

    @Override
    public String getCreationDate() throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements file = document.select("ul.blog-nav-about");
        return file.select("li:contains(on )").text().replace("on ", "");
    }
    @Override
    public String getContent() throws IOException {
        Elements article = createFile(url);
        String content = "";
        Elements paragraphs = article.select("p");
        for (Element paragraph: paragraphs){
            content += paragraph.text();
            content += "\n";
        }
        return content;
    }


    @Override
    public String getTitle() throws IOException {
        Elements article = createFile(url);
        return article.select("h1.pho-main-heading").text();
    }

    @Override
    public String getWebsiteSource() {
        return "https://101blockchains.com/";
    }

    @Override
    public String getType() {
        return "Blog";
    }

    @Override
    public List<String> getReference() throws IOException {
        Elements article = createFile(url);
        Elements paragraphs = article.select("p");
        List<String> links = new ArrayList<>();

        for (Element paragraph: paragraphs) {
            String link = paragraph.select("a").attr("href");
            if (!link.isEmpty()){
                links.add(link);
            }
        }
        return links;
    }

}

