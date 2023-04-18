package model.songs;

import java.util.List;

public class Song {
    private String title;
    private String author;
    private List<String> strofe;

    public Song(String title, String author, List<String> strofe) {
        this.title = title;
        this.author = author;
        this.strofe = strofe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getStrofe() {
        return strofe;
    }

    public void setStrofe(List<String> strofe) {
        this.strofe = strofe;
    }
}
