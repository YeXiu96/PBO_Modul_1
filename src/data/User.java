package data;

import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;

import java.util.ArrayList;

public class User {

    public static ArrayList<Book> bookList = new ArrayList<>();

    public void displayBook() {
        if(!Admin.bookList.isEmpty()) {
            System.out.println("==========================================================================================================");
            System.out.printf("|| %-3s ||   %-17s||            %-20s ||  %-10s ||  %-10s || %-5s  ||%n", "No", "Book ID", "Title", "Author", "Category", "Stock");
            System.out.println("==========================================================================================================");
            for (int i = 0; i < Admin.bookList.size(); i++) {
                System.out.printf("|| %-3d ||  %-17s ||  %-30s ||  %-10s ||  %-10s ||  %-5d ||%n", i + 1, User.bookList.get(i).getBookId(), User.bookList.get(i).getTitle(), User.bookList.get(i).getAuthor(), User.bookList.get(i).getCategory(), User.bookList.get(i).getStock());
            }
            System.out.println("==========================================================================================================");
        }
    }

    public static void addBook() {

    }

}
