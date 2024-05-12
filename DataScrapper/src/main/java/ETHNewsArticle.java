import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ETHNewsArticle implements ArticleInformation{
    private final String url;
    private final String pictureLink;

    public ETHNewsArticle(String url, String pictureLink) {
        this.url = url;
        this.pictureLink = pictureLink;
    }

    @Override
    public Elements createFile(String url) throws IOException {
        Document document = Jsoup.connect(url).ignoreHttpErrors(true).get();
        return document.getElementsByClass("vc_column tdi_55  wpb_column vc_column_container tdc-column td-pb-span8");
    }

    @Override
    public String getCategory() throws IOException {
        Elements article = createFile(url);
        Elements tags = article.select("ul.tdb-tags > li");
        StringBuilder categories = new StringBuilder();

        for (Element link : tags) {
            if (!categories.isEmpty()) {
                categories.append(", ");
            }
            categories.append(link.text().replace("Tags",""));
        }
        return categories.toString();
    }

    @Override
    public String getAuthor() throws IOException {
        Elements article = createFile(url);
        return article.select("div.tdb-author-name-wrap > a").text();
    }

    @Override
    public String getCreationDate() throws IOException {
        Elements article = createFile(url);
        return article.select("div.tdb-block-inner.td-fix-index > time").text();
    }

    @Override
    public String getContent() throws IOException {
        Elements article = createFile(url);
        String content = "";
        Elements paragraphs = article.select("div.td_block_wrap.tdb_single_content.tdi_63.td-pb-border-top.td_block_template_1.td-post-content.tagdiv-type > div > p");
        for (Element paragraph: paragraphs){
            content += paragraph.text();
            content += "\n";
        }
        return content;
    }

    @Override
    public String getTitle() throws IOException {
        Elements article = createFile(url);
        return article.select("h1.tdb-title-text").text();
    }

    @Override
    public String getWebsiteSource() {
        return "https://www.ethnews.com/";
    }

    @Override
    public String getType() throws IOException {
        Elements article = createFile(url);
        return article.select("a.tdb-entry-category").text();
    }

    @Override
    public List<String> getReference() throws IOException {
        Elements article = createFile(url);
        List<String> links = new ArrayList<>();
        Elements paragraphs = article.select("p");

        for (Element paragraph: paragraphs) {
            if (!paragraph.select("p > a").attr("href").isEmpty()){
                links.add(paragraph.select("p > a").attr("href"));
            }
            }

        return links;
    }
}
