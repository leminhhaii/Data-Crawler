package Crawler.PotatoNews;

import Crawler.Blog;
import Crawler.SingleArticle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PotatoNewsMain {
    public static void main(String[] args) throws IOException {
        List<SingleArticle> articleList = new ArrayList<>();

        for (int i = 1; i < 2; i++) {
            String url = "https://cryptopotato.com/category/crypto-news/page/" + i + "/";
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByTag("article");

            System.out.println(url);
            for (Element element : elements) {
                String articlelink = element.select("h3 > a").attr("href");
                String pictureLink = element.getElementsByTag("img").attr("src");;

                SingleArticle blog = Blog.getSingleArticlePotatoNews(articlelink, pictureLink);
                if (blog != null) {
                    articleList.add(blog);
                }
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(articleList);

            try (PrintWriter writer = new PrintWriter(new FileOutputStream(("D:/HUST/20232/OOP/project/DataScrapper/DataScrapper/DATA/dataPotato.json"), true))) {
                writer.println(json);

            }
        }
    }
}
