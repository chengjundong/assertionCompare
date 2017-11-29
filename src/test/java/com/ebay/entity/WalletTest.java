package com.ebay.entity;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WalletTest {

	private final int walletId = 100;
	private final String walletOwner = "jucheng";
	private final Wallet wallet = new Wallet();

	// CC
	private final int ccId = 1;
	private final String ccVal = "CC_123";

	// DD
	private final int ddId = 2;
	private final String ddVal = "DD_456";

	// PP
	private final int ppId = 3;
	private final String ppVal = "PP_789";

	@Before
	public void setUp() {
		Set<Instrument> instruments = new HashSet<>(3);
		instruments.add(new Instrument(ccId, ccVal, InstrumentType.CreditCard));
		instruments.add(new Instrument(ddId, ddVal, InstrumentType.DirectDebit));
		instruments.add(new Instrument(ppId, ppVal, InstrumentType.PayPal));
		wallet.setId(walletId);
		wallet.setOwner(walletOwner);
		wallet.setInstruments(instruments);
	}

	@Test
	public void testNotEmpty() throws Exception {
		// junit
		Assert.assertFalse(wallet.getInstruments().isEmpty());

		// Hamcrest
		// assertThat(wallet.getInstruments(), not(empty()));
		Assert.assertThat(wallet.getInstruments(), Matchers.not(Matchers.empty()));

		// AssertJ
		// assertThat(wallet.getInstruments()).isNotEmpty();
		Assertions.assertThat(wallet.getInstruments()).isNotEmpty();
	}

	@Test
	public void testSize() throws Exception {
		// junit
		Assert.assertEquals(3, wallet.getInstruments().size());

		// Hamcrest
		Assert.assertThat(wallet.getInstruments(), Matchers.hasSize(3));

		// AssertJ
		Assertions.assertThat(wallet.getInstruments()).hasSize(3);
	}

	@Test
	public void testPPIdFromInstruments() throws Exception {
		// junit
		for (Instrument instrument : wallet.getInstruments()) {
			if (InstrumentType.PayPal.equals(instrument.getType())) {
				Assert.assertEquals(ppId, instrument.getId().intValue());
			}
		}

		// Hamcrest
		Assert.assertThat(wallet.getInstruments(),
				Matchers.hasItem(Matchers.allOf(Matchers.hasProperty("type", Matchers.is(InstrumentType.PayPal)),
						Matchers.hasProperty("id", Matchers.is(ppId)))));

		// AssertJ
		Assertions.assertThat(wallet.getInstruments()).filteredOn("type", InstrumentType.PayPal).hasSize(1)
				.extracting("id").first().isEqualTo(ppId);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testPairValueByExacting() throws Exception {
		// CC type + cc id & PP type + pp id

		// Hamcrest
		Assert.assertThat(wallet.getInstruments(),
				Matchers.hasItems(
						Matchers.allOf(Matchers.hasProperty("type", Matchers.is(InstrumentType.PayPal)),
								Matchers.hasProperty("id", Matchers.is(ppId))),
						Matchers.allOf(Matchers.hasProperty("type", Matchers.is(InstrumentType.CreditCard)),
								Matchers.hasProperty("id", Matchers.is(ccId)))));

		// AssertJ
		Assertions.assertThat(wallet.getInstruments()).extracting("id", "type").contains(
				Assertions.tuple(ccId, InstrumentType.CreditCard), Assertions.tuple(ppId, InstrumentType.PayPal));
	}

	@Test
	public void testRunMethod() throws Exception {
		// Run a sample method of a list of items and assert the return value

		// AssertJ
		Assertions.assertThat(wallet.getInstruments())
			.extractingResultOf("showTypeShortName", String.class)
			.containsExactlyInAnyOrder("CC", "PP", "DD");
	}
}
