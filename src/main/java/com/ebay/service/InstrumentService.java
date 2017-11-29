package com.ebay.service;

import com.ebay.dao.InstrumentDAO;
import com.ebay.entity.Instrument;

public class InstrumentService {

	private final InstrumentDAO dao;

	public InstrumentService(InstrumentDAO dao) {
		this.dao = dao;
	}

	public Instrument getInstrument(int id) {
		return dao.findInstrumentById(id);
	}
}
