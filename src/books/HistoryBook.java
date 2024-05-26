package books;

public class HistoryBook extends Book {

    String category = "History";

    public HistoryBook(String bookId, String title, String author, int stock) {
        super(bookId, title, author, stock);
        setCategory(category);
    }
}
