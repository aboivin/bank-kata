package fr.boivina.bank.printer;

import java.util.List;
import fr.boivina.bank.Operation;

@FunctionalInterface
public interface StatementPrinter {

    String print(List<Operation> operations);
}
