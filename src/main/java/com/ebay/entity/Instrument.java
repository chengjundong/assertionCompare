package com.ebay.entity;

import java.io.Serializable;

public class Instrument implements Serializable {

	private static final long serialVersionUID = 9121840953329461708L;

	private Integer id;
	private String value;
	private InstrumentType type;

	public Instrument() {
	}

	public Instrument(Integer id, String value, InstrumentType type) {
		this.id = id;
		this.value = value;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public InstrumentType getType() {
		return type;
	}

	public void setType(InstrumentType type) {
		this.type = type;
	}
	
	public String showTypeShortName() {
		return null == this.type ? "" : this.type.getShortName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instrument other = (Instrument) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Instrument [id=" + id + ", value=" + value + ", type=" + type + "]";
	}
}
