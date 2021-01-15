package edu.kit.informatik;

/**
 * Eine Klasse zum testweisen instanziieren und aufrufen
 * der vorher implementierten Klassen bzw Methoden
 *  
 * @version 1
 * @author Tobias Mintel
 * @author Leon Gauweiler
 * @author Fredrik Pytlik
 *
 */

public class Test {
    public static void main(String[] args) {
        Bank bank1 = new Bank(61060500);
        Bank bank2 = new Bank(66091200);
        
        bank1.createAccount();
        bank1.createAccount();
        bank2.createAccount();
        bank2.createAccount();
        
        bank1.length();
        bank2.length();
        
        bank1.size();
        bank2.size();
        
        bank2.containsAccount(0);
        bank2.containsAccount(1);
        
        bank2.removeAccount(0);
        bank2.removeAccount(1);
        
        bank1.containsAccount(0);
        bank2.containsAccount(0);
        
        bank1.getAccount(1);
        bank1.getAccount(3);
        
        bank1.internalBankTransfer(0, 1, 30);
        bank1.internalBankTransfer(1, 0, 30);
        
        bank1.getAccounts()[0].deposit(30);
        bank1.getAccounts()[1].deposit(100);
        
        bank1.internalBankTransfer(0, 1, 30);
        bank1.internalBankTransfer(1, 0, 130);
        
        bank1.getAccounts()[0].withdraw(70);
        bank1.getAccounts()[1].withdraw(10);
        
    }
}