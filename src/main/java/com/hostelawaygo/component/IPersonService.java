package com.hostelawaygo.component;

import javax.servlet.ServletException;

import com.hostelawaygo.Person;

public interface IPersonService {
  public Person getPersonDetail(final Integer id, final String name) throws ServletException;
  
  public void insertPersonDetail(final Person person) throws ServletException;
}
