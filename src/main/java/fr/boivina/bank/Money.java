package fr.boivina.bank;

import static java.util.Objects.requireNonNull;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Money {

    private final BigDecimal amount;

    private static final DecimalFormat FORMAT = new DecimalFormat("#0.00");

    public static Money money(BigDecimal amount) {
        requireNonNull(amount, "The amount must not be null");
        if (isNegativeOrZero(amount)) {
            throw new IllegalArgumentException("The amount must be strictly positive");
        }
        return new Money(amount);
    }

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    private static boolean isNegativeOrZero(BigDecimal amount) {
        return amount.signum() != 1;
    }

    public BigDecimal amount() {
        return amount;
    }

    @Override
    public String toString() {
        return FORMAT.format(amount);
    }
}
