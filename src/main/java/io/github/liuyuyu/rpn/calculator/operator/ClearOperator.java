package io.github.liuyuyu.rpn.calculator.operator;

import java.math.BigDecimal;
import java.util.Stack;

public class ClearOperator implements Operator {

    @Override
    public BigDecimal handle(Stack<BigDecimal> numberStack) {
        numberStack.clear();
        return null;
    }

    @Override
    public boolean isValid(Stack<BigDecimal> numberStack) {
        return true;
    }
}
