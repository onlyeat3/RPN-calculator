package io.github.liuyuyu.rpn.calculator.advice;

import io.github.liuyuyu.rpn.calculator.operator.UndoOperator;

import java.util.ArrayList;
import java.util.List;

public class OperatorAdvices {
    public static final List<OperatorAdvice> ADVICE_LIST = new ArrayList<>();
    static {
        ADVICE_LIST.add(new UndoOperator());
    }
}
