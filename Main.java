package UAP;

import java.util.Scanner;

public class Main {
    private static GoDriveRentalSystem system = new GoDriveRentalSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws KendaraanTidakTersedia {
        int menu = 0;
        do {
            System.out.println("\n======= MENU GO DRIVE RENTAL SYSTEM =======");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tampilkan Daftar Armada");
            System.out.println("3. Sewa Kendaraan");
            System.out.println("4. Kembalikan Kendaraan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            
            if (scanner.hasNextInt()) {
                menu = scanner.nextInt();
                scanner.nextLine(); 
                switch (menu) {
                    case 1:
                        menuTambah();
                        break;
                    case 2:
                        system.tampilkanDaftarKendaraan();
                        break;
                    case 3:
                        menuSewa();
                        break;
                    case 4:
                        menuKembalikan();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Pilihan menu tidak valid!");
                }
            } else {
                System.out.println("Masukkan input angka yang valid.");
                scanner.nextLine();
            }
        } while (menu != 5);
    }

    private static void menuTambah() {
        System.out.print("Masukkan jenis kendaraan (mobil/motor): ");
        String jenis = scanner.nextLine().trim();
        System.out.print("Masukkan kode kendaraan: ");
        String kode = scanner.nextLine().trim();
        System.out.print("Masukkan nama kendaraan: ");
        String nama = scanner.nextLine().trim();
        System.out.print("Masukkan harga sewa per hari: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();

        if (jenis.equalsIgnoreCase("mobil")) {
            System.out.print("Masukkan kapasitas kursi: ");
            int kursi = scanner.nextInt();
            scanner.nextLine();
            system.tambahKendaraan(new Mobil(kode, nama, harga, kursi));
        } else if (jenis.equalsIgnoreCase("motor")) {
            System.out.print("Masukkan jenis transmisi: ");
            String transmisi = scanner.nextLine().trim();
            system.tambahKendaraan(new Motor(kode, nama, harga, transmisi));
        } else {
            System.out.println("[ERROR] Jenis kendaraan tidak dikenal.");
        }
    }

    private static void menuSewa() throws KendaraanTidakTersedia {
        System.out.print("Masukkan kode kendaraan yang ingin disewa: ");
        String kode = scanner.nextLine().trim();
        System.out.print("Masukkan durasi sewa (dalam hari): ");
        int durasi = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Apakah Anda Member VIP? (y/n): ");
        String vipInput = scanner.nextLine().trim();
        boolean isVIP = vipInput.equalsIgnoreCase("y");

        
        system.sewaKendaraan(kode, durasi, isVIP);
    }

    private static void menuKembalikan() {
        System.out.print("Masukkan kode kendaraan yang ingin dikembalikan: ");
        String kode = scanner.nextLine().trim();
        system.kembalikanKendaraan(kode);
    }
}
