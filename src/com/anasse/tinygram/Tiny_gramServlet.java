package com.anasse.tinygram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class Tiny_gramServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * Méthode peuplant le datastore en crééant 50 Utilisateurs 
	 * ayant 10 followers et 10 followings chacun
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Insertion de 50 entities.");
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Random r = new Random();
		int nbUsers = 50;
		
		for (int i = 0; i < nbUsers; i++) {
			Entity entity = new Entity("User", "user_fac" + i);
			entity.setProperty("username", "Bernard44");
			entity.setProperty("firstName", "Bernard");
			entity.setProperty("name", "Dupond");

			ArrayList<String> followers = new ArrayList<String>();
			ArrayList<String> followings = new ArrayList<String>();
			
			for (int j = 0; j < 10; j++) {
				followers.add("user_fac" + r.nextInt(nbUsers+1));
				followings.add("user_fac" + r.nextInt(nbUsers+1));
			}
			
			entity.setProperty("followers", followers);
			entity.setProperty("followings", followings);
	
			ds.put(entity);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}