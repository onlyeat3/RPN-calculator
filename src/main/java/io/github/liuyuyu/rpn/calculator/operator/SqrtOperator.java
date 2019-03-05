package io.github.liuyuyu.rpn.calculator.operator;

import java.math.BigDecimal;
import java.util.Stack;

public class SqrtOperator implements Operator {
    @Override
    public BigDecimal handle(Stack<BigDecimal> s) {
        BigDecimal number1 = s.peek();
        s.pop();

        BigDecimal result = number1.sqrt(Operators.getMC());
        s.push(result);
        return result;
    }

    @Override
    public boolean isValid(Stack<BigDecimal> numberStack) {
        return numberStack.size() > 0;
    }
}
