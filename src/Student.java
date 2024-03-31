import java.util.*;

public class Student extends User {

    String name, faculty, nim, programStudi;
    public static Object[] borrowedBooks = new Object[10];
    public static int borrowedBooksCount = 0;

    public static boolean isStudent;

    public Student (String name, String faculty, String nim, String programStudi) {
        this.name = name;
        this.faculty = faculty;
        this.nim = nim;
        this.programStudi = programStudi;
    }

    public Student () {

    }

    public static void displayInfo(String name) {
        System.out.println("Successfully login as " + name);
    }

    public static void showBorrowedBooks() {
        System.out.println("=============================================================================================================");
        System.out.printf("|| %-3s ||   %-17s||            %-20s ||  %-10s ||  %-10s || %-8s  ||%n", "No", "Book ID", "Title", "Author", "Category", "Duration");
        System.out.println("=============================================================================================================");
        int no = 1;
        for (int i = 0; i < Admin.bookCount; i++) {
            for (int j = 0; j < borrowedBooksCount; j++) {
                if(borrowedBooks[j] != null && User.bookList[i].getBookId().equals(borrowedBooks[j])) {
                    System.out.printf("|| %-3d ||  %-17s ||  %-30s ||  %-10s ||  %-10s ||  %-8d ||%n", no, User.bookList[i].getBookId(), User.bookList[i].getTitle(), User.bookList[i].getAuthor(), User.bookList[i].getCategory(), User.bookList[i].getDuration());
                    no++;
                }
            }
        }
        System.out.println("=============================================================================================================");
    }

    @Override
    public void displayBook() {
        if(Admin.bookCount != 0) {
            System.out.println("==========================================================================================================");
            System.out.printf("|| %-3s ||   %-17s||            %-20s ||  %-10s ||  %-10s || %-5s  ||%n", "No", "Book ID", "Title", "Author", "Category", "Stock");
            System.out.println("==========================================================================================================");
            for (int i = 0; i < Admin.bookCount; i++) {
                System.out.printf("|| %-3d ||  %-17s ||  %-30s ||  %-10s ||  %-10s ||  %-5d ||%n", i + 1, User.bookList[i].getBookId(), User.bookList[i].getTitle(), User.bookList[i].getAuthor(), User.bookList[i].getCategory(), User.bookList[i].getStock());
            }
            System.out.println("==========================================================================================================");
        }
    }

    static Student student = new Student();

    public static void logout() {
        Scanner input = new Scanner(System.in);
        if(borrowedBooksCount == 0) {
            isStudent = false;
            return;
        }else {
            student.displayBook();
            System.out.println("Apakah kamu yakin untuk meminjam semua buku tersebut?");
            System.out.println("Input Y (iya) atau T (tidak): ");
            String option = input.next();
            input.nextLine();
            if(option.equals("T") || option.equals("t")) {
                return;
            }else if(option.equals("Y") || option.equals("y")) {
                System.out.println("Peminjaman buku berhasil dilakukan.");
                System.out.println("Terima kasih...");
                isStudent = false;
                return;
            }
        }
    }

    public static void returnBooks() {
        Scanner input = new Scanner(System.in);
        showBorrowedBooks();
        System.out.print("Input ID Buku yang ingin dihapus (Input 99 untuk back): ");
        String inputID = input.next();
        input.nextLine();
        if (inputID.equals("99")) {
            return;
        } else {
            boolean bookFound = false;
            for (int i = 0; i < borrowedBooksCount; i++) {
                if (borrowedBooks[i].equals(inputID)) {
                    for (int j = 0; j < Admin.bookCount; j++) {
                        if (User.bookList[j].getBookId().equals(inputID)) {
                            int stockNow = User.bookList[j].getStock();
                            User.bookList[j].setStock(stockNow + 1);
                            System.out.println("Successfully to return the book with title '" + User.bookList[j].getTitle() + "'");
                            for (int k = i; k < borrowedBooksCount - 1; k++) {
                                borrowedBooks[k] = borrowedBooks[k + 1];
                            }
                            borrowedBooksCount--;
                            bookFound = true;
                            break;
                        }
                    }
                    if (!bookFound) {
                        System.out.println("Book with ID '" + inputID + "' is not found in the available books.");
                    }
                    break;
                }
            }
            if (!bookFound) {
                System.out.println("Borrowed book with ID '" + inputID + "' is not found.");
            }
        }
    }

}
