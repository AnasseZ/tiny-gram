package com.anasse.tinygram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
		response.getWriter().append("Insertion de 50 messages liés à 100user.");
				
		Random r=new Random();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		DataConstant dataConstant = new DataConstant();
		
		int maxmessage=50;
		int maxuser=100;
		
		for (int i = 0; i < maxmessage; i++) {
			Entity e = new Entity("Message", "m" + i);
			int indexRandom = r.nextInt(dataConstant.contents.size());
			e.setProperty("userId", "u"+r.nextInt(maxuser+1));
			e.setProperty("content", dataConstant.contents.get(indexRandom));
			
			indexRandom = r.nextInt(dataConstant.imageUrls.size());
			response.getWriter().println(indexRandom +  "    " + dataConstant.imageUrls.get(indexRandom));	
			e.setProperty("imageUrl", dataConstant.imageUrls.get(indexRandom));
			
			ArrayList<String> hashtags = new ArrayList<String>();
			
			for(int j = 0; j < (indexRandom +1); j++) {
				indexRandom = r.nextInt(dataConstant.hashtags.size());
				hashtags.add(dataConstant.hashtags.get(indexRandom));
			}
			
			e.setProperty("hashtags",hashtags);

			e.setProperty("publicationDate",new GregorianCalendar(
					r.nextInt(2018-2017) + 2017,
					r.nextInt(12-1) + 1,
					r.nextInt(31-1) + 1,
					r.nextInt(24-1) + 1,
					r.nextInt(60) + 1
					).getTime()
			);
			
			datastore.put(e);
			
			Entity index = new Entity("MessageIndex","i"+i,e.getKey());
			
			ArrayList<String> followers = new ArrayList<String>();
			
			for (int j = 0; j < 100; j++) {
				followers.add("u"+r.nextInt(maxuser+1));
			}
			
			index.setProperty("followers", followers);
			
			response.getWriter().println("wrote:"+e.getKey()+","+index.getKey() + "<br>");
			
			datastore.put(index);
		}
		response.getWriter().println("done");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}