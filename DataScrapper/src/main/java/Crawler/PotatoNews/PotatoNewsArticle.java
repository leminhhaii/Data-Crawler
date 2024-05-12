package Crawler.PotatoNews;

import Crawler.ArticleInformation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PotatoNewsArticle implements ArticleInformation {
    private final String url;
    private final String pictureLink;

    public PotatoNewsArticle(String url, String pictureLink) {
        this.url = url;
        this.pictureLink = pictureLink;
    }

    @Override
    public Elements createFile(String url) throws IOException {
        return null;
    }

    public Document getArticleDoc(String url) throws IOException{
        return Jsoup.connect(url).get();
    }

    @Override
    public String getCategory() throws IOException {
        Document articleDoc = getArticleDoc(url);
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
        Document articleDoc = getArticleDoc(url);
        return articleDoc.select(".entry-user").text().replace("Author: ", "");
    }

    @Override
    public String getCreationDate() throws IOException {
        Document articleDoc = getArticleDoc(url);
        String tempDate = articleDoc.select(".last-modified-timestamp").text();
        return tempDate.substring(0, tempDate.indexOf("@")).trim();
    }

    @Override
    public String getContent() throws IOException {
        Document articleDoc = getArticleDoc(url);
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
        Document articleDoc = getArticleDoc(url);
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
        Document articleDoc = getArticleDoc(url);
        List<String> referenceLinks = new ArrayList<>();
        Elements articleContent = articleDoc.getElementsByClass("coincodex-content").select("p");

        Elements reference = articleContent.select("p > a");
        for (Element referenceElement : reference) {
            String refs = referenceElement.attr("href");
            referenceLinks.add(refs);
        }
        return referenceLinks;
    }
}
