package com.anasse.entity;

import com.anasse.entity.PMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(name = "messageendpoint", namespace = @ApiNamespace(ownerDomain = "anasse.com", ownerName = "anasse.com", packagePath = "entity"))
public class MessageEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listMessage", path="timeline")
	public CollectionResponse<Message> listMessage(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit,
			@Nullable @Named ("userId") String userId) {

		PersistenceManager mgr = null;
		Cursor cursor = null;
		List<Message> execute = null;

		try {
			mgr = getPersistenceManager();
			Query query = mgr.newQuery(Message.class);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				HashMap<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				query.setExtensions(extensionMap);
			}

			if (limit != null) {
				query.setRange(0, limit);
			}

			execute = (List<Message>) query.execute();
			cursor = JDOCursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Message obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Message> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
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
	public Message insertMessage(Message message) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (containsMessage(message)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.makePersistent(message);
		} finally {
			mgr.close();
		}
		return message;
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
