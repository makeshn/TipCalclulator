package com.example.android.tipcalclulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private final static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private final static NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private double billamount = 0.0;
    private double percent = 0.15;
    private TextView amounttextview;
    private TextView percenttextview;
    private TextView tiptextview;
    private TextView totaltextview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amounttextview = (TextView) findViewById(R.id.amountTextView);
        percenttextview = (TextView) findViewById(R.id.percenttextView);
        tiptextview = (TextView) findViewById(R.id.tipTextView);
        totaltextview = (TextView) findViewById(R.id.totaltextView);
        tiptextview.setText(currencyFormat.format(0));
        totaltextview.setText(currencyFormat.format(0));
        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        SeekBar seekBar = (SeekBar) findViewById(R.id.percentseekBar);
        seekBar.setOnSeekBarChangeListener(seekBarListener);



    }
    public void calculate()
    {
        percenttextview.setText(percentFormat.format(percent));
        double tip = billamount*percent;
        double total = billamount+tip;
        totaltextview.setText(currencyFormat.format(total));
        tiptextview.setText(currencyFormat.format(tip));



    }

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try
            {
                billamount = Double.parseDouble(s.toString())/100;
                amounttextview.setText(currencyFormat.format(billamount));



        }
        catch (NumberFormatException e)
        {
            amounttextview.setText(" ");
            billamount=0.0;

        }
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress/100.0;
            calculate();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
