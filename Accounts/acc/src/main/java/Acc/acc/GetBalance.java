package Acc.acc;

import java.io.IOException;

 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import operation.Bank;
import util.Factory;
import util.HttpUtils;





@WebServlet("/getbalance/*")
public class GetBalance extends HttpServlet {
	private static final long serialVersionUID = -3462096228274971485L;
	@Override
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response) 
			throws ServletException, IOException {
			response.getWriter().println("Hello World!");
	}
	protected void doPost(
			HttpServletRequest request, 
			HttpServletResponse response) 	throws ServletException, IOException {
		String res="";
		String reqbody=HttpUtils.getBody(request);
		
		System.out.println(reqbody);
		JSONParser parser = new JSONParser(); 
		try {
			JSONObject json = (JSONObject) parser.parse(reqbody);
			System.out.println(json.get("name"));
			
			Bank bank=Factory.getBank("SBI");
			 String accnum=json.get("account").toString();
			
			 
			 System.out.println(accnum+"  "+accnum);
			
			//Bank.createCustomer(json.get("name").toString());
			 res=bank.getBAblance(accnum);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Read more: http://www.java67.com/2016/10/3-ways-to-convert-string-to-json-object-in-java.html#ixzz5pNcsNZdu
		response.getWriter().println("{'balance':'"+res+"'}");
	}

	
}