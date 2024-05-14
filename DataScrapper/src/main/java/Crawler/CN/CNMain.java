package Crawler.CN;

import Crawler.Blog;
import Crawler.SingleArticle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CNMain {
    public static void main(String[] args) throws IOException {
        String baseUrl = "https://cryptonews.com/news/";
        List<SingleArticle> articleList = new ArrayList<>();

        Document document = Jsoup.connect(baseUrl).get();
        Elements nextElements = document.select("a.next.page-numbers");

        while (!nextElements.attr("href").equals("https://cryptonews.com/news/page/100/")) {
            Elements elements = document.getElementsByClass("col-lg-3 d-flex");
            System.out.println(nextElements.attr("href"));

            for (Element element : elements) {
                String link = element.select("div.news-one-title > a").attr("href");
                String pictureLink = element.getElementsByTag("img").attr("src");
                SingleArticle blog = Blog.getSingleArticleCN(link, pictureLink);
                if (blog != null) {
                    articleList.add(blog);
                }
            }
            String nextPage = nextElements.attr("abs:href");
            document = Jsoup.connect(nextPage).get();
            nextElements = document.select("a.next.page-numbers");
        }
        Blog.WriteToJson(articleList, "D:/HUST/20232/OOP/project/DataScrapper/DATA/dataCN.json");
    }
}


