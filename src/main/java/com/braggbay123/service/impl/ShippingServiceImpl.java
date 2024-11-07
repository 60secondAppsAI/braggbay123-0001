package com.braggbay123.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbay123.dao.GenericDAO;
import com.braggbay123.service.GenericService;
import com.braggbay123.service.impl.GenericServiceImpl;
import com.braggbay123.dao.ShippingDAO;
import com.braggbay123.domain.Shipping;
import com.braggbay123.dto.ShippingDTO;
import com.braggbay123.dto.ShippingSearchDTO;
import com.braggbay123.dto.ShippingPageDTO;
import com.braggbay123.dto.ShippingConvertCriteriaDTO;
import com.braggbay123.dto.common.RequestDTO;
import com.braggbay123.dto.common.ResultDTO;
import com.braggbay123.service.ShippingService;
import com.braggbay123.util.ControllerUtils;





@Service
public class ShippingServiceImpl extends GenericServiceImpl<Shipping, Integer> implements ShippingService {

    private final static Logger logger = LoggerFactory.getLogger(ShippingServiceImpl.class);

	@Autowired
	ShippingDAO shippingDao;

	


	@Override
	public GenericDAO<Shipping, Integer> getDAO() {
		return (GenericDAO<Shipping, Integer>) shippingDao;
	}
	
	public List<Shipping> findAll () {
		List<Shipping> shippings = shippingDao.findAll();
		
		return shippings;	
		
	}

	public ResultDTO addShipping(ShippingDTO shippingDTO, RequestDTO requestDTO) {

		Shipping shipping = new Shipping();

		shipping.setShippingId(shippingDTO.getShippingId());


		shipping.setShippingAddress(shippingDTO.getShippingAddress());


		shipping.setShippingMethod(shippingDTO.getShippingMethod());


		shipping.setShippingDate(shippingDTO.getShippingDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		shipping = shippingDao.save(shipping);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Shipping> getAllShippings(Pageable pageable) {
		return shippingDao.findAll(pageable);
	}

	public Page<Shipping> getAllShippings(Specification<Shipping> spec, Pageable pageable) {
		return shippingDao.findAll(spec, pageable);
	}

	public ResponseEntity<ShippingPageDTO> getShippings(ShippingSearchDTO shippingSearchDTO) {
	
			Integer shippingId = shippingSearchDTO.getShippingId(); 
 			String shippingAddress = shippingSearchDTO.getShippingAddress(); 
 			String shippingMethod = shippingSearchDTO.getShippingMethod(); 
   			String sortBy = shippingSearchDTO.getSortBy();
			String sortOrder = shippingSearchDTO.getSortOrder();
			String searchQuery = shippingSearchDTO.getSearchQuery();
			Integer page = shippingSearchDTO.getPage();
			Integer size = shippingSearchDTO.getSize();

	        Specification<Shipping> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, shippingId, "shippingId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, shippingAddress, "shippingAddress"); 
			
			spec = ControllerUtils.andIfNecessary(spec, shippingMethod, "shippingMethod"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("shippingAddress")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("shippingMethod")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Shipping> shippings = this.getAllShippings(spec, pageable);
		
		//System.out.println(String.valueOf(shippings.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(shippings.getTotalPages()));
		
		List<Shipping> shippingsList = shippings.getContent();
		
		ShippingConvertCriteriaDTO convertCriteria = new ShippingConvertCriteriaDTO();
		List<ShippingDTO> shippingDTOs = this.convertShippingsToShippingDTOs(shippingsList,convertCriteria);
		
		ShippingPageDTO shippingPageDTO = new ShippingPageDTO();
		shippingPageDTO.setShippings(shippingDTOs);
		shippingPageDTO.setTotalElements(shippings.getTotalElements());
		return ResponseEntity.ok(shippingPageDTO);
	}

	public List<ShippingDTO> convertShippingsToShippingDTOs(List<Shipping> shippings, ShippingConvertCriteriaDTO convertCriteria) {
		
		List<ShippingDTO> shippingDTOs = new ArrayList<ShippingDTO>();
		
		for (Shipping shipping : shippings) {
			shippingDTOs.add(convertShippingToShippingDTO(shipping,convertCriteria));
		}
		
		return shippingDTOs;

	}
	
	public ShippingDTO convertShippingToShippingDTO(Shipping shipping, ShippingConvertCriteriaDTO convertCriteria) {
		
		ShippingDTO shippingDTO = new ShippingDTO();
		
		shippingDTO.setShippingId(shipping.getShippingId());

	
		shippingDTO.setShippingAddress(shipping.getShippingAddress());

	
		shippingDTO.setShippingMethod(shipping.getShippingMethod());

	
		shippingDTO.setShippingDate(shipping.getShippingDate());

	

		
		return shippingDTO;
	}

	public ResultDTO updateShipping(ShippingDTO shippingDTO, RequestDTO requestDTO) {
		
		Shipping shipping = shippingDao.getById(shippingDTO.getShippingId());

		shipping.setShippingId(ControllerUtils.setValue(shipping.getShippingId(), shippingDTO.getShippingId()));

		shipping.setShippingAddress(ControllerUtils.setValue(shipping.getShippingAddress(), shippingDTO.getShippingAddress()));

		shipping.setShippingMethod(ControllerUtils.setValue(shipping.getShippingMethod(), shippingDTO.getShippingMethod()));

		shipping.setShippingDate(ControllerUtils.setValue(shipping.getShippingDate(), shippingDTO.getShippingDate()));



        shipping = shippingDao.save(shipping);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ShippingDTO getShippingDTOById(Integer shippingId) {
	
		Shipping shipping = shippingDao.getById(shippingId);
			
		
		ShippingConvertCriteriaDTO convertCriteria = new ShippingConvertCriteriaDTO();
		return(this.convertShippingToShippingDTO(shipping,convertCriteria));
	}







}
