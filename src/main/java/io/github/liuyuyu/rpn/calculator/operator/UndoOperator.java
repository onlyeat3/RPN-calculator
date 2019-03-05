package io.github.liuyuyu.rpn.calculator.operator;

import java.math.BigDecimal;
import java.util.Stack;

public class UndoOperator implements Operator {
    @Override
    public BigDecimal handle(Stack<BigDecimal> numberStack) {
        numberStack.remove(0);
        return null;
    }

    @Override
    public boolean isValid(Stack<BigDecimal> numberStack) {
        return true;
    }
}
