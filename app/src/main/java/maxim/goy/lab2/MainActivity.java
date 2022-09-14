package maxim.goy.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar present;
    EditText sum, viewPresent, initialPayment, year;
    RadioButton typePayment;
    TextView overpayment, viewSum, generalSumPayment, presentOverpayment, avePay, viewAvePay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        present = findViewById(R.id.procent);
        sum = findViewById(R.id.sum);
        viewPresent = findViewById(R.id.viewProcent);
        initialPayment = findViewById(R.id.initialPayment);
        year = findViewById(R.id.year);
        typePayment = findViewById(R.id.differentiated);

        overpayment = findViewById(R.id.overpayment);
        viewSum = findViewById(R.id.viewSum);
        generalSumPayment = findViewById(R.id.generalSumPayment);
        presentOverpayment = findViewById(R.id.procentOverpayment);
        avePay = findViewById(R.id.avePay);
        viewAvePay = findViewById(R.id.textView9);

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
        present.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                viewPresent.setText(String.valueOf(present.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        viewPresent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                present.setProgress(Integer.parseInt(String.valueOf(viewPresent.getText())), true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @SuppressLint("SetTextI18n")
    public void calculateCredit(View view) {
        float sum = Float.parseFloat(String.valueOf(this.sum.getText()));
        Credit.typePayment typePay = this.typePayment.isChecked() ?
                Credit.typePayment.Differentiated : Credit.typePayment.Annuity;
        float initialPayment = Float.parseFloat(String.valueOf(this.initialPayment.getText()));
        float present = Float.parseFloat(String.valueOf(viewPresent.getText()));
        float year = Float.parseFloat(String.valueOf(this.year.getText()));
        Credit credit = new Credit(sum, initialPayment, typePay, present, year);
        credit.credit();
        viewSum.setText(credit.getSumCredit() + "");
        presentOverpayment.setText(credit.getPresentOverpayment() + "");
        generalSumPayment.setText(credit.getGeneralSum() + "");
        overpayment.setText(credit.getOverPayment() + "");
        if (typePay == Credit.typePayment.Annuity) {
            avePay.setVisibility(View.VISIBLE);
            viewAvePay.setVisibility(View.VISIBLE);
            avePay.setText(credit.getAvePay() + "");
        } else {
            avePay.setVisibility(View.INVISIBLE);
            viewAvePay.setVisibility(View.INVISIBLE);
        }
    }
}