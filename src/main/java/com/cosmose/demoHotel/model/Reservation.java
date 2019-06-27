package com.cosmose.demoHotel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "T_RESERVATION")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Reservation extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1777830981375104366L;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;// user who booked the room

	private String residentName;// room guest

	@ManyToMany
	@JoinTable(name = "T_ROOM_ORDER")
	@OrderBy("id")
	private List<Room> rooms;

	private Date checkInDate;

	private Date checkOutDate;

	@Enumerated
	@Column(nullable = false)
	@Builder.Default
	private BookingState bookingState = BookingState.ACTIVE;
}
