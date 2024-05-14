package Crawler.BLog101;

import Crawler.GetArticleInformation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Blog101Article extends GetArticleInformation {
    public Blog101Article(String url, String pictureLink) {
        super(url, pictureLink);
    }

    @Override
    public Elements createFile(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        return document.getElementsByClass("col-md-9 col-sm-7");
    }

    @Override
    public String getPicture() {
        return getPictureLink();
    }
    @Override
    public String getCategory() throws IOException {
        Elements article = createFile(getUrl());
        return article.select("a.blog-category-green").text();
    }

    @Override
    public String getAuthor() throws IOException {
        Elements article = createFile(getUrl());
        return article.select("li a[href*='/author/']").text();
    }

    @Override
    public String getCreationDate() throws IOException {
        Document document = Jsoup.connect(getUrl()).get();
        Elements file = document.select("ul.blog-nav-about");
        return file.select("li:contains(on )").text().replace("on ", "");
    }
    @Override
    public String getContent() throws IOException {
        Elements article = createFile(getUrl());
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
        Elements article = createFile(getUrl());
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
        Elements article = createFile(getUrl());
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

