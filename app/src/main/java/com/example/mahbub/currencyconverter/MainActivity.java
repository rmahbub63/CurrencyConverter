package com.example.mahbub.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Declare spinner for two currency
    Spinner spinnerFirstCurrency, spinnerSecondCurrency;
    // Declare edit text for two currency
    EditText editTextFc, editTextSc;
    // Declare text view for heading
    TextView textViewHead;

    // Declare string for save both currency name full
    String firstCurrencyName, secondCurrencyName;
    // Declare double for both currency
    double firstCurrency, secondCurrency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // providing reference to all spinner, edit text, text view
        // as this code written in android studio 3.0 so there is no type casting in reference of view
        spinnerFirstCurrency = findViewById(R.id.spinner_first_currency);
        spinnerSecondCurrency = findViewById(R.id.spinner_second_currency);
        editTextFc = findViewById(R.id.editText_first_input);
        editTextSc = findViewById(R.id.editText_second_input);
        textViewHead = findViewById(R.id.textView_head);
        // Set the value for heading
        textViewHeadSet();

        spinnerFirstCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textViewHeadSet();
                spinnerChange();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerSecondCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textViewHeadSet();
                spinnerChange();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        editTextFc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editTextFc.isFocused()) {
                    if (editTextFc.getText().length() > 0 && editTextSc.getText().length() > 0) {
//                        Toast.makeText(MainActivity.this, "IN IF first Currency", Toast.LENGTH_SHORT).show();
                        editTextSc.setText(String.valueOf(calculationForFirstSpinner()));
                    } else {
                        editTextSc.setText("0");
                    }
                }
            }
        });

        editTextSc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editTextSc.isFocused()) {
                    if (editTextFc.getText().length() > 0 && editTextSc.getText().length() > 0) {
//                        Toast.makeText(MainActivity.this, "IN IF second Currency", Toast.LENGTH_SHORT).show();
                        editTextFc.setText(String.valueOf(calculationForSecondSpinner()));
                    } else {
                        editTextFc.setText("0");
                    }
                }
            }
        });

        editTextFc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                textViewHeadSet();
            }
        });
        editTextSc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                textViewHeadSet();
            }
        });
    }

    public void textViewHeadSet(){
        firstCurrencyName = spinnerFirstCurrency.getSelectedItem().toString();
        secondCurrencyName = spinnerSecondCurrency.getSelectedItem().toString();

        if(editTextFc.isFocused()) {
            String headLine = firstCurrencyName.substring(0,3) + " TO " + secondCurrencyName.substring(0,3);
            textViewHead.setText(headLine);
        } else if(editTextSc.isFocused()) {
            String headLine = secondCurrencyName.substring(0,3) + " TO " + firstCurrencyName.substring(0,3);
            textViewHead.setText(headLine);
        }
    }

    public double calculationForFirstSpinner(){
        firstCurrencyName = spinnerFirstCurrency.getSelectedItem().toString();
        secondCurrencyName = spinnerSecondCurrency.getSelectedItem().toString();
        firstCurrency = Double.valueOf(editTextFc.getText().toString());
        secondCurrency = Double.valueOf(editTextSc.getText().toString());
        double value = 1;
        if (firstCurrencyName.equals(Currency.BDT)){
            value = calculationForCurrency(secondCurrencyName, firstCurrency,
                    Currency.BDTToBDT, Currency.BDTToEUR, Currency.BDTToINR, Currency.BDTToQAR, Currency.BDTToUSD);
        } else if (firstCurrencyName.equals(Currency.EUR)){
            value = calculationForCurrency(secondCurrencyName, firstCurrency,
                    Currency.EURToBDT, Currency.EURToEUR, Currency.EURToINR, Currency.EURToQAR, Currency.EURToUSD);
        } else if (firstCurrencyName.equals(Currency.INR)){
            value = calculationForCurrency(secondCurrencyName, firstCurrency,
                    Currency.INRToBDT, Currency.INRToEUR, Currency.INRToINR, Currency.INRToQAR, Currency.INRToUSD);
        } else if (firstCurrencyName.equals(Currency.QAR)){
            value = calculationForCurrency(secondCurrencyName, firstCurrency,
                    Currency.QARToBDT, Currency.QARToEUR, Currency.QARToINR, Currency.QARToQAR, Currency.QARToUSD);
        } else if (firstCurrencyName.equals(Currency.USD)){
            value = calculationForCurrency(secondCurrencyName, firstCurrency,
                    Currency.USDToBDT, Currency.USDToEUR, Currency.USDToINR, Currency.USDToQAR, Currency.USDToUSD);
        }
        return value;
    }

    public double calculationForSecondSpinner(){
        firstCurrencyName = spinnerFirstCurrency.getSelectedItem().toString();
        secondCurrencyName = spinnerSecondCurrency.getSelectedItem().toString();
        firstCurrency = Double.valueOf(editTextFc.getText().toString());
        secondCurrency = Double.valueOf(editTextSc.getText().toString());
        double value = 1;
        if (secondCurrencyName.equals(Currency.BDT)){
            value = calculationForCurrency(firstCurrencyName, secondCurrency,
                    Currency.BDTToBDT, Currency.BDTToEUR, Currency.BDTToINR, Currency.BDTToQAR, Currency.BDTToUSD);
        } else if (secondCurrencyName.equals(Currency.EUR)){
            value = calculationForCurrency(firstCurrencyName, secondCurrency,
                    Currency.EURToBDT, Currency.EURToEUR, Currency.EURToINR, Currency.EURToQAR, Currency.EURToUSD);
        } else if (secondCurrencyName.equals(Currency.INR)){
            value = calculationForCurrency(firstCurrencyName, secondCurrency,
                    Currency.INRToBDT, Currency.INRToEUR, Currency.INRToINR, Currency.INRToQAR, Currency.INRToUSD);
        } else if (secondCurrencyName.equals(Currency.QAR)){
            value = calculationForCurrency(firstCurrencyName, secondCurrency,
                    Currency.QARToBDT, Currency.QARToEUR, Currency.QARToINR, Currency.QARToQAR, Currency.QARToUSD);
        } else if (secondCurrencyName.equals(Currency.USD)){
            value = calculationForCurrency(firstCurrencyName, secondCurrency,
                    Currency.USDToBDT, Currency.USDToEUR, Currency.USDToINR, Currency.USDToQAR, Currency.USDToUSD);
        }
        return value;
    }

    public double calculationForCurrency(String currencyName, double currencyValue, double BDT, double EUR, double INR, double QAR, double USD){
        double value = 1;
        switch (currencyName){
            case Currency.BDT:
                value = currencyValue * BDT;
                break;
            case Currency.EUR:
                value = currencyValue * EUR;
                break;
            case Currency.INR:
                value = currencyValue * INR;
                break;
            case Currency.QAR:
                value = currencyValue * QAR;
                break;
            case Currency.USD:
                value = currencyValue * USD;
                break;
        }
        return value;
    }

    public void spinnerChange(){
        if(editTextFc.isFocused()) {
            if (editTextFc.getText().length() > 0 && editTextSc.getText().length() > 0) {
//                Toast.makeText(MainActivity.this, "IN IF first Currency", Toast.LENGTH_SHORT).show();
                editTextSc.setText(String.valueOf(calculationForFirstSpinner()));
            } else {
                editTextSc.setText("");
            }
        } else if(editTextSc.isFocused()) {
            if (editTextFc.getText().length() > 0 && editTextSc.getText().length() > 0) {
//                Toast.makeText(MainActivity.this, "IN IF second Currency", Toast.LENGTH_SHORT).show();
                editTextFc.setText(String.valueOf(calculationForSecondSpinner()));
            } else {
                editTextFc.setText("");
            }
        }
    }

    public void buttonClearOnClick(View view) {
        editTextFc.setText("");
        editTextSc.setText("");
    }
}
