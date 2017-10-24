package fr.boivina.acceptance;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import static org.assertj.core.api.Assertions.assertThat;
import static fr.boivina.bank.Money.money;
import java.math.BigDecimal;
import cucumber.api.java8.En;
import fr.boivina.bank.BankAccount;
import fr.boivina.bank.printer.TextStatementPrinter;

public class BankAccountStepdefinitions implements En {

    private BankAccount bankAccount;

    private String statement;

    public BankAccountStepdefinitions() {
        Given("a new bank account", () -> {
            bankAccount = new BankAccount();
        });
        When("the client makes a deposit of (-?\\d+.\\d+)", (BigDecimal amount) -> {
            bankAccount.deposit(money(amount));
        });
        When("the client makes a withdrawal of (-?\\d+.\\d+)", (BigDecimal amount) -> {
            bankAccount.withdraw(money(amount));
        });
        When("he displays the operations history", () -> {
            statement = bankAccount.printStatementUsing(new TextStatementPrinter());
        });
        Then("the account balance must be (-?\\d+.\\d+)", (BigDecimal balance) -> {
            assertThat(bankAccount.balance().amount).isEqualTo(balance);
        });
        Then("the following statement is displayed with date close to now", (String statement) -> {
            String currentDate = ISO_DATE.format(now());
            String statementWithDate = statement.replace("TODAY", currentDate);
            assertThat(this.statement).isEqualTo(statementWithDate);
        });
    }
}
