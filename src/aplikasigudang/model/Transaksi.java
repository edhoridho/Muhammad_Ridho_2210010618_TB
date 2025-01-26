/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasigudang.model;

/**
 *
 * @author ACER A314
 */
import java.util.Date;

public class Transaksi {
    private int idTransaksi;
    private String jenisTransaksi;
    private String kodeBarang;
    private int jumlah;
    private Date tanggal;

    public Transaksi(int idTransaksi, String jenisTransaksi, String kodeBarang, int jumlah, Date tanggal) {
        this.idTransaksi = idTransaksi;
        this.jenisTransaksi = jenisTransaksi;
        this.kodeBarang = kodeBarang;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
    }

    public int getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(int idTransaksi) { this.idTransaksi = idTransaksi; }

    public String getJenisTransaksi() { return jenisTransaksi; }
    public void setJenisTransaksi(String jenisTransaksi) { this.jenisTransaksi = jenisTransaksi; }

    public String getKodeBarang() { return kodeBarang; }
    public void setKodeBarang(String kodeBarang) { this.kodeBarang = kodeBarang; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }
}