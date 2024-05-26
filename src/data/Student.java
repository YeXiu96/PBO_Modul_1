package data;

import java.util.*;
import util.iMenu;

public class Student extends User implements iMenu{

    public String name, faculty, nim, programStudi;
    public static ArrayList<String> borrowedBooks = new ArrayList<>();

    public static boolean isStudent;

    public Student (String name, String faculty, String nim, String programStudi) {
        this.name = name;
        this.faculty = faculty;
        this.nim = nim;
        this.programStudi = programStudi;
    }

    public Student () {
    }

    User user = new User();

    public static void displayInfo(String name) {
        System.out.println("Successfully login as " + name);
    }

    public static void showBorrowedBooks() {
        System.out.println("=============================================================================================================");
        System.out.printf("|| %-3s ||   %-17s||            %-20s ||  %-10s ||  %-10s || %-8s  ||%n", "No", "Book ID", "Title", "Author", "Category", "Duration");
        System.out.println("=============================================================================================================");
        int no = 1;
        for (int i = 0; i < Admin.bookList.size(); i++) {
            for (int j = 0; j < borrowedBooks.size(); j++) {
                if(borrowedBooks.get(j) != null && User.bookList.get(i).getBookId().equals(borrowedBooks.get(j))) {
                    System.out.printf("|| %-3d ||  %-17s ||  %-30s ||  %-10s ||  %-10s ||  %-8d ||%n", no, User.bookList.get(i).getBookId(), User.bookList.get(i).getTitle(), User.bookList.get(i).getAuthor(), User.bookList.get(i).getCategory(), User.bookList.get(i).getDuration());
                    no++;
                }
            }
        }
        System.out.println("=============================================================================================================");
    }

    static Student student = new Student();

    public static void logout() {
        Scanner input = new Scanner(System.in);
        if(borrowedBooks.isEmpty()) {
            isStudent = false;
            return;
        }else {
            showBorrowedBooks();
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
            for (int i = 0; i < borrowedBooks.size(); i++) {
                if (borrowedBooks.get(i).equals(inputID)) {
                    for (int j = 0; j < Admin.bookList.size(); j++) {
                        if (User.bookList.get(j).getBookId().equals(inputID)) {
                            int stockNow = User.bookList.get(j).getStock();
                            User.bookList.get(j).setStock(stockNow + 1);
                            System.out.println("Successfully to return the book with title '" + User.bookList.get(j).getTitle() + "'");
                            borrowedBooks.remove(i);
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

    public void choiceBook() {
        Scanner input = new Scanner(System.in);
        int loop = 1;
        while(loop == 1) {
            user.displayBook();
            System.out.println("Input Id buku yang ingin dipinjam (input 99 untuk back)");
            System.out.print("Input: ");
            String inputID = input.nextLine();
            if(inputID.equals("99")) {
                loop = 0;
                System.out.println("Kembali ke menu awal...");
            }
            for (int i = 0; i < Admin.bookList.size(); i++) {
                if (User.bookList.get(i).getBookId().equals(inputID)) {
                    if (User.bookList.get(i).getStock() == 0) {
                        System.out.println("Stock buku kosong!");
                        System.out.println("Silahkan pilih yang lain.");
                    } else {
                        System.out.println("Berapa lama buku akan dipinjam? (maksimal 14 hari)");
                        System.out.print("Input lama (hari): ");
                        int duration = input.nextInt();
                        input.nextLine();
                        User.bookList.get(i).setDuration(duration);
                        borrowedBooks.add(User.bookList.get(i).getBookId());
                        int stockNow = User.bookList.get(i).getStock();
                        User.bookList.get(i).setStock(stockNow-1);
                    }
                }
            }
        }
    }

    @Override
    public void menu() {
        Scanner input = new Scanner(System.in);

        while (Student.isStudent) {
            System.out.println("=== Student Menu ===");
            System.out.println("1. Buku terpinjam");
            System.out.println("2. Pinjam buku");
            System.out.println("3. Kembalikan buku");
            System.out.println("4. Pinjam buku atau Logout");
            System.out.print("Choose option (1-4): ");
            try {
                int option = input.nextInt();

                input.nextLine();
                switch (option) {
                    case 1:
                        if (borrowedBooks.isEmpty()) {
                            System.out.println("Belum ada buku yang dipilih");
                            System.out.println("Silahkan pilih buku terlebih dahulu");
                        } else {
                            Student.showBorrowedBooks();
                        }
                        break;
                    case 2:
                        choiceBook();
                        break;
                    case 3:
                        if (borrowedBooks.isEmpty()) {
                            System.out.println("Belum ada buku yang dipilih");
                            System.out.println("Silahkan pilih buku terlebih dahulu");
                        } else {
                            Student.returnBooks();
                        }
                        break;
                    case 4:
                        Student.logout();
                        break;
                    default:
                        System.out.println("There is only 1-4.");
                        break;
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input, there is only 1-4.");
                input.next();
            }

        }
    }
}
