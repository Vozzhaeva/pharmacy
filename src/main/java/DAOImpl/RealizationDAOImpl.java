package DAOImpl;

import DAO.RealizationDAO;
import Model.DbConnection;
import Model.Medicament;
import Model.Realization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anastasia on 22.03.2017.
 */
public class RealizationDAOImpl implements RealizationDAO {
    private DbConnection connection = null;

    private List<Realization> listRealization;

    public RealizationDAOImpl(DbConnection dbConnection)
    {
        this.connection = dbConnection;
    }

    @Override
    public Realization getId(Integer id_ph, Integer id_med) throws SQLException {
        Realization realization =new Realization();
        String sql = "SELECT * FROM realization WHERE id_ph = ? AND id_med=&";

        connection.connect();

        PreparedStatement statement = connection.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id_ph);
        statement.setInt(2, id_med);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            realization.setId_ph(rs.getInt("id_ph"));
            realization.setId_med(rs.getInt("id_med"));
            realization.setDate_r(rs.getDate("date_r"));
            realization.setPrice(rs.getInt("price"));
        }
        rs.close();
        statement.close();

        connection.disconnect();

        return realization;
    }

    @Override
    public void Insert(Realization realization) throws SQLException {

        connection.connect();
        PreparedStatement ps = connection.getJdbcConnection().prepareStatement(
                "INSERT INTO realization(id_ph, id_med, date_r, price)" +
                        " VALUES (?,?,?,?)");
        ps.setInt(1, realization.getId_ph());
        ps.setInt(2, realization.getId_med());
        ps.setDate(3, (Date) realization.getDate_r());
        ps.setInt(4, realization.getPrice());

        ps.executeUpdate();
        ps.close();
        connection.disconnect();
    }

    @Override
    public void Update(Realization realization) throws SQLException {
        connection.connect();
        PreparedStatement ps = connection.getJdbcConnection().prepareStatement("update realization set date_r=?," +
                "price=? where id_ph=? AND id_med=?");
;
        ps.setDate(1, (Date) realization.getDate_r());
        ps.setInt(2, realization.getPrice());
        ps.setInt(3, realization.getId_ph());
        ps.setInt(4, realization.getId_med());

        ps.executeUpdate();
        ps.close();
        connection.disconnect();
    }

    @Override
    public void Delete(Realization realization) throws SQLException {
        connection.connect();
        PreparedStatement ps = connection.getJdbcConnection().prepareStatement("delete from realization where id_ph=? AND id_med=?");

        ps.setInt(1, (realization.getId_ph()));
        ps.setInt(2, (realization.getId_med()));
        ps.executeUpdate();
        ps.close();
        connection.disconnect();

    }

    @Override
    public List<Realization> GetAll() throws SQLException {
        listRealization = new ArrayList<Realization>();

        connection.connect();
        Statement ps = connection.getJdbcConnection().createStatement();
        ResultSet rs = ps.executeQuery("select * from realization");
        while (rs.next()) {
            Realization realization= new Realization();
            realization.setId_ph(rs.getInt("id_ph"));
            realization.setId_med(rs.getInt("id_med"));
            realization.setDate_r(rs.getDate("date_r"));
            realization.setPrice(rs.getInt("price"));


            listRealization.add(realization);
        }
        ps.close();
        connection.disconnect();

        return listRealization;

    }
}
