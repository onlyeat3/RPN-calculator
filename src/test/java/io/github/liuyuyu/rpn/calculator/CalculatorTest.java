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
    public void testSimple(){
        Output output1 = this.run("5","2","+");
        Assert.assertTrue(output1.getMessages().contains("stack：7"));
        this.run("clear");

        Output output2 = this.run("5","2","-");
        Assert.assertTrue(output2.getMessages().contains("stack：3"));
        this.run("clear");

        Output output3 = this.run("5","2","*");
        Assert.assertTrue(output3.getMessages().contains("stack：10"));
        this.run("clear");

        Output output4 = this.run("5","2","/");
        Assert.assertTrue(output4.getMessages().contains("stack：2.5"));
    }

    @Test
    public void testExample1(){
        Output output = this.run("5", "2");
        Assert.assertTrue(output.getMessages().contains("stack：5 2"));
    }

    @Test
    public void testExample2(){
        Output output1 = this.run("2","sqrt");
        Assert.assertTrue(output1.getMessages().contains("stack：1.4142135624"));

        Output output2 = this.run("clear", "9", "sqrt");
        Assert.assertTrue(output2.getMessages().contains("stack：3"));
    }

    @Test
    public void testExample3(){
        Output output1 = this.run("5","2","-");
        Assert.assertTrue(output1.getMessages().contains("stack：3"));

        Output output2 = this.run("3","-");
        Assert.assertTrue(output2.getMessages().contains("stack：0"));

        Output output3 = this.run("clear");
        Assert.assertTrue(output3.getMessages().contains("stack："));

    }

    @Test
    public void testExample4(){
        Output output1 = this.run("5","4","3","2");
        Assert.assertTrue(output1.getMessages().contains("stack：5 4 3 2"));

        Output output2 = this.run("undo","undo","*");
        Assert.assertTrue(output2.getMessages().contains("stack：20"));

        Output output3 = this.run("5","*");
        Assert.assertTrue(output3.getMessages().contains("stack：100"));

        Output output4 = this.run("undo");
        Assert.assertTrue(output4.getMessages().contains("stack：20 5"));

    }

}
