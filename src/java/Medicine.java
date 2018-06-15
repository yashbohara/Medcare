
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.console;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author YashBohara
 */
@WebServlet(urlPatterns = {"/Medicine"})
public class Medicine extends HttpServlet {
            int count = 0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            
            
            out.print(" <head>\n" +
"        <title>MedCare</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"   <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" +
"   <link rel=\"stylesheet\" type=\"text/css\" href=\"new.css\"/>\n" +
"   <link rel=\"stylesheet\" href=\"font-awesome-4.7.0/css/font-awesome.min.css\">"
                    + "</head>");
            
            out.println("<body>");
            
            
            
            
            out.print(" <div class='a'>\n" +
"                <table>\n" +
"                    <tr>\n" +
"                        <th><i class=\"fa fa-home\"></i></th>\n" +
"                        <th><i class=\"fa fa-search-plus\"></i></th>\n" +
"                        <th><i class=\"fa fa-cart-plus\"></i></th>\n" +
"                        <th><i class=\"fa fa-sign-out\"></i></th>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <th><a href=\"index.html\"><font>Home</font></a></th>\n" +
"                        <th><a href=\"search.html\"><font>Search</font></a></th>\n" +
"                        <th><a href=\"cart.java\"><font>Cart</font></a></th>\n" +
"                        <th><a href=\"\"><font>Logout</font></a></th>\n" +
"                    </tr></table>\n" +
"                    </div>");
            
            
            
            
            
            
            
            out.println("<a href='index.html'>Go Back</a>");
            String s = request.getParameter("Enter");
            //String url = "http://www.healthos.co/api/v1/medicines/brands?page=1&size=10";
            String url = "http://www.healthos.co/api/v1/search/medicines/brands/"+s;

            URL object = new URL(url);
            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setRequestMethod("GET");
            String USER_AGENT = "Google Chrome";
            String aa = "27dd0f1c8675b2a5d167aabd8bdfac32c65e618a0b85350167887b562b37c79b";
            con.setRequestProperty("Authorization", "bearer " + aa);
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Content-Type", "application/xml");

            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer respons = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                respons.append(inputLine);
            }
            String result = respons.toString();
            JSONArray a1 = new JSONArray(result);
                     
   ArrayList<String> list=new ArrayList<>();
            out.print("<form action='Cart'>");
           
            out.print("<table border='1' width='50%'>");
          
            while (count < a1.length()) {
                JSONObject o1 = a1.getJSONObject(count);
                String name = o1.getString("name");
                double price=o1.getDouble("price");
                out.print("<tr style='border: 1px solid black'>");
//                out.print("<p>" + name+" "+price+ "</p>");
                out.print("<td>");
                out.print("<div class='check'>");                        
                out.print("<h4 id=name"+count+" name=name"+count+">"+name+"</h4>");
                //out.print("<input type='text' id=name"+count+" value="+name+" name=name"+count+">");
                out.print("</div>");
                out.print("</td>");
                
                out.print("<td>");
                out.print("<div class='check'>");      
                out.print("<h4 id=t"+count+" name=price"+count+">"+price+"</h1>");
                //out.print("<input type='text' id=t"+count+" value="+price+" name=price"+count+">");
                out.print("</div>");
                out.print("</td>");
                
                out.print("<td class='check'>");
                out.print("<div>");                               
                out.print("<input type='checkbox' id="+count+" name="+count+">");
                out.print("</div>");
                out.print("</td>");
                
                out.print("</tr>");
                count++;
            }
          
              out.print("</table>");
            
  
            
            
            
out.print("<script>");
out.print("function myFunction() {");
for(int i=0;i<count;i++)
{
    String a="t"+i;
    out.print("var x = document.getElementById('"+a+"').value;");
    out.print("var y = document.getElementById('"+i+"');");
    out.print("if(y.checked==true){");
    list.add(request.getParameter("x"));
    out.print("alert(x);}");
}
    out.print("}");
    out.print("</script>");

out.print("<input type='submit' value='Add to cart'>");    
out.print("</form>");

out.println("</body>");
out.println("</html>");
            in.close();
        } catch (Exception e) {
        }
        String g="ll";
//request.setAttribute("l", g);
//    RequestDispatcher rd = request.getRequestDispatcher("Cart");
//rd.forward(request,response);
HttpSession aSession = request.getSession();
String username = request.getParameter("filename");
aSession.setAttribute("c", count);
           
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
