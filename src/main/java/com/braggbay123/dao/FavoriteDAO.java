package com.braggbay123.dao;

import java.util.List;

import com.braggbay123.dao.GenericDAO;
import com.braggbay123.domain.Favorite;





public interface FavoriteDAO extends GenericDAO<Favorite, Integer> {
  
	List<Favorite> findAll();
	






}


