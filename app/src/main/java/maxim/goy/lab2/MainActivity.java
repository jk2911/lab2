package maxim.goy.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    SeekBar procent;
    EditText sum, viewProcent, initialPayment, year;
    RadioButton typePayment;
    TextView overpaymant, viewSum, generalSumPayment, procentOverpayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        procent = findViewById(R.id.procent);
        sum = findViewById(R.id.sum);
        viewProcent = findViewById(R.id.viewProcent);
        initialPayment = findViewById(R.id.initialPayment);
        year = findViewById(R.id.year);

        overpaymant = findViewById(R.id.overpayment);
        viewSum = findViewById(R.id.viewSum);
        generalSumPayment = findViewById(R.id.viewSum);
        procentOverpayment = findViewById(R.id.procentOverpayment);

        sum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        procent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                viewProcent.setText(String.valueOf(procent.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        viewProcent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                procent.setProgress(Integer.valueOf(String.valueOf(viewProcent.getText())), true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        typePayment = findViewById(R.id.differentiated);
    }

    public void calculateCredit(View view) {
        float sum = Float.parseFloat(String.valueOf(this.sum.getText()));
        Credit.typePayment typePay = this.typePayment.isActivated() ?
                Credit.typePayment.Differentiated : Credit.typePayment.Annuity;
        float initialPayment = Float.parseFloat(String.valueOf(this.initialPayment.getText()));
        float procent = Float.parseFloat(String.valueOf(viewProcent.getText()));
        float year = Float.parseFloat(String.valueOf(this.year.getText()));
        Credit credit = new Credit(sum, initialPayment, typePay, procent, year);
        double summ = credit.credit();
        viewSum.setText(summ + "");
    }
}