package com.ebay.entity;

public enum InstrumentType {

	CreditCard("CC"), DirectDebit("DD"), PayPal("PP");

	private final String shortName;

	private InstrumentType(String shortName) {
		this.shortName = shortName;
	}

	public String getShortName() {
		return shortName;
	}
}
