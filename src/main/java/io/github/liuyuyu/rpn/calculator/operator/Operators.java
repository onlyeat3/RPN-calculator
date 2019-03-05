package io.github.liuyuyu.rpn.calculator.operator;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Operators {
    public static final Map<String,Operator> OPERATOR_MAP = new HashMap<>();

    static {
        OPERATOR_MAP.put("+",new AdditionOperator());
        OPERATOR_MAP.put("-",new SubstractionOperator());
        OPERATOR_MAP.put("*",new MultiplicationOperator());
        OPERATOR_MAP.put("/",new DivisionOperator());
        OPERATOR_MAP.put("sqrt",new SqrtOperator());
        OPERATOR_MAP.put("clear",new ClearOperator());
        OPERATOR_MAP.put("undo",new UndoOperator());
    }

    public static MathContext getMC(){
        return new MathContext(15, RoundingMode.HALF_UP);
    }
}
