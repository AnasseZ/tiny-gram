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

public class PopulateUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * Méthode peuplant le datastore en crééant 50 Utilisateurs 
	 * ayant 10 followers
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("Insertion de 500 users.");
				
		Random r=new Random();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		DataConstant dataConstant = new DataConstant();
		
		int maxUsers=5010;
		int maxFollowers=5000;
		int nbFollowers;
		
		for (int i = 4800; i < maxUsers; i++) {
			Entity e = new Entity("User", "u" + i);
			int indexRandom = r.nextInt(dataConstant.firstName.size());
			e.setProperty("userName",  dataConstant.firstName.get(indexRandom) + "Du"+ r.nextInt(96));
			e.setProperty("firstName", dataConstant.firstName.get(indexRandom));
			e.setProperty("lastName", new StringBuilder(dataConstant.firstName.get(indexRandom)).reverse().toString());
			
			indexRandom = r.nextInt(dataConstant.profilImagesUrl.size());
			e.setProperty("avatar", dataConstant.profilImagesUrl.get(indexRandom));
			
			ArrayList<String> followers = new ArrayList<>();
			
			if (i == 0) {
				nbFollowers = 100;
			} else if (i == 1)  {
				nbFollowers = 1000;
			} else if (i == 2) {
				nbFollowers = 5000;
			} else {
				nbFollowers = r.nextInt((maxFollowers - 100) + 100);
			}
			
			for(int j = 0; j < nbFollowers; j++) {
				indexRandom = r.nextInt(maxFollowers+1);
				followers.add("u" + indexRandom);
			}
			
			e.setProperty("followers", followers);
			
			datastore.put(e);
		}
		response.getWriter().println("done");	
	}
}