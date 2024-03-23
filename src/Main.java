import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String adminUsername = "admin";
    static String adminPassword = "admin";
    static Book[] bookList = new Book[5];
    static ArrayList<Student> registeredStudents = new ArrayList<>();

    public static void main(String[] args) {
        initializeBooks();
        menu();
    }

    public static void menu() {
        System.out.println("Welcome to Library Management System");
        System.out.println("1. Login as Admin");
        System.out.println("2. Login as Student");
        System.out.println("3. Exit");

        System.out.print("Select option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                loginAdmin();
                break;
            case 2:
                loginStudent();
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice!");
                menu();
        }
    }

    public static void loginAdmin() {
        System.out.print("Enter your username (admin): ");
        String username = scanner.nextLine();
        System.out.print("Enter your password (admin): ");
        String password = scanner.nextLine();

        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            menuAdmin();
        } else {
            System.out.println("Invalid credentials for admin.");
            menu();
        }
    }

    public static void loginStudent() {
        System.out.print("Enter student NIM: ");
        String nim = scanner.nextLine().trim();

        Student student = findStudent(nim);
        if (student != null) {
            menuStudent(student);
        } else {
            System.out.println("Nim harus 15 digit.");
            menu();
        }
    }

    public static void menuAdmin() {
        System.out.println("Admin Menu:");
        System.out.println("1. Add Student");
        System.out.println("2. Display Registered Students");
        System.out.println("3. Logout");

        System.out.print("Select option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                displayRegisteredStudents();
                break;
            case 3:
                System.out.println("Logging out...");
                menu();
                break;
            default:
                System.out.println("Invalid choice!");
                menuAdmin();
        }
    }

    public static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        String nim;
        System.out.print("Enter Student NIM: ");
        nim = scanner.nextLine();
        if (nim.length() != 15) {
            System.out.println("NIM harus 15 digit.");
            addStudent();
            return;
        }
        System.out.print("Enter faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter program: ");
        String program = scanner.nextLine();

        registeredStudents.add(new Student(name, nim, faculty, program));
        System.out.println("Student successfully registered.");
        menuAdmin();
    }

    public static void displayRegisteredStudents() {
        System.out.println("Registered Students:");
        for (Student student : registeredStudents) {
            System.out.println("Name: " + student.getName());
            System.out.println("NIM: " + student.getNim());
            System.out.println("Faculty: " + student.getFaculty());
            System.out.println("Program: " + student.getProgram());
            System.out.println();
        }
        menuAdmin();
    }

    public static void menuStudent(Student student) {
        System.out.println("Student Menu:");
        System.out.println("1. Buku terpinjam");
        System.out.println("2. Buku dipinjam");
        System.out.println("3. Pinjam buku atau Logout");

        System.out.print("Select option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (choice) {
            case 1:
                displayBorrowedBooks(student);
                break;
            case 2:
                borrowBook(student);
                break;
            case 3:
                System.out.println("Logging out...");
                menu();
                break;
            default:
                System.out.println("Invalid choice!");
                menuStudent(student);
        }
    }

    public static void displayBorrowedBooks(Student student) {
        System.out.println("Borrowed Books:");
        if (student.getBorrowedBooks().isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            for (Book book : student.getBorrowedBooks()) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println();
            }
        }
        menuStudent(student);
    }

    public static void borrowBook(Student student) {
        System.out.println("=================================================================================");
        System.out.println("No. ||       ID Buku      ||   Nama Buku  ||  Author    ||  Category  || Stock ||");
        System.out.println("=================================================================================");
        for (int i = 0; i < bookList.length; i++) {
            Book book = bookList[i];
            if (book != null) {
                System.out.printf("%d   ||   %s   ||    %s    ||   %s   ||   %s   ||   %d   ||\n", (i + 1), book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
                System.out.println("------------------------------------------------------------------------------");
            }
        }
        System.out.println("=================================================================================");
        System.out.print("Input ID buku yang ingin dipinjam (input 99 untuk kembali): ");
        String input = scanner.nextLine().trim();
        if (input.equals("99")) {
            menu();
            return;
        }

        int index = Integer.parseInt(input) - 1;
        if (index < 0 || index >= bookList.length || bookList[index] == null) { // Check if index is within bounds and book at index is not null
            System.out.println("Invalid input.");
            menu();
            return;
        }

        Book selectedBook = bookList[index];
        if (selectedBook.getStock() > 0) {
            student.borrowBook(selectedBook);
            selectedBook.stock--;
            System.out.println("Anda berhasil meminjam buku: " + selectedBook.getTitle());
        } else {
            System.out.println("Buku tidak tersedia.");
        }
        menu();
    }



    public static void returnBook(Student student) {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();

        Book book = findBook(title);
        if (book != null && student.getBorrowedBooks().contains(book)) {
            student.returnBook(book);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book not found or not borrowed by the student.");
        }
        menuStudent(student);
    }

    public static Student findStudent(String nim) {
        for (Student student : registeredStudents) {
            if (student.getNim().equals(nim)) {
                return student;
            }
        }
        return null;
    }

    public static Book findBook(String title) {
        for (Book book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public static void initializeBooks() {
        bookList[0] = new Book("388c-e681-9152", "title", "author", "Sejarah", 4);
        bookList[1] = new Book("ed90-be30-5cdb", "title", "author", "Sejarah", 0);
        bookList[2] = new Book("d95e-0c4a-9523", "title", "author", "Sejarah", 2);
    }
}

class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    public int stock;

    public Book(String id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }
}

class Student {
    private String name;
    private String nim;
    private String faculty;
    private String program;
    private ArrayList<Book> borrowedBooks;

    public Student(String name, String nim, String faculty, String program) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getProgram() {
        return program;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.stock--;
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.stock++;
    }
}
