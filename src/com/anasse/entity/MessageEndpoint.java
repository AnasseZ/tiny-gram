package com.anasse.entity;

import com.anasse.entity.PMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(name = "messageendpoint", namespace = @ApiNamespace(ownerDomain = "anasse.com", ownerName = "anasse.com", packagePath = "entity"))
public class MessageEndpoint {

	
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name="getTimeline", path="{user}/get-timeline/{limit}")
	public List<Message> getTimeLine(
			@Named("user") String userId,
			@Named("limit") Integer limit
			) throws NotFoundException
	{
		PersistenceManager mgr = null;
		List<Message> finalResponse = new ArrayList<Message>();
		List<MessageIndex> queryResult;

		try {
			mgr = getPersistenceManager();
			
			User user = mgr.getObjectById(User.class, userId);
			
			if (user == null) {
				throw new NotFoundException(userId +" not found");
			}
			
			Query query = mgr.newQuery(MessageIndex.class);
			
			String x = "i10";
			query.setFilter("followers == userId");

			query.declareParameters("String userId");

			if (limit != null) {
				query.setRange(0, limit);
			} else {
				query.setRange(0, 10);
			}	
			
			 queryResult = (List<MessageIndex>) query.execute(userId);
			
			// Récupère tout les messages depuis les messagesIndex vu que c'est leurs parents
			// Ainsi que les users associés
			for(MessageIndex msgIndex : queryResult) {
				Message m = mgr.getObjectById(Message.class, msgIndex.getId().getParent());
				User userWhoPosted = mgr.getObjectById(User.class, m.getUserId());
				
				IncompleteUser temp = new IncompleteUser();
				temp.setAvatar(userWhoPosted.getAvatar());
				temp.setFirstName(userWhoPosted.getFirstName());
				temp.setLastName(userWhoPosted.getLastName());
				temp.setUserName(userWhoPosted.getUserName());
				
				m.setUser(temp);
								
				finalResponse.add(m);
			}

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Message obj : finalResponse)
				;
				
		} finally {
			//mgr.close();
		}
		
		return finalResponse;
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getMessage")
	public Message getMessage(@Named("id") String id) {
		PersistenceManager mgr = getPersistenceManager();
		Message message = null;
		try {
			message = mgr.getObjectById(Message.class, id);
		} finally {
			mgr.close();
		}
		return message;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param message the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertMessage", path="create-message")
	public Entity insertMessage(IncompleteMessage incompleteMessage) {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Random r = new Random();
		int indexRandom = r.nextInt(60000-6000) +1;
		Entity e = new Entity("Message", "m" + indexRandom);

		e.setProperty("userId", incompleteMessage.userId);
		e.setProperty("content", incompleteMessage.content);
		e.setProperty("imageUrl", incompleteMessage.imageUrl);
		e.setProperty("hashtags", incompleteMessage.hashtags);
		e.setProperty("publicationDate", new Date());

		datastore.put(e);
		
		indexRandom = r.nextInt(60000-6000) +1;
		Entity messageIndex = new Entity("MessageIndex", "i" + indexRandom, e.getKey());
		messageIndex.setProperty("followers", incompleteMessage.followers);
		
		datastore.put(messageIndex);
		
		return e;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param message the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateMessage")
	public Message updateMessage(Message message) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (!containsMessage(message)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.makePersistent(message);
		} finally {
			mgr.close();
		}
		return message;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeMessage")
	public void removeMessage(@Named("id") String id) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			Message message = mgr.getObjectById(Message.class, id);
			mgr.deletePersistent(message);
		} finally {
			mgr.close();
		}
	}

	private boolean containsMessage(Message message) {
		PersistenceManager mgr = getPersistenceManager();
		boolean contains = true;
		try {
			mgr.getObjectById(Message.class, message.getPostId());
		} catch (javax.jdo.JDOObjectNotFoundException ex) {
			contains = false;
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}

}
