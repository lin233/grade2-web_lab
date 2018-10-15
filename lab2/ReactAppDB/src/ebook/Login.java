package ebook;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;


import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.hibernate.Session;

import ebook.HibernateUtil;
import ebook.User;

/**
 * Servlet implementation class JsonServlet
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//List<DOMTreeRow> rowList;
    JsonStructure parsed;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private Boolean IFvalid;
    private String user_name;
    private String user_password;
    private User user = null;

    
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    	
    	
        String content = request.getParameter("content");
        parseJson(content);
        

        PrintWriter out = response.getWriter();
        out.println(buildJson());            

        if(this.IFvalid) {
	        request.setAttribute("user", this.user);        
	        User u = (User) request.getAttribute("user");        
	        System.out.println(u.getName());   
        }
    }
    
    public void parseJson(String content) {
        /* Parse the data using the document object model approach */
        try (JsonReader reader = Json.createReader(new StringReader(content))) {
            parsed = reader.readObject();
        }

        /* Represent the DOM tree on a list for a JSF table */
        this.printTree(parsed, 0, "");
        
    }
    
    /* Used to populate rowList to display the DOM tree on a JSF table */
    public void printTree(JsonValue tree, int level, String key) {

        switch (tree.getValueType()) {
            case OBJECT:
                JsonObject object = (JsonObject) tree;
                for (int i = 0; i < level; i++)
                	System.out.print(" ");
                System.out.println( level + " " + tree.getValueType().toString()  + "| " +  key + "--");
                for (String name : object.keySet()) {
                   this.printTree(object.get(name), level+1, name);
                }
                break;
            case ARRAY:
                JsonArray array = (JsonArray) tree;
                for (int i = 0; i < level; i++)
                	System.out.print(" ");
             
                System.out.println( level + " " + tree.getValueType().toString() + " " + key + "--");

                
                for (JsonValue val : array) {
                    this.printTree(val, level+1, "");
                }
                break;
            case STRING:
                JsonString st = (JsonString) tree;
                for (int i = 0; i < level; i++)
                	System.out.print(" ");
                System.out.println( level + " " + tree.getValueType().toString() + " " + key + " " + st.getString());

                System.out.println("-----4----");

                switch(key) {
                	case "name":
                		this.user_name=st.getString();
                		break;
                	case "password":
                		this.user_password=st.getString();
                		break;   
                };
                
                break;
            case NUMBER:
                JsonNumber num = (JsonNumber) tree;
                for (int i = 0; i < level; i++)
                	System.out.print(" ");
                System.out.println( level + " " + tree.getValueType().toString() + " " + key + " " + num.toString());
                break;
            case FALSE:
            case TRUE:
            case NULL:
                String valtype = tree.getValueType().toString();
                for (int i = 0; i < level; i++)
                	System.out.print(" ");
                System.out.println( level + " " + valtype + " " + key + " " + valtype.toLowerCase());
                break;
        }
    }
    

    @SuppressWarnings("unchecked")
	public String buildJson() {        
        /* Build JSON Object Model */
    	 
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		 System.out.println("-----2----");
		List<User> result = session.createQuery("from User where name="+"\'"+this.user_name+"\'").list();
		 System.out.println("-----3----");
		 
		Iterator<User> it = result.iterator();
		
		JsonObject model=Json.createObjectBuilder() 
					.add("", "")	            
				.build();;
		
		if(it.hasNext()) {
			User user=(User) it.next();
			if(user.getPassword().equals(this.user_password)){
				model = Json.createObjectBuilder()
		            .add("Login", "successful!")	            
		        .build();
				this.user = user;
				this.IFvalid = true;
				
				}
			else {
					model = Json.createObjectBuilder()
			            .add("Login", "fail: password wrong!")	            
			        .build();
					this.IFvalid = false;
			}
		}
			
		
		else {            	
			model = Json.createObjectBuilder()
            .add("Login", "fail: the name is not exist")
          
        .build();
			this.IFvalid = false;
	
        }
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		
        /* Write JSON Output */
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(model);
        }
        //return stWriter.toString();
        
        /* Write formatted JSON Output */
        Map<String,String> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, "");
        JsonWriterFactory factory = Json.createWriterFactory(config);
        
        StringWriter stWriterF = new StringWriter();
        try (JsonWriter jsonWriterF = factory.createWriter(stWriterF)) {
            jsonWriterF.writeObject(model);
        }                
    
        return stWriterF.toString();
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        	       
	}

}
