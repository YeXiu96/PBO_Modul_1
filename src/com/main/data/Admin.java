package com.main.data;
import com.main.books.StoryBook;
import com.main.books.HistoryBook;
import com.main.books.TextBook;
import com.main.util.iMenu;

import java.util.*;

public class Admin extends User implements iMenu {

    private static String adminUsername = "admin";
    private static String adminPassword = "admin";
    public static Student[] studentList = new Student[10];

    public static int bookCount = 0;
    public static int studentCount = 0;

    public static boolean isAdmin;

    public static void addStudent() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String inputName = input.nextLine();
        System.out.print("Enter student nim: ");
        String inputNim = input.next();
        input.nextLine();
        if (inputNim.length() != 15) {
            System.out.println("NIM must have 15 digits!!");
            return;
        } else if (!inputNim.matches("\\d+")) {
            System.out.println("Invalid NIM!");
            return;
        }
        System.out.print("Enter student faculty: ");
        String inputFaculty = input.nextLine();
        System.out.print("Enter student program: ");
        String inputProgram = input.nextLine();

        studentList[studentCount] = new Student(inputName, inputFaculty, inputNim, inputProgram);
        studentCount++;

        System.out.println("Student successfully registered.");
    }

    // Method overloading kedua: menerima input dari parameter
    public static void addStudent(String name, String faculty, String nim, String program) {
        studentList[studentCount] = new Student(name, faculty, nim, program);
        studentCount++;

        System.out.println("Student successfully registered.");
    }



    public static void inputBook() {
        Scanner input = new Scanner(System.in);
        System.out.print("Select book category: \n1. Story book\n2. History book\n3. Text book\nChoose category (1-3): ");
        int option = input.nextInt();
        String inputTitle, inputAuthor;
        int inputStock;
        input.nextLine();
        switch (option) {
            case 1:
                System.out.print("Enter book title: ");
                inputTitle = input.nextLine();
                System.out.print("Enter author: ");
                inputAuthor = input.nextLine();
                System.out.print("Enter the stock: ");
                inputStock = input.nextInt();
                input.nextLine();
                if(inputStock < 1) {
                    System.out.println("Minimum stock is 1.");
                    return;
                }
                User.bookList[bookCount] = new StoryBook(generateId(), inputTitle, inputAuthor, inputStock);
                bookCount++;
                System.out.println("Book successfully added to the library.");
                break;
            case 2:
                System.out.print("Enter book title: ");
                inputTitle = input.nextLine();
                System.out.print("Enter author: ");
                inputAuthor = input.nextLine();
                System.out.print("Enter the stock: ");
                inputStock = input.nextInt();
                input.nextLine();
                if(inputStock < 1) {
                    System.out.println("Minimum stock is 1.");
                    return;
                }
                User.bookList[bookCount] = new HistoryBook(generateId(), inputTitle, inputAuthor, inputStock);
                bookCount++;
                System.out.println("Book successfully added to the library.");
                break;
            case 3:
                System.out.print("Enter book title: ");
                inputTitle = input.nextLine();
                System.out.print("Enter author: ");
                inputAuthor = input.nextLine();
                System.out.print("Enter the stock: ");
                inputStock = input.nextInt();

                input.nextLine();
                if(inputStock < 1) {
                    System.out.println("Minimum stock is 1.");
                    return;
                }
                User.bookList[bookCount] = new TextBook(generateId(), inputTitle, inputAuthor, inputStock);
                bookCount++;
                System.out.println("Book successfully added to the library.");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    @Override
    public void displayBook() {
        if(bookCount != 0) {
            System.out.println("==========================================================================================================");
            System.out.printf("|| %-3s ||   %-17s||            %-20s ||  %-10s ||  %-10s || %-5s  ||%n", "No", "Book ID", "Title", "Author", "Category", "Stock");
            System.out.println("==========================================================================================================");
            for (int i = 0; i < bookCount; i++) {
                System.out.printf("|| %-3d ||  %-17s ||  %-30s ||  %-10s ||  %-10s ||  %-5d ||%n", i + 1, User.bookList[i].getBookId(), User.bookList[i].getTitle(), User.bookList[i].getAuthor(), User.bookList[i].getCategory(), User.bookList[i].getStock());
            }
            System.out.println("==========================================================================================================");
        } else {
            System.out.println("No books available.");
        }
    }

    public static void displayStudent() {
        if(studentCount != 0) {
            System.out.println("List of Registered Students: ");
            for (int i = 0; i < studentCount; i++) {
                if (studentList[i] != null && studentList[i].name != null && studentList[i].faculty != null && studentList[i].nim != null && studentList[i].programStudi != null) {
                    System.out.print("Name: " + studentList[i].name + "\nFaculty: " + studentList[i].faculty + "\nNIM: " + studentList[i].nim + "\nProgram: " + studentList[i].programStudi + "\n\n");
                }
            }
        } else {
            System.out.println("There is no student registered.");
        }
    }


    public static boolean isAdmin(String uname, String pw) {
        return uname.equals(adminUsername) && pw.equals(adminPassword);
    }

    public static String generateId() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        String formattedID = uuidString.substring(0, 4) + "-" +
                uuidString.substring(9, 13) + "-" +
                uuidString.substring(14, 18);

        return formattedID;
    }

    public void menu() {

    }

    public static Student[] getStudentList() {
        return studentList;
    }
}
