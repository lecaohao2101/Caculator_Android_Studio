package com.example.logbook1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result,show;
    MaterialButton buttonBrackOpen,buttonBrackClose, buttonMultiply, buttonDivide, buttonPlus,buttonMinus,buttonEquals, buttonSQRT, buttonMU, buttonPercent;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9,buttonDot, buttonPi;
    MaterialButton buttonAC, buttonDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        show = findViewById(R.id.show);

        buttonId(buttonBrackOpen,R.id.open_bracket);
        buttonId(buttonBrackClose,R.id.close_bracket);
        buttonId(buttonDivide,R.id.divide);
        buttonId(buttonMultiply,R.id.multiplys);
        buttonId(buttonPlus,R.id.plus);
        buttonId(buttonMinus,R.id.minus);
        buttonId(buttonEquals,R.id.equals);
        buttonId(buttonSQRT,R.id.sqrt);
        buttonId(buttonMU,R.id.mu);
        buttonId(buttonPercent,R.id.percents);

        buttonId(button0,R.id.n0);
        buttonId(button1,R.id.n1);
        buttonId(button2,R.id.n2);
        buttonId(button3,R.id.n3);
        buttonId(button4,R.id.n4);
        buttonId(button5,R.id.n5);
        buttonId(button6,R.id.n6);
        buttonId(button7,R.id.n7);
        buttonId(button8,R.id.n8);
        buttonId(button9,R.id.n9);
        buttonId(buttonPi,R.id.pi);
        buttonId(buttonDot,R.id.dot);

        buttonId(buttonAC,R.id.clear);
        buttonId(buttonDel,R.id.delete);
    }

    void buttonId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataCaculator = show.getText().toString();

        if (buttonText.equals("AC")) {
            show.setText("");
            result.setText("0");
            return;
        }

        if (buttonText.equals("=")) {
            String results = getResult(dataCaculator);
            show.setText(dataCaculator);
            result.setText(results);
            return;
        }

        if (buttonText.equals("⌫")) {
            dataCaculator = dataCaculator.substring(0, dataCaculator.length() - 1);
        } else {
            dataCaculator = dataCaculator + buttonText;
        }

        show.setText(dataCaculator);
    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String results =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(results.endsWith(".0")){
                results = results.replace(".0","");
            }
            return results;
        }catch (Exception e){
            return "Err";
        }
    }
}



