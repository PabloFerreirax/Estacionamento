package model.services;

public class BrazilTaxServices implements TaxServices{
	public double tax(double ammont) {
		if(ammont <= 100.0) {
			return ammont * 0.20;
		}
		else {
			return ammont * 0.15;
		}
	}
}
