package Crawler.BLog101;

import Crawler.Blog;
import Crawler.SingleArticle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Blog101Main {
    public static void main(String[] args) throws IOException {
        String Url = "https://101blockchains.com/blog/";

        Document document = Jsoup.connect(Url).get();
        List<SingleArticle> articleList = new ArrayList<>();

        Elements nextElements = document.select("a.next.page-numbers");
        while (!nextElements.attr("abs:href").equals("https://101blockchains.com/blog/page/49/")) {
            Elements articles = document.select("div.col-md-6 > article");

            System.out.println(nextElements.attr("abs:href"));
            for (Element article : articles) {
                String articlelink = article.select("h2 > a").attr("href");
                String pictureLink = article.getElementsByTag("img").attr("data-lazy-src");
                SingleArticle blog = Blog.getSingleArticleBlog101(articlelink, pictureLink);
                articleList.add(blog);
            }
            String nextPage = nextElements.attr("abs:href");
            document = Jsoup.connect(nextPage).get();
            nextElements = document.select("a.next.page-numbers");

        }
        Blog.WriteToJson(articleList,"data101.json");
    }
}



