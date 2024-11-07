package com.braggbay123.dao;

import java.util.List;

import com.braggbay123.dao.GenericDAO;
import com.braggbay123.domain.Wishlist;





public interface WishlistDAO extends GenericDAO<Wishlist, Integer> {
  
	List<Wishlist> findAll();
	






}


