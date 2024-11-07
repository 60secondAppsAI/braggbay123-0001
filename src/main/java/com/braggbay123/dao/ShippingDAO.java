package com.braggbay123.dao;

import java.util.List;

import com.braggbay123.dao.GenericDAO;
import com.braggbay123.domain.Shipping;





public interface ShippingDAO extends GenericDAO<Shipping, Integer> {
  
	List<Shipping> findAll();
	






}


