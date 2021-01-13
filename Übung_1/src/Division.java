public class Division {
	public static void main(String args[]) {
		//Variablen werden initialisiert
		double a = 6;
		double b = 3;
		double quotient = 0;

		//Regel Bedingungen anhand von if-Anweisungen
		if(b==0) {
			System.out.println("NaN");
		} else {
			quotient = a/b;
			System.out.println(quotient);
		}
	}
}