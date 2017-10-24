package fr.boivina.bank;

import static java.util.Objects.requireNonNull;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Balance {

    public static final Balance ZERO = balance(BigDecimal.ZERO);

    private static final DecimalFormat FORMAT = new DecimalFormat("#0.00");

    public final BigDecimal amount;

    public static Balance balance(BigDecimal amount) {
        requireNonNull(amount, "The balance must not be null");

        return new Balance(amount);
    }

    private Balance(BigDecimal amount) {
        this.amount = amount;
    }

    Balance add(Money amount) {
        return new Balance(this.amount.add(amount.amount()));
    }

    Balance subtract(Money amount) {
        return new Balance(this.amount.subtract(amount.amount()));
    }

    @Override
    public String toString() {
        return FORMAT.format(amount);
    }
}
