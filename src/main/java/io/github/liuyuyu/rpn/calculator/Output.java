package io.github.liuyuyu.rpn.calculator;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


@Data
public class Output {
    /**
     * 输出的文本消息
     */
    private List<String> messages = new ArrayList<>();
    /**
     * 栈数据
     */
    private Stack<BigDecimal> stack = new Stack<>();
}
