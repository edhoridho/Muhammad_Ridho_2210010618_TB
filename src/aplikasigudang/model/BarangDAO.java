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

public class BarangDAO {
    private Connection connection;

    public BarangDAO() throws Exception {
        connection = KoneksiDatabase.getConnection();
    }

    public void tambahBarang(Barang barang) throws SQLException {
        String query = "INSERT INTO barang (kode_barang, nama_barang, stok) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, barang.getKodeBarang());
        ps.setString(2, barang.getNamaBarang());
        ps.setInt(3, barang.getStok());
        ps.executeUpdate();
    }

    public void updateBarang(Barang barang) throws SQLException {
        String query = "UPDATE barang SET nama_barang = ?, stok = ? WHERE kode_barang = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, barang.getNamaBarang());
        ps.setInt(2, barang.getStok());
        ps.setString(3, barang.getKodeBarang());
        ps.executeUpdate();
    }

    public void hapusBarang(String kodeBarang) throws SQLException {
        String query = "DELETE FROM barang WHERE kode_barang = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, kodeBarang);
        ps.executeUpdate();
    }

    public List<Barang> getAllBarang() throws SQLException {
        List<Barang> listBarang = new ArrayList<>();
        String query = "SELECT * FROM barang";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            listBarang.add(new Barang(
                rs.getInt("id_barang"),
                rs.getString("kode_barang"),
                rs.getString("nama_barang"),
                rs.getInt("stok")
            ));
        }
        return listBarang;
    }
}