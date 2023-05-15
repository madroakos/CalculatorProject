package calculator;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator extends JFrame {

    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(330, 500);
        setLayout(null);
        Font font = new Font("Arial", Font.PLAIN, 12);
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);


        DecimalFormat resultFormat = new DecimalFormat("#.##");

        JLabel ResultLabel = new JLabel();
        ResultLabel.setText("0");
        ResultLabel.setName("ResultLabel");
        ResultLabel.setBounds(20, 20, 270, 30);
        ResultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ResultLabel.setFont(new Font("Plain" ,Font.BOLD, 32));
        add(ResultLabel);

        JLabel EquationLabel = new JLabel();
        EquationLabel.setText("");
        EquationLabel.setName("EquationLabel");
        EquationLabel.setBounds(20,70,270,20);
        EquationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(EquationLabel);

        //Zeroth row
        JButton Parentheses = new JButton();
        Parentheses.setName("Parentheses");
        Parentheses.setText("( )");
        Parentheses.setBounds(20, 110, 60, 40);
        add(Parentheses);

        Parentheses.addActionListener(e -> {
            if (parenthesisCheck(EquationLabel.getText()) == 0) {
                EquationLabel.setText(EquationLabel.getText() + "(");
            } else if (EquationLabel.getText().substring(EquationLabel.getText().length() - 1).contains("(") ||
                    isSymbol(EquationLabel.getText().substring(EquationLabel.getText().length() - 1))) {
                EquationLabel.setText(EquationLabel.getText() + "(");
            } else {
                EquationLabel.setText(EquationLabel.getText() + ")");
            }
        });

        JButton ClearEntry = new JButton();
        ClearEntry.setName("ClearEntry");
        ClearEntry.setText("CE");
        ClearEntry.setBounds(90, 110, 60, 40);
        add(ClearEntry);

        ClearEntry.addActionListener(e -> {
            if (!EquationLabel.getText().isEmpty()) {
                if (!isSymbol(EquationLabel.getText().substring(EquationLabel.getText().length() - 1))) {
                    for (int i = EquationLabel.getText().length() - 1; i > 0; i--) {
                        if (isSymbol(EquationLabel.getText().substring(i, i + 1))) {
                            if (!(i == EquationLabel.getText().length())) {
                                EquationLabel.setText(EquationLabel.getText().substring(0, i + 1));
                                break;
                            }
                        }
                        if (i == 1) {
                            EquationLabel.setText("");
                        }
                    }
                }
            }
        });

        JButton Clear = new JButton();
        Clear.setName("Clear");
        Clear.setText("C");
        Clear.setBounds(160, 110, 60, 40);
        add(Clear);

        Clear.addActionListener(e -> EquationLabel.setText(""));

        JButton Delete = new JButton();
        Delete.setName("Delete");
        Delete.setText("Del");
        Delete.setBounds(230,110,60,40);
        add(Delete);

        Delete.addActionListener(e -> {
            if (!EquationLabel.getText().isEmpty()) {
                EquationLabel.setText(EquationLabel.getText().substring(0, EquationLabel.getText().length() - 1));
            }
        });

        //First row
        JButton PowerTwo = new JButton();
        PowerTwo.setName("PowerTwo");
        PowerTwo.setText("X" + '²');
        PowerTwo.setBounds(20,160,60,40);
        add(PowerTwo);

        PowerTwo.addActionListener(e -> {
            if (!EquationLabel.getText().isEmpty()) {
                EquationLabel.setText(EquationLabel.getText() + "^(2)");
            }
        });

        JButton PowerY = new JButton();
        PowerY.setName("PowerY");
        PowerY.setText("Xⁿ");
        PowerY.setBounds(90,160,60,40);
        add(PowerY);

        PowerY.addActionListener(e -> {
            if (!EquationLabel.getText().isEmpty()) {
                EquationLabel.setText(EquationLabel.getText() + "^(");
            }
        });

        JButton SquareRoot = new JButton();
        SquareRoot.setName("SquareRoot");
        SquareRoot.setText("√");
        SquareRoot.setBounds(160,160,60,40);
        add(SquareRoot);

        SquareRoot.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + "√("));

        JButton Divide = new JButton();
        Divide.setName("Divide");
        Divide.setText("÷");
        Divide.setBounds(230,160,60,40);
        add(Divide);

        Divide.addActionListener(e -> {
            if (!(EquationLabel.getText().length() == 0) && !isSymbol(EquationLabel.getText().substring(EquationLabel.getText().length() - 1))) {
                EquationLabel.setText(EquationLabel.getText() + Divide.getText());
                EquationLabel.setText(dotCheck(EquationLabel.getText()));
            } else if (!(EquationLabel.getText().length() == 0) && isSymbol(EquationLabel.getText().substring(EquationLabel.getText().length() - 1))) {
                EquationLabel.setText(EquationLabel.getText().substring(0, EquationLabel.getText().length() - 1) + Divide.getText());
            }

        });

        //Second row
        JButton Seven = new JButton();
        Seven.setName("Seven");
        Seven.setText("7");
        Seven.setBounds(20,210,60,40);
        add(Seven);

        Seven.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Seven.getText()));

        JButton Eight = new JButton();
        Eight.setName("Eight");
        Eight.setText("8");
        Eight.setBounds(90,210,60,40);
        add(Eight);

        Eight.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Eight.getText()));

        JButton Nine = new JButton();
        Nine.setName("Nine");
        Nine.setText("9");
        Nine.setBounds(160,210,60,40);
        add(Nine);

        Nine.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Nine.getText()));

        JButton Multiply = new JButton();
        Multiply.setName("Multiply");
        Multiply.setText("×");
        Multiply.setBounds(230,210,60,40);
        add(Multiply);

        Multiply.addActionListener(e -> {
            if (!(EquationLabel.getText().length() == 0) && !isSymbol(EquationLabel.getText().substring(EquationLabel.getText().length()-1))) {
                EquationLabel.setText(EquationLabel.getText() + Multiply.getText());
                EquationLabel.setText(dotCheck(EquationLabel.getText()));
            } else if (!(EquationLabel.getText().length() == 0) && isSymbol(EquationLabel.getText().substring(EquationLabel.getText().length() - 1))) {
                EquationLabel.setText(EquationLabel.getText().substring(0, EquationLabel.getText().length() - 1) + Multiply.getText());
            }
        });


        //Third row
        JButton Four = new JButton();
        Four.setName("Four");
        Four.setText("4");
        Four.setBounds(20,260,60,40);
        add(Four);

        Four.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Four.getText()));

        JButton Five = new JButton();
        Five.setName("Five");
        Five.setText("5");
        Five.setBounds(90,260,60,40);
        add(Five);

        Five.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Five.getText()));

        JButton Six = new JButton();
        Six.setName("Six");
        Six.setText("6");
        Six.setBounds(160,260,60,40);
        add(Six);

        Six.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Six.getText()));

        JButton Subtract = new JButton();
        Subtract.setName("Subtract");
        Subtract.setText("-");
        Subtract.setBounds(230,260,60,40);
        add(Subtract);

        Subtract.addActionListener(e -> {
            if (!(EquationLabel.getText().length() == 0) && !isSymbol(EquationLabel.getText().substring(EquationLabel.getText().length()-1))) {
                EquationLabel.setText(EquationLabel.getText() + Subtract.getText());
                EquationLabel.setText(dotCheck(EquationLabel.getText()));
            } else if (!(EquationLabel.getText().length() == 0) && isSymbol(EquationLabel.getText().substring(EquationLabel.getText().length() - 1))) {
                EquationLabel.setText(EquationLabel.getText().substring(0, EquationLabel.getText().length() - 1) + Subtract.getText());
            }
        });


        //Fourth row
        JButton One = new JButton();
        One.setName("One");
        One.setText("1");
        One.setBounds(20,310,60,40);
        add(One);

        One.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + One.getText()));

        JButton Two = new JButton();
        Two.setName("Two");
        Two.setText("2");
        Two.setBounds(90,310,60,40);
        add(Two);

        Two.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Two.getText()));

        JButton Three = new JButton();
        Three.setName("Three");
        Three.setText("3");
        Three.setBounds(160,310,60,40);
        add(Three);

        Three.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Three.getText()));

        JButton Add = new JButton();
        Add.setName("Add");
        Add.setText("+");
        Add.setBounds(230,310,60,40);
        add(Add);

        Add.addActionListener(e -> {
            if (!(EquationLabel.getText().length() == 0) && !isSymbol(EquationLabel.getText().substring(EquationLabel.getText().length()-1))) {
                EquationLabel.setText(EquationLabel.getText() + Add.getText());
                EquationLabel.setText(dotCheck(EquationLabel.getText()));
            } else if (!(EquationLabel.getText().length() == 0) && isSymbol(EquationLabel.getText().substring(EquationLabel.getText().length() - 1))) {
                EquationLabel.setText(EquationLabel.getText().substring(0, EquationLabel.getText().length() - 1) + Add.getText());
            }
        });

        //Fifth row
        JButton PlusMinus = new JButton();
        PlusMinus.setName("PlusMinus");
        PlusMinus.setText("±");
        PlusMinus.setBounds(20,360,60,40);
        add(PlusMinus);

        PlusMinus.addActionListener(e -> {
            String expression = EquationLabel.getText();
            char lastChar = expression.isEmpty() ? ' ' : expression.charAt(expression.length() - 1);
            String lastTwoChars = expression.length() < 2 ? " " : expression.substring(expression.length() - 2);
            if (expression.length() == 0) {
                EquationLabel.setText("(-");
            } else if (expression.length() == 1 && !isSymbol(String.valueOf(lastChar))) {
                EquationLabel.setText("(-" + lastChar);
            } else if (expression.length() > 1 && lastTwoChars.equals("(-")) {
                EquationLabel.setText("");
            } else if (expression.length() > 2 && !isSymbol(String.valueOf(lastChar))) {
                int i = expression.length() - 1;
                do {
                    if (isSymbol(String.valueOf(expression.charAt(i)))) {
                        if (expression.substring(i - 1, i + 1).equals("(-")) {
                            if (i == 1) {
                                EquationLabel.setText(expression.substring(i + 1));
                            } else {
                                EquationLabel.setText(expression.substring(0, i - 1) + expression.substring(i + 1));
                            }
                        } else {
                            EquationLabel.setText(expression.substring(0, i + 1) + "(-" + expression.substring(i + 1));
                        }
                        break;
                    }
                    i--;
                } while (i > 0);

            } else if (expression.length() > 2 && isSymbol(String.valueOf(lastChar))) {
                EquationLabel.setText(expression + "(-");
            }
        });

        JButton Zero = new JButton();
        Zero.setName("Zero");
        Zero.setText("0");
        Zero.setBounds(90,360,60,40);
        add(Zero);

        Zero.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Zero.getText()));

        JButton Dot = new JButton();
        Dot.setName("Dot");
        Dot.setText(".");
        Dot.setBounds(160,360,60,40);
        add(Dot);
        Dot.addActionListener(e -> EquationLabel.setText(EquationLabel.getText() + Dot.getText()));

        JButton Equals = new JButton();
        Equals.setName("Equals");
        Equals.setText("=");
        Equals.setBounds(230,360,60,40);
        add(Equals);

        Equals.addActionListener(e -> {
            String equation = EquationLabel.getText();
            String equationEnd = equation.substring(equation.length()-1);

            if (equationEnd.contains("+") ||
                    equationEnd.contains("-") ||
                    equationEnd.contains("×") ||
                    equationEnd.contains("÷") ||
                    parenthesisCheck(EquationLabel.getText()) == 1) {
                EquationLabel.setForeground(Color.RED.darker());
            } else if (EquationLabel.getText().substring(EquationLabel.getText().length()-1).contains("0") &&
                    EquationLabel.getText().substring(EquationLabel.getText().length()-2,EquationLabel.getText().length()-1).contains("÷")) {
                EquationLabel.setForeground(Color.RED.darker());
            } else {
                EquationLabel.setForeground(Color.GREEN.darker());
                double result = evaluateExpression(equation);
                ResultLabel.setText(resultFormat.format(result));
            }
        });

        this.setVisible(true);
    }

    private static double applyOperation(double a, double b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '×' -> a * b;
            case '÷' -> a / b;
            case '^' -> Math.pow(a, b);
            default -> throw new IllegalArgumentException("Invalid operator");
        };
    }
    private static boolean isSymbol (String expression){
        Pattern regex = Pattern.compile("[+\\-×÷^√]");
        Matcher matcher = regex.matcher(expression);
        return matcher.find();
    }

    private static String dotCheck (String expression){
        List<Integer> dotIndices = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '.') {
                dotIndices.add(i);
            }
        }

        if (!dotIndices.isEmpty()) {
            for (int index: dotIndices) {
                if (index == 0) {
                    return "0" + expression;
                } else if (index > 1) {
                    if (isSymbol(expression.substring(index - 1, index))) {
                        expression = expression.substring(0, index) + "0" + expression.substring(index);
                        return expression;
                    }
                }
                if (index == expression.length() - 2) { //have to count in the last operator too
                    return expression.substring(0, expression.length() - 1) + "0" + expression.substring(expression.length() - 1);
                }
            }
        }
        return expression;
    }

    private static int parenthesisCheck (String expression) {
        int leftP = 0;
        int rightP = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                leftP++;
            } else if (expression.charAt(i) == ')') {
                rightP++;
            }
        }
        return Integer.compare(leftP, rightP);
    }

    public static double evaluateExpression(String equation) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);

            if (c == '(') {
                operators.push(c);
            } else if (c == '√'){
                operators.push(c);
            } else if (c == '-') {
                if (i == 0 || String.valueOf(equation.charAt(i - 1)).equals("(")) {
                    StringBuilder sb = new StringBuilder("-");
                    while (i + 1 < equation.length() && Character.isDigit(equation.charAt(i + 1))) {
                        sb.append(equation.charAt(i + 1));
                        i++;
                    }
                    values.push(Double.parseDouble(sb.toString()));
                } else {
                    while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                        double val2 = values.pop();
                        double val1 = values.pop();
                        char op = operators.pop();
                        values.push(applyOperation(val1, val2, op));
                    }
                    operators.push(c);
                }
            } else if (isSymbol(String.valueOf(c))) {
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    double val2 = values.pop();
                    double val1 = values.pop();
                    char op = operators.pop();
                    values.push(applyOperation(val1, val2, op));
                }
                operators.push(c);
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    double val2 = values.pop();
                    double val1 = values.pop();
                    char op = operators.pop();
                    values.push(applyOperation(val1, val2, op));
                }
                operators.pop();
                if (!operators.empty() && operators.peek() == '√') {
                    values.push(Math.sqrt(values.pop()));
                    operators.pop();
                }

            } else if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (i + 1 < equation.length()) {
                    if (Character.isDigit(equation.charAt(i + 1)) || equation.charAt(i + 1) == '.') {
                        sb.append(equation.charAt(i + 1));
                        i++;
                    } else {
                        break;
                    }
                }
                values.push(Double.parseDouble(sb.toString()));
            }
        }

        while (!operators.isEmpty()) {
            double val2 = values.pop();
            double val1 = values.pop();
            char op = operators.pop();
            values.push(applyOperation(val1, val2, op));
        }

        return values.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        return (op1 != '×' && op1 != '÷' && op1 != '^' && op1 != '√') || (op2 != '+' && op2 != '-');
    }
}