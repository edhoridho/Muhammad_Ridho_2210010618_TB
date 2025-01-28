/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplikasigudang;

import aplikasigudang.model.Transaksi;
import aplikasigudang.model.TransaksiDAO;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

/**
 *
 * @author ACER A314
 */
public class LaporanFrame extends javax.swing.JFrame {
    private TransaksiDAO transaksiDAO;

    /**
     * Creates new form LaporanFrame
     */
    public LaporanFrame() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        try {
        transaksiDAO = new TransaksiDAO(); // Inisialisasi DAO
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }    
    }

private void loadLaporanToTable(List<Transaksi> laporan) {
    DefaultTableModel model = (DefaultTableModel) tblReport.getModel();
    model.setRowCount(0); // Bersihkan tabel
    for (Transaksi transaksi : laporan) {
        model.addRow(new Object[]{
            transaksi.getIdTransaksi(),
            transaksi.getJenisTransaksi(),
            transaksi.getKodeBarang(),
            transaksi.getJumlah(),
            transaksi.getTanggal()
        });
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        JPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dcTanggalAwal = new com.toedter.calendar.JDateChooser();
        dcTanggalAkhir = new com.toedter.calendar.JDateChooser();
        btnReport = new javax.swing.JButton();
        btnExportPDF = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        dcTanggalTunggal = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReport = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reporting");
        jPanel2.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 361;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jPanel2, gridBagConstraints);

        JPanel3.setBackground(new java.awt.Color(102, 102, 102));
        JPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Masukkan Data Disini", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        JPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Imprint MT Shadow", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tanggal Awal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 40, 0, 0);
        JPanel3.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Imprint MT Shadow", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tanggal Akhir");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipady = -1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 40, 0, 0);
        JPanel3.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 149;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 40, 0, 0);
        JPanel3.add(dcTanggalAwal, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 149;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 40, 0, 0);
        JPanel3.add(dcTanggalAkhir, gridBagConstraints);

        btnReport.setBackground(new java.awt.Color(51, 51, 51));
        btnReport.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnReport.setForeground(new java.awt.Color(255, 255, 255));
        btnReport.setText("Load Report");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(70, 14, 20, 0);
        JPanel3.add(btnReport, gridBagConstraints);

        btnExportPDF.setBackground(new java.awt.Color(51, 51, 51));
        btnExportPDF.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnExportPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnExportPDF.setText("Cetak PDF");
        btnExportPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportPDFActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(70, 6, 20, 0);
        JPanel3.add(btnExportPDF, gridBagConstraints);

        btnExcel.setBackground(new java.awt.Color(51, 51, 51));
        btnExcel.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnExcel.setText("Cetak Excel");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(70, 6, 20, 20);
        JPanel3.add(btnExcel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 149;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 40, 0, 0);
        JPanel3.add(dcTanggalTunggal, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Imprint MT Shadow", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tanggal Tunggal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(36, 40, 0, 0);
        JPanel3.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 25, 12, 0);
        jPanel1.add(JPanel3, gridBagConstraints);

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 102));

        tblReport.setBackground(new java.awt.Color(102, 102, 102));
        tblReport.setForeground(new java.awt.Color(255, 255, 255));
        tblReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "Jenis", "Kode Barang", "Jumlah", "Tanggal"
            }
        ));
        jScrollPane1.setViewportView(tblReport);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 352;
        gridBagConstraints.ipady = 341;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(26, 18, 12, 0);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        try {
            // Validasi Input Bertumpuk
            if ((dcTanggalTunggal.getDate() != null) &&
                (dcTanggalAwal.getDate() != null || dcTanggalAkhir.getDate() != null)) {
                JOptionPane.showMessageDialog(this, 
                    "Isi hanya salah satu filter: Tanggal Tunggal atau Rentang Tanggal (Awal dan Akhir)", 
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validasi Rentang Tanggal
            if (dcTanggalAwal.getDate() != null && dcTanggalAkhir.getDate() != null) {
                Date tanggalAwal = dcTanggalAwal.getDate();
                Date tanggalAkhir = dcTanggalAkhir.getDate();

                if (tanggalAwal.after(tanggalAkhir)) {
                    JOptionPane.showMessageDialog(this, "Tanggal awal tidak boleh setelah tanggal akhir!", 
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                List<Transaksi> laporan = transaksiDAO.getLaporanByRentangTanggal(
                    new java.sql.Date(tanggalAwal.getTime()), 
                    new java.sql.Date(tanggalAkhir.getTime())
                );
                loadLaporanToTable(laporan);
                return;
            }

            // Validasi Tanggal Tunggal
            if (dcTanggalTunggal.getDate() != null) {
                Date tanggalTunggal = dcTanggalTunggal.getDate();
                List<Transaksi> laporan = transaksiDAO.getLaporanByTanggalTunggal(
                    new java.sql.Date(tanggalTunggal.getTime())
                );
                loadLaporanToTable(laporan);
                return;
            }

            // Validasi Jika Tidak Ada Input
            JOptionPane.showMessageDialog(this, "Pilih salah satu filter tanggal untuk menampilkan laporan!", 
                    "Peringatan", JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }    // TODO add your handling code here:
    }//GEN-LAST:event_btnReportActionPerformed

    private void saveToPDF() {
        // Membuat nama file berdasarkan tanggal dan waktu
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String formattedDate = formatter.format(new Date());
        String fileName = "Report_" + formattedDate + ".pdf";

        try {
            // Membuat objek Document untuk PDF dengan ukuran halaman A4
            com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4);

            // Menghubungkan dokumen dengan FileOutputStream menggunakan PdfWriter
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            // Membuka dokumen untuk penulisan
            document.open();

            // Menambahkan judul ke PDF dengan font tebal dan ukuran 16
            com.lowagie.text.Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            com.lowagie.text.Paragraph title = new com.lowagie.text.Paragraph("Laporan Data Thrift", titleFont);
            title.setAlignment(Element.ALIGN_CENTER); // Mengatur judul rata tengah
            document.add(title);

            // Menambahkan spasi sebelum tabel
            document.add(new com.lowagie.text.Paragraph(" "));
            document.add(new com.lowagie.text.Paragraph(" ")); // Spasi tambahan

            // Membuat tabel dengan jumlah kolom sesuai dengan tabel
            int columnCount = tblReport.getColumnCount();
            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(columnCount);
            table.setWidthPercentage(100); // Mengatur lebar tabel agar memenuhi halaman

            // Menambahkan header ke tabel
            for (int i = 0; i < columnCount; i++) {
                com.lowagie.text.pdf.PdfPCell headerCell = new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(tblReport.getColumnName(i)));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER); // Rata tengah untuk header
                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // Rata tengah vertikal
                table.addCell(headerCell); // Menambahkan header ke tabel
            }

            // Menambahkan data dari `tblReport` ke tabel PDF
            for (int i = 0; i < tblReport.getRowCount(); i++) { // Iterasi baris
                for (int j = 0; j < columnCount; j++) { // Iterasi kolom
                    Object cellValue = tblReport.getValueAt(i, j);
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


    
    private void btnExportPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportPDFActionPerformed
        saveToPDF();// TODO add your handling code here:
    }//GEN-LAST:event_btnExportPDFActionPerformed

    private void saveToCSV() {
        // Membuat nama file berdasarkan tanggal dan waktu
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String formattedDate = formatter.format(new Date());
        String fileName = "Report_" + formattedDate + ".csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write the header row
            writer.write("ID Transaksi, Jenis Transaksi, Kode Barang, Jumlah, Tanggal");
            writer.newLine();

            // Write data from the table
            for (int i = 0; i < tblReport.getRowCount(); i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 0; j < tblReport.getColumnCount(); j++) {
                    // Append each cell value followed by a comma, except for the last one
                    row.append(tblReport.getValueAt(i, j));
                    if (j < tblReport.getColumnCount() - 1) {
                        row.append(",");
                    }
                }
                writer.write(row.toString());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan ke " + fileName, "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error menyimpan data ke CSV: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        saveToCSV();   // TODO add your handling code here:
    }//GEN-LAST:event_btnExcelActionPerformed

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
            java.util.logging.Logger.getLogger(LaporanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaporanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaporanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaporanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LaporanFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel3;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnExportPDF;
    private javax.swing.JButton btnReport;
    private com.toedter.calendar.JDateChooser dcTanggalAkhir;
    private com.toedter.calendar.JDateChooser dcTanggalAwal;
    private com.toedter.calendar.JDateChooser dcTanggalTunggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReport;
    // End of variables declaration//GEN-END:variables
}
