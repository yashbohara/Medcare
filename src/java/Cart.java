import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/Cart"})
public class Cart extends HttpServlet {

	
    
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        String a=(String) request.getAttribute("l");
//                        //int a=(int) request.getParameter("l");
                        HttpSession aSession = request.getSession();
int count = Integer.parseInt(aSession.getAttribute("c").toString());

        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");

            //Test commit

            out.println("<title>Servlet Cart</title>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='index.html'>Go Back</a>");
            out.print("<form action=https://paytm.com/paytmwallet>");
            out.print("<table border='1' width='50%'>");
            float sum=0;
            for(int i=0;i<count;i++)
            {
            if("on".equals(request.getParameter(""+i)))
            {
                sum+=Float.parseFloat(request.getParameter("price"+i));
                out.print("<tr style='border: 1px solid black'>");
                out.print("<td>");
                out.print("<div>");                               
                out.print(request.getParameter("name"+i));
                out.print("</div>");
                out.print("</td>");
                out.print("<td>");
                out.print("<div>");                               
                out.print(request.getParameter("price"+i));
                out.print("</div>");
                out.print("</td>");
                out.print("</tr>");
                    
            }
            }
                out.print("<tr style='border: 1px solid black'>");
                out.print("<td>");
                out.print("<div>");                               
                out.print("Total");
                out.print("</div>");
                out.print("</td>");
                out.print("<td>");
                out.print("<div>");                               
                out.print(""+sum);
                out.print("</div>");
                out.print("</td>");
                out.print("</tr>");
            
            out.print("</table>");
            out.print("<input type='submit' value='Go to payment'>");
            out.print("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
