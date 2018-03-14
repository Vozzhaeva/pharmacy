package DAOImpl;

import DAO.MedicamentDAO;
import Model.DbConnection;
import Model.Manufacturer;
import Model.Medicament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anastasia on 19.03.2017.
 */
public class MedicamentDAOImpl implements MedicamentDAO {
    private DbConnection connection = null;

    private List<Medicament> listMedicament;

    public MedicamentDAOImpl(DbConnection dbConnection)
    {
        this.connection = dbConnection;
    }

    @Override
    public Medicament getId(Integer id) throws SQLException {
        Medicament medicament =new Medicament();
        String sql = "SELECT * FROM medicament WHERE id_med = ?";

        connection.connect();

        PreparedStatement statement = connection.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            medicament.setId_med(rs.getInt("id_med"));
            medicament.setName_med(rs.getString("name_med"));
            medicament.setRecipe(rs.getBoolean("recipe"));
            medicament.setDate_med(rs.getDate("date_med"));
            medicament.setLimitation(rs.getDate("limitation"));
            medicament.setId_m(rs.getInt("id_m"));

        }
        rs.close();
        statement.close();

        connection.disconnect();

        return medicament;
    }

    @Override
    public void Insert(Medicament medicament) throws SQLException {

        connection.connect();
        PreparedStatement ps = connection.getJdbcConnection().prepareStatement(
                "INSERT INTO medicament(name_med, recipe, date_med, limitation, id_m)" +
                        " VALUES (?,?,?,?,?)");
        ps.setString(1, medicament.getName_med());
        ps.setBoolean(2, medicament.getRecipe());
        ps.setDate(3, (Date) medicament.getDate_med());
        ps.setDate(4, (Date) medicament.getLimitation());
        ps.setInt(5, medicament.getId_m());
        ps.executeUpdate();
        ps.close();
        connection.disconnect();
    }

    @Override
    public void Update(Medicament medicament) throws SQLException {
        connection.connect();
        PreparedStatement ps = connection.getJdbcConnection().prepareStatement("update medicament set name_med=?," +
                "recipe=?,date_med=?, limitation=?, id_m=? where id_med=?");
        ps.setString(1, medicament.getName_med());
        ps.setBoolean(2, medicament.getRecipe());
        ps.setDate(3, (Date) medicament.getDate_med());
        ps.setDate(4, (Date) medicament.getLimitation());
        ps.setInt(5, medicament.getId_m());
        ps.setInt(6, medicament.getId_med());
        ps.executeUpdate();
        ps.close();
        connection.disconnect();
    }

    @Override
    public void Delete(Medicament medicament) throws SQLException {
        connection.connect();
        PreparedStatement ps = connection.getJdbcConnection().prepareStatement("delete from medicament where id_med=?");

        ps.setInt(1, (medicament.getId_med()));
        ps.executeUpdate();
        ps.close();
        connection.disconnect();

    }

    @Override
    public List<Medicament> GetAll() throws SQLException {
        listMedicament = new ArrayList<Medicament>();

        connection.connect();
        Statement ps = connection.getJdbcConnection().createStatement();
        ResultSet rs = ps.executeQuery("select * from medicament");
        while (rs.next()) {
            Medicament medicament= new Medicament();
            medicament.setId_med(rs.getInt("id_med"));
            medicament.setName_med(rs.getString("name_med"));
            medicament.setRecipe(rs.getBoolean("recipe"));
            medicament.setDate_med(rs.getDate("date_med"));
            medicament.setLimitation(rs.getDate("limitation"));
            medicament.setId_m(rs.getInt("id_m"));


            listMedicament.add(medicament);
        }
        ps.close();
        connection.disconnect();

        return listMedicament;

    }



}
