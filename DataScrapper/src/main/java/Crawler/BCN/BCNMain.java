package Crawler.BCN;

import Crawler.Article;
import Crawler.Blog;
import Crawler.SingleArticle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BCNMain {
    public static void main(String[] args) throws IOException {
        String baseUrl = "https://blockchain.news";
        List<SingleArticle> articleList = new ArrayList<>();

        for (int i = 0; i < 50; i++){
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
                    articleList.add(blog);
                }
            }
        }
        Blog.WriteToJson(articleList,"D:/HUST/20232/OOP/project/DataScrapper/DATA/dataBCN.json");
    }
}


