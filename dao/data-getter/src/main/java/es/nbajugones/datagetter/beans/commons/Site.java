/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter.beans.commons;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author iblanco
 */
public class Site {

	@JsonProperty("surface")
	private String surface;

	@JsonProperty("capacity")
	private Integer capacity;

	@JsonProperty("name")
	private String name;

	@JsonProperty("state")
	private String state;

	@JsonProperty("city")
	private String city;

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
}
