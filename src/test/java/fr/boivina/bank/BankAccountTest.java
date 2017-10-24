package fr.boivina.bank;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static fr.boivina.bank.Money.money;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import fr.boivina.bank.printer.TextStatementPrinter;

@RunWith(JUnitQuickcheck.class)
public class BankAccountTest {

    @Test
    public void should_have_zero_balance_when_no_deposit() {
        // When
        BankAccount bankAccount = new BankAccount();

        // Then
        assertThat(bankAccount.balance().amount).isEqualTo(ZERO);
    }

    @Property
    public void should_deposit_money(@InRange(min = "1") BigDecimal amountToDeposit) {
        // Given
        BankAccount bankAccount = new BankAccount();
        Money amount = money(amountToDeposit);

        // When
        bankAccount.deposit(amount);

        // Then
        assertThat(bankAccount.balance().amount).isEqualTo(amountToDeposit);
    }

    @Test
    public void should_throw_an_exception_when_null_money_is_deposited() throws Exception {
        // Given
        BankAccount bankAccount = new BankAccount();
        Money amount = null;

        // When / Then
        assertThatThrownBy(() -> bankAccount.deposit(amount)).isInstanceOf(NullPointerException.class);
    }

    @Property
    public void should_withdraw_money_when_enough_balance(@InRange(min = "1", max = "1000") BigDecimal amountToWithDraw,
                                                          @InRange(min = "1000") BigDecimal initialDepositAmount) throws Exception {
        // Given
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(money(initialDepositAmount));
        Money amount = money(amountToWithDraw);

        // When
        bankAccount.withdraw(amount);

        // Then
        assertThat(bankAccount.balance().amount).isNotNegative();
    }

    @Test
    public void should_withdraw_money_when_enough_balance() throws Exception {
        // Given
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(money(new BigDecimal(2)));
        Money amount = money(ONE);

        // When
        bankAccount.withdraw(amount);

        // Then
        assertThat(bankAccount.balance().amount).isEqualTo(ONE);
    }

    @Property
    public void should_withdraw_money_when_not_enough_balance(@InRange(min = "1") BigDecimal money) throws Exception {
        // Given
        BankAccount bankAccount = new BankAccount();
        Money amount = money(money);

        // When
        bankAccount.withdraw(amount);

        // Then
        assertThat(bankAccount.balance().amount).isNegative();
    }

    @Test
    public void should_throw_an_exception_when_null_money_is_withdrawn() throws Exception {
        // Given
        BankAccount bankAccount = new BankAccount();
        Money amount = null;

        // When / Then
        assertThatThrownBy(() -> bankAccount.withdraw(amount)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void should_throw_an_exception_when_null_statement_printer() throws Exception {
        // Given
        BankAccount bankAccount = new BankAccount();
        TextStatementPrinter statementPrinter = null;

        // When / Then
        assertThatThrownBy(() -> bankAccount.printStatementUsing(statementPrinter)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void should_print_statement() throws Exception {
        // Given
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(money(new BigDecimal(10)));
        bankAccount.withdraw(money(new BigDecimal(10)));

        // When
        String statement = bankAccount.printStatementUsing(operations -> "My statement of " + operations.size() + " operations");

        // Then
        assertThat(statement).isEqualTo("My statement of 2 operations");
    }
}