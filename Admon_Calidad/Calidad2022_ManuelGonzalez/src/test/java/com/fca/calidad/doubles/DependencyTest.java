package com.fca.calidad.doubles;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

public class DependencyTest {
	private Dependency dependency;

	@Before
	public void setUp() throws Exception {
		dependency = Mockito.mock(Dependency.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDummy() {
		assertThat(dependency.getClassName(), is(nullValue()));
		//assertThat(dependency.getClassName(), is("Dependency"));
	}
	private void setBehavior() {
		when(dependency.getClassName()).thenReturn("Nombre de la clase");
	}
	@Test
	public void getClassNameTest() {
		setBehavior();
		String resultadoEsperado = "Nombre de la clase";
		assertThat(dependency.getClassName(), is(resultadoEsperado));
		//assertThat(dependency.getClassName(), is("Dependency"));
	}
	private void setAddTwo() {
		when(dependency.addTwo(anyInt())).thenReturn(3);
	}
	@Test
	public void addTwoTest() {
		setAddTwo();
		int resultadoEsperado = 3;
		int resultadoEsperado2= 3;
		assertThat(dependency.addTwo(1), is(resultadoEsperado));
		assertThat(dependency.addTwo(2), is(resultadoEsperado2));
	}
	
	//Prueba thenThrow
	@Test(expected = ArithmeticException.class)
	public void mockException_Test() {
		when(dependency.getClassName()).thenThrow(ArithmeticException.class);
		dependency.getClassName();
	}
	
	//PruebathenCallRealMethod
	@Test
	public void addTwoRealMethodTest() {
		when(dependency.addTwo(anyInt())).thenCallRealMethod();
		int resultadoEsperado = 3;
		int resultadoEsperado2= 4;
		assertThat(dependency.addTwo(1), is(resultadoEsperado));
		assertThat(dependency.addTwo(2), is(resultadoEsperado2));
	}
	
	@Test
	public void mockAnswer_Test2() {
		//Set the behavior of the instance
		when(dependency.addTwo(anyInt())).thenAnswer(
				new Answer<Integer>(){
					//Method within the class
					@Override
					public Integer answer(InvocationOnMock invocation) throws Throwable {
						//Set behavior in every invocation
						int arg = (Integer) invocation.getArguments()[0];
						//Return the invoked value
						return arg + 10;
						}
						
				}
			);
		//Exercise the test of mock
		//Verify
		assertThat(20, is(dependency.addTwo(10)));
		assertThat(49 , is(dependency.addTwo(39)));
	}
}
