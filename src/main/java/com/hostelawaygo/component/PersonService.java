package com.hostelawaygo.component;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hostelawaygo.Person;
import com.hostelawaygo.persistence.MongoDbConnection;

@Component
public class PersonService implements IPersonService {
	
	@Autowired
	private MongoDbConnection mongDBConnection;
	
	@Override
	public Person getPersonDetail(final Integer id, final String name) throws ServletException{
		Person  p = new Person();
		p.setId(id);
		p.setName(name); 
		Person person = mongDBConnection.retrieve(p);
		return person;
	}
	
	@Override
	public void insertPersonDetail(Person person) throws ServletException{
		Person p = new Person();
		p.setLocation("Ronane");
		p.setName(person.getName());
		mongDBConnection.save(p);
	}
}
