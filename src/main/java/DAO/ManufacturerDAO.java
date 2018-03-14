package DAO;

import Model.Manufacturer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anastasia on 18.03.2017.
 */
public interface ManufacturerDAO {
    public Manufacturer getId (Integer id)  throws SQLException;
    public void Delete(Manufacturer manufacturer) throws SQLException;
    public void Update(Manufacturer manufacturer) throws SQLException;
    public void Insert(Manufacturer manufacturer) throws SQLException;
    public List<Manufacturer> GetAll() throws SQLException;
}

