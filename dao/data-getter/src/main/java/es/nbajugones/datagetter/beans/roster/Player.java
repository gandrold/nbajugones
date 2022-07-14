/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter.beans.roster;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author iblanco
 */
public class Player {

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("display_name")
	private String displayName;

	@JsonProperty("birthdate")
	private Date birthdate;

	@JsonProperty("birthplace")
	private String birthplace;

	@JsonProperty("age")
	private Integer age;

	@JsonProperty("height_in")
	private Integer heightIn;

	@JsonProperty("height_cm")
	private Float heightCm;

	@JsonProperty("height_m")
	private Float heightM;

	@JsonProperty("height_formatted")
	private String heightFormatted;

	@JsonProperty("weight_lb")
	private Integer weightIn;

	@JsonProperty("weight_kg")
	private Float weightKg;

	@JsonProperty("position")
	private String position;

	@JsonProperty("uniform_number")
	private Integer uniformNumber;

	@JsonProperty("roster_status")
	private String status;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getHeightIn() {
		return heightIn;
	}

	public void setHeightIn(Integer heightIn) {
		this.heightIn = heightIn;
	}

	public Float getHeightCm() {
		return heightCm;
	}

	public void setHeightCm(Float heightCm) {
		this.heightCm = heightCm;
	}

	public Float getHeightM() {
		return heightM;
	}

	public void setHeightM(Float heightM) {
		this.heightM = heightM;
	}

	public String getHeightFormatted() {
		return heightFormatted;
	}

	public void setHeightFormatted(String heightFormatted) {
		this.heightFormatted = heightFormatted;
	}

	public Integer getWeightIn() {
		return weightIn;
	}

	public void setWeightIn(Integer weightIn) {
		this.weightIn = weightIn;
	}

	public Float getWeightKg() {
		return weightKg;
	}

	public void setWeightKg(Float weightKg) {
		this.weightKg = weightKg;
	}



	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getUniformNumber() {
		return uniformNumber;
	}

	public void setUniformNumber(Integer uniformNumber) {
		this.uniformNumber = uniformNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
