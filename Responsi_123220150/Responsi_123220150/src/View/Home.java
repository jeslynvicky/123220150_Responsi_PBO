package View;

import Controller.ControllerMahasiswa;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Home extends JFrame {
    // Membuat sebuah instance bernama controller dari class "ControllerMahasiswa".
    ControllerMahasiswa controller;
    
    JLabel judul = new JLabel();
    JButton tombolMahasiswa = new JButton("Menu Mahasiswa");
    JButton tombolDosen = new JButton("Menu Dosen");

    public Home(String usernamee) {
        setTitle("Data Dosen dan Mahasiswa");
        setVisible(true);
        setSize(480, 240);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(judul);
        judul.setText("Welcome, " + usernamee); 
        judul.setBounds(10, 10, 480, 24);
        judul.setFont(new Font("Arial", Font.PLAIN, 20));
        
        add(judul); // Change "header" to "judul"
        add(tombolMahasiswa);
        add(tombolDosen);
        
        // Untuk memperbesar ukuran font
        judul.setFont(new Font("Sans-Serif", Font.PLAIN, 24)); // Change "header" to "judul"
        
        // Change "header" to "judul"
        judul.setBounds(20, 12, 440, 36);
        tombolDosen.setBounds(80, 56, 320, 40);
        tombolMahasiswa.setBounds(80, 100, 320, 40);

        /* 
          Memberikan event handling,
          Ketika tombol diklik, maka akan menuju ke halaman Mahasiswa atau Dosen.
         */
        tombolMahasiswa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new View.Mahasiswa.ViewData();
            }
        });
        tombolDosen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new View.Dosen.ViewData();
            }
        });
    }

    public Home() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
