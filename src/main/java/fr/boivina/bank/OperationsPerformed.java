package fr.boivina.bank;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static fr.boivina.bank.Balance.ZERO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import fr.boivina.bank.printer.StatementPrinter;

public class OperationsPerformed {

    private List<Operation> operations = new ArrayList<>();

    void save(Operation operation) {
        operations.add(operation);
    }

    Balance computeCurrentBalance() {
        return lastOperationFrom(operations).map(Operation::computeCurrentBalance)
                                            .orElse(ZERO);
    }

    String printUsing(StatementPrinter statementPrinter) {
        return statementPrinter.print(operations);
    }

    private Optional<Operation> lastOperationFrom(List<Operation> operations) {
        if (operations.size() == 0) {
            return empty();
        }

        return of(operations.get(operations.size() - 1));
    }
}
