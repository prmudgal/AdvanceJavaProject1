package edu.pdx.cs410J.pmudgal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by Vijay on 7/13/2016.
 */
public class PolishCalculator {

    String operators="+-*/";

    public int doOperation(String operation) {
        String[] strarray =operation.split(" ");
    Stack<String> stack = new Stack<String>();

            for(String value:strarray){
                if(!operators.contains(value)){
                    stack.push(value);
                }else{
                    int  firstVal=Integer.valueOf(stack.pop());
                    int secondVal=Integer.valueOf(stack.pop());
                    switch(value){
                        case "+":

                            stack.push(String.valueOf(secondVal+firstVal));
                            break;
                        case "-":
                            stack.push(String.valueOf(secondVal-firstVal));
                            break;
                        case "*":
                            stack.push(String.valueOf(secondVal*firstVal));
                            break;
                        case "/":
                            stack.push(String.valueOf(secondVal/firstVal));
                            break;
                    }
                }
            }
        return Integer.valueOf(stack.pop());
    }

}
