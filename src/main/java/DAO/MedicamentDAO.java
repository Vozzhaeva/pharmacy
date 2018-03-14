package DAO;


import Model.Medicament;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anastasia on 18.03.2017.
 */
public interface MedicamentDAO {
    public Medicament getId (Integer id)  throws SQLException;
    public void Delete(Medicament manufacturer) throws SQLException;
    public void Update(Medicament manufacturer) throws SQLException;
    public void Insert(Medicament manufacturer) throws SQLException;
    public List<Medicament> GetAll() throws SQLException;
}
