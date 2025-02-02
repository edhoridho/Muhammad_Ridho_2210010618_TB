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
import aplikasigudang.model.Transaksi;
import aplikasigudang.model.TransaksiDAO;
import aplikasigudang.model.BarangDAO;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Date;
import javax.swing.JOptionPane;

public class TransaksiFrame extends javax.swing.JFrame {
    private TransaksiDAO transaksiDAO;
    private DefaultTableModel tableModel;
    /**
     * Creates new form TransaksiFrame
     */
    public TransaksiFrame() {
        initComponents();
        setLocationRelativeTo(null); // Pusatkan jendela
        loadBarangComboBox(); // Muat data barang ke ComboBox
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        
        try {
            transaksiDAO = new TransaksiDAO(); // Inisialisasi DAO
            tableModel = (DefaultTableModel) tblTransaksi.getModel(); // Ambil model tabel
            loadTransaksi(); // Muat data transaksi
            loadBarangComboBox(); // Muat data barang ke ComboBox
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }    
    }

//muat data
private void loadTransaksi() {
    try {
        tableModel.setRowCount(0); // Bersihkan data lama
        List<Transaksi> listTransaksi = transaksiDAO.getAllTransaksi(); // Ambil data
        for (Transaksi transaksi : listTransaksi) {
            tableModel.addRow(new Object[]{
                transaksi.getIdTransaksi(),
                transaksi.getJenisTransaksi(),
                transaksi.getJumlah(),
                transaksi.getTanggal()
            });
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

//mengisi combobox dari tabel barang
private void loadBarangComboBox() {
    try {
        // Bersihkan item lama di ComboBox
        cmbBarang.removeAllItems();
        
        // Ambil data barang dari database melalui DAO
        List<Barang> listBarang = new BarangDAO().getAllBarang();
        
        // Tambahkan setiap barang ke ComboBox
        for (Barang barang : listBarang) {
            // Format: "Kode Barang - Nama Barang"
            cmbBarang.addItem(barang.getKodeBarang() + " - " + barang.getNamaBarang());
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

//clear input
private void clearInputFields() {
    txtJumlah.setText("");
    dcTanggal.setDate(null);
    cmbJenisTransaksi.setSelectedIndex(0);
}

//validasi input 
private boolean validateInput() {
    if (cmbBarang.getSelectedItem() == null || cmbJenisTransaksi.getSelectedItem() == null
            || txtJumlah.getText().isEmpty() || dcTanggal.getDate() == null) {
        JOptionPane.showMessageDialog(this, "Semua input harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return false;
    }
    return true;
}


    private boolean validateStok(String kodeBarang, int jumlah) {
        try {
            BarangDAO barangDAO = new BarangDAO();
            List<Barang> listBarang = barangDAO.getAllBarang();

            for (Barang barang : listBarang) {
                if (barang.getKodeBarang().equals(kodeBarang)) {
                    return barang.getStok() >= jumlah;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
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
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        cmbBarang = new javax.swing.JComboBox<>();
        cmbJenisTransaksi = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        dcTanggal = new com.toedter.calendar.JDateChooser();
        btnSimpan = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Manajemen Transaksi");
        jPanel2.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 299;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Masukan Input Disini", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Imprint MT Shadow", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Barang");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(63, 22, 0, 0);
        jPanel3.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Imprint MT Shadow", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Jenis Transaksi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(34, 20, 0, 0);
        jPanel3.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Imprint MT Shadow", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jumlah");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(28, 20, 0, 0);
        jPanel3.add(jLabel4, gridBagConstraints);

        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 118;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 88, 0, 0);
        jPanel3.add(txtJumlah, gridBagConstraints);

        cmbBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 64;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(58, 88, 0, 0);
        jPanel3.add(cmbBarang, gridBagConstraints);

        cmbJenisTransaksi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masuk", "Keluar" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 62;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 88, 0, 0);
        jPanel3.add(cmbJenisTransaksi, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Imprint MT Shadow", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tanggal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 20, 0, 0);
        jPanel3.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 48, 0, 0);
        jPanel3.add(dcTanggal, gridBagConstraints);

        btnSimpan.setBackground(new java.awt.Color(51, 51, 51));
        btnSimpan.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(97, 6, 28, 25);
        jPanel3.add(btnSimpan, gridBagConstraints);

        btnRefresh.setBackground(new java.awt.Color(51, 51, 51));
        btnRefresh.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(97, 70, 28, 0);
        jPanel3.add(btnRefresh, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipady = 91;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 19, 21, 0);
        jPanel1.add(jPanel3, gridBagConstraints);

        tblTransaksi.setBackground(new java.awt.Color(102, 102, 102));
        tblTransaksi.setForeground(new java.awt.Color(255, 255, 255));
        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "Jenis", "Jumlah", "Tanggal"
            }
        ));
        jScrollPane1.setViewportView(tblTransaksi);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 319;
        gridBagConstraints.ipady = 362;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 21, 0);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try {
            // Validasi input
            if (!validateInput()) {
                return;
            }

            // Ambil data input
            String jenisTransaksi = cmbJenisTransaksi.getSelectedItem().toString();
            String kodeBarang = cmbBarang.getSelectedItem().toString().split(" - ")[0];
            int jumlah = Integer.parseInt(txtJumlah.getText());
            Date tanggal = dcTanggal.getDate();

            // Validasi stok untuk transaksi keluar
            if (jenisTransaksi.equalsIgnoreCase("Keluar")) {
                if (!validateStok(kodeBarang, jumlah)) {
                    JOptionPane.showMessageDialog(this, "Stok barang tidak mencukupi untuk transaksi keluar!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // Simpan transaksi ke database
            Transaksi transaksi = new Transaksi(0, jenisTransaksi, kodeBarang, jumlah, tanggal);
            transaksiDAO.tambahTransaksi(transaksi);

            // Tampilkan pesan sukses
            JOptionPane.showMessageDialog(this, "Transaksi berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            // Refresh data di tabel dan bersihkan input
            loadTransaksi();
            clearInputFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }      // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadBarangComboBox(); // Muat ulang data barang ke ComboBox        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshActionPerformed

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
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbBarang;
    private javax.swing.JComboBox<String> cmbJenisTransaksi;
    private com.toedter.calendar.JDateChooser dcTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtJumlah;
    // End of variables declaration//GEN-END:variables
}
