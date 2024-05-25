package Model.Mahasiswa;

import Model.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOMahasiswa implements InterfaceDAOMahasiswa {

    @Override
    public void insert(ModelMahasiswa mahasiswa) {
        try {
            String query = "INSERT INTO mahasiswa (nama, nim, email, password, angkatan) VALUES (?, ?, ?, ?, ?);";

            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, mahasiswa.getNama());
            statement.setString(2, mahasiswa.getNim());
            statement.setString(3, mahasiswa.getEmail());
            statement.setString(4, mahasiswa.getPassword());
            statement.setString(5, mahasiswa.getAngkatan());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(ModelMahasiswa mahasiswa) {
        try {
            String query = "UPDATE mahasiswa SET nama=?, nim=?, email=?, password=?, angkatan=? WHERE id=?;";

            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, mahasiswa.getNama());
            statement.setString(2, mahasiswa.getNim());
            statement.setString(3, mahasiswa.getEmail());
            statement.setString(4, mahasiswa.getPassword());
            statement.setString(5, mahasiswa.getAngkatan());
            statement.setInt(6, mahasiswa.getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Update Failed! (" + e.getLocalizedMessage() + ")");
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM mahasiswa WHERE id=?;";

            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<ModelMahasiswa> getAll() {
        List<ModelMahasiswa> listMahasiswa = null;

        try {
            listMahasiswa = new ArrayList<>();

            Statement statement = Connector.Connect().createStatement();
            String query = "SELECT * FROM mahasiswa;";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ModelMahasiswa mahasiswa = new ModelMahasiswa();

                mahasiswa.setId(resultSet.getInt("id"));
                mahasiswa.setNama(resultSet.getString("nama"));
                mahasiswa.setNim(resultSet.getString("nim"));
                mahasiswa.setEmail(resultSet.getString("email"));
                mahasiswa.setPassword(resultSet.getString("password"));
                mahasiswa.setAngkatan(resultSet.getString("angkatan"));

                listMahasiswa.add(mahasiswa);
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listMahasiswa;
    }
    
@Override
public List<ModelMahasiswa> search(String keyword) {
    List<ModelMahasiswa> searchResult = new ArrayList<>();
    try {
        String query = "SELECT * FROM mahasiswa WHERE nama LIKE ? OR nim LIKE ? OR email LIKE ? OR angkatan LIKE ?";
        PreparedStatement statement = Connector.Connect().prepareStatement(query);
        String searchKeyword = "%" + keyword + "%";
        for (int i = 1; i <= 4; i++) {
            statement.setString(i, searchKeyword);
        }

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            ModelMahasiswa mahasiswa = new ModelMahasiswa();
            mahasiswa.setId(resultSet.getInt("id"));
            mahasiswa.setNama(resultSet.getString("nama"));
            mahasiswa.setNim(resultSet.getString("nim"));
            mahasiswa.setEmail(resultSet.getString("email"));
            mahasiswa.setPassword(resultSet.getString("password"));
            mahasiswa.setAngkatan(resultSet.getString("angkatan"));
            searchResult.add(mahasiswa);
        }

        statement.close();
    } catch (SQLException e) {
        System.out.println("Search Failed: " + e.getMessage());
    }
    return searchResult;
}

}
