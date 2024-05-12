package Crawler;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public interface ArticleInformation {
    Elements createFile(String url) throws IOException;

    String getCategory() throws IOException;

    String getAuthor() throws IOException;

    String getCreationDate() throws IOException;

    String getContent() throws IOException;

    String getTitle() throws IOException;

    String getWebsiteSource();

    String getType() throws IOException;

    List<String> getReference() throws IOException;
}
