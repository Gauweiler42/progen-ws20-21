import java.math.BigInteger;

/**
 * Eine Klasse, um eine IBAN Adresse berechnen zu können
 *
 * @version 1
 * @author Leon Gauweiler
 * @author Tobias Mintel
 * @author Frederik Pytlik
 */
public class GenerateIBAN {
    private String iban;
    private String countryCode;
    private String bankCode;
    private String accountNumber;

    /**
     *
     * @param countryCode Der Länderkennungscode als String, übergeben von main
     * @param bankCode Die Bankleitzahl als String, übergeben von main
     * @param accountNumber Die Kontonummer als String, übergeben von main
     */
    public GenerateIBAN(String countryCode, String bankCode, String accountNumber) {
        this.countryCode = countryCode;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;

        System.out.println(this.calculateIban());
    }

    /**
     *
     * @param args Kommanzozeilenparameter in Reihenfolge: Ländercode, Bankleitzahl, Kontonummer
     */
    public static void main(String[] args) {
        new GenerateIBAN(args[0], args[1], args[2]);
    }

    /**
     *
     * @return Gibt die IBAN zurück
     */
    public String getIban() {
        return this.iban;
    }

    /**
     *
     * @return Gibt den Ländercode zurück
     */
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     *
     * @return Gibt die Bankleitzahl zurück
     */
    public String getBankCode() {
        return this.bankCode;
    }

    /**
     *
     * @return Gibt die Kontonummer zurück
     */
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     *
     * @param iban Will einen String als IBAN
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     *
     * @param accountNumber Will einen String für die Kontonummer
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     *
     * @param bankCode Will einen String für die Bankleitzahl
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     *
     * @return Gibt die fertig berechnet und formatierte IBAN aus.
     */
    private String calculateIban() {
        if (this.getAccountNumber().length() < 10) {
            // Kontonummer mit 0 auffüllen, falls kürzer als 10
            while (this.getAccountNumber().length() < 10) {
                this.setAccountNumber("0" + this.getAccountNumber());
            }
        }
        this.setBankCode(this.getBankCode() + this.getAccountNumber());

        // Länderkennung Codieren
        String decodedCountryCode = "";
        for (int index = 0; index < 2; index++) {
            decodedCountryCode = decodedCountryCode + (((int) this.getCountryCode().charAt(index)) - 55);
        }
        decodedCountryCode = decodedCountryCode + "00";

        // Bigint deklarieren und zusammensetzen
        BigInteger bankCodeCountryCodeOutput = new BigInteger(this.getBankCode());
        BigInteger bankCodeCountryCode = new BigInteger(this.getBankCode() + decodedCountryCode);

        int checkDigit = 98 - Integer.parseInt(String.valueOf(bankCodeCountryCode.remainder(BigInteger.valueOf(97))));

        String checkDigitStr = "";
        if (checkDigit < 10) {
            checkDigitStr = "0" + String.valueOf(checkDigit);
        } else {
            checkDigitStr = String.valueOf(checkDigit);
        }

        // IBAN zusammenfügen
        this.iban = this.getCountryCode() + checkDigitStr + String.valueOf(bankCodeCountryCodeOutput);
        this.formateIban();

        return this.getIban();
    }

    /**
     * Formatiert die IBAN mit Leerzeichen
     */
    private void formateIban() {
        String formattedIban = "";
        for (int i = 0; i < this.getIban().length(); i++) {
            if (i != 0 && i % 4 == 0) {
                // Nach 4 Zeichen wird ein leertaste eingefügt
                formattedIban = formattedIban + " " + this.getIban().charAt(i);
            } else {
                formattedIban = formattedIban + this.getIban().charAt(i);
            }
        }
        this.setIban(formattedIban);
    }
}
