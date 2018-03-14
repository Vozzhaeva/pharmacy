package Control;

import DAOImpl.ManufacturerDAOImpl;
import Model.DbConnection;
import Model.Manufacturer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ServletManufacturer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ManufacturerDAOImpl manufacturerDAOImpl;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        DbConnection dbConnection = new DbConnection(jdbcURL, jdbcUsername, jdbcPassword);
        manufacturerDAOImpl= new ManufacturerDAOImpl(dbConnection);

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
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertManufacturer(request, response);
                    break;
                case "/delete":
                    deleteManufacturer(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateManufacturer(request, response);
                    break;
                case "/list":
                    listManufacturer(request, response);
                    break;

                case "/listMedicament":
                    request.getRequestDispatcher("/ServletMedicament").forward(request, response);
                    break;
                case "/listtRealization":
                    request.getRequestDispatcher("/ServletRealization").forward(request, response);
                    break;
                case "/listPharmacy":
                    request.getRequestDispatcher("/ServletPharmacy").forward(request, response);
                    default:
                     listManufacturer(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listManufacturer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Manufacturer> listManufacturer = manufacturerDAOImpl.GetAll();
        request.getSession().setAttribute("listManufacturer", listManufacturer);
        request.getRequestDispatcher("Manufacturer.jsp").forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddManufacturer.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id_m"));
        Manufacturer existingManufacturer = manufacturerDAOImpl.getId(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddManufacturer.jsp");
        request.setAttribute("manufacturer", existingManufacturer);
        dispatcher.forward(request, response);

    }

    private void insertManufacturer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name_m = request.getParameter("name_m");
        String address_m = request.getParameter("address_m");
        String phone_m = request.getParameter("phone_m");
        String email_m =request.getParameter("email_m");

        Manufacturer newManufacturer = new Manufacturer();
        newManufacturer.setName_m(name_m);
        newManufacturer.setAddress_m(address_m);
        newManufacturer.setPhone_m(phone_m);
        newManufacturer.setEmail_m(email_m);

        manufacturerDAOImpl.Insert(newManufacturer);
        response.sendRedirect("list");
    }

    private void updateManufacturer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id_m = Integer.parseInt(request.getParameter("id_m"));
        String name_m = request.getParameter("name_m");
        String address_m = request.getParameter("address_m");
        String phone_m = request.getParameter("phone_m");
        String email_m =request.getParameter("email_m");


        Manufacturer newManufacturer = new Manufacturer();
        newManufacturer.setId_m(id_m);
        newManufacturer.setName_m(name_m);
        newManufacturer.setAddress_m(address_m);
        newManufacturer.setPhone_m(phone_m);
        newManufacturer.setEmail_m(email_m);

        manufacturerDAOImpl.Update(newManufacturer);
        response.sendRedirect("list");
    }

    private void deleteManufacturer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id_m"));
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId_m(id);
        manufacturerDAOImpl.Delete(manufacturer);
        response.sendRedirect("list");
    }
}
