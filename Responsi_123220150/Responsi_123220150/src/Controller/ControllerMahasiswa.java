package Controller;

import Model.Mahasiswa.*;
import View.Mahasiswa.*;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerMahasiswa {

    ViewData halamanTable;
    InputData halamanInput;
    EditData halamanEdit;

    InterfaceDAOMahasiswa daoMahasiswa;

    List<ModelMahasiswa> daftarMahasiswa;

    public ControllerMahasiswa(ViewData halamanTable) {
        this.halamanTable = halamanTable;
        this.daoMahasiswa = new DAOMahasiswa();
    }

    public ControllerMahasiswa(InputData halamanInput) {
        this.halamanInput = halamanInput;
        this.daoMahasiswa = new DAOMahasiswa();
    }

    public ControllerMahasiswa(EditData halamanEdit) {
        this.halamanEdit = halamanEdit;
        this.daoMahasiswa = new DAOMahasiswa();
    }

    public void showAllMahasiswa() {
        daftarMahasiswa = daoMahasiswa.getAll();
        ModelTable table = new ModelTable(daftarMahasiswa);
        halamanTable.getTableMahasiswa().setModel(table);
    }

    public void insertMahasiswa() {
        try {
            ModelMahasiswa mahasiswaBaru = new ModelMahasiswa();
            String nama = halamanInput.getInputNama();
            String nim = halamanInput.getInputNIM();
            String email = halamanInput.getInputEmail();
            String password = halamanInput.getInputPassword();
            String angkatan = halamanInput.getInputAngkatan();

            if ("".equals(nama) || "".equals(nim) || "".equals(email) || "".equals(password) || "".equals(angkatan)) {
                throw new Exception("Data tidak boleh kosong!");
            }

            mahasiswaBaru.setNama(nama);
            mahasiswaBaru.setNim(nim);
            mahasiswaBaru.setEmail(email);
            mahasiswaBaru.setPassword(password);
            mahasiswaBaru.setAngkatan(angkatan);

            daoMahasiswa.insert(mahasiswaBaru);

            JOptionPane.showMessageDialog(null, "Mahasiswa baru berhasil ditambahkan.");

            halamanInput.dispose();
            new ViewData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void editMahasiswa(int id) {
        try {
            ModelMahasiswa mahasiswaYangMauDiedit = new ModelMahasiswa();
            String nama = halamanEdit.getInputNama();
            String nim = halamanEdit.getInputNIM();
            String email = halamanEdit.getInputEmail();
            String password = halamanEdit.getInputPassword();
            String angkatan = halamanEdit.getInputAngkatan();

            if ("".equals(nama) || "".equals(nim) || "".equals(email) || "".equals(password) || "".equals(angkatan)) {
                throw new Exception("Data tidak boleh kosong!");
            }

            mahasiswaYangMauDiedit.setId(id);
            mahasiswaYangMauDiedit.setNama(nama);
            mahasiswaYangMauDiedit.setNim(nim);
            mahasiswaYangMauDiedit.setEmail(email);
            mahasiswaYangMauDiedit.setPassword(password);
            mahasiswaYangMauDiedit.setAngkatan(angkatan);

            daoMahasiswa.update(mahasiswaYangMauDiedit);

            JOptionPane.showMessageDialog(null, "Data mahasiswa berhasil diubah.");

            halamanEdit.dispose();
            new ViewData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteMahasiswa(Integer baris) {
        Integer id = (int) halamanTable.getTableMahasiswa().getValueAt(baris, 0);
        String nama = halamanTable.getTableMahasiswa().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Mahasiswa",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            daoMahasiswa.delete(id);
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");
            showAllMahasiswa();
        }
    }

    public void searchMahasiswa(String keyword) {
        List<ModelMahasiswa> searchResult = daoMahasiswa.search(keyword);
        if (searchResult.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tidak ada mahasiswa yang cocok dengan kata kunci pencarian.");
        } else {
            ModelTable table = new ModelTable(searchResult);
            halamanTable.getTableMahasiswa().setModel(table);
        }
    }
}
