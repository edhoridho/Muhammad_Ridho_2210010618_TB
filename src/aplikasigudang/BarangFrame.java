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
        java.awt.GridBagConstraints gridBagConstraints;

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
        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnUpdate.setBackground(new java.awt.Color(51, 51, 51));
        btnUpdate.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Edit");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 32, 0, 0);
        jPanel1.add(btnUpdate, gridBagConstraints);

        btnDelete.setBackground(new java.awt.Color(51, 51, 51));
        btnDelete.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Hapus");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 32, 0, 0);
        jPanel1.add(btnDelete, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Manajemen Barang");
        jPanel2.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 388;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Masukkan Data Disini", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Imprint MT Shadow", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Barang");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(86, 20, 0, 0);
        jPanel3.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Imprint MT Shadow", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Barang");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        jPanel3.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Imprint MT Shadow", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Stok");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 20, 0, 0);
        jPanel3.add(jLabel4, gridBagConstraints);

        txtKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeBarangActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 175;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 20, 0, 123);
        jPanel3.add(txtKodeBarang, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 175;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 20, 0, 123);
        jPanel3.add(txtNamaBarang, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 175;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 20, 78, 123);
        jPanel3.add(txtStok, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 103;
        gridBagConstraints.ipady = 58;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 21, 6, 0);
        jPanel1.add(jPanel3, gridBagConstraints);

        btnAdd.setBackground(new java.awt.Color(51, 51, 51));
        btnAdd.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Tambah");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(100, 32, 0, 0);
        jPanel1.add(btnAdd, gridBagConstraints);

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        tblBarang.setBackground(new java.awt.Color(102, 102, 102));
        tblBarang.setForeground(new java.awt.Color(255, 255, 255));
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 346;
        gridBagConstraints.ipady = 347;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(26, 18, 6, 0);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        btnCetak.setBackground(new java.awt.Color(51, 51, 51));
        btnCetak.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(255, 255, 255));
        btnCetak.setText("Cetak");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 32, 0, 0);
        jPanel1.add(btnCetak, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1216, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
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
