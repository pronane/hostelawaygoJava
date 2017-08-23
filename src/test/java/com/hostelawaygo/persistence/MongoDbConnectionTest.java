package com.hostelawaygo.persistence;

import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.ServletException;

import org.junit.Ignore;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

@Ignore
public class MongoDbConnectionTest {

	@Test
	public void testSave() throws ServletException, UnknownHostException {
		System.out.println("Running unit test ");
		//mongodb://admin:1c7L-VsHw5cA@127.4.168.2:27017/
		MongoClient mongoClient = new MongoClient("127.4.168.2" , 27017 );
		DB db = mongoClient.getDB("hostelawaygo");
		boolean auth = db.authenticate("admin", "1c7L-VsHw5cA".toCharArray());
		if(auth == false) {
			System.out.println("Error could not authenticate");
			
		}
		DBCollection table = db.getCollection("user");
		BasicDBObject document = new BasicDBObject();
		document.put("name", "mkyong");
		document.put("age", 30);
		document.put("createdDate", new Date());
		table.insert(document);
		System.out.println("Finished unit test succesfully");
	}
}
