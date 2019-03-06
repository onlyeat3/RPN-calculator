package io.github.liuyuyu.rpn.calculator;

import io.github.liuyuyu.rpn.calculator.operator.UndoOperator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CalculatorTest {

    public Output run(String... args){
        Calculator calculator = new Calculator();
        Output output = calculator.calc(args);
        for (String message : output.getMessages()) {
            System.out.print(message+" ");
        }
        System.out.println();
        return output;
    }

    /**
     * 这些全局变量需要重置
     */
    @After
    public void clear(){
        Calculator.NUMBER_STACK.clear();
        UndoOperator.CACHE.clear();
        UndoOperator.LAST_OPERATOR = null;
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

    @Test
    public void testExample5(){
        Output output1 = this.run("7","12","2","/");
        Assert.assertTrue(output1.getMessages().contains("stack：7 6"));

        Output output2 = this.run("*");
        Assert.assertTrue(output2.getMessages().contains("stack：42"));

        Output output3 = this.run("4","/");
        Assert.assertTrue(output3.getMessages().contains("stack：10.5"));
    }

    @Test
    public void testExample6(){
        Output output1 = this.run("1","2","3","4","5");
        Assert.assertTrue(output1.getMessages().contains("stack：1 2 3 4 5"));

        Output output2 = this.run("*");
        Assert.assertTrue(output2.getMessages().contains("stack：1 2 3 20"));

        Output output3 = this.run("clear","3","4","-");
        Assert.assertTrue(output3.getMessages().contains("stack：-1"));
    }

    @Test
    public void testExample7(){
        Output output1 = this.run("1","2","3","4","5");
        Assert.assertTrue(output1.getMessages().contains("stack：1 2 3 4 5"));

        Output output2 = this.run("*","*","*","*");
        Assert.assertTrue(output2.getMessages().contains("stack：120"));
    }

    @Test
    public void testExample8(){
        Output output1 = this.run("1","2","3","*","5","+","*","*","6","5");
        Assert.assertTrue(output1.getMessages().contains("operator * (position: 15);insufficient parameters."));
        Assert.assertTrue(output1.getMessages().contains("stack：11"));
    }

    //模拟命令行调用
    @Test
    public void testPlainStringArgs(){
        String[] args = "5 2 +".split(" ");
        Calculator calculator = new Calculator();
        Output output = calculator.calc(args);
        for (String message : output.getMessages()) {
            System.out.print(message+" ");
        }
        System.out.println();
        Assert.assertTrue(output.getStack().contains(BigDecimal.valueOf(7)));
    }

}
