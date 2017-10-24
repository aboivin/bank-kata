package fr.boivina.bank;

import static java.util.Objects.requireNonNull;
import fr.boivina.bank.printer.StatementPrinter;

public class BankAccount {

    private final OperationsPerformed operationsPerformed = new OperationsPerformed();

    public Balance balance() {
        return operationsPerformed.computeCurrentBalance();
    }

    public void deposit(Money money) {
        requireNonNull(money, "The amount must not be null");

        operationsPerformed.save(Operation.deposit(money, balance()));
    }

    public void withdraw(Money money) {
        requireNonNull(money, "The amount must not be null");

        operationsPerformed.save(Operation.withdrawal(money, balance()));
    }

    public String printStatementUsing(StatementPrinter statementPrinter) {
        requireNonNull(statementPrinter, "The statementPrinter must not be null");

        return operationsPerformed.printUsing(statementPrinter);
    }

}
