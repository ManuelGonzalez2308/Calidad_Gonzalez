package com.fca.calidad.pruebas;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AritmeticaParametrizadaTest {
	private float arg1;    //Value 3
	private float arg2;    //Value 2
	private float expected; //Expected value
	private Aritmetica aritmetica;  //Object
	
	public AritmeticaParametrizadaTest (float arg1,float arg2, float expected) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.expected = expected;
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
			{4,2,2},    //Intergers
			{6,-3,-2},   // Negative intergers
			{5,5,1},    //Same number
			{5,2,2.5f},  //Decimal
			{5,-2,-2.5f}  //Negative decimal
		});
		}

	@Before
	public void setUp() throws Exception {
		//create new object 
		aritmetica = new Aritmetica();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//Using MamCrest
		//Exercise code to run and test
		float resEjecucion = aritmetica.division(this.arg1, this.arg2);
		
		//verify 
		assertThat(this.expected, is (resEjecucion));
	}

}
