package Control;

import DAOImpl.MedicamentDAOImpl;
import DAOImpl.PharmacyDAOImpl;
import DAOImpl.RealizationDAOImpl;
import Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anastasia on 22.03.2017.
 */
public class ServletRealization extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private RealizationDAOImpl realizationDAOImpl;
    private PharmacyDAOImpl pharmacyDAOImpl;
    private MedicamentDAOImpl medicamentDAOImpl;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        DbConnection dbConnection = new DbConnection(jdbcURL, jdbcUsername, jdbcPassword);
        realizationDAOImpl= new RealizationDAOImpl(dbConnection);
        pharmacyDAOImpl= new PharmacyDAOImpl(dbConnection);
        medicamentDAOImpl= new MedicamentDAOImpl(dbConnection);

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
                case "/new_r":
                    showNewForm(request, response);
                    break;
                case "/insert_r":
                    insertRealization(request, response);
                    break;
                case "/delete_r":
                    deleteRealization(request, response);
                    break;
                case "/edit_r":
                    showEditForm(request, response);
                    break;
                case "/update_r":
                    updateRealization(request, response);
                    break;
                case "/list_r":
                    listRealization(request, response);
                    break;
                case "/listtManufacturer":
                    request.getRequestDispatcher("/ServletManufacturer").forward(request, response);
                    break;
                case "/listtMedicament":
                    request.getRequestDispatcher("/ServletMedicament").forward(request, response);
                    break;
                case "/listtPharmacy":
                    request.getRequestDispatcher("/ServletPharmacy").forward(request, response);
                    break;

                default:
                    listRealization(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void listRealization(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Realization> listRealization = realizationDAOImpl.GetAll();
        List<Medicament> listMedicament = medicamentDAOImpl.GetAll();
        List<Pharmacy> listPharmacy = pharmacyDAOImpl.GetAll();
        request.getSession().setAttribute("listPharmacy", listPharmacy);
        request.getSession().setAttribute("listMedicament", listMedicament);
        request.getSession().setAttribute("listRealization", listRealization);
        request.getRequestDispatcher("Realization.jsp").forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Realization> listRealization = realizationDAOImpl.GetAll();
        List<Medicament> listMedicament = medicamentDAOImpl.GetAll();
        List<Pharmacy> listPharmacy = pharmacyDAOImpl.GetAll();
        request.getSession().setAttribute("listPharmacy", listPharmacy);
        request.getSession().setAttribute("listMedicament", listMedicament);
        request.getSession().setAttribute("listRealization", listRealization);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddRealization.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id_ph = Integer.parseInt(request.getParameter("id_ph"));
        int id_med = Integer.parseInt(request.getParameter("id_med"));

        Realization existingRealization = realizationDAOImpl.getId(id_ph,id_med);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditRealization.jsp");
        request.setAttribute("medicament", existingRealization);
        dispatcher.forward(request, response);

    }

    private void insertRealization(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");

        Integer id_ph = Integer.parseInt(request.getParameter("id_ph"));
        Integer id_med = Integer.parseInt(request.getParameter("id_med"));
        Date date_r = Date.valueOf(request.getParameter("date_r"));
        Integer price = Integer.parseInt(request.getParameter("price"));

        Realization newRealization = new Realization();
        newRealization.setId_ph(id_ph);
        newRealization.setId_med(id_med);
        newRealization.setDate_r(date_r);
        newRealization.setPrice(price);

        realizationDAOImpl.Insert(newRealization);
        response.sendRedirect("list_r");
    }

    private void updateRealization(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");

        Integer id_ph = Integer.parseInt(request.getParameter("id_ph"));
        Integer id_med = Integer.parseInt(request.getParameter("id_med"));
        Date date_r = Date.valueOf(request.getParameter("date_r"));
        Integer price = Integer.parseInt(request.getParameter("price"));

        Realization newRealization = new Realization();

        newRealization.setId_ph(id_ph);
        newRealization.setId_med(id_med);
        newRealization.setDate_r(date_r);
        newRealization.setPrice(price);

        realizationDAOImpl.Update(newRealization);
        response.sendRedirect("list_r");
    }

    private void deleteRealization(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id_ph = Integer.parseInt(request.getParameter("id_ph"));
        int id_med = Integer.parseInt(request.getParameter("id_med"));
        Realization realization = new Realization();
        realization.setId_ph(id_ph);
        realization.setId_med(id_med);
        realizationDAOImpl.Delete(realization);
        response.sendRedirect("list_r");


    }
}
