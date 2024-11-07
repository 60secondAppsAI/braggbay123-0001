package com.braggbay123.dao;

import java.util.List;

import com.braggbay123.dao.GenericDAO;
import com.braggbay123.domain.WishlistItem;





public interface WishlistItemDAO extends GenericDAO<WishlistItem, Integer> {
  
	List<WishlistItem> findAll();
	






}


