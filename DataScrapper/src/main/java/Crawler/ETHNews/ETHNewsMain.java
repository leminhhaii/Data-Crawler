package Crawler.ETHNews;

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

public class ETHNewsMain {
    public static void main(String[] args) throws IOException {
        List<SingleArticle> articleList = new ArrayList<>();

        for (int i = 1; i < 140; i++) {
            String url = "https://www.ethnews.com/category/news/page/" + i + "/";
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(".tdb_module_loop.td_module_wrap.td-animation-stack.td-cpt-post");

            System.out.println(url);
            for (Element element: elements){
                String articlelink = element.selectFirst("h3 > a").attr("href");
                String pictureLink = element.select("div.td-module-thumb > a > span").attr("data-bg");

                SingleArticle singleArticle = Blog.getSingleArticleETHNews(articlelink, pictureLink);
                if (singleArticle != null){
                    articleList.add(singleArticle);
                }
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(articleList);

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(("D:/HUST/20232/OOP/project/DataScrapper/DATA/dataETHNews.json"), true))){
            writer.println(json);
        }
    }
}
