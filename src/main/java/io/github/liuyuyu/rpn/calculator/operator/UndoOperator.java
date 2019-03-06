package io.github.liuyuyu.rpn.calculator.operator;

import io.github.liuyuyu.rpn.calculator.advice.OperatorAdvice;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class UndoOperator implements Operator, OperatorAdvice {
    public static OperatorType LAST_OPERATOR = null;

    @Override
    public BigDecimal handle(Stack<BigDecimal> numberStack) {
        numberStack.pop();
        CACHE.pop();
        if (!CACHE.isEmpty()) {
            //缓存的数据回填到numberStack
            if (null != LAST_OPERATOR) {
                if (LAST_OPERATOR.isUnitOperator()) {
                    numberStack.push(CACHE.pop());
                }else{
                    BigDecimal number2 = CACHE.pop();
                    BigDecimal number1 = CACHE.pop();

                    numberStack.push(number1);
                    numberStack.push(number2);
                }
            }

        }

        return null;
    }

    @Override
    public boolean isValid(Stack<BigDecimal> numberStack) {
        return true;
    }

    /**
     * 多元运算符
     */
    private void clearCacheForPolyhydricOperator() {
        if (OperatorAdvice.CACHE.size() > 2) {
            OperatorAdvice.CACHE.remove(0);
            OperatorAdvice.CACHE.remove(0);
        }
    }

    private void clearCacheForUnitCache() {
        if (!OperatorAdvice.CACHE.isEmpty()) {
            OperatorAdvice.CACHE.remove(0);
        }
    }

    @Override
    public void before(String arg, Stack<BigDecimal> numberStack) {
    }

    @Override
    public void after(String arg, Stack<BigDecimal> numberStack) {
        if (!numberStack.isEmpty()) {
            if (!"undo".equalsIgnoreCase(arg)) {
                CACHE.push(numberStack.get(numberStack.size() - 1));
//                if (!RPNUtils.isNumber(arg)) {
//                    if (OperatorType.isUnitOperator(arg)) {
//                        this.clearCacheForUnitCache();
//                    } else {
//                        this.clearCacheForPolyhydricOperator();
//                    }
//                }
                //记录最近一次的运算符
                boolean isOperator = Arrays.stream(OperatorType.values())
                        .collect(Collectors.toList())
                        .stream()
                        .map(OperatorType::getOperator)
                        .anyMatch(o-> o.equalsIgnoreCase(arg));
                if (isOperator) {
                    LAST_OPERATOR = OperatorType.parse(arg);
                }
            }
        }

    }


}
