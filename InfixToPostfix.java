import java.util.Stack;

public class InfixToPostfix {

    // Method to convert infix expression to postfix expression
    public static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If the scanned character is an operand, add it to output
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            // If the scanned character is '(', push it to the stack
            else if (c == '(') {
                stack.push(c);
            }
            // If the scanned character is ')', pop and output from the stack
            // until an '(' is encountered
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            } else { // an operator is encountered
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        
        return result.toString();
    }

    // Method to return precedence of operators
    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Method to evaluate postfix expression
    public static int evaluatePostfix(String exp) {
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            
            // If the scanned character is an operand (number here),
            // push it to the stack.
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            }
            // If the scanned character is an operator, pop two
            // elements from stack apply the operator
            else {
                int val1 = stack.pop();
                int val2 = stack.pop();
                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                }
            }
        }
        return stack.pop();
    }

    // Method to evaluate an infix expression
    public static int evaluateInfix(String exp) {
        String postfix = infixToPostfix(exp);
        return evaluatePostfix(postfix);
    }

    public static void main(String[] args) {
        String infixExpression = "3+5*(2-8)";
        System.out.println("Infix Expression: " + infixExpression);
        String postfixExpression = infixToPostfix(infixExpression);
        System.out.println("Postfix Expression: " + postfixExpression);
        int result = evaluateInfix(infixExpression);
        System.out.println("Evaluated Result: " + result);
    }
}