package com.hostelawaygo;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hostelawaygo.component.IPersonService;

@RestController
@RequestMapping("/data")
public class PersonController {
	@Autowired
	private IPersonService personService;
	
	@RequestMapping("/person")
	public Person getPersonDetail(@RequestParam(value = "id",required = false,
	                                                    defaultValue = "0") Integer id,
									@RequestParam(value = "name",required = false,
										defaultValue = "0") String name) throws ServletException {
		Person p = personService.getPersonDetail(id, name);
		return p;
	}
	
	@RequestMapping("/insert")
	public void insertPersonDetail(@RequestParam(value = "name",required = false,
	                                                    defaultValue = "0") String name) throws ServletException {
		Person p = new Person();
		p.setName(name);
		personService.insertPersonDetail(p);
	}
}