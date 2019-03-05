package io.github.liuyuyu.rpn.calculator.operator;

import java.math.BigDecimal;
import java.util.Stack;

public interface Operator {
    BigDecimal handle(Stack<BigDecimal> numberStack);
    boolean isValid(Stack<BigDecimal> numberStack);
}
