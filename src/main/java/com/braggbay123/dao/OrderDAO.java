package com.braggbay123.dao;

import java.util.List;

import com.braggbay123.dao.GenericDAO;
import com.braggbay123.domain.Order;





public interface OrderDAO extends GenericDAO<Order, Integer> {
  
	List<Order> findAll();
	






}


