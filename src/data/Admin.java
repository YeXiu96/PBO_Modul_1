package data;

import java.util.*;

import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import exception.custom.illegalAdminAccess;
import util.iMenu;

public class Admin extends User implements iMenu{

    private static String adminUsername = "admin";
    private static String adminPassword = "admin";
    public static ArrayList<Student> StudentList = new ArrayList<>();

    public static boolean isAdmin;

    User user = new User();

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
        }else if(!inputNim.matches("\\d+")){
            System.out.println("Invalid NIM!");
            return;
        }
        System.out.print("Enter student faculty: ");
        String inputFaculty = input.nextLine();
        System.out.print("Enter student program: ");
        String inputProgram = input.nextLine();

        StudentList.add(new Student(inputName, inputFaculty, inputNim, inputProgram));

        System.out.println("Student successfully registered.");
    }

    public static void inputBook() {
        Scanner input = new Scanner(System.in);
        System.out.print("Select book category: \n1. Story book\n2. History book\n3. Text book\nChoose category (1-3): ");
        int option = input.nextInt();
        String inputTitle, inputAuthor;
        int inputStock;
        input.nextLine();
        if(option < 4 && option > 0) {
            System.out.print("Enter book title: ");
            inputTitle = input.nextLine();
            System.out.print("Enter author: ");
            inputAuthor = input.nextLine();
            System.out.print("Enter the stock: ");
            inputStock = input.nextInt();
            input.nextLine();
            if (inputStock < 1) {
                System.out.println("Minimum stock is 1.");
                return;
            }

            switch (option) {
                case 1:
                    User.bookList.add(new StoryBook(generateId(), inputTitle, inputAuthor, inputStock));
                    System.out.println("Book successfully added to the library.");
                    break;
                case 2:
                    User.bookList.add(new HistoryBook(generateId(), inputTitle, inputAuthor, inputStock));
                    System.out.println("Book successfully added to the library.");
                    break;
                case 3:
                    User.bookList.add(new TextBook(generateId(), inputTitle, inputAuthor, inputStock));
                    System.out.println("Book successfully added to the library.");
                    break;
            }
        }else {
            System.out.println("Invalid option.");
        }
    }

    @Override
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

    public static void displayStudent() {
        if(!StudentList.isEmpty()) {
            System.out.println("List of Registered Students: ");
            for (int i = 0; i < StudentList.size(); i++) {
                System.out.print("Name: " + StudentList.get(i).name + "\nFaculty: " + StudentList.get(i).faculty + "\nNIM: " + StudentList.get(i).nim + "\nProgram: " + StudentList.get(i).programStudi + "\n\n");
            }
        }else {
            System.out.println("There is no student registered.");
        }
    }

    public static boolean isAdmin(String uname, String pw) throws illegalAdminAccess {
        if (!(uname.equals(adminUsername) && pw.equals(adminPassword))) {
            throw new illegalAdminAccess("Invalid credentials.");
        }else {
            return true;
        }
    }

    public static String generateId() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        String formattedID = uuidString.substring(0, 4) + "-" +
                uuidString.substring(9, 13) + "-" +
                uuidString.substring(14, 18);

        return formattedID;
    }

    @Override
    public void menu() {
        Scanner input = new Scanner(System.in);

        while (Admin.isAdmin) {
            System.out.println("==== Admin Menu ====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Book");
            System.out.println("3. Display Registered Students");
            System.out.println("4. Display Available Books");
            System.out.println("5. Logout");
            System.out.print("Choose option (1-5): ");

            int option;
            try {
                option = input.nextInt();

                switch (option) {
                    case 1:
                        Admin.addStudent();
                        break;
                    case 2:
                        Admin.inputBook();
                        break;
                    case 3:
                        Admin.displayStudent();
                        break;
                    case 4:
                        displayBook();
                        break;
                    case 5:
                        Admin.isAdmin = false;
                        System.out.println("Logging out from admin account.");
                        return;
                    default:
                        System.out.println("There is only 1-5.");
                        break;
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input, there is only 1-5.");
                input.next();
            }
        }
    }
}
