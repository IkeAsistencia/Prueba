package com.ike.service.banbajio.generateInstructionCollection.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class petition2 {

	@NotBlank(message = "Charge type cannot be empty")
	private String typeCharge;
	
	@NotNull(message = "Customer number cannot be empty")
	private Integer numberCustomer;
	
	@NotBlank(message = "clAfiltmk cannot be empty")
	private String clAfiltmk;
	
	@NotBlank(message = "product cannot be empty")
	private String product;
	
	//@NotBlank(message = "byProduct cannot be empty")
	private String byProduct;
	
	@NotBlank(message = "card cannot be empty")
	private String card;
	
	@NotBlank(message = "packet cannot be empty")
	private String packet;
	
	//@NotBlank(message = "description cannot be empty")
	private String description;
	
	private boolean gratuitous;
	
	@NotBlank(message = "comments cannot be empty")
	private String comments;

	public String getTypeCharge() {
		return typeCharge;
	}

	public void setTypeCharge(String typeCharge) {
		this.typeCharge = typeCharge;
	}

	public Integer getNumberCustomer() {
		return numberCustomer;
	}

	public void setNumberCustomer(Integer numberCustomer) {
		this.numberCustomer = numberCustomer;
	}

	public String getClAfiltmk() {
		return clAfiltmk;
	}

	public void setClAfiltmk(String clAfiltmk) {
		this.clAfiltmk = clAfiltmk;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getByProduct() {
		return byProduct;
	}

	public void setByProduct(String byProduct) {
		this.byProduct = byProduct;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getPacket() {
		return packet;
	}

	public void setPacket(String packet) {
		this.packet = packet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isGratuitous() {
		return gratuitous;
	}

	public void setGratuitous(boolean gratuitous) {
		this.gratuitous = gratuitous;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
