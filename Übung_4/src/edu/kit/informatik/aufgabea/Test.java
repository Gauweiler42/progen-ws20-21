package edu.kit.informatik.aufgabea;

/**
 * Eine Klasse, um die Band Klasse zu testen
 *
 * @version 1
 * @author Leon Gauweiler
 */
public final class Test {
    private Test() {

    }

    public static void main(String[] args) {
        Bank b1 = new Bank(1234);

        b1.createAccount();
        b1.createAccount();

        b1.internalBankTransfer(0, 1, 0);
    }
}
