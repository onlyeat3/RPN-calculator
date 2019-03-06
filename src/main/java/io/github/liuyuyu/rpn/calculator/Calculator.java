package io.github.liuyuyu.rpn.calculator;

import io.github.liuyuyu.rpn.calculator.advice.OperatorAdvice;
import io.github.liuyuyu.rpn.calculator.advice.OperatorAdvices;
import io.github.liuyuyu.rpn.calculator.operator.Operator;
import io.github.liuyuyu.rpn.calculator.operator.Operators;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Stack;

public class Calculator {
    //public 是为了方便测试
    public static final Stack<BigDecimal> NUMBER_STACK = new Stack<>();

    public Output calc(String[] args) {
        Output output = new Output();
        DecimalFormat displayFormat = new DecimalFormat("0.##########");

        output.setStack(NUMBER_STACK);

        int currentArgLength = -0;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            currentArgLength += arg.length();//1是空格占用空间

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
                        int pos = (currentArgLength  - 1) + (i+1); //(参数文本长度) + (空格数量)
                        output.getMessages().add(String.format("operator %s (position: %d);insufficient parameters.",arg,pos));
                        break;
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
            for (OperatorAdvice operatorAdvice : OperatorAdvices.ADVICE_LIST) {
                operatorAdvice.after(arg,NUMBER_STACK);
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
