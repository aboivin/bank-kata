package fr.boivina.bank.printer;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static fr.boivina.bank.Balance.ZERO;
import static fr.boivina.bank.Balance.balance;
import static fr.boivina.bank.Money.money;
import static fr.boivina.bank.Operation.deposit;
import static fr.boivina.bank.Operation.withdrawal;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import fr.boivina.bank.Operation;

public class StatementPrinterTest {

    @Test
    public void should_print_operations_history() throws Exception {
        // Given
        StatementPrinter statementPrinter = new TextStatementPrinter();
        List<Operation> operations = asList(deposit(money(new BigDecimal(10)), ZERO),
                                            withdrawal(money(new BigDecimal(3)), balance(new BigDecimal(10))),
                                            withdrawal(money(new BigDecimal(5)), balance(new BigDecimal(7))),
                                            deposit(money(new BigDecimal(2)), balance(new BigDecimal(2))));

        // When
        String statement = statementPrinter.print(operations);

        // Then
        assertThat(statement).isEqualTo("|TYPE        |DATE        |AMOUNT      |BALANCE     |\n" +
                                        "|DEPOSIT     |2017-10-24  |10.00       |0.00        |\n" +
                                        "|WITHDRAWAL  |2017-10-24  |3.00        |10.00       |\n" +
                                        "|WITHDRAWAL  |2017-10-24  |5.00        |7.00        |\n" +
                                        "|DEPOSIT     |2017-10-24  |2.00        |2.00        |");

    }

}