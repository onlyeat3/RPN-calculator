package io.github.liuyuyu.rpn.calculator.operator;

import java.math.BigDecimal;
import java.util.Stack;

public class DivisionOperator implements Operator {

    @Override
    public BigDecimal handle(Stack<BigDecimal> s) {
        BigDecimal number1 = s.peek();
        BigDecimal number2 = s.peek();
        s.pop();
        BigDecimal result = number1.divide(number2,Operators.getMC());
        s.push(result);
        return result;
    }

    @Override
    public boolean isValid(Stack<BigDecimal> numberStack) {
        return numberStack.size() > 1;
    }
}
