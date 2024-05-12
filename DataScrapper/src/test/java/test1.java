import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test1 {
    public static String extractImageLinkFromSVG(String svgCode) {
        Pattern pattern = Pattern.compile("data-u=\"(.*?)\"");
        Matcher matcher = pattern.matcher(svgCode);

        if (matcher.find()) {
            String encodedImageLink = matcher.group(1);
            String imageLink = decodeURL(encodedImageLink);
            return imageLink;
        }

        return null;
    }

    public static String decodeURL(String encodedURL) {
        try {
            return URLDecoder.decode(encodedURL, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws IOException {
        String link = "https://cryptopotato.com/category/crypto-news/";

        Document document = Jsoup.connect(link).get();
        Elements elements = document.getElementsByTag("article");
        for (Element element : elements) {
            String articlelink = element.select("h3 > a").attr("href");
            String pictureLink = element.getElementsByTag("img").attr("src");
            String trimmedLinks = pictureLink.substring(pictureLink.indexOf(",") + 1);
            byte[] decodedBytes = Base64.getDecoder().decode(trimmedLinks);
            String svgContent = new String(decodedBytes);
            String imageLink = extractImageLinkFromSVG(svgContent);
            System.out.println("Image Link: " + imageLink);


        }
        }
}
