package com.braggbay123.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay123.domain.Shipping;
import com.braggbay123.dto.ShippingDTO;
import com.braggbay123.dto.ShippingSearchDTO;
import com.braggbay123.dto.ShippingPageDTO;
import com.braggbay123.dto.ShippingConvertCriteriaDTO;
import com.braggbay123.service.GenericService;
import com.braggbay123.dto.common.RequestDTO;
import com.braggbay123.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ShippingService extends GenericService<Shipping, Integer> {

	List<Shipping> findAll();

	ResultDTO addShipping(ShippingDTO shippingDTO, RequestDTO requestDTO);

	ResultDTO updateShipping(ShippingDTO shippingDTO, RequestDTO requestDTO);

    Page<Shipping> getAllShippings(Pageable pageable);

    Page<Shipping> getAllShippings(Specification<Shipping> spec, Pageable pageable);

	ResponseEntity<ShippingPageDTO> getShippings(ShippingSearchDTO shippingSearchDTO);
	
	List<ShippingDTO> convertShippingsToShippingDTOs(List<Shipping> shippings, ShippingConvertCriteriaDTO convertCriteria);

	ShippingDTO getShippingDTOById(Integer shippingId);







}





