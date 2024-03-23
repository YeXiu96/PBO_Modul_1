import java.util.ArrayList;
import java.util.Scanner;

class Mahasiswa {
    static String universitas;
    String nama;
    String nim;
    String jurusan;
//Pakai String karena int tidak cukup untuk menampung 15 digit Nim
    public Mahasiswa(String nama, String nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
        this.universitas = universitas;
    }

    public String tampilDataMahasiswa() {
        return "Nama: " + nama + "\nNIM: " + nim + "\nJurusan: " + jurusan;
    }

    public static String tampilUniversitas() {
        return "Nama Universitas: " + universitas;
    }
}

public class Main {
    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void inputMahasiswa() {
        System.out.print("Masukkan nama mahasiswa: ");
        String nama = scanner.nextLine();
        String nim;
        do {
            System.out.print("Masukkan NIM mahasiswa : ");
            nim = scanner.nextLine();
            if (nim.length() != 15) {
                System.out.println("NIM harus terdiri dari 15 digit. Silakan coba lagi.");
            }
        } while (nim.length() != 15);

        System.out.print("Masukkan jurusan mahasiswa: ");
        String jurusan = scanner.nextLine();
        daftarMahasiswa.add(new Mahasiswa(nama, nim, jurusan));
        System.out.println("Data mahasiswa berhasil ditambahkan.");
    }

    public static void tampilMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Tambah Data Mahasiswa");
        System.out.println("2. Tampilkan Data Mahasiswa");
        System.out.println("3. Keluar");
    }

    public static void main(String[] args) {
        while (true) {
            tampilMenu();
            System.out.print("Pilihan Anda: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    inputMahasiswa();
                    break;
                case "2":
                    System.out.println("\nData Mahasiswa:");
                    for (Mahasiswa mahasiswa : daftarMahasiswa) {
                        System.out.println(mahasiswa.tampilDataMahasiswa());
                    }
                    break;
                case "3":
                    System.out.println("Terima kasih!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Menu tidak valid. Silakan coba lagi.");
            }
        }
    }
}
