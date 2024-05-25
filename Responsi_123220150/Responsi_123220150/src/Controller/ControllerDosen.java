package Controller;

import Model.Dosen.*;
import View.Dosen.*;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerDosen {

    ViewData halamanTable;
    InputData halamanInput;
    EditData halamanEdit;

    InterfaceDAODosen daoDosen;
    List<ModelDosen> daftarDosen;

    public ControllerDosen(ViewData halamanTable) {
        this.halamanTable = halamanTable;
        this.daoDosen = new DAODosen();
    }

    public ControllerDosen(InputData halamanInput) {
        this.halamanInput = halamanInput;
        this.daoDosen = new DAODosen();
    }

    public ControllerDosen(EditData halamanEdit) {
        this.halamanEdit = halamanEdit;
        this.daoDosen = new DAODosen();
    }

    public void showAllDosen() {
        daftarDosen = daoDosen.getAll();
        ModelTable table = new ModelTable(daftarDosen);
        halamanTable.getTableDosen().setModel(table);
    }

    public void insertDosen() {
        try {
            ModelDosen dosenBaru = new ModelDosen();
            String nama = halamanInput.getInputNama();
            String no_hp = halamanInput.getInputNoHP();
            String email = halamanInput.getInputEmail();

            if ("".equals(nama) || "".equals(no_hp) || "".equals(email)) {
                throw new Exception("Nama, No HP, atau Email tidak boleh kosong!");
            }

            dosenBaru.setNama(nama);
            dosenBaru.setNo_hp(no_hp);
            dosenBaru.setEmail(email);

            daoDosen.insert(dosenBaru);

            JOptionPane.showMessageDialog(null, "Dosen baru berhasil ditambahkan.");

            halamanInput.dispose();
            new ViewData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void editDosen(int id) {
        try {
            ModelDosen dosenYangMauDiedit = new ModelDosen();
            String nama = halamanEdit.getInputNama();
            String no_hp = halamanEdit.getInputNoHP();
            String email = halamanEdit.getInputEmail();

            if ("".equals(nama) || "".equals(no_hp) || "".equals(email)) {
                throw new Exception("Nama, No HP, atau Email tidak boleh kosong!");
            }

            dosenYangMauDiedit.setId(id);
            dosenYangMauDiedit.setNama(nama);
            dosenYangMauDiedit.setNo_hp(no_hp);
            dosenYangMauDiedit.setEmail(email);

            daoDosen.update(dosenYangMauDiedit);

            JOptionPane.showMessageDialog(null, "Data dosen berhasil diubah.");

            halamanEdit.dispose();
            new ViewData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteDosen(Integer baris) {
        Integer id = (int) halamanTable.getTableDosen().getValueAt(baris, 0);
        String nama = halamanTable.getTableDosen().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Dosen",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            daoDosen.delete(id);
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");
            showAllDosen();
        }
    }
    
    public void searchDosen(String keyword) {
    List<ModelDosen> searchResult = DAODosen.search(keyword);
    if (searchResult.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Tidak ada dosen yang cocok dengan kata kunci pencarian.");
    } else {
        ModelTable table = new ModelTable(searchResult);
        halamanTable.getTableDosen().setModel(table);
    }
}
}
