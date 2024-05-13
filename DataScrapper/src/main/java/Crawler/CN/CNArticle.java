package Crawler.CN;

import Crawler.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CNArticle extends Article {
    public CNArticle(String url, String pictureLink) {
        super(url, pictureLink);
    }


    @Override
    public Elements createFile(String url) throws IOException {
        Document document = Jsoup.connect(url).ignoreHttpErrors(true).get();
        return document.getElementsByClass("col-12 col-md-9 section-post-width mb-40");
    }

    @Override
    public String getCategory() throws IOException {
        Elements article = createFile(getUrl());
        Elements links = article.select("div.article-tag-box.text-lg-right > a");
        StringBuilder categories = new StringBuilder();
        for (Element link : links) {
            if (categories.length() > 0) {
                categories.append(", ");
            }
            categories.append(link.text());
        }
        return categories.toString();
    }

    @Override
    public String getAuthor() throws IOException {
        Elements article = createFile(getUrl());
        Elements author = article.select("div.author-title > a");
        if(!author.isEmpty()){
            return author.text();
        }
        return null;
    }

    @Override
    public String getCreationDate() throws IOException {
        Elements article = createFile(getUrl());
        Elements day = article.select("div.fs-14.date-section > time");
        if(!day.isEmpty()){
            String dateText = day.text();
            try {
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm z");
                LocalDateTime dateTime = LocalDateTime.parse(dateText, inputFormatter);
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
                return dateTime.format(outputFormatter);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }


    @Override
    public String getContent() throws IOException {
        Elements article = createFile(getUrl());
        String content = "";
        Elements paragraphs = article.select("div > p");
        for (Element paragraph: paragraphs){
            content += paragraph.text();
            content += "\n";
        }
        return content;
    }

    @Override
    public String getTitle() throws IOException {
        Elements article = createFile(getUrl());
        if (!article.select("h1.mb-10").isEmpty()){
            return article.select("h1.mb-10").text();
        }
        return null;
    }
    @Override
    public String getPicture() {
        return getPictureLink();
    }

    @Override
    public String getWebsiteSource() {
        return "https://cryptonews.com";
    }

    @Override
    public String getType() throws IOException {
        return "News";
    }

    @Override
    public List<String> getReference() throws IOException {
        Elements article = createFile(getUrl());
        Elements paragraphs = article.select("p");
        List<String> links = new ArrayList<>();

        for (Element paragraph: paragraphs) {

            String link = paragraph.select("a").attr("href");
            if (!link.isEmpty()) {
                links.add(link);
            }
        }
        return links;
    }
}
