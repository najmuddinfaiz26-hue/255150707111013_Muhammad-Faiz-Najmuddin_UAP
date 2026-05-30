package UAP;
import java.util.Locale;

public class Motor extends Kendaraan {
    private String jenisTransmisi;

    public Motor(String kodeKendaraan, String namaKendaraan, double hargaSewaPerHari, String jenisTransmisi) {
        super(kodeKendaraan, namaKendaraan, hargaSewaPerHari);
        this.jenisTransmisi = jenisTransmisi;
    }

    public String getJenisTransmisi() { return jenisTransmisi; }
    public void setJenisTransmisi(String jenisTransmisi) { this.jenisTransmisi = jenisTransmisi; }

    @Override
    public double hitungBiayaDasar(int lamaSewa) {
        return lamaSewa * getHargaSewaPerHari();
    }

    @Override
    public void tampilInfo() {
        String statusStr = isTersedia() ? "Tersedia" : "Tidak Tersedia";
        System.out.printf(Locale.US, "[MOTOR] Kode: %s | Nama: %-17s | Transmisi: %s | Tarif: Rp%,.0f/hari | Status: %s\n",
                getKodeKendaraan(), getNamaKendaraan(), jenisTransmisi, getHargaSewaPerHari(), statusStr);
    }
}
