package fr.boivina.bank.printer;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.apache.commons.lang3.StringUtils.rightPad;
import java.util.List;
import java.util.stream.Stream;
import fr.boivina.bank.Operation;

public class TextStatementPrinter implements StatementPrinter {

    private static final String HEADER = formatLine(of("TYPE", "DATE", "AMOUNT", "BALANCE"));

    private static final String DELIMITER = "|";
    private static final String LINE_DELIMITER = "\n";
    private static final int FIELD_SIZE = 12;

    @Override
    public String print(List<Operation> operations) {
        StringBuilder statement = new StringBuilder();
        statement.append(HEADER).append(LINE_DELIMITER);
        String operationLines = operations.stream()
                                          .map(operation -> operation.printUsingFormater(TextStatementPrinter::formatLine))
                                          .collect(joining(LINE_DELIMITER));
        statement.append(operationLines);

        return statement.toString();
    }

    private static String formatLine(Stream<String> fields) {
        return fields.map(field -> rightPad(field, FIELD_SIZE))
                     .collect(joining(DELIMITER, DELIMITER, DELIMITER));
    }
}
