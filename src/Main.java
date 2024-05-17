import java.util.ArrayList;
import java.util.Scanner;

class MahasiswaInput {
    public static void main(String[] args) {
        ArrayList<String> namaMahasiswa = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int counter = 1;

        while (true) {
            System.out.print("Masukkan nama mahasiswa ke-" + counter + ": ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("selesai")) {
                break;
            }

            try {
                if (input.trim().isEmpty()) {
                    throw new IllegalArgumentException("Nama tidak boleh kosong");
                }
                namaMahasiswa.add(input);
                counter++;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nDaftar nama mahasiswa yang sudah diinput:");
        for (int i = 0; i < namaMahasiswa.size(); i++) {
            System.out.println("- " + namaMahasiswa.get(i));
        }
    }
}
