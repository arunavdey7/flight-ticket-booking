package com.nagarro.Assignment_4v1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flight_record")
public class FlightData {
	
	@Id
	@Column(name="flight_no")
	private String flight_no;
	
	@Column(name="dep_loc")
	private String dep_loc;
	
	@Column(name="arr_loc")
	private String arr_loc;
	
	@Column(name="valid_till")
	private String valid_till;
	
	@Column(name="flight_time")
	private String flight_time;
	
	@Column(name="flight_dur")
	private Double flight_dur;
	
	@Column(name="fare")
	private int fare;
	
	@Column(name="seat_availability")
	private String seat_availability;


	@Column(name="passenger_class")
	private String passenger_class;
	
	public FlightData()
	{
		this.flight_no = "";
		this.dep_loc = "";
		this.arr_loc = "";
		this.valid_till = "";		
		this.flight_time = "";
		this.flight_dur = 0.0;
		this.fare = 0;
		this.seat_availability = "";
		this.passenger_class = "";
	}
	
	public FlightData(String flight_no,String dep_loc,String arr_loc,
			String valid_till,String flight_time,Double flight_dur,
			int fare,String seat_availability,String passenger_class)
	{
		this.flight_no = flight_no;
		this.dep_loc = dep_loc;
		this.arr_loc = arr_loc;
		this.valid_till = valid_till;		
		this.flight_time = flight_time;
		this.flight_dur = flight_dur;
		this.fare = fare;
		this.seat_availability = seat_availability;
		this.passenger_class = passenger_class;
	}
	
	//getters
	
	public String getFlightNo()
	{
		return this.flight_no;
	}
	public String getDepLoc()
	{
		return this.dep_loc;
	}
	public String getArrLoc()
	{
		return this.arr_loc;
	}
	public String getValidTill()
	{
		return this.valid_till;
	}
	public String getFlightTime()
	{
		return this.flight_time;
	}
	public Double getFlightDur()
	{
		return this.flight_dur;
	}
	public int getFare()
	{
		return this.fare;
	}
	public String getSeatAvailability()
    {
		return this.seat_availability;
	}
	public String getPassengerClass()
	{
		return this.passenger_class;
	}
	
	//Setters
	
	public void setFlightNo(String flight_no)
	{
		this.flight_no = flight_no;
	}
	public void setDepLoc(String dep_loc)
	{
		this.dep_loc = dep_loc;
	}
	public void setArrLoc(String arr_loc)
	{
		this.arr_loc = arr_loc;
	}
	public void setValidTill(String valid_till)
	{
		this.valid_till = valid_till;
	}
	public void setFlightTime(String flight_time)
	{
		this.flight_time = flight_time;
	}
	public void setFlightDur(Double flight_dur)
	{
		this.flight_dur = flight_dur;
	}
	public void setFare(int fare)
	{
		this.fare = fare;
	}
	public void setSeatAvailability(String seat_availability)
	{
		this.seat_availability = seat_availability;
	}
	public void setPassengerClass(String passenger_class)
	{
		this.passenger_class = passenger_class;
	}
	
}
