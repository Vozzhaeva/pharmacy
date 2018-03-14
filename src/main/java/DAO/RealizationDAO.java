package DAO;

import Model.Realization;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anastasia on 18.03.2017.
 */
public interface RealizationDAO {
    public Realization getId (Integer id_ph, Integer id_med)  throws SQLException;
    public void Delete(Realization realization) throws SQLException;
    public void Update(Realization realization) throws SQLException;
    public void Insert(Realization realization) throws SQLException;
    public List<Realization> GetAll() throws SQLException;
}
