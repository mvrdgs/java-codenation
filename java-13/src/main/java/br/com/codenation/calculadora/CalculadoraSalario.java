package br.com.codenation.calculadora;

public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		double salarioLiquido = salarioBase;

		if (salarioBase <= 1039) return 0;

		double inss = calcularInss(salarioBase);
		salarioLiquido -= inss;

		double irrf = calcularIrrf(salarioLiquido);
		salarioLiquido -= irrf;

		return Math.round(salarioLiquido);
	}


	private double calcularInss(double salarioBase) {
		double inssPercent;

		if (salarioBase < 1500.01) inssPercent = .08;
		else if (salarioBase < 4000.01) inssPercent = .09;
		else inssPercent = .11;

		return salarioBase * inssPercent;
	}

	private double calcularIrrf(double salarioBase) {
		double irrfPercent;

		if (salarioBase < 3000.01) return 0.0;
		else if (salarioBase < 6000.01) irrfPercent = .075;
		else irrfPercent = .15;

		return salarioBase * irrfPercent;
	}
}
