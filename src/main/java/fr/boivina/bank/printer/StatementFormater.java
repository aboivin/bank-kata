package fr.boivina.bank.printer;

import java.util.stream.Stream;

@FunctionalInterface
public interface StatementFormater {

    String formatLine(Stream<String> fields);
}
