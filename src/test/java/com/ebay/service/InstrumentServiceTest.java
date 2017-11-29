package com.ebay.service;

import static org.mockito.BDDMockito.*;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ebay.dao.InstrumentDAO;

public class InstrumentServiceTest {

	@InjectMocks
	private InstrumentService svc;
	
	@Mock
	private InstrumentDAO dao;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private final int id = 0;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		given(dao.findInstrumentById(id))
			.willThrow(new IllegalArgumentException("No instrument for id 0", new RuntimeException()));
	}
	
	@Test
	public void testExceptionInHamcrest() throws Exception {
		// Hamcrest
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectCause(Matchers.isA(RuntimeException.class));
		expectedException.expectMessage(Matchers.containsString(String.valueOf(id)));
		
		svc.getInstrument(id);
	}
	
	@Test
	public void testExceptionInAssertJ8() throws Exception {
		Assertions.assertThatThrownBy(() -> svc.getInstrument(id))
			.isInstanceOf(IllegalArgumentException.class)
			.hasCauseInstanceOf(RuntimeException.class)
			.hasMessageContaining(String.valueOf(id));
	}
	
	@Test
	public void testExceptionInAssertJ7() throws Exception {
		ThrowingCallable tc = new ThrowingCallable() {
			@Override
			public void call() throws Throwable {
				svc.getInstrument(id);
			}
		};
		
		Assertions.assertThatThrownBy(tc)
			.isInstanceOf(IllegalArgumentException.class)
			.hasCauseInstanceOf(RuntimeException.class)
			.hasMessageContaining(String.valueOf(id));
	}
}
