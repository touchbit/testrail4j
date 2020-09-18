package org.touchbit.testrail4j.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.touchbit.testrail4j.core.field.Estimate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DTO fields classes tests")
public class FieldTests {

    @Test
    @DisplayName("Estimate.class")
    void unitTest_20200918164754() {
        Estimate estimate = new Estimate()
                .withDay(1)
                .withHour(2)
                .withMin(3)
                .withSec(4);
        assertThat(estimate).hasToString("1d 2h 3m 4s");
    }

}
