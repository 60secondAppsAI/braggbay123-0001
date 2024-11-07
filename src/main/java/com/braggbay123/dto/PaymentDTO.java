package com.braggbay123.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PaymentDTO {

	private Integer paymentId;

	private String paymentMethod;

	private Date paymentDate;

	private Double amount;






}
