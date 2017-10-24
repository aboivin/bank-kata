package fr.boivina.bank;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static fr.boivina.bank.Money.money;
import java.math.BigDecimal;
import org.junit.Test;

public class MoneyTest {

    @Test
    public void should_not_construct_negative_money() throws Exception {
        // Given
        BigDecimal amount = new BigDecimal(-10);

        // When / Then
        assertThatThrownBy(() -> money(amount)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void should_construct_positive_money() throws Exception {
        // Given
        BigDecimal amount = new BigDecimal(10);

        // When / Then
        assertThatCode(() -> money(amount)).doesNotThrowAnyException();
    }
}