package fr.boivina.bank;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.util.stream.Stream.of;
import static fr.boivina.bank.OperationType.DEPOSIT;
import static fr.boivina.bank.OperationType.WITHDRAWAL;
import java.time.LocalDateTime;
import fr.boivina.bank.printer.StatementFormater;

public class Operation {

    private final OperationType type;

    private final LocalDateTime date;

    private final Money amount;

    private final Balance balance;

    public static Operation deposit(Money amount, Balance balance) {
        return new Operation(DEPOSIT, now(), amount, balance);
    }

    public static Operation withdrawal(Money amount, Balance balance) {
        return new Operation(WITHDRAWAL, now(), amount, balance);
    }

    private Operation(OperationType type, LocalDateTime date, Money amount, Balance balance) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    Balance computeCurrentBalance() {
        switch (type) {
            case DEPOSIT:
                return balance.add(amount);
            case WITHDRAWAL:
                return balance.subtract(amount);
            default:
                throw new IllegalStateException("Unknown operation type");
        }
    }

    public String printUsingFormater(StatementFormater lineFormater) {
        String date = this.date.format(ISO_DATE);
        return lineFormater.formatLine(of(type.name(), date, amount.toString(), balance.toString()));
    }
}
