package Crawler;

import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.List;

public class GetArticleInformation {
    private final String url;
    private final String pictureLink;


    public GetArticleInformation(String url, String pictureLink) {
        this.url = url;
        this.pictureLink = pictureLink;
    }

    public String getUrl() {
        return url;
    }

    public String getPicture(){
        return null;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public Elements createFile(String url) throws IOException{
        return null;
    }

    public String getCategory() throws IOException{
        return null;
    }

    public String getAuthor() throws IOException{
        return null;
    }

    public String getCreationDate() throws IOException{
        return null;
    }

    public String getContent() throws IOException{
        return null;
    }

    public String getTitle() throws IOException{
        return null;
    }

    public String getWebsiteSource(){
        return null;
    }

    public String getType() throws IOException{
        return null;
    }

    public List<String> getReference() throws IOException{
        return null;
    }
}
