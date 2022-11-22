package com.fca.calidad.pruebas;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AritmeticaTest {
	private Aritmetica aritmetica;
	
	@Before
	public void setUp() throws Exception {
		aritmetica = new Aritmetica();
		System.out.println("Este es el before");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Este es el after");
	}

	@Test
	public void sumaTest() {
		//Inicializar
		float resultadoEsperado = 4; // 2+2
		float resultadoEjecucion = 0;
		//Ejercicio del codigo
		resultadoEjecucion = aritmetica.suma(2, 2);
		//Verificar
		assertThat(resultadoEsperado, is(equalTo(resultadoEjecucion)));
	}
	@Test
	public void sumaTestFalla() {
		//Inicializar
		float resultadoEsperado = 4; // 2+2
		float resultadoEjecucion = 0;
		//Ejercicio del codigo
		resultadoEjecucion = aritmetica.suma(1, 2);
		//Verificar
		assertThat(resultadoEsperado, is(equalTo(resultadoEjecucion)));
	}
	@Test
	public void restaTest() {
		//Inicializar
				float resultadoEsperado = 4; // 2-2
				float resultadoEjecucion = 0;
				//Ejercicio del codigo
				resultadoEjecucion = aritmetica.resta(8, 4);
				//Verificar
				assertThat(resultadoEsperado, is(equalTo(resultadoEjecucion)));
	}
	@Test
	public void multiplicacionTest() {
		//Inicializar
				float resultadoEsperado = 4; // 2*2
				float resultadoEjecucion = 0;
				//Ejercicio del codigo
				resultadoEjecucion = aritmetica.multiplicacion(2, 2);
				//Verificar
				assertThat(resultadoEsperado, is(equalTo(resultadoEjecucion)));
	}
	@Test
	public void divisionTest() {
		//Inicializar
				float resultadoEsperado = 1; // 2/2
				float resultadoEjecucion = 0;
				//Ejercicio del codigo
				resultadoEjecucion = aritmetica.division(2, 2);
				//Verificar
				assertThat(resultadoEsperado, is(equalTo(resultadoEjecucion)));
	}
	@Test
	public void divisionCeroTest() {
		//Inicializar
				float resultadoEsperado = Float.POSITIVE_INFINITY;
				float resultadoEjecucion = 0;
				//Ejercicio del codigo
				resultadoEjecucion = aritmetica.division(2, 0);
				//Verificar

				assertThat(resultadoEsperado, is(equalTo(resultadoEjecucion)));
	}
	@Test(expected = ArithmeticException.class)
	public void divisionEnteraTest() {
		//Inicializar
				int resultadoEsperado = 2; 
				//Ejercicio del codigo
				int resultadoEjecucion = aritmetica.divisionEntera(2, 0);
				
	}

}
