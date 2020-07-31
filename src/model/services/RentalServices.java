package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalServices {
	private Double pricePerDay;
	private Double pricePerHour;
	
	private BrazilTaxServices taxService;

	public RentalServices(Double pricePerDay, Double pricePerHour, BrazilTaxServices taxService) {
		super();
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}

	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		// acima pegamos em milesegundo as horas de entrada e depois de saida, 
		//abaixo pegamos o tempo de saida e subtraimos o tempo de entrada, e apos isso para achar as houras dividimos por 1000, 60, 60
		double hours = (double) (t2 - t1) / 1000 / 60 / 60;
		
		
		double basicPayment;
		if(hours <= 12) {
			// MATH.CEIL ARREDONDA POR HORA
			basicPayment = Math.ceil(hours) * pricePerHour;
		}
		else {
			basicPayment = Math.ceil(hours / 24) * pricePerDay;
		}
		
		// calcula o valor do imposto
		double tax = taxService.tax(basicPayment);
		
		// cria um novo valor
		carRental.setInvoice(new Invoice(basicPayment, tax));
		
	}
	
	
}
