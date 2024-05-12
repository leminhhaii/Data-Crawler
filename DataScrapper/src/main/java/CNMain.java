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

public class CNMain {
    public static void main(String[] args) throws IOException {
        String baseUrl = "https://cryptonews.com/news/";
        List<SingleArticle> list = new ArrayList<>();

        Document document = Jsoup.connect(baseUrl).get();
        Elements nextElements = document.select("a.next.page-numbers");

        while (!nextElements.attr("href").equals("https://cryptonews.com/news/page/3/")) {
            Elements elements = document.getElementsByClass("col-lg-3 d-flex");
            System.out.println(nextElements.attr("href"));

            for (Element element : elements) {
                String link = element.select("div.news-one-title > a").attr("href");
                String pictureLink = element.getElementsByTag("img").attr("src");
                SingleArticle blog = Blog.getSingleArticleCN(link, pictureLink);
                if (blog != null) {
                    list.add(blog);
                }
            }
            String nextPage = nextElements.attr("abs:href");
            document = Jsoup.connect(nextPage).get();
            nextElements = document.select("a.next.page-numbers");
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(("D:/HUST/20232/OOP/project/DataScrapper/dataCN.json"), true))) {
            writer.println(json);
        }
    }
}


