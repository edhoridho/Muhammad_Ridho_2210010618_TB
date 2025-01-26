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

}