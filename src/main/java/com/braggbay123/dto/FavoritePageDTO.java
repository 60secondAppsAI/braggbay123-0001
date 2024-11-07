package com.braggbay123.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FavoritePageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<FavoriteDTO> favorites;
}




