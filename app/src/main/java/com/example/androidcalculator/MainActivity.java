package com.example.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_finalNumber = (TextView) findViewById(R.id.txt_finalNumber);
        txt_previousNumber = (TextView) findViewById(R.id.txt_previousNumber);
    }

    TextView txt_finalNumber, txt_previousNumber;

    private double numActual = 0;
    private double numFinal = 0;
    private int decimal = 0;
    private String trigonometria = "";
    private String calculsActuals = "";


    //Actulitzar els textos
    public void actualNumber(){
        txt_finalNumber.setText(String.valueOf(trigonometria + numActual));
    }
    public void previousNumber(String sign){
        double numAnterior = 0;
        if(Objects.equals(trigonometria, "sin(")){
            numAnterior = Math.sin(numActual);
            trigonometria = "";
        } else if (Objects.equals(trigonometria, "cos(")) {
            numAnterior = Math.cos(numActual);
            trigonometria = "";
        } else if (Objects.equals(trigonometria, "tan(")) {
            numAnterior = Math.tan(numActual);
            trigonometria = "";
        }
        else {
            numAnterior = numActual;
        }
        numActual = 0;
        decimal = 0;
        calculsActuals = String.valueOf(calculsActuals+ numAnterior +sign);
        txt_previousNumber.setText(calculsActuals);
        actualNumber();
    }
    public void equalsTo(View v){
        previousNumber("");
        calcular();
        txt_finalNumber.setText(String.valueOf(numFinal));
        calculsActuals = "";
    }

    //Manejar els numeros
    public void afegirNum(double num){
        if(decimal == 0){
            numActual = numActual * 10 + num;
        }
        else{
            numActual = (numActual + num/Math.pow(10,decimal));
            decimal++;
        }
        actualNumber();
    }
    public void click_del(View v){
        if(decimal == 0){
            double a = numActual % 10;
            numActual = (numActual - a)/10;
        }
        else{
            decimal--;
            double a = Math.pow(10, decimal-1);
            numActual = Math.round(numActual*a)/a;
        }
        actualNumber();
    }
    public void click_C(View v){
        numActual = 0;
        calculsActuals = "";
        previousNumber("");
    }
    public void click_comma(View v){
        decimal++;
    }
    public void click_percentage(View v){
        numActual = numActual/100;
        actualNumber();
    }
    public void click_sin(View v){
        trigonometria = "sin(";
        txt_finalNumber.setText(trigonometria);
    }
    public void click_cos(View v){
        trigonometria = "cos(";
        txt_finalNumber.setText(trigonometria);
    }
    public void click_tan(View v){
        trigonometria = "tan(";
        txt_finalNumber.setText(trigonometria);
    }

    //Operacions
    public void calcular() {
        double num1 = 0;
        double num2 = 0;
        String operacio = "";
        Pattern pattern = Pattern.compile("[\\d.]+|[\\-+x/]");
        Matcher matcher = pattern.matcher(calculsActuals);

        for (int i = 0;matcher.find(); i++) {
            String token = matcher.group();
            if (token.matches("[\\d.]+")) {
                if (i == 0)
                    num1 = Double.valueOf(token);
                else
                    num2 = Double.valueOf(token);
            } else if (token.matches("[\\-+x/]")) {
                operacio = token;
            }
            if(num1 != 0 && num2 != 0 && operacio.matches("[\\-+x/]")) {
                if (Objects.equals(operacio, "+")) {
                    num1 = num1 + num2;
                } else if (Objects.equals(operacio, "-")) {
                    num1 = num1 - num2;
                } else if (Objects.equals(operacio, "x")) {
                    num1 = num1 * num2;
                } else if (Objects.equals(operacio, "/")) {
                    num1 = num1 / num2;
                }
            }
        }
        numFinal = num1;
        numActual = 0;
    }
    public void click_plus(View v){
        previousNumber("+");
    }
    public void click_minus(View v){
        previousNumber("-");
    }
    public void click_multiply(View v){
        previousNumber("x");
    }
    public void click_divide(View v){
        previousNumber("/");
    }

    //Numeros
    public void click_0(View v){
        afegirNum(0);
    }
    public void click_1(View v){
        afegirNum(1);
    }
    public void click_2(View v){
        afegirNum(2);
    }
    public void click_3(View v){
        afegirNum(3);
    }
    public void click_4(View v){
        afegirNum(4);
    }
    public void click_5(View v){
        afegirNum(5);
    }
    public void click_6(View v){
        afegirNum(6);
    }
    public void click_7(View v){
        afegirNum(7);
    }
    public void click_8(View v){
        afegirNum(8);
    }
    public void click_9(View v){
        afegirNum(9);
    }
}