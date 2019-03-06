package io.github.liuyuyu.rpn.calculator.advice;

import java.math.BigDecimal;
import java.util.Stack;

public interface OperatorAdvice {
    Stack<BigDecimal> CACHE = new Stack<>();
    void before(String arg,Stack<BigDecimal> stack);
    void after(String arg,Stack<BigDecimal> stack);
}
