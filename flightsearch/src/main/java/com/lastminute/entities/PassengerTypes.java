/**
 * flights search
 */
package com.lastminute.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author ojuarez
 *
 */
@Entity
@Table(name = "passenger_types")
public class PassengerTypes implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "passenger_type_id")
	private int passengerTypeId;
    
	@Column(name = "passenger_type_description")
	private String passengerTypeDescription;
    
	@Column(name = "passenger_type_price")
	private Double passengerTypePrice;
    
	@NotNull
	@Column(name = "passenger_type_days_rule")
	private boolean passengerTypeDaysRule;
	
//	private int numAdultPassengers;
//	private int numChildPassengers;
//	private int numInfantPassengers;
	
	//constructors
	public PassengerTypes() {
    }

	
    public PassengerTypes(int passengerTypeId, boolean passengerTypeDaysRule) {
        this.passengerTypeId = passengerTypeId;
        this.passengerTypeDaysRule = passengerTypeDaysRule;
    }
    public PassengerTypes(int passengerTypeId, String passengerTypeDescription, Double passengerTypePrice, boolean passengerTypeDaysRule) {
       this.passengerTypeId = passengerTypeId;
       this.passengerTypeDescription = passengerTypeDescription;
       this.passengerTypePrice = passengerTypePrice;
       this.passengerTypeDaysRule = passengerTypeDaysRule;
    }
    
    //setters and getters
    public int getPassengerTypeId() {
        return this.passengerTypeId;
    }
    
    public void setPassengerTypeId(int passengerTypeId) {
        this.passengerTypeId = passengerTypeId;
    }
    
    public String getPassengerTypeDescription() {
        return this.passengerTypeDescription;
    }
    
    public void setPassengerTypeDescription(String passengerTypeDescription) {
        this.passengerTypeDescription = passengerTypeDescription;
    }
    
    public Double getPassengerTypePrice() {
        return this.passengerTypePrice;
    }
    
    public void setPassengerTypePrice(Double passengerTypePrice) {
        this.passengerTypePrice = passengerTypePrice;
    }
    
    public boolean isPassengerTypeDaysRule() {
        return this.passengerTypeDaysRule;
    }
    
    public void setPassengerTypeDaysRule(boolean passengerTypeDaysRule) {
        this.passengerTypeDaysRule = passengerTypeDaysRule;
    }
    
//    public int getNumAdultPassengers(){
//    	return this.numAdultPassengers;
//    }
//    
//    public void setNumAdultPassengers (int numAdultPassengers){
//    	this.numAdultPassengers = numAdultPassengers;
//    }
//    
//    public int getNumChildPassengers(){
//    	return this.numChildPassengers;
//    }
//    
//    public void setNumChildPassengers (int numChildPassengers){
//    	this.numChildPassengers = numChildPassengers;
//    }
//    
//    public int getNumInfantPassengers(){
//    	return this.numInfantPassengers;
//    }
//    
//    public void setNumInfantPassengers (int numInfantPassengers){
//    	this.numInfantPassengers = numInfantPassengers;
//    }
}
