package com.hostelawaygo.persistence;

import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hostelawaygo.Person;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Component
public class MongoDbConnection {
	
	private Logger LOG = Logger.getLogger(MongoDbConnection.class);

	public void save(Person p) throws ServletException {
		LOG.info("Saving Person " + p);
		DBCollection table = getConnection().getCollection("user");
		BasicDBObject document = new BasicDBObject();
		document.put("name", p.getName());
		document.put("age", p.getId());
		document.put("createdDate", new Date()); 
		table.insert(document);
		LOG.info("Saved Person " + p);
	}
	
	public Person retrieve(Person p) throws ServletException {
		LOG.info("Retrieveing Person " + p);
		DBCollection table = getConnection().getCollection("user");

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", p.getName());

		DBCursor cursor = table.find(searchQuery);
		Person person = new Person();
		while (cursor.hasNext()) {
			//System.out.println(cursor.next());
			DBObject obj = cursor.next();
			LOG.info("Retried obj " + obj);
			person.setId((Integer) obj.get("age"));
			person.setLocation("test");
			person.setName((String) obj.get("name"));
		}
		return person;
	}
	
	private DB getConnection() throws ServletException {
		MongoClient mongoClient = null;
		try {
			System.out.println(System.getenv("OPENSHIFT_MONGODB_DB_HOST"));
			System.out.println(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
			System.out.println(System.getenv("OPENSHIFT_MONGODB_DB_USERNAME"));
			System.out.println(System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD"));
			System.out.println(System.getenv("OPENSHIFT_MONGODB_DB_URL"));
			mongoClient = new MongoClient(new MongoClientURI(System.getenv("OPENSHIFT_MONGODB_DB_URL")));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 throw new ServletException(e);
		}
		DB db = mongoClient.getDB("hostelawaygo");
		boolean auth = db.authenticate("admin", "1c7L-VsHw5cA".toCharArray());
		if(auth == false) {
			 throw new ServletException("Failed to authenticate against db: "+db);
		}
		return db;
	}
	
}
