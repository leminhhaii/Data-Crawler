package Crawler.BLog101;

import Crawler.Blog;
import Crawler.SingleArticle;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Blog101Main {
    public static void main(String[] args) throws IOException {
        String Url = "https://101blockchains.com/blog/";

        Document document = Jsoup.connect(Url).get();
        List<SingleArticle> list = new ArrayList<>();

        Elements nextElements = document.select("a.next.page-numbers");
        while (!nextElements.attr("abs:href").equals("https://101blockchains.com/blog/page/49/")) {
            Elements articles = document.select("div.col-md-6 > article");

            System.out.println(nextElements.attr("abs:href"));
            for (Element article : articles) {
                String articlelink = article.select("h2 > a").attr("href");
                String pictureLink = article.getElementsByTag("img").attr("data-lazy-src");
                SingleArticle blog = Blog.getSingleArticleBlog101(articlelink, pictureLink);
                list.add(blog);
            }
            String nextPage = nextElements.attr("abs:href");
            document = Jsoup.connect(nextPage).get();
            nextElements = document.select("a.next.page-numbers");

        }
        // Convert blog to JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("D:/HUST/20232/OOP/project/DataScrapper/DATA/data101.json"), true))) {
            writer.println(json);
        }
    }
}



