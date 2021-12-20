package de.raphaelkunis.springbootmysql.demo;

import de.raphaelkunis.springbootmysql.demo.DemoService;
import de.raphaelkunis.springbootmysql.demo.DemoServiceImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoUnitTests {

    @Test
    public void testDemoSayHello() {
        DemoService demo = new DemoServiceImpl();

        String result = demo.sayHello();

        assertEquals("Hello", result);
    }
}