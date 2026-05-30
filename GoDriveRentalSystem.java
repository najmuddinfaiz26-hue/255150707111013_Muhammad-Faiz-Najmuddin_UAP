package UAP;
import java.util.ArrayList;
import java.util.Locale;

public class GoDriveRentalSystem {
    private ArrayList<Kendaraan> daftarKendaraan;

    public GoDriveRentalSystem() {
        daftarKendaraan = new ArrayList<>();
       
        daftarKendaraan.add(new Mobil("MBL01", "Toyota Avanza", 350000, 7));
        daftarKendaraan.add(new Mobil("MBL02", "Daihatsu Sigra", 300000, 7));
        daftarKendaraan.add(new Mobil("MBL03", "Honda Brio", 280000, 5));
        daftarKendaraan.add(new Motor("MTR01", "Honda Vario", 80000, "Matik"));
        daftarKendaraan.add(new Motor("MTR02", "Yamaha NMAX", 100000, "Matik"));
        daftarKendaraan.add(new Motor("MTR03", "Kawasaki KLX", 90000, "Manual"));
    }

    public void tambahKendaraan(Kendaraan k) {
        
        for (Kendaraan unitAda : daftarKendaraan) {
            if (unitAda.getKodeKendaraan().equalsIgnoreCase(k.getKodeKendaraan())) {
                System.out.printf("[ERROR] Kode kendaraan %s sudah terdaftar! Gagal menambahkan.\n", k.getKodeKendaraan());
                return;
            }
        }

        
        daftarKendaraan.add(k);
        System.out.printf("[INFO] Kendaraan berhasil ditambahkan: %s (%s)\n", k.getNamaKendaraan(), k.getKodeKendaraan());
    }

    public void tampilkanDaftarKendaraan() {
        System.out.println("\n=== DAFTAR ARMADA GODRIVE ===");
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarKendaraan.get(i).tampilInfo();
        }
    }

    public void sewaKendaraan(String kode, int lamaSewa, boolean isVIP) throws KendaraanTidakTersedia {
        Kendaraan target = null;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                target = k;
                break;
            }
        }

        
        if (target == null || !target.isTersedia()) {
            throw new KendaraanTidakTersedia("Kendaraan dengan kode " + kode + " gagal disewa. Alasan: Kendaraan sedang disewa atau tidak ditemukan!");
        }

        
        target.setTersedia(false);

        double biayaDasar = target.hitungBiayaDasar(lamaSewa);
        double tambahan = 0;
        String tambahanStr = "";

       
        if (target instanceof Mobil) {
            Mobil m = (Mobil) target;
            if (m.getJumlahKursi() > 5) {
                tambahan = 50000;
               
                tambahanStr = String.format(Locale.US, "Tambahan Kursi (>5): Rp %,.0f\n", tambahan);
            }
        } else if (target instanceof Motor) {
            Motor mot = (Motor) target;
            if (mot.getJenisTransmisi().equalsIgnoreCase("Matik")) {
                tambahan = 10000 * lamaSewa;
                tambahanStr = String.format(Locale.US, "Tambahan Asuransi (Matik): Rp %,.0f\n", tambahan);
            }
        }

        double subtotal = biayaDasar + tambahan;
        double diskonVIP = 0;
        double diskonDurasi = 0;

        if (isVIP) {
            diskonVIP = 0.10 * subtotal;
        }
        if (lamaSewa > 7) {
            diskonDurasi = 0.05 * subtotal;
        }

        double totalBiaya = subtotal - diskonVIP - diskonDurasi;

        
        System.out.println("\n=== TRANSAKSI SEWA GODRIVE ===");
        System.out.println("Kendaraan Berhasil Disewa!");
        System.out.printf("Unit          : %s (%s)\n", target.getNamaKendaraan(), target.getKodeKendaraan());
        System.out.printf("Lama Sewa     : %d hari\n", lamaSewa);
        System.out.printf(Locale.US, "Biaya Dasar Harian : Rp %,.0f\n", biayaDasar);
        if (!tambahanStr.isEmpty()) {
            System.out.print(tambahanStr);
        }
        if (diskonVIP > 0) {
            System.out.printf(Locale.US, "Diskon Member VIP (10%%): -Rp %,.0f\n", diskonVIP);
        }
        if (diskonDurasi > 0) {
            System.out.printf(Locale.US, "Diskon Durasi Sewa (>7 Hari) (5%%): -Rp %,.0f\n", diskonDurasi);
        }
        System.out.println("-------------------------------------");
        System.out.printf(Locale.US, "TOTAL BIAYA AKHIR: Rp %,.0f\n", totalBiaya);
    }

    public void kembalikanKendaraan(String kode) {
         Kendaraan target = null;
         for (Kendaraan k : daftarKendaraan) {
             if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                 target = k;
                 break;
             }
         }

         if (target != null && !target.isTersedia()) {
             target.setTersedia(true);
             System.out.printf("[INFO] Kendaraan %s (%s) berhasil dikembalikan. Status: Tersedia.\n", target.getNamaKendaraan(), target.getKodeKendaraan());
         } else {
             System.out.println("[ERROR] Kendaraan tidak ditemukan atau sedang tidak dalam status disewa.");
         }
    }
}
