package Control;

import DAOImpl.PharmacyDAOImpl;
import Model.DbConnection;
import Model.Pharmacy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anastasia on 22.03.2017.
 */
public class ServletPharmacy  extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private PharmacyDAOImpl pharmacyDAOImpl;


    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        DbConnection dbConnection = new DbConnection(jdbcURL, jdbcUsername, jdbcPassword);
        pharmacyDAOImpl= new PharmacyDAOImpl(dbConnection);

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
                case "/new_ph":
                    showNewForm(request, response);
                    break;
                case "/insert_ph":
                    insertPharmacy(request, response);
                    break;
                case "/delete_ph":
                    deletePharmacy(request, response);
                    break;
                case "/edit_ph":
                    showEditForm(request, response);
                    break;
                case "/update_ph":
                    updatePharmacy(request, response);
                    break;
                case "/list_ph":
                    listPharmacy(request, response);
                    break;
                case "/list_Manufacturer":
                    request.getRequestDispatcher("/ServletManufacturer").forward(request, response);
                    break;
                case "/list_Medicament":
                    request.getRequestDispatcher("/ServletMedicament").forward(request, response);
                    break;
                case "/list_Realization":
                    request.getRequestDispatcher("/ServletRealization").forward(request, response);
                    break;
                default:
                    listPharmacy(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void listPharmacy(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Pharmacy> listPharmacy = pharmacyDAOImpl.GetAll();
        request.getSession().setAttribute("listPharmacy", listPharmacy);
        request.getRequestDispatcher("Pharmacy.jsp").forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddPharmacy.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id_ph"));
        Pharmacy existingPharmacy = pharmacyDAOImpl.getId(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddPharmacy.jsp");
        request.setAttribute("pharmacy", existingPharmacy);
        dispatcher.forward(request, response);

    }

    private void insertPharmacy(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name_ph = request.getParameter("name_ph");
        String address_ph = request.getParameter("address_ph");
        String phone_ph = request.getParameter("phone_ph");
        String site_ph =request.getParameter("site_ph");

        Pharmacy newPharmacy = new Pharmacy();
        newPharmacy.setName_ph(name_ph);
        newPharmacy.setAddress_ph(address_ph);
        newPharmacy.setPhone_ph(phone_ph);
        newPharmacy.setSite_ph(site_ph);

        pharmacyDAOImpl.Insert(newPharmacy);
        response.sendRedirect("list_ph");
    }

    private void updatePharmacy(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        Integer id_ph = Integer.parseInt(request.getParameter("id_ph"));
        String name_ph = request.getParameter("name_ph");
        String address_ph = request.getParameter("address_ph");
        String phone_ph = request.getParameter("phone_ph");
        String site_ph =request.getParameter("site_ph");


        Pharmacy newPharmacy = new Pharmacy();
        newPharmacy.setId_ph(id_ph);
        newPharmacy.setName_ph(name_ph);
        newPharmacy.setAddress_ph(address_ph);
        newPharmacy.setPhone_ph(phone_ph);
        newPharmacy.setSite_ph(site_ph);

        pharmacyDAOImpl.Update(newPharmacy);
        response.sendRedirect("list_ph");
    }

    private void deletePharmacy(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id_ph"));
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId_ph(id);
       pharmacyDAOImpl.Delete(pharmacy);
        response.sendRedirect("list_ph");





    }
}
