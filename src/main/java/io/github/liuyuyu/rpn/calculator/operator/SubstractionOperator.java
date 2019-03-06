package io.github.liuyuyu.rpn.calculator.operator;

import java.math.BigDecimal;
import java.util.Stack;

public class SubstractionOperator implements Operator {

    @Override
    public BigDecimal handle(Stack<BigDecimal> s) {
        BigDecimal number2 = s.peek();
        s.remove(number2);
        BigDecimal number1 = s.peek();
        s.remove(number1);

        BigDecimal result = number1.subtract(number2);


        s.push(result);
        return result;
    }

    @Override
    public boolean isValid(Stack<BigDecimal> numberStack) {
        return numberStack.size() > 1;
    }
}
