package Control;

import DAOImpl.ManufacturerDAOImpl;
import DAOImpl.MedicamentDAOImpl;
import Model.DbConnection;
import Model.Manufacturer;
import Model.Medicament;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import java.util.List;

/**
 * Created by Anastasia on 20.03.2017.
 */
public class ServletMedicament extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MedicamentDAOImpl medicamentDAOImpl;
    private ManufacturerDAOImpl manufacturerDAOImpl;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        DbConnection dbConnection = new DbConnection(jdbcURL, jdbcUsername, jdbcPassword);
        medicamentDAOImpl= new MedicamentDAOImpl(dbConnection);
        manufacturerDAOImpl=new ManufacturerDAOImpl(dbConnection);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action)  {
                case "/new_med":
                    showNewForm(request, response);
                    break;
                case "/insert_med":
                    insertMedicament(request, response);
                    break;
                case "/delete_med":
                    deleteMedicament(request, response);
                    break;
                case "/edit_med":
                    showEditForm(request, response);
                    break;
                case "/update_med":
                    updateMedicament(request, response);
                    break;

                case "/list_med":
                    listMedicament(request, response);
                    break;
                case "/listManufacturer":
                    request.getRequestDispatcher("/ServletManufacturer").forward(request, response);
                    break;
                case "/listPharmacy":
                    request.getRequestDispatcher("/ServletPharmacy").forward(request, response);
                    break;
                case "/listRealization":
                    request.getRequestDispatcher("/ServletRealization").forward(request, response);

                    break;
                default:
                    listMedicament(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void listMedicament(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Medicament> listMedicament = medicamentDAOImpl.GetAll();
        List<Manufacturer> listManufacturer = manufacturerDAOImpl.GetAll();
        request.getSession().setAttribute("listMedicament", listMedicament);
        request.getSession().setAttribute("listManufacturer", listManufacturer);
        request.getRequestDispatcher("Medicament.jsp").forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Manufacturer> listManufacturer = manufacturerDAOImpl.GetAll();
        request.getSession().setAttribute("listManufacturer", listManufacturer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddMedicament.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Manufacturer> listManufacturer = manufacturerDAOImpl.GetAll();         //
        request.getSession().setAttribute("listManufacturer", listManufacturer);//
        int id = Integer.parseInt(request.getParameter("id_med"));
        Medicament existingMedicament = medicamentDAOImpl.getId(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddMedicament.jsp");
        request.setAttribute("medicament", existingMedicament);
        dispatcher.forward(request, response);

    }

    private void insertMedicament(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name_med = request.getParameter("name_med");
        Boolean recipe = Boolean.parseBoolean(request.getParameter("recipe"));
        Date date_med = Date.valueOf(request.getParameter("date_med"));
        Date limitation = Date.valueOf(request.getParameter("limitation"));
        Integer id_m = Integer.parseInt(request.getParameter("id_m"));

        Medicament newMedicament = new Medicament();
        newMedicament.setName_med(name_med);
        newMedicament.setRecipe(recipe);
        newMedicament.setDate_med(date_med);
        newMedicament.setLimitation(limitation);
       newMedicament.setId_m(id_m);

        medicamentDAOImpl.Insert(newMedicament);
        response.sendRedirect("list_med");
    }

    private void updateMedicament(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {


        request.setCharacterEncoding("UTF-8");

        List<Manufacturer> listManufacturer = manufacturerDAOImpl.GetAll();//
        request.getSession().setAttribute("listManufacturer", listManufacturer);//

        Integer id_med = Integer.parseInt(request.getParameter("id_med"));
        String name_med = request.getParameter("name_med");
        Boolean recipe = Boolean.parseBoolean(request.getParameter("recipe"));
        Date date_med = Date.valueOf(request.getParameter("date_med"));
        Date limitation = Date.valueOf(request.getParameter("limitation"));
        Integer id_m = Integer.parseInt(request.getParameter("id_m"));

        Medicament newMedicament = new Medicament();

        newMedicament.setId_med(id_med);
        newMedicament.setName_med(name_med);
        newMedicament.setRecipe(recipe);
        newMedicament.setDate_med(date_med);
        newMedicament.setLimitation(limitation);
        newMedicament.setId_m(id_m);

        medicamentDAOImpl.Update(newMedicament);
        response.sendRedirect("list_med");
    }

    private void deleteMedicament(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id_med"));
        Medicament medicament = new Medicament();
        medicament.setId_med(id);
        medicamentDAOImpl.Delete(medicament);
        response.sendRedirect("list_med");





    }
}
