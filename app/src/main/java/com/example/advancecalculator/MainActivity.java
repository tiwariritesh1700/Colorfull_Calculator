package com.example.advancecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class MainActivity extends AppCompatActivity {

    TextView calculations;
    TextView answer;
    int count = 0;
    String expression = "";
    String text = "";
    Double result = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculations = findViewById(R.id.calculation);
        calculations.setMovementMethod(new ScrollingMovementMethod());

        answer = findViewById(R.id.answer);

    }

    public void Onclick(View view) {

        switch (view.getId()) {

            case R.id.btn_doublezero:
                answer.setText(answer.getText() + "00");
                break;
            case R.id.btn_zero:
                answer.setText(answer.getText() + "0");
                break;
            case R.id.btn_one:
                answer.setText(answer.getText() + "1");
                break;
            case R.id.btn_two:
                answer.setText(answer.getText() + "2");
                break;
            case R.id.btn_three:
                answer.setText(answer.getText() + "3");
                break;
            case R.id.btn_four:
                answer.setText(answer.getText() + "4");
                break;
            case R.id.btn_five:
                answer.setText(answer.getText() + "5");
                break;
            case R.id.btn_six:
                answer.setText(answer.getText() + "6");
                break;
            case R.id.btn_seven:
                answer.setText(answer.getText() + "7");
                break;
            case R.id.btn_eight:
                answer.setText(answer.getText() + "8");
                break;
            case R.id.btn_nine:
                answer.setText(answer.getText() + "9");
                break;

            case R.id.btn_dot:

                if (count == 0 && answer.length() != 0) {
                    answer.setText((answer.getText()) + ".");
                    count++;
                }
                break;

            case R.id.btn_plus:
                operationClicked("+");
                break;

            case R.id.btn_minus:
                operationClicked("-");
                break;
            case R.id.btn_division:
                operationClicked("/");
                break;

            case R.id.btn_multiplication:
                operationClicked("*");
                break;

            case R.id.btn_squareroot:
                if (answer.length() != 0) {
                    text = answer.getText().toString();
                    answer.setText("sqrt(" + text + ")");
                }
                break;

            case R.id.btn_square:
                if (answer.length() != 0) {
                    text = answer.getText().toString();
                    answer.setText("(" + text + ")^2");
                }
                break;

            case R.id.btn_plus_or_minus:
                if (answer.length() != 0) {
                    String s = answer.getText().toString();
                    char arr[] = s.toCharArray();
                    if (arr[0] == '-') {
                        answer.setText(s.substring(1, s.length()));
                    } else
                        answer.setText("-" + s);
                }
                break;

            case R.id.btn_equal:
                if (answer.length() != 0) {
                    text = answer.getText().toString();
                    expression = calculations.getText().toString() + text;

                }
                calculations.setText("");
                if (expression.length() == 0)
                    expression = "0.0";

                DoubleEvaluator evaluator = new DoubleEvaluator();

                try {
                    result = new ExtendDoubleEvaluator().evaluate(expression);
                    if (!expression.equals("0.0"))
                        answer.setText(result + "");

                } catch (Exception e) {
                    answer.setText("Invalid Expression");
                    calculations.setText("");
                    expression = "";
                    e.printStackTrace();
                }
                break;

            case R.id.btn_openbracket:
                answer.setText(answer.getText() + "(");
                break;

            case R.id.btn_closebracket:
                answer.setText(answer.getText() + ")");
                break;

            case R.id.btn_del:
                CharSequence clear = answer.getText().toString();
                CharSequence clear1 = calculations.getText().toString();
                if (clear.length() != 0) {
                    answer.setText(clear.subSequence(0, clear.length() - 1));
                }
                if (clear1.length() != 0) {
                    calculations.setText(clear1.subSequence(0, clear1.length() - 1));
                }
                break;

            case R.id.btn_clear:
                answer.setText("");
                calculations.setText("");
                count = 0;
                expression = "";
        }
    }

    private void operationClicked(String op) {

        if (answer.length() != 0) {
            String text = answer.getText().toString();
            calculations.setText(calculations.getText() + text + op);
            answer.setText("");
            count = 0;
        } else {
            String text = calculations.getText().toString();
            if (text.length() > 0) {
                String newText = text.substring(0, text.length() - 1) + op;
                calculations.setText(newText);
            }
        }
    }
}
