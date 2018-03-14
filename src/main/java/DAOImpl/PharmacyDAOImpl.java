package DAOImpl;

import DAO.PharmacyDAO;
import Model.DbConnection;
import Model.Manufacturer;
import Model.Pharmacy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anastasia on 22.03.2017.
 */
public class PharmacyDAOImpl implements PharmacyDAO {
    private DbConnection connection = null;

    private List<Pharmacy> listPharmacy;

    public PharmacyDAOImpl(DbConnection dbConnection)
    {
        this.connection = dbConnection;
    }

    @Override
    public Pharmacy getId(Integer id) throws SQLException {
        Pharmacy pharmacy =new Pharmacy();
        String sql = "SELECT * FROM pharmacy WHERE id_ph = ?";

        connection.connect();

        PreparedStatement statement = connection.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            pharmacy.setId_ph(rs.getInt("id_ph"));
            pharmacy.setName_ph(rs.getString("name_ph"));
            pharmacy.setAddress_ph(rs.getString("address_ph"));
            pharmacy.setPhone_ph(rs.getString("phone_ph"));
            pharmacy.setSite_ph(rs.getString("site_ph"));
        }
        rs.close();
        statement.close();

        connection.disconnect();

        return pharmacy;
    }

    @Override
    public void Insert(Pharmacy pharmacy) throws SQLException {

        connection.connect();
        PreparedStatement ps = connection.getJdbcConnection().prepareStatement(
                "INSERT INTO pharmacy(name_ph, address_ph, phone_ph, site_ph)" +
                        " VALUES (?,?,?,?)");
        ps.setString(1, pharmacy.getName_ph());
        ps.setString(2, pharmacy.getAddress_ph());
        ps.setString(3, pharmacy.getPhone_ph());
        ps.setString(4, pharmacy.getSite_ph());
        ps.executeUpdate();
        ps.close();
        connection.disconnect();
    }

    @Override
    public void Update(Pharmacy pharmacy) throws SQLException {
        connection.connect();
        PreparedStatement ps = connection.getJdbcConnection().prepareStatement("update pharmacy set name_ph=?," +
                "address_ph=?,phone_ph=?, site_ph=? where id_ph=?");
        ps.setString(1, pharmacy.getName_ph());
        ps.setString(2, pharmacy.getAddress_ph());
        ps.setString(3, pharmacy.getPhone_ph());
        ps.setString(4, pharmacy.getSite_ph());
        ps.setInt(5, pharmacy.getId_ph());
        ps.executeUpdate();
        ps.close();
        connection.disconnect();
    }

    @Override
    public void Delete(Pharmacy pharmacy) throws SQLException {
        connection.connect();
        PreparedStatement ps = connection.getJdbcConnection().prepareStatement("delete from pharmacy where id_ph=?");

        ps.setInt(1, (pharmacy.getId_ph()));
        ps.executeUpdate();
        ps.close();
        connection.disconnect();

    }

    @Override
    public List<Pharmacy> GetAll() throws SQLException {
        listPharmacy = new ArrayList<Pharmacy>();

        connection.connect();
        Statement ps = connection.getJdbcConnection().createStatement();
        ResultSet rs = ps.executeQuery("select * from pharmacy");
        while (rs.next()) {
            Pharmacy pharmacy = new Pharmacy();
            pharmacy.setId_ph(rs.getInt("id_ph"));
            pharmacy.setName_ph(rs.getString("name_ph"));
            pharmacy.setAddress_ph(rs.getString("address_ph"));
            pharmacy.setPhone_ph(rs.getString("phone_ph"));
            pharmacy.setSite_ph(rs.getString("site_ph"));


            listPharmacy.add(pharmacy);
        }
        ps.close();
        connection.disconnect();

        return listPharmacy;

    }
}
