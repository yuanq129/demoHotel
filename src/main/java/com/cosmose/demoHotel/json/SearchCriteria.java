package com.cosmose.demoHotel.json;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchCriteria {

	private Date checkInDate;
	
	private Date checkOutDate;
	
	private String city;
	
	private int priceMin;
	
	private int priceMax;
}
