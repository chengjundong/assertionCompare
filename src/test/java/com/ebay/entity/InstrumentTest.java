package com.ebay.entity;

import java.util.regex.Pattern;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InstrumentTest {

	private final int id = 1;
	private final String instrumentRef = "CC_123";
	private final InstrumentType type = InstrumentType.CreditCard;
	private final Instrument cc = new Instrument();
	private final String regex = "^(CC|DD|PP)_\\d+$";

	@Before
	public void setUp() {
		cc.setId(id);
		cc.setValue(instrumentRef);
		cc.setType(type);
	}

	@Test
	public void testNotNull() throws Exception {
		// junit assert
		Assert.assertNotNull(cc.getValue());
		
		// junit assert with Hamcrest extention
		Assert.assertThat(cc.getValue(), Matchers.notNullValue());
		cc.setValue("");
		Assert.assertThat(cc.getValue(), Matchers.isEmptyString());
		Assert.assertThat(cc.getValue(), Matchers.isEmptyOrNullString());
		
		// AssertJ
		Assertions.assertThat(cc.getValue()).isNotNull();
		cc.setValue("");
		Assertions.assertThat(cc.getValue()).isEmpty();
	}
	
	@Test
	public void testIntegerEquals() throws Exception {
		// junit assert
		Assert.assertEquals(id, cc.getId().intValue());

		// junit assert with Hamcrest extention
		// assertThat(cc.getId(), is(id));
		Assert.assertThat(cc.getId(), Matchers.is(id));

		// AssertJ
		// assertThat(cc.getId()).isEqualTo(id);
		Assertions.assertThat(cc.getId()).isEqualTo(id);
	}

	@Test
	public void testStringStartsWith() throws Exception {
		// junit assert
		Assert.assertEquals(id, cc.getId().intValue());

		// junit assert with Hamcrest extention
		// assertThat(cc.getId(), is(id));
		Assert.assertThat(cc.getId(), Matchers.is(id));

		// AssertJ
		// assertThat(cc.getId()).isEqualTo(id);
		Assertions.assertThat(cc.getId()).isEqualTo(id);
	}

	@Test
	public void testStringRegex() throws Exception {
		// junit assert
		Assert.assertTrue(Pattern.matches(regex, cc.getValue()));

		// junit assert with Hamcrest extention
		// Not support. You could find this method in Hamcrest 2 (2015, 2.0.0)
		// assertThat("FooBarBaz", matchesPattern("^Foo"));

		// AssertJ
		// assertThat(cc.getValue()).matches(regex)
		Assertions.assertThat(cc.getValue()).matches(regex);
	}

	@Test
	public void testHamcrestAssertError() throws Exception {
		String errorMsg = "Instrument value should be %0";

		String fakeInstrumentRef = "fake";
		cc.setValue(fakeInstrumentRef);
		
		// junit assert with Hamcrest extention
		// assertThat(instrumentRef, describedAs(errorMsg, equalTo(cc.getValue()), instrumentRef));
		Assert.assertThat(instrumentRef, Matchers.describedAs(errorMsg, Matchers.is(cc.getValue()), instrumentRef));
	}

	@Test
	public void testAssertJAssertError() throws Exception {
		String errorMsg = "Instrument value should be %s";

		String fakeInstrumentRef = "fake";
		cc.setValue(fakeInstrumentRef);

		// AssertJ
		// assertThat(cc.getValue()).as(errorMsg, instrumentRef).isEqualTo(instrumentRef);
		Assertions.assertThat(cc.getValue()).as(errorMsg, instrumentRef).isEqualTo(instrumentRef);
	}
	
	@Test
	public void testFieldCompare() throws Exception {
		final Instrument cc2 = new Instrument();
		cc2.setType(InstrumentType.CreditCard);
		cc2.setId(1000);
		cc2.setValue("CC_222");
		
		// junit assert
		Assert.assertNotEquals(cc2.getId(), cc.getId());
		Assert.assertNotEquals(cc2.getValue(), cc.getValue());
		Assert.assertEquals(cc2.getType(), cc.getType());
		
		// junit assert with Hamcrest extention
		// assertThat(cc, hasProperty("id", not(cc2.getId())));
		Assert.assertThat(cc, Matchers.hasProperty("id", Matchers.not(cc2.getId())));
		Assert.assertThat(cc, Matchers.hasProperty("value", Matchers.not(cc2.getValue())));
		// assertThat(cc, hasProperty("type", is(cc2.getType())));
		Assert.assertThat(cc, Matchers.hasProperty("type", Matchers.is(cc2.getType())));
		// assertThat(cc, hasProperty("type", hasProperty("shortName", is(cc2.getType().getShortName()))));
		Assert.assertThat(cc, Matchers.hasProperty("type", Matchers.hasProperty("shortName", Matchers.is(cc2.getType().getShortName()))));
		
		// AssertJ
		Assertions.assertThat(cc).isEqualToIgnoringGivenFields(cc2, "id", "value");
		Assertions.assertThat(cc).isEqualToComparingOnlyGivenFields(cc2, "type");
		Assertions.assertThat(cc).isEqualToComparingOnlyGivenFields(cc2, "type.name");
	}
}
