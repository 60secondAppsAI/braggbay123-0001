package com.braggbay123.dao;

import java.util.List;

import com.braggbay123.dao.GenericDAO;
import com.braggbay123.domain.Notification;





public interface NotificationDAO extends GenericDAO<Notification, Integer> {
  
	List<Notification> findAll();
	






}


