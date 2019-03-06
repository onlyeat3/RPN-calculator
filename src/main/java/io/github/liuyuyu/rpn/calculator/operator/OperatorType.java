package io.github.liuyuyu.rpn.calculator.operator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum OperatorType {
    ADDITION("+"),
    SUBSTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),

    SQRT("sqrt"),
    CLEAR("clear"),
    UNDO("undo");

    private String operator;

    OperatorType(String operator) {
        this.operator = operator;
    }

    /**
     * 单元运算符
     */
    private static final List<OperatorType> UNIT_OPERATORS = new ArrayList<>();

    /**
     * 数学运算符
     */
    private static final List<OperatorType> MATH_OPERATORS = new ArrayList<>();

    static {
        UNIT_OPERATORS.add(SQRT);
        UNIT_OPERATORS.add(CLEAR);
        UNIT_OPERATORS.add(UNDO);

        MATH_OPERATORS.add(ADDITION);
        MATH_OPERATORS.add(SUBSTRACTION);
        MATH_OPERATORS.add(MULTIPLICATION);
        MATH_OPERATORS.add(DIVISION);
        MATH_OPERATORS.add(SQRT);
    }

    /**
     * 是否单元运算符
     * @param operator 操作符字符串
     */
    public static boolean isUnitOperator(String operator){
        return UNIT_OPERATORS.stream()
                .map(OperatorType::getOperator)
                .anyMatch(o -> o.equalsIgnoreCase(operator));
    }

    public static OperatorType parse(String arg) {
        return Arrays.stream(values())
                .filter(o-> o.getOperator().equalsIgnoreCase(arg))
                .findFirst()
                .orElseThrow();
    }

    public boolean isUnitOperator(){
        return UNIT_OPERATORS.stream()
                .map(OperatorType::getOperator)
                .anyMatch(o -> o.equalsIgnoreCase(this.operator));
    }

    public boolean isMathOperator(){
        return MATH_OPERATORS.stream()
                .map(OperatorType::getOperator)
                .anyMatch(o -> o.equalsIgnoreCase(this.operator));
    }


}
