package com.ebay.controller;

import com.ebay.entity.Instrument;
import com.ebay.entity.InstrumentType;
import com.ebay.entity.TransactionResult;
import com.ebay.entity.Wallet;
import com.ebay.service.PaymentService;
import com.ebay.service.WalletService;

public class PaymentController {

	private final WalletService walletSvc;
	@SuppressWarnings("unused")
	private final PaymentService pmtSvc;

	public PaymentController(WalletService walletSvc, PaymentService pmtSvc) {
		this.walletSvc = walletSvc;
		this.pmtSvc = pmtSvc;
	}

	public TransactionResult buyLabel(int walletId, InstrumentType type, Double amount) {
		TransactionResult result = new TransactionResult();
		Wallet wallet = walletSvc.getWallet(walletId);
		for(Instrument instrument : wallet.getInstruments()) {
			if(type.equals(instrument.getType())) {
				// TODO call payment & set trxid & set success to true
			}
		}
		return result;
	}
	
	public TransactionResult sellerFee(int walletId, InstrumentType type, Double amount) {
		// TODO biz logic in here
		return null;
	}
}
