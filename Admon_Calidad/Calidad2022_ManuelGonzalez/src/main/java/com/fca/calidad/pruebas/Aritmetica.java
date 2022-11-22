package com.fca.calidad.pruebas;

public class Aritmetica {
	private float ultimoResultado;
	public float suma(float primerSumado, float segundoSumado) {
		return ultimoResultado = primerSumado + segundoSumado;
	}
	public float resta(float minuendo, float sustraendo) {
		return ultimoResultado = minuendo - sustraendo;
	}
	public float multiplicacion(float primerFactor, float segundoFactor) {
		return ultimoResultado = primerFactor * segundoFactor;
	}
	public float division(float dividendo, float divisor) {
		return ultimoResultado = dividendo / divisor;
	}
	public int divisionEntera(int dividendo, int divisor) {
		return dividendo/divisor;
	}
	public float getUltimaResultado() {
		return ultimoResultado;
	}

}
