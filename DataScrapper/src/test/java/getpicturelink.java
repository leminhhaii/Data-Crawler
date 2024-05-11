import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class getpicturelink {

    public static void main(String[] args) throws IOException {
        String website = "https://101blockchains.com/blog/";
        List<String> list = new ArrayList<String>();

        Document document = Jsoup.connect(website).get();
        Elements elements = document.getElementsByTag("picture");
        int i = 0;

        for (Element element : elements) {
            i += 1;
            String link = element.getElementsByTag("img").attr("data-lazy-src");
            list.add(link);
            URL url = new URL(link);
            InputStream in = url.openStream();
            OutputStream out = new BufferedOutputStream(new FileOutputStream("D:/HUST/20232/OOP/project/DataScrapper/Blockchainnews" + "\\" + "page" + i + ".jpg"));
            for (int b; (b = in.read()) != -1; ) {
                out.write(b);
            }
            out.close();
            in.close();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(("D:/HUST/20232/OOP/project/DataScrapper/picturelink.json"), true))) {
            writer.println(json);
        }
    }
}
