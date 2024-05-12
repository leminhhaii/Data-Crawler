package Crawler.BCN;

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


public class BCNArticle  implements ArticleInformation {
    private final String url;
    private final String pictureLink;
    public BCNArticle(String url, String pictureLink) {
        this.url = url;
        this.pictureLink = pictureLink;
    }

    @Override
    public String getWebsiteSource() {
        return "https://blockchain.news";
    }

    @Override
    public String getType() throws IOException {
        Elements article = createFilehead(url);
        Elements type = article.select("a.badge.text-bg-danger");
        if (!type.isEmpty()) {
            return type.text();
        }
        return null;
    }

    @Override
    public Elements createFile(String url) throws IOException {
        Document document = Jsoup.connect(url).ignoreHttpErrors(true).get();
        return document.getElementsByClass("pt-0");
    }

    public Elements createFilehead(String url) throws IOException {
        Document document = Jsoup.connect(url).ignoreHttpErrors(true).get();
        return document.getElementsByClass("pb-3 pb-lg-5");
    }

    @Override
    public String getCategory() throws IOException {
        Elements article = createFile(url);
        Elements links = article.select("a.btn.btn-sm.btn-primary-soft");
        StringBuilder categories = new StringBuilder();

        for (Element link : links) {
            if (!categories.isEmpty()) {
                categories.append(", ");
            }
            categories.append(link.text());
        }

        return categories.toString();

    }

    @Override
    public String getAuthor() throws IOException {
        Elements article = createFile(url);
        Elements author = article.select("a.h5.stretched-link");
        if(!author.isEmpty()){
            return author.text();
        }
        return null;
    }

    @Override
    public String getCreationDate() throws IOException {
        Elements article = createFile(url);
        Elements day = article.select("li.list-inline-item.d-lg-block.my-lg-2");
        if(!day.isEmpty()){
            String dateText = day.text();
            try {
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
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
        Elements article = createFile(url);
        String content = "";
        Elements paragraphs = article.select("div > p");
        for (Element paragraph: paragraphs){
            content += paragraph.text();
            content += "\n";
        }
        return content;
    }
    public String getTitle() throws IOException {
        Elements article = createFilehead(url);
        if (!article.select("div.col-12 > h1").isEmpty()){
            return article.select("div.col-12 > h1").text();
        }
        return null;
    }

    @Override
    public List<String> getReference() throws IOException{
        Elements article = createFile(url);
        Elements paragraphs = article.select("p");
        List<String> links = new ArrayList<>();

        for (Element paragraph: paragraphs) {

            String link = paragraph.select("a[rel=nofollow]").attr("href");
            if (!link.isEmpty()) {
                if (!link.startsWith("https://")){
                    link = "https://blockchain.news" + link;
                }
                links.add(link);
            }
        }
        return links;
    }
}

