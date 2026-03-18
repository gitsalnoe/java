package net.gobbz.net;

public class MathAusdrueckeMain {

	public static void main(String[] args) {
		Operation o = 
				new Potenz(
						new Division(
								new Multiplikation(
										new Konstante(3),
										new Potenz(
												new Addition(
														new Konstante(6), 
														new Konstante(7)
												), 
												new Konstante(5)
										)
								),
								new Logarithmus(
										new Argument(10),
										new Wurzel(
												new Argument(2),
												new Addition(
														new Division(
																new Konstante(70), 
																new Konstante(4)
														),
														new Division(
																new Konstante(990), 
																new Konstante(8)
														)
												)
										)
								)
						),
				new Konstante(4)
		);
		System.out.println(o.toString());
	}
}
