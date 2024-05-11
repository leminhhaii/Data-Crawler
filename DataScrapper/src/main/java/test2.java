import java.io.*;
public class test2 {
    public static void main(String[] args) throws IOException {
        String link = "https://blockchain.news/news/arthur-hayes-discusses-crypto-market-turbulenceus-tax-season-federal-reserve-uncertainty-and-bitcoin-halving";

        SingleArticle blog = Blog.getSingleArticleBCN(link);
        System.out.println(blog.getCreationDate());
        System.out.println(blog.getTitle());
        System.out.println(blog.getWebsiteSource());
        System.out.println(blog.getCategory());
        System.out.println(blog.getAuthor());
        System.out.println(blog.getContent());
        System.out.println(blog.getLink());
        System.out.println(blog.getReferenceLinks());
    }
}
