/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasigudang;

/**
 *
 * @author ACER A314
 */
import aplikasigudang.model.Barang;
import aplikasigudang.model.BarangDAO;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JOptionPane;

public class BarangFrame extends javax.swing.JFrame {
    private BarangDAO barangDAO;
    private DefaultTableModel tableModel;
    /**
     * Creates new form BarangFrame
     */
    public BarangFrame() {
        initComponents();
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        tblBarang.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tblBarang.getSelectedRow() >= 0) {
                int selectedRow = tblBarang.getSelectedRow();
                txtKodeBarang.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtNamaBarang.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtStok.setText(tableModel.getValueAt(selectedRow, 2).toString());
            }
        });

      try {
        barangDAO = new BarangDAO(); // Inisialisasi BarangDAO
        tableModel = (DefaultTableModel) tblBarang.getModel(); // Ambil model tabel
        loadBarang(); // Muat data awal ke tabel
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
//memuat data dari database ke JTable    
private void loadBarang() {
    try {
        tableModel.setRowCount(0); // Bersihkan data lama
        List<Barang> listBarang = barangDAO.getAllBarang(); // Ambil data barang
        for (Barang barang : listBarang) {
            tableModel.addRow(new Object[]{barang.getKodeBarang(), barang.getNamaBarang(), barang.getStok()});
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

//bersihkan kolom inout
private void clearInputFields() {
    txtKodeBarang.setText("");
    txtNamaBarang.setText("");
    txtStok.setText("");
    txtKodeBarang.requestFocus(); // Fokuskan kembali ke kolom Kode Barang
}

//export pdf 
private void saveToPDF() {
    // Nama file PDF dengan format "Data Stok Gudang" + urutan
    String baseFileName = "Data Stok Gudang";
    int counter = 1;
    String fileName = baseFileName + counter + ".pdf";
    while (new java.io.File(fileName).exists()) {
        counter++;
        fileName = baseFileName + counter + ".pdf";
    }

    try {
        // Membuat objek Document untuk PDF dengan ukuran halaman A4
        com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4);

        // Menghubungkan dokumen dengan FileOutputStream menggunakan PdfWriter
        PdfWriter.getInstance(document, new FileOutputStream(fileName));

        // Membuka dokumen untuk penulisan
        document.open();

        // Menambahkan judul ke PDF dengan font tebal dan ukuran 16
        com.lowagie.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        com.lowagie.text.Paragraph title = new com.lowagie.text.Paragraph("Laporan Data Stok Gudang", titleFont);
        title.setAlignment(Element.ALIGN_CENTER); // Mengatur judul rata tengah
        document.add(title);

        // Menambahkan spasi sebelum tabel
        document.add(new com.lowagie.text.Paragraph(" "));
        document.add(new com.lowagie.text.Paragraph(" ")); // Spasi tambahan

        // Membuat tabel dengan jumlah kolom sesuai dengan tabel
        int columnCount = tblBarang.getColumnCount();
        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(columnCount);
        table.setWidthPercentage(100); // Mengatur lebar tabel agar memenuhi halaman

        // Menambahkan header ke tabel
        for (int i = 0; i < columnCount; i++) {
            com.lowagie.text.pdf.PdfPCell headerCell = new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(tblBarang.getColumnName(i)));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Rata tengah untuk header
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // Rata tengah vertikal
            table.addCell(headerCell); // Menambahkan header ke tabel
        }

        // Menambahkan data dari `tblBarang` ke tabel PDF
        for (int i = 0; i < tblBarang.getRowCount(); i++) { // Iterasi baris
            for (int j = 0; j < columnCount; j++) { // Iterasi kolom
                Object cellValue = tblBarang.getValueAt(i, j);
                com.lowagie.text.pdf.PdfPCell dataCell = new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(cellValue == null ? "" : cellValue.toString()));
                dataCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Rata tengah untuk data
                dataCell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // Rata tengah vertikal
                table.addCell(dataCell); // Menambahkan data ke tabel
            }
        }

        // Menambahkan tabel ke dokumen PDF
        document.add(table);

        // Menutup dokumen setelah selesai
        document.close();

        // Menampilkan pesan sukses
        JOptionPane.showMessageDialog(this, "Laporan berhasil disimpan ke " + fileName, "Sukses", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
        // Menampilkan pesan error jika terjadi masalah saat menyimpan data
        JOptionPane.showMessageDialog(this, "Error menyimpan laporan ke PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtKodeBarang = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        btnCetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        btnUpdate.setBackground(new java.awt.Color(51, 51, 51));
        btnUpdate.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Edit");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(51, 51, 51));
        btnDelete.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Hapus");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Manajemen Barang");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(15, 15, 15))
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Masukkan Data Disini", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Barang");

        jLabel3.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Barang");

        jLabel4.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Stok");

        txtKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtKodeBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(txtStok)
                    .addComponent(txtNamaBarang)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtKodeBarang, txtNamaBarang, txtStok});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        btnAdd.setBackground(new java.awt.Color(51, 51, 51));
        btnAdd.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Tambah");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(153, 153, 153));

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Stok"
            }
        ));
        jScrollPane1.setViewportView(tblBarang);

        btnCetak.setBackground(new java.awt.Color(51, 51, 51));
        btnCetak.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak.setText("Cetak");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdd, btnCetak, btnDelete, btnUpdate});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnCetak)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            // Cek apakah baris tabel dipilih
            int selectedRow = tblBarang.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Pilih baris data yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Ambil kode barang dari baris yang dipilih
            String kodeBarang = tableModel.getValueAt(selectedRow, 0).toString();

            // Tampilkan dialog konfirmasi
            int response = JOptionPane.showConfirmDialog(this, 
                    "Apakah Anda yakin ingin menghapus data dengan kode barang: " + kodeBarang + "?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            // Jika pengguna memilih "Yes", lanjutkan penghapusan
            if (response == JOptionPane.YES_OPTION) {
                barangDAO.hapusBarang(kodeBarang); // Hapus barang dari database
                loadBarang(); // Perbarui data di tabel
                clearInputFields(); // Bersihkan input
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }    // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
        int selectedRow = tblBarang.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Pilih baris data yang ingin diubah!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String kodeBarang = txtKodeBarang.getText();
        String namaBarang = txtNamaBarang.getText();
        String stokStr = txtStok.getText();

        if (kodeBarang.isEmpty() || namaBarang.isEmpty() || stokStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int stok = Integer.parseInt(stokStr);

        Barang barang = new Barang(0, kodeBarang, namaBarang, stok);
        barangDAO.updateBarang(barang); // Panggil DAO untuk update
        loadBarang(); // Refresh tabel
        clearInputFields(); // Bersihkan input

        JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }    // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeBarangActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            String kodeBarang = txtKodeBarang.getText();
            String namaBarang = txtNamaBarang.getText();
            String stokStr = txtStok.getText();

            // Validasi input
            if (kodeBarang.isEmpty() || namaBarang.isEmpty() || stokStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int stok = Integer.parseInt(stokStr); // Konversi stok ke integer

            // Tambahkan barang menggunakan DAO
            Barang barang = new Barang(0, kodeBarang, namaBarang, stok);
            barangDAO.tambahBarang(barang);

            // Refresh tabel dan bersihkan input
            loadBarang();
            clearInputFields();

            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        saveToPDF(); // TODO add your handling code here:
    }//GEN-LAST:event_btnCetakActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BarangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BarangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BarangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BarangFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BarangFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
