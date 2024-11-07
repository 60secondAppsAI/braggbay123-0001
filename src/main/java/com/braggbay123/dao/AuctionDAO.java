package com.braggbay123.dao;

import java.util.List;

import com.braggbay123.dao.GenericDAO;
import com.braggbay123.domain.Auction;





public interface AuctionDAO extends GenericDAO<Auction, Integer> {
  
	List<Auction> findAll();
	






}


