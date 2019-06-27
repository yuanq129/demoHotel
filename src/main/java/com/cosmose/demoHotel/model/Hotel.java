package com.cosmose.demoHotel.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "T_HOTEL")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Hotel extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7954118428575499462L;

	@ManyToOne
	@JoinColumn(name="hotelOwnerId")
	private HotelOwner hotelOwner;
	
	private String name;
	
	private String city;
	
	private String address;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="hotel")
	private Set<Room> rooms;
}
