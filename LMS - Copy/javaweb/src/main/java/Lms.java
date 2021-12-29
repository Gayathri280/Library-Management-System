public class Lms {
    String ISBN;
    String BookName;
    String Author;
    String PublisherName;
    public Lms(String isbn, String bookName, String author, String publisherName) {
        ISBN = isbn;
        BookName = bookName;
        Author = author;
        PublisherName = publisherName;
    }
    public Lms() {
    }
    public  String getISBN() {
        return ISBN;
    }
    public void setISBN(String isbn) {
        ISBN = isbn;
    }
    public String getBookName() {
        return BookName;
    }
    public void setBookName(String bookName) {
        BookName = bookName;
    }
    public String getAuthor() {
        return Author;
    }
    public void setAuthor(String author) {
        Author = author;
    }
    public String getPublisherName() {
        return PublisherName;
    }
    public void setPublisherName(String publisherName) {
        PublisherName = publisherName;
    }
     
}
