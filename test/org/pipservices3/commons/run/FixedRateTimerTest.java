package org.pipservices3.commons.run;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedRateTimerTest {
    @Test
    public void tesRunWithTask() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();

        FixedRateTimer timer = new FixedRateTimer(
                (String correlationId, Parameters args) -> counter.getAndIncrement(),
                100, 0
        );

        timer.start();

        Thread.sleep(500);

        assertTrue(counter.get() > 3);
    }
}
