package com.ebay.controller;

import static org.mockito.BDDMockito.*;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.ebay.entity.Instrument;
import com.ebay.entity.InstrumentType;
import com.ebay.entity.TransactionResult;
import com.ebay.entity.Wallet;
import com.ebay.service.PaymentService;
import com.ebay.service.WalletService;

/**
 * TDD cases
 * 
 * @author Administrator
 *
 */
public class PaymentControllerTest {

	@InjectMocks
	private PaymentController ctrl;

	@Mock
	private WalletService walletSvc;

	@Mock
	private PaymentService pmtSvc;

	private final int walletId = 1;
	private final String trxId = "123456";
	private final double amount = 10.1;

	private final Instrument cc = new Instrument(1, "CC_1", InstrumentType.CreditCard);
	private final Instrument pp = new Instrument(2, "PP_2", InstrumentType.PayPal);

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shippingLabel_PayWithCC_Paid_Hamcrest() throws Exception {
		// assign
		given(walletSvc.getWallet(walletId)).willAnswer(new Answer<Wallet>() {
			@Override
			public Wallet answer(InvocationOnMock arg0) throws Throwable {
				Set<Instrument> instruments = new HashSet<>(1);
				instruments.add(cc);

				Wallet result = new Wallet();
				result.setInstruments(instruments);
				return result;
			}
		});

		given(pmtSvc.payWithInstrument(cc, amount)).willReturn(trxId);

		// act
		TransactionResult trx = ctrl.buyLabel(walletId, InstrumentType.CreditCard, amount);

		// assert
		Assert.assertNotNull(trx);
		Assert.assertEquals(trx.getTrxId(), trxId);
		Assert.assertTrue(trx.isSuccess());
	}

	@Test
	public void shippingLabel_PayWithCC_Paid_AssertJ() throws Exception {
		// assign
		given(walletSvc.getWallet(walletId)).willAnswer(new Answer<Wallet>() {
			@Override
			public Wallet answer(InvocationOnMock arg0) throws Throwable {
				Set<Instrument> instruments = new HashSet<>(1);
				instruments.add(cc);

				Wallet result = new Wallet();
				result.setInstruments(instruments);
				return result;
			}
		});

		given(pmtSvc.payWithInstrument(cc, amount)).willReturn(trxId);

		// act
		TransactionResult trx = ctrl.buyLabel(walletId, InstrumentType.CreditCard, amount);

		// assert
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(trx).isNotNull();
		softly.assertThat(trx).hasFieldOrPropertyWithValue("trxId", trxId);
		softly.assertThat(trx).hasFieldOrPropertyWithValue("success", true);
		softly.assertAll();
	}

	@Test
	public void sellerFee_PayWithPP_Paid_Hamcrest() throws Exception {
		// assign
		given(walletSvc.getWallet(walletId)).willAnswer(new Answer<Wallet>() {
			@Override
			public Wallet answer(InvocationOnMock arg0) throws Throwable {
				Set<Instrument> instruments = new HashSet<>(1);
				instruments.add(pp);

				Wallet result = new Wallet();
				result.setInstruments(instruments);
				return result;
			}
		});

		given(pmtSvc.payWithInstrument(pp, amount)).willReturn(trxId);

		// act
		TransactionResult trx = ctrl.sellerFee(walletId, InstrumentType.PayPal, amount);

		// assert
		Assert.assertNotNull(trx);
		Assert.assertEquals(trx.getTrxId(), trxId);
		Assert.assertTrue(trx.isSuccess());
	}

	@Test
	public void sellerFee_PayWithPP_Paid_AssertJ() throws Exception {
		// assign
		given(walletSvc.getWallet(walletId)).willAnswer(new Answer<Wallet>() {
			@Override
			public Wallet answer(InvocationOnMock arg0) throws Throwable {
				Set<Instrument> instruments = new HashSet<>(1);
				instruments.add(pp);

				Wallet result = new Wallet();
				result.setInstruments(instruments);
				return result;
			}
		});

		given(pmtSvc.payWithInstrument(pp, amount)).willReturn(trxId);

		// act
		TransactionResult trx = ctrl.sellerFee(walletId, InstrumentType.PayPal, amount);
		
		// assert
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(trx).isNotNull();
		softly.assertThat(trx).hasFieldOrPropertyWithValue("trxId", trxId);
		softly.assertThat(trx).hasFieldOrPropertyWithValue("success", true);
		softly.assertAll();
	}
}
