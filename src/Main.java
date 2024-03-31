import java.util.Scanner;

public class Main {

    public static boolean exit = false;

    public static void main(String[] args) {
        Main main = new Main();
        Admin.StudentList[Admin.studentCount] = new Student("radan", "Teknik", "202210370311208", "Informatika");;
        Admin.studentCount++;
        Admin.StudentList[Admin.studentCount] = new Student("katak salto", "FEB", "201910330211809", "Bisnis");;
        Admin.studentCount++;
        Admin.StudentList[Admin.studentCount] = new Student("ini nama", "FIKES", "202010370321210", "Kedokteran Mesin");;
        Admin.studentCount++;

        User.bookList[Admin.bookCount] = new HistoryBook("388c-e681-9152", "title", "author", 4);
        Admin.bookCount++;
        User.bookList[Admin.bookCount] = new StoryBook("ed90-be30-5cdb", "title", "author", 0);
        Admin.bookCount++;
        User.bookList[Admin.bookCount] = new TextBook("d95e-0c4a-9523", "title", "author", 1);
        Admin.bookCount++;
        while (!exit) {
            main.menu();
        }
    }

    User user = new User();

    public void menu() {
        Scanner input = new Scanner(System.in);

        System.out.println("==== Library System ====");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. Exit");
        System.out.print("Choose option (1-3): ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                inputNim();
                break;
            case 2:
                System.out.print("Enter your username (admin): ");
                String username = input.next();
                System.out.print("Enter your password (admin): ");
                String password = input.next();
                if(Admin.isAdmin(username, password)) {
                    System.out.println("Successfully login as Admin.");
                    Admin.isAdmin = true;
                    menuAdmin();
                }else {
                    System.out.println("Invalid credentials for admin.");
                }
                break;
            case 3:
                return;
            default:
                break;
        }
    }

    public void inputNim() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your NIM (input 99 untuk back): ");
        String nim = input.next();
        checkNim(nim);
    }

    public void checkNim(String nim) {
        if (nim.equals("99")) {
            return;
        }

        for (int i = 0; i < Admin.studentCount; i++) {
            if(Admin.StudentList[i].nim.equals(nim)) {
                Student.isStudent = true;
                Student.displayInfo(Admin.StudentList[i].name);
                menuStudent();
                return;
            }
        }

        System.out.println("NIM not found.");
    }

    public void menuStudent() {
        Student student = new Student();
        Scanner input = new Scanner(System.in);

        while (Student.isStudent) {
            System.out.println("=== Student Menu ===");
            System.out.println("1. Buku terpinjam");
            System.out.println("2. Pinjam buku");
            System.out.println("3. Kembalikan buku");
            System.out.println("4. Pinjam buku atau Logout");
            System.out.print("Choose option (1-4): ");
            int option = input.nextInt();

            input.nextLine();
            switch (option) {
                case 1:
                    if(Student.borrowedBooksCount == 0) {
                        System.out.println("Belum ada buku yang dipilih");
                        System.out.println("Silahkan pilih buku terlebih dahulu");
                    }else {
                        Student.showBorrowedBooks();
                    }
                    break;
                case 2:
                    int loop = 1;
                    while(loop == 1) {
                        student.displayBook();
                        System.out.println("Input Id buku yang ingin dipinjam (input 99 untuk back)");
                        System.out.print("Input: ");
                        String inputID = input.nextLine();
                        if(inputID.equals("99")) {
                            loop = 0;
                            System.out.println("Kembali ke menu awal...");
                        }
                        for (int i = 0; i < Admin.bookCount; i++) {
                            if (User.bookList[i].getBookId().equals(inputID)) {
                                if (User.bookList[i].getStock() == 0) {
                                    System.out.println("Stock buku kosong!");
                                    System.out.println("Silahkan pilih yang lain.");
                                } else {
                                    System.out.println("Berapa lama buku akan dipinjam? (maksimal 14 hari)");
                                    System.out.print("Input lama (hari): ");
                                    int duration = input.nextInt();
                                    input.nextLine();
                                    User.bookList[i].setDuration(duration);
                                    Student.borrowedBooks[Student.borrowedBooksCount] = User.bookList[i].getBookId();
                                    Student.borrowedBooksCount++;
                                    int stockNow = User.bookList[i].getStock();
                                    User.bookList[i].setStock(stockNow-1);
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    if(Student.borrowedBooksCount == 0) {
                        System.out.println("Belum ada buku yang dipilih");
                        System.out.println("Silahkan pilih buku terlebih dahulu");
                    }else {
                        Student.returnBooks();
                    }
                    break;
                case 4:
                    Student.logout();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public void menuAdmin() {
        Scanner input = new Scanner(System.in);

        while (Admin.isAdmin) {
            System.out.println("==== Admin Menu ====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Book");
            System.out.println("3. Display Registered Students");
            System.out.println("4. Display Available Books");
            System.out.println("5. Logout");
            System.out.print("Choose option (1-5): ");
            int option = input.nextInt();

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
                    user.displayBook();
                    break;
                case 5:
                    Admin.isAdmin = false;
                    System.out.println("Logging out from admin account.");
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void addTempStudent(){

    }

    public static void addTempBooks() {

    }
}