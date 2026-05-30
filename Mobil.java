package UAP;
import java.util.Locale;

public class Mobil extends Kendaraan {
    private int jumlahKursi;

    public Mobil(String kodeKendaraan, String namaKendaraan, double hargaSewaPerHari, int jumlahKursi) {
        super(kodeKendaraan, namaKendaraan, hargaSewaPerHari);
        this.jumlahKursi = jumlahKursi;
    }

    public int getJumlahKursi() { return jumlahKursi; }
    public void setJumlahKursi(int jumlahKursi) { this.jumlahKursi = jumlahKursi; }

    @Override
    public double hitungBiayaDasar(int lamaSewa) {
        return lamaSewa * getHargaSewaPerHari();
    }

    @Override
    public void tampilInfo() {
        String statusStr = isTersedia() ? "Tersedia" : "Tidak Tersedia";
        // Menggunakan Locale.US agar format ribuan otomatis menggunakan koma (,) sesuai screenshot
        System.out.printf(Locale.US, "[MOBIL] Kode: %s | Nama: %-17s | Kursi: %d | Tarif: Rp%,.0f/hari | Status: %s\n",
                getKodeKendaraan(), getNamaKendaraan(), jumlahKursi, getHargaSewaPerHari(), statusStr);
    }
}
