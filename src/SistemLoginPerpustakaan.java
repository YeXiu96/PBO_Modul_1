import java.util.Scanner;

public class SistemLoginPerpustakaan {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selamat datang di Sistem Login Perpustakaan");

        while (true) {
            System.out.println("Pilih jenis pengguna:");
            System.out.println("1. Admin");
            System.out.println("2. Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan Anda: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    loginAdmin(scanner);
                    break;
                case 2:
                    loginMahasiswa(scanner);
                    break;
                case 3:
                    System.out.println("Keluar...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan lagi.");
            }
        }
    }

    private static void loginAdmin(Scanner scanner) {
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Login admin berhasil!");
        } else {
            System.out.println("Username atau password salah. Silakan coba lagi.");
        }
    }

    private static void loginMahasiswa(Scanner scanner) {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();

        if (nim.length() == 15) {
            System.out.println("Login mahasiswa berhasil!");
        } else {
            System.out.println("Panjang NIM tidak valid. NIM harus terdiri dari 15 karakter.");
        }
    }
}
