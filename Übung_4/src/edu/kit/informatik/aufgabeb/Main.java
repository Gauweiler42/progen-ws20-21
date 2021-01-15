package edu.kit.informatik;

public final class Main {
    private MailCenter center;
    private String activeUser = null;
    private String type = "";

    private Main() {
        center = new MailCenter();
    }

    public static void main(String[] args) {
        Main querry = new Main();

        while (true) {
            String line = Terminal.readLine();
            String[] arguments = line.split(" ");

            if (arguments[0].contains("add-customer")) {
                querry.excecuteAddCustomer(arguments[1].split(";"));
            } else if (arguments[0].contains("add-mailman")) {
                querry.excecuteAddMailman(arguments[1].split(";"));
            } else if (arguments[0].contains("add-agent")) {
                querry.excecuteAddAgent(arguments[1].split(";"));
            } else if (arguments[0].contains("authenticate")) {
                querry.excecuteAuthentication(arguments[1].split(";"));
            } else if (arguments[0].contains("logout")) {
                if (querry.activeUser != null) {
                    querry.type = null;
                    querry.activeUser = null;
                    Terminal.printLine("OK");
                } else {
                    Terminal.printLine("Error , Es gab einen Fehler");
                }
            } else if (arguments[0].contains("send-mail")) {
                querry.excecuteSendmail(arguments[1].split(";"));
            } else if (arguments[0].contains("get-mail")) {
                if (querry.activeUser != null && querry.type == "Customer") {
                    querry.center.getCustomer(querry.activeUser).fetchPackages();
                    Terminal.printLine("OK");
                } else if (querry.activeUser != null && querry.type == "Mailman") {
                    querry.excecuteFetchmail(arguments[1]);
                }
            } else if (arguments[0].contains("list-mail")) {
                if (querry.activeUser != null && querry.type == "Customer") {
                    querry.center.getCustomer(querry.activeUser).listMail();
                } else if (querry.activeUser != null && (querry.type == "Mailman" || querry.type == "Agent")) {
                    querry.excecuteListmail(arguments[1]);
                }
            } else if (arguments[0].contains("list-price")) {
                if (querry.activeUser != null && querry.type == "Customer") {
                    Terminal.printLine(querry.center.getCustomer(querry.activeUser).listPrice());
                } else if (querry.activeUser != null && (querry.type == "Mailman" || querry.type == "Agent")) {
                    querry.excecuteListprice(arguments[1]);
                }
            } else if (arguments[0].contains("reset-pin")) {
                if (querry.activeUser != null && querry.type == "Agent") {
                    querry.excecuteResetpin(arguments[1].split(";"));
                } else {
                    Terminal.printLine("Error , Es gab einen Fehler");
                }
            } else if (arguments[0].contains("quit")) {
                break;
            }

        }
    }

    public void excecuteResetpin(String[] arguments) {
        Customer excecution;
        int check;
        excecution = center.getCustomer(arguments[0]);
        if (excecution != null) {
            check = excecution.resetPassword(arguments[1]);
            if (check == 0) {
                Terminal.printLine("OK");
            } else {
                Terminal.printLine("Error , Die Anfrage war ungültig");
            }
        } else {
            Terminal.printLine("Error , Die Anfrage war ungültig");
        }
    }

    public void excecuteListprice(String argument) {
        Customer excecution;
        excecution = center.getCustomer(argument);
        if (excecution != null) {
            Terminal.printLine(excecution.listPrice());
        } else {
            Terminal.printLine("Error , Die Anfrage war ungültig");
        }
    }

    public void excecuteListmail(String argument) {
        Customer excecution;
        excecution = center.getCustomer(argument);
        if (excecution != null) {
            excecution.listMail();
        } else {
            Terminal.printLine("Error , Die Anfrage war ungültig");
        }
    }

    public void excecuteSendmail(String[] arguments) {
        int excecution;
        if (activeUser != null && type == "Customer") {
            if (arguments.length == 2) {
                excecution = center.sendMail(arguments[0], arguments[1], activeUser);
                if (excecution == 0) {
                    Terminal.printLine("OK");
                } else {
                    Terminal.printLine("Error , Es gab einen Fehler");
                }
            } else {
                Terminal.printLine("Error , Die Anfrage war ungültig");
            }
        } else if (activeUser != null && type == "Mailman") {
            if (arguments.length == 3) {
                excecution = center.sendMail(arguments[0], arguments[1], arguments[2]);
                if (excecution == 0) {
                    Terminal.printLine("OK");
                } else {
                    Terminal.printLine("Error , Es gab einen Fehler");
                }
            } else {
                Terminal.printLine("Error , Die Anfrage war ungültig");
            }
        }
    }

    public void excecuteFetchmail(String argument) {
        Customer excecution;
        excecution = center.getCustomer(argument);
        if (excecution != null) {
            excecution.fetchPackages();
            Terminal.printLine("OK");
        } else {
            Terminal.printLine("Error , Die Anfrage war ungültig");
        }
    }

    public void excecuteAuthentication(String[] arguments) {
        if (activeUser == null) {
            int excecution;

            if (arguments.length == 2) {
                excecution = center.authentificate(arguments[0], arguments[1]);
                if (excecution == 0) {
                    activeUser = arguments[0];
                    type = "Customer";
                    Terminal.printLine("OK");
                } else if (excecution == 3) {
                    activeUser = arguments[0];
                    type = "Mailman";
                    Terminal.printLine("OK");
                } else if (excecution == 4) {
                    activeUser = arguments[0];
                    type = "Agent";
                    Terminal.printLine("OK");
                } else {
                    Terminal.printLine("Error , Es gab einen Fehler");
                }
            } else {
                Terminal.printLine("Error , Die Anfrage war ungültig");
            }
        }
    }

    public void excecuteAddAgent(String[] arguments) {
        if (activeUser == null) {
            int excecution;

            if (arguments.length == 4) {
                excecution = center.addAgent(arguments[0], arguments[1], Integer.parseInt(arguments[2]), arguments[3]);
                if (excecution == 0) {
                    Terminal.printLine("OK");
                } else {
                    Terminal.printLine("Error , Es gab einen Fehler");
                }
            } else {
                Terminal.printLine("Error , Die Anfrage war ungültig");
            }
        }
    }

    public void excecuteAddMailman(String[] arguments) {
        if (activeUser == null) {
            int excecution;

            if (arguments.length == 4) {
                excecution = center.addMailman(arguments[0],
                        arguments[1],
                        Integer.parseInt(arguments[2]),
                        arguments[3]);
                if (excecution == 0) {
                    Terminal.printLine("OK");
                } else {
                    Terminal.printLine("Error , Es gab einen Fehler");
                }
            } else {
                Terminal.printLine("Error , Die Anfrage war ungültig");
            }
        }
    }

    public void excecuteAddCustomer(String[] arguments) {
        if (activeUser == null) {
            int excecution;

            if (arguments.length == 5) {
                excecution = center.addCustomer(arguments[0], arguments[1], arguments[2], arguments[3], arguments[4]);
                if (excecution == 0) {
                    Terminal.printLine("OK");
                } else {
                    Terminal.printLine("Error , Es gab einen Fehler");
                }
            } else {
                Terminal.printLine("Error , Die Anfrage war ungültig");
            }
        }
    }
}
