package Crawler.PotatoNews;

import Crawler.GetArticleInformation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PotatoNewsArticle extends GetArticleInformation {
    public PotatoNewsArticle(String url, String pictureLink) {
        super(url, pictureLink);
    }

    public Document getArticleDoc(String url) throws IOException{
        return Jsoup.connect(url).get();
    }

    @Override
    public String getCategory() throws IOException {
        Document articleDoc = getArticleDoc(getUrl());
        Elements fullCategory = articleDoc.select("a[rel=tag]");
        String category = "";
        if (fullCategory.isEmpty()) {
            category = "General news";
        } else {
            for (Element fullEleCategory : fullCategory) {
                String cates = fullEleCategory.text();
                category += cates + ", ";
            }
            category = category.substring(0, category.length() - 2);
        }
        return category;
    }

    @Override
    public String getAuthor() throws IOException {
        Document articleDoc = getArticleDoc(getUrl());
        return articleDoc.select(".entry-user").text().replace("Author: ", "");
    }

    @Override
    public String getCreationDate() throws IOException {
        Document articleDoc = getArticleDoc(getUrl());
        String tempDate = articleDoc.select(".last-modified-timestamp").text();
        return tempDate.substring(0, tempDate.indexOf("@")).trim();
    }

    @Override
    public String getContent() throws IOException {
        Document articleDoc = getArticleDoc(getUrl());
        Elements articleContent = articleDoc.getElementsByClass("coincodex-content").select("p");
        String content = "";

        for (Element subarticleContent : articleContent) {
            String subContents = subarticleContent.text();
            content += subContents + "\n";
        }
        return content;
    }

    @Override
    public String getTitle() throws IOException {
        Document articleDoc = getArticleDoc(getUrl());
        return articleDoc.getElementsByClass("page-title").text();
    }

    @Override
    public String getWebsiteSource() {

        return "https://cryptopotato.com/";
    }

    @Override
    public String getType() throws IOException {
        return "News";
    }

    @Override
    public List<String> getReference() throws IOException {
        Document articleDoc = getArticleDoc(getUrl());
        List<String> referenceLinks = new ArrayList<>();
        Elements articleContent = articleDoc.getElementsByClass("coincodex-content").select("p");

        Elements reference = articleContent.select("p > a");
        for (Element referenceElement : reference) {
            String refs = referenceElement.attr("href");
            referenceLinks.add(refs);
        }
        return referenceLinks;
    }

    @Override
    public String getPicture() throws StackOverflowError{
        if (getPictureLink().contains("https://")) {
            return getPictureLink();
        } else {
            String trimmedLinks = getPictureLink().substring(getPictureLink().indexOf(",") + 1);
            byte[] decodedBytes = Base64.getDecoder().decode(trimmedLinks);
            String svgContent = new String(decodedBytes);
            return extractImageLinkFromSVG(svgContent);
        }
    }

    public static String extractImageLinkFromSVG(String svgCode) {
        Pattern pattern = Pattern.compile("data-u=\"(.*?)\"");
        Matcher matcher = pattern.matcher(svgCode);

        if (matcher.find()) {
            String encodedImageLink = matcher.group(1);
            return decodeURL(encodedImageLink);
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
}
