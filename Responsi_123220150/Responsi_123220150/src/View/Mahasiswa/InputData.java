package View.Mahasiswa;

import Controller.ControllerMahasiswa;
import java.awt.event.*;
import javax.swing.*;

public class InputData extends JFrame {

    ControllerMahasiswa controller;

    JLabel header = new JLabel("Input Mahasiswa");
    JLabel labelInputNama = new JLabel("Nama");
    JLabel labelInputNIM = new JLabel("NIM");
    JLabel labelInputEmail = new JLabel("Email");
    JLabel labelInputPassword = new JLabel("Password");
    JLabel labelInputAngkatan = new JLabel("Angkatan");
    JTextField inputNama = new JTextField();
    JTextField inputNIM = new JTextField();
    JTextField inputEmail = new JTextField();
    JTextField inputPassword = new JTextField();
    JTextField inputAngkatan = new JTextField();
    JButton tombolTambah = new JButton("Tambah Mahasiswa");
    JButton tombolKembali = new JButton("Kembali");

    public InputData() {
        setTitle("Input Mahasiswa");
        setVisible(true);
        setSize(480, 420);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(header);
        add(labelInputNama);
        add(labelInputNIM);
        add(labelInputEmail);
        add(labelInputPassword);
        add(labelInputAngkatan);
        add(inputNama);
        add(inputNIM);
        add(inputEmail);
        add(inputPassword);
        add(inputAngkatan);
        add(tombolTambah);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        labelInputNama.setBounds(20, 32, 440, 24);
        inputNama.setBounds(18, 56, 440, 36);
        labelInputNIM.setBounds(20, 96, 440, 24);
        inputNIM.setBounds(18, 120, 440, 36);
        labelInputEmail.setBounds(20, 160, 440, 24);
        inputEmail.setBounds(18, 184, 440, 36);
        labelInputPassword.setBounds(20, 224, 440, 24);
        inputPassword.setBounds(18, 248, 440, 36);
        labelInputAngkatan.setBounds(20, 288, 440, 24);
        inputAngkatan.setBounds(18, 312, 440, 36);
        tombolKembali.setBounds(20, 360, 215, 40);
        tombolTambah.setBounds(240, 360, 215, 40);

        controller = new ControllerMahasiswa(this);

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewData();
            }
        });

        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.insertMahasiswa();
            }
        });
    }

    public String getInputNama() {
        return inputNama.getText();
    }

    public String getInputNIM() {
        return inputNIM.getText();
    }

    public String getInputEmail() {
        return inputEmail.getText();
    }    
    
    public String getInputPassword() {
        return inputPassword.getText();
    }    
    
    public String getInputAngkatan() {
        return inputAngkatan.getText();
    }    
}
