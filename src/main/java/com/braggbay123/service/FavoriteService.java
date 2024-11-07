package com.braggbay123.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay123.domain.Favorite;
import com.braggbay123.dto.FavoriteDTO;
import com.braggbay123.dto.FavoriteSearchDTO;
import com.braggbay123.dto.FavoritePageDTO;
import com.braggbay123.dto.FavoriteConvertCriteriaDTO;
import com.braggbay123.service.GenericService;
import com.braggbay123.dto.common.RequestDTO;
import com.braggbay123.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FavoriteService extends GenericService<Favorite, Integer> {

	List<Favorite> findAll();

	ResultDTO addFavorite(FavoriteDTO favoriteDTO, RequestDTO requestDTO);

	ResultDTO updateFavorite(FavoriteDTO favoriteDTO, RequestDTO requestDTO);

    Page<Favorite> getAllFavorites(Pageable pageable);

    Page<Favorite> getAllFavorites(Specification<Favorite> spec, Pageable pageable);

	ResponseEntity<FavoritePageDTO> getFavorites(FavoriteSearchDTO favoriteSearchDTO);
	
	List<FavoriteDTO> convertFavoritesToFavoriteDTOs(List<Favorite> favorites, FavoriteConvertCriteriaDTO convertCriteria);

	FavoriteDTO getFavoriteDTOById(Integer favoriteId);







}





