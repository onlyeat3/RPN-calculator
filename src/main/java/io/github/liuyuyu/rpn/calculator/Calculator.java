package io.github.liuyuyu.rpn.calculator;

import io.github.liuyuyu.rpn.calculator.operator.Operator;
import io.github.liuyuyu.rpn.calculator.operator.Operators;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Calculator {
    private static final Stack<BigDecimal> NUMBER_STACK = new Stack<>();

    public Output calc(String[] args) {
        Output output = new Output();
        DecimalFormat displayFormat = new DecimalFormat("0.##########");

        output.setStack(NUMBER_STACK);

        for (String arg : args) {
            if(Operators.OPERATOR_MAP.keySet().contains(arg)){
                //如果有两个数字
                Operator operator = Operators.OPERATOR_MAP.get(arg);
                if(operator != null){
                    if (operator.isValid(NUMBER_STACK)) {
                        operator.handle(NUMBER_STACK);
                    }else{
                        String argsString = Arrays.stream(args)
                                .reduce((a, b) -> a + " " + b)
                                .orElse("");
                        int pos = argsString.indexOf(arg);
                        output.getMessages().add(String.format("operator<%s>(position:<%d>);insufficient parameters.",arg,pos));
                        String stackState = NUMBER_STACK.stream()
                                .map(displayFormat::format)
                                .reduce((a, b) -> a + " " + b)
                                .orElse("-");
                        output.getMessages().add(String.format("栈状态%s",stackState));
                    }
                }else{
                    output.getMessages().add("不支持的操作符");
                }

            }else{
                try {
                    BigDecimal number = BigDecimal.valueOf(Integer.parseInt(arg));
                    NUMBER_STACK.push(number);
                }catch (NumberFormatException nfe){
                    output.getMessages().add(String.format("不是数字也不是操作符的文本:'%s'",arg));
                }
            }
        }
        String result = NUMBER_STACK.stream()
                .map(displayFormat::format)
                .reduce((a, b) -> a + " " + b)
                .orElse("");
        output.getMessages().add(String.format("stack：%s",result));
        return output;
    }
}
