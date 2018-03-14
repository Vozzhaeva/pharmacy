package DAOImpl;

import DAO.ManufacturerDAO;
import Model.DbConnection;
import Model.Manufacturer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ManufacturerDAOImpl implements ManufacturerDAO {
    private DbConnection connection = null;

    private List<Manufacturer> listManufacturer;

    public ManufacturerDAOImpl(DbConnection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public Manufacturer getId(Integer id) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        String sql = "SELECT * FROM manufacturer WHERE id_m = ?";

        connection.connect();

        PreparedStatement statement = connection.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            manufacturer.setId_m(rs.getInt("id_m"));
            manufacturer.setName_m(rs.getString("name_m"));
            manufacturer.setAddress_m(rs.getString("address_m"));
            manufacturer.setPhone_m(rs.getString("phone_m"));
            manufacturer.setEmail_m(rs.getString("email_m"));
        }
        rs.close();
        statement.close();

        connection.disconnect();

        return manufacturer;
    }

    @Override
    public void Insert(Manufacturer manufacturer) throws SQLException {

        connection.connect();
        PreparedStatement ps = connection.getJdbcConnection().prepareStatement(
                "INSERT INTO manufacturer(name_m, address_m, phone_m, email_m)" +
                        " VALUES (?,?,?,?)");
        ps.setString(1, manufacturer.getName_m());
        ps.setString(2, manufacturer.getAddress_m());
        ps.setString(3, manufacturer.getPhone_m());
        ps.setString(4, manufacturer.getEmail_m());
        ps.executeUpdate();
        ps.close();
        connection.disconnect();
    }

    @Override
    public void Update(Manufacturer manufacturer) throws SQLException {
        connection.connect();
        PreparedStatement ps = connection.getJdbcConnection().prepareStatement("update manufacturer set name_m=?," +
                "address_m=?,phone_m=?, email_m=? where id_m=?");
        ps.setString(1, manufacturer.getName_m());
        ps.setString(2, manufacturer.getAddress_m());
        ps.setString(3, manufacturer.getPhone_m());
        ps.setString(4, manufacturer.getEmail_m());
        ps.setInt(5, manufacturer.getId_m());
        ps.executeUpdate();
        ps.close();
        connection.disconnect();
    }

    @Override
    public void Delete(Manufacturer manufacturer) throws SQLException {
        connection.connect();
        PreparedStatement ps = connection.getJdbcConnection().prepareStatement("delete from manufacturer where id_m=?");

        ps.setInt(1, (manufacturer.getId_m()));
        ps.executeUpdate();
        ps.close();
        connection.disconnect();

    }

    @Override
    public List<Manufacturer> GetAll() throws SQLException {
        listManufacturer = new ArrayList<Manufacturer>();


        connection.connect();
        Statement ps = connection.getJdbcConnection().createStatement();
        ResultSet rs = ps.executeQuery("select * from manufacturer");
        while (rs.next()) {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setId_m((rs.getInt("id_m")));
            manufacturer.setName_m((rs.getString("name_m")));
            manufacturer.setAddress_m((rs.getString("address_m")));
            manufacturer.setPhone_m((rs.getString("phone_m")));
            manufacturer.setEmail_m((rs.getString("email_m")));


            listManufacturer.add(manufacturer);
        }
        ps.close();
        connection.disconnect();

        return listManufacturer;

    }
}

