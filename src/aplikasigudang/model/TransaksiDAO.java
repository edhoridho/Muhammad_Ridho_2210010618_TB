/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasigudang.model;

/**
 *
 * @author ACER A314
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {
    private Connection connection;

    public TransaksiDAO() throws Exception {
        connection = KoneksiDatabase.getConnection();
    }

    public void tambahTransaksi(Transaksi transaksi) throws SQLException {
        String query = "INSERT INTO transaksi (kode_barang, jenis_transaksi, jumlah, tanggal) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, transaksi.getKodeBarang());
        ps.setString(2, transaksi.getJenisTransaksi());
        ps.setInt(3, transaksi.getJumlah());
        ps.setDate(4, new java.sql.Date(transaksi.getTanggal().getTime()));
        ps.executeUpdate();

        // Update stok barang berdasarkan jenis transaksi
        updateStokBarang(transaksi.getKodeBarang(), transaksi.getJenisTransaksi(), transaksi.getJumlah());
    }

    private void updateStokBarang(String kodeBarang, String jenisTransaksi, int jumlah) throws SQLException {
        // Ambil stok barang saat ini
        String queryGetStok = "SELECT stok FROM barang WHERE kode_barang = ?";
        PreparedStatement psGet = connection.prepareStatement(queryGetStok);
        psGet.setString(1, kodeBarang);
        ResultSet rs = psGet.executeQuery();

        int stokSekarang = 0;
        if (rs.next()) {
            stokSekarang = rs.getInt("stok");
        }

        // Hitung stok baru berdasarkan jenis transaksi
        int stokBaru = stokSekarang;
        if (jenisTransaksi.equalsIgnoreCase("Masuk")) {
            stokBaru += jumlah;
        } else if (jenisTransaksi.equalsIgnoreCase("Keluar")) {
            stokBaru -= jumlah;
            if (stokBaru < 0) { // Cegah stok negatif
                throw new SQLException("Stok tidak mencukupi untuk transaksi keluar!");
            }
        }

        // Update stok barang di database
        String queryUpdateStok = "UPDATE barang SET stok = ? WHERE kode_barang = ?";
        PreparedStatement psUpdate = connection.prepareStatement(queryUpdateStok);
        psUpdate.setInt(1, stokBaru);
        psUpdate.setString(2, kodeBarang);
        psUpdate.executeUpdate();
    }

    public List<Transaksi> getAllTransaksi() throws SQLException {
        List<Transaksi> listTransaksi = new ArrayList<>();
        String query = "SELECT * FROM transaksi";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            listTransaksi.add(new Transaksi(
                rs.getInt("id_transaksi"),
                rs.getString("jenis_transaksi"),
                rs.getString("id_barang"),
                rs.getInt("jumlah"),
                rs.getDate("tanggal")
            ));
        }
        return listTransaksi;
    }
    
    public List<Transaksi> getLaporan(Date tanggalAwal, Date tanggalAkhir) throws SQLException {
    List<Transaksi> listLaporan = new ArrayList<>();
    String query = "SELECT * FROM transaksi WHERE tanggal BETWEEN ? AND ?";
    PreparedStatement ps = connection.prepareStatement(query);
    ps.setDate(1, new java.sql.Date(tanggalAwal.getTime()));
    ps.setDate(2, new java.sql.Date(tanggalAkhir.getTime()));
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        listLaporan.add(new Transaksi(
            rs.getInt("id_transaksi"),
            rs.getString("jenis_transaksi"),
            rs.getString("id_barang"),
            rs.getInt("jumlah"),
            rs.getDate("tanggal")
        ));
    }
    return listLaporan;
    }
  
//untuk laporan transaksi tanggal tunggal(harian)
    public List<Transaksi> getLaporanByTanggalTunggal(Date tanggal) throws SQLException {
        List<Transaksi> list = new ArrayList<>();
        String query = "SELECT * FROM transaksi WHERE DATE(tanggal) = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(tanggal.getTime()));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Transaksi(
                    rs.getInt("id_transaksi"),
                    rs.getString("jenis_transaksi"),
                    rs.getString("kode_barang"),
                    rs.getInt("jumlah"),
                    rs.getDate("tanggal")
            ));
        }
        return list;
    }

    //untuk laporan transaksi antar tanggal (dengan rentang waktu)
    public List<Transaksi> getLaporanByRentangTanggal(Date tanggalAwal, Date tanggalAkhir) throws SQLException {
        List<Transaksi> list = new ArrayList<>();
        String query = "SELECT * FROM transaksi WHERE DATE(tanggal) BETWEEN ? AND ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(tanggalAwal.getTime()));
        ps.setDate(2, new java.sql.Date(tanggalAkhir.getTime()));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Transaksi(
                    rs.getInt("id_transaksi"),
                    rs.getString("jenis_transaksi"),
                    rs.getString("kode_barang"),
                    rs.getInt("jumlah"),
                    rs.getDate("tanggal")
            ));
        }
        return list;
    }

}