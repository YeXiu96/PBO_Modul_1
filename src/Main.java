import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan nama Anda:");
        String nama = scanner.nextLine();

        System.out.println("Masukkan jenis kelamin (P/L):");
        String jenisKelaminInput = scanner.nextLine();
        String jenisKelamin = jenisKelaminInput.equalsIgnoreCase("P") ? "Perempuan" : "Laki-laki";

        System.out.println("Masukkan tanggal lahir (format: yyyy-MM-dd):");
        String tanggalLahirString = scanner.nextLine();
        LocalDate tanggalLahir = LocalDate.parse(tanggalLahirString, DateTimeFormatter.ISO_DATE);
        LocalDate tanggalSekarang = LocalDate.now();
        int tahun = tanggalSekarang.getYear() - tanggalLahir.getYear();
        int bulan = tanggalSekarang.getMonthValue() - tanggalLahir.getMonthValue();
        if (bulan < 0) {
            tahun--;
            bulan += 12;
        }

        System.out.println("\n===== Data Diri =====");
        System.out.println("Nama: " + nama);
        System.out.println("Jenis Kelamin: " + jenisKelamin);
        System.out.println("Umur: " + tahun + " tahun " + bulan + " bulan");

        scanner.close();
    }
}
