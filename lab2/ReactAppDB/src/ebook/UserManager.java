package ebook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;


/**
 * Servlet implementation class UserManagerServlet
 */
@WebServlet("/UserManager")
public class UserManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserManager() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

   
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            
            System.out.println("This is a user manager");

            
       

            if(!request.getAttribute("user").equals("awake") ) {
            	 User user = (User) request.getAttribute("user");  
	             ArrayList<JSONArray> booksJson = new ArrayList<JSONArray>();

	              ArrayList<String> arrayList = new ArrayList<String>();
	               
	                arrayList.add(user.getName());
	                arrayList.add(user.getPhone());
	                arrayList.add(user.getEmail());
	                
	                booksJson.add(JSONArray.fromObject(arrayList));
	
	            JSONArray books = JSONArray.fromArray(booksJson.toArray());
	
	            
	            System.out.println(books);
	
	           out.println(books);
	 
           }
            else {
            	out.println("please login first");
            }
            out.flush();
	        out.close();

        }
        catch (Exception ex) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            if ( ServletException.class.isInstance( ex ) ) {
                throw ( ServletException ) ex;
            }
            else {
                throw new ServletException( ex );
            }
        }
	}

}
