package io.github.liuyuyu.rpn.calculator;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    public Output run(String... args){
        Calculator calculator = new Calculator();
        Output output = calculator.calc(args);
        for (String message : output.getMessages()) {
            System.out.println(message);
        }
        return output;
    }

    @Test
    public void testExample1(){
        Output output = this.run("5", "2");
        Assert.assertTrue(output.getMessages().contains("stack：5 2"));
    }

    @Test
    public void testExample2(){
        Output output = this.run("2","sqrt");
        Assert.assertTrue(output.getMessages().contains("stack：1.4142135624"));
    }
}
