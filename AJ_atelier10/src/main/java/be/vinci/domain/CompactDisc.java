package be.vinci.domain;

public class CompactDisc {
    private String title;
    private String artist;
    private int stock;
    private double price;
    private String id;
    public CompactDisc(String title, String artist, int stock, double price, String id) {
        this.title = title;
        this.artist = artist;
        this.stock = stock;
        this.price = price;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public String getArtist() {
        return artist;
    }
}
