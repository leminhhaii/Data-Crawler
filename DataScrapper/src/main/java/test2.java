import java.io.*;
public class test2 {
    public static void main(String[] args) throws IOException {
        String link = "https://blockchain.news/news/arthur-hayes-discusses-crypto-market-turbulenceus-tax-season-federal-reserve-uncertainty-and-bitcoin-halving";

        SingleArticle blog = Blog.getSingleArticleBCN(link, "https://blockchainstock.azureedge.net/thumbnails/A30D7E61FB779EFF09C7D255CCB02C320271B97C2830DFBA9D0C6BD03999A073.jpg");
        System.out.println(blog.getCreationDate());
        System.out.println(blog.getTitle());
        System.out.println(blog.getWebsiteSource());
        System.out.println(blog.getCategory());
        System.out.println(blog.getAuthor());
        System.out.println(blog.getContent());
        System.out.println(blog.getLink());
        System.out.println(blog.getReferenceLinks());
        System.out.println(blog.getPictureLink());
        System.out.println(blog.getType());
    }
}
