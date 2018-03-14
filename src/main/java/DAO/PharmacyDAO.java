package DAO;

import Model.Medicament;
import Model.Pharmacy;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anastasia on 18.03.2017.
 */
public interface PharmacyDAO {

        public Pharmacy getId (Integer id)  throws SQLException;
        public void Delete(Pharmacy pharmacy) throws SQLException;
        public void Update(Pharmacy pharmacy) throws SQLException;
        public void Insert(Pharmacy pharmacy) throws SQLException;
        public List<Pharmacy> GetAll() throws SQLException;
}
