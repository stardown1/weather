package cn.hnust;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;


public class Search extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		String question_id = request.getParameter("id");
		System.out.println("id:" + question_id);
		try {
			DBConnection db = new DBConnection();
			ResultSet rs = db.executeQuery("select * from question where id = '" + question_id +"'");	
			String id = "";
			String question = "";
			String a = "";
			String c = "";
			String d = "";
			String b = "";
			String answer = "";
			
			while(rs.next()){
				id=rs.getString("id");
				question=rs.getString("question");
				a=rs.getString("a");
				b=rs.getString("b");
				c=rs.getString("c");
				d=rs.getString("d");
				answer=rs.getString("answer");
			}
			db.close();
			
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			
			obj.put("result", "ok");
			obj.put("id", question_id);
			obj.put("question", question);
			obj.put("a", a);
			obj.put("b", b);
			obj.put("c", c);
			obj.put("d", d);
			obj.put("answer", answer);
			
			System.out.println(obj.toString());
			out.print(obj.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void doSearch(String question_id) {
		try {
			DBConnection db = new DBConnection();
			Random r =new Random();
			int i=r.nextInt(4)+1;
			question_id=String.valueOf(i);	
			ResultSet rs = db.executeQuery("select * from question where id = '" + question_id +"'");
			
			String question = "";
			String a = "";
			String c = "";
			String d = "";
			String b = "";
			String answer = "";
			
			while(rs.next()){
				question=rs.getString("question");
				a=rs.getString("a");
				b=rs.getString("b");
				c=rs.getString("c");
				d=rs.getString("d");
				answer=rs.getString("answer");
			}
			db.close();
			
			JSONObject obj = new JSONObject();
			obj.put("result", "ok");
			obj.put("id", question_id);
			obj.put("question", question);
			obj.put("a", a);
			obj.put("b", b);
			obj.put("c", c);
			obj.put("d", d);
			obj.put("answer", answer);
			
			System.out.println(obj.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		doSearch("1");
	}

}
