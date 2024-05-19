package Crawler.PotatoNews;

import Crawler.Blog;
import Crawler.SingleArticle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
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
            Blog.WriteToJson(articleList, "dataPotato.json");
        }
    }
}
