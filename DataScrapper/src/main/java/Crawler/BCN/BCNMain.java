package Crawler.BCN;

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

public class BCNMain {
    public static void main(String[] args) throws IOException {
        String baseUrl = "https://blockchain.news";
        List<SingleArticle> abcd = new ArrayList<>();

        for (int i = 0; i < 20; i++){
            String link = "https://blockchain.news/tag/Bitcoin/" + i;
            Document document = Jsoup.connect(link).get();
            Elements articles = document.getElementsByClass("card mb-4");
            System.out.println(link);
            for (Element article: articles) {
                String smallLink = article.select("h3 > a").attr("href");
                String pictureLink = article.getElementsByTag("img").attr("src");
                smallLink = smallLink.replace("%", "%25");
                smallLink = smallLink.replace("\"", "%22");

                String articlelink = baseUrl + smallLink;
                SingleArticle blog = Blog.getSingleArticleBCN(articlelink, pictureLink);
                if (blog != null) {
                    abcd.add(blog);
                }
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(abcd);

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(("D:/HUST/20232/OOP/project/DataScrapper/DATA/dataBCN.json"), true))){
            writer.println(json);
        }
    }
}


