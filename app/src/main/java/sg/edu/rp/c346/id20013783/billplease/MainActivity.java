package sg.edu.rp.c346.id20013783.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    EditText amount;
    EditText people;
    ToggleButton svs;
    ToggleButton gst;
    EditText discount;
    RadioButton cash;
    Button split;
    Button reset;
    TextView totalAmnt;
    TextView eachpay;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editTextNumber);
        people = findViewById(R.id.editTextNumber2);
        svs = findViewById(R.id.toggleButton);
        gst = findViewById(R.id.toggleButton2);
        discount = findViewById(R.id.editTextNumberDecimal2);
        cash = findViewById(R.id.radioButton7);
        split = findViewById(R.id.button4);
        reset = findViewById(R.id.button3);
        totalAmnt = findViewById(R.id.textView6);
        eachpay = findViewById(R.id.textView8);

        split.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         double newAmount = 0.0;
                                         String firstAmnt = amount.getText().toString();
                                         String numOfPeople = people.getText().toString();
                                         String disc = discount.getText().toString();

                                         Toast toast = new Toast(getApplicationContext());

                                         if (amount.getText().toString().trim().length()== 0 && people.getText().toString().trim().length() == 0) {

                                             TextView tv = new TextView(MainActivity.this);
                                             tv.setTextColor(Color.RED);
                                             tv.setText("Input the empty spaces");
                                             toast.setView(tv);
                                             toast.show();

                                         } else {

                                             if (svs.isChecked() && gst.isChecked()) {
                                                 newAmount = Double.parseDouble(amount.getText().toString()) * 1.17;
                                             } else if (!svs.isChecked() && gst.isChecked()) {
                                                 newAmount = Double.parseDouble(amount.getText().toString()) * 1.07;
                                             } else if (svs.isChecked() && !gst.isChecked()) {
                                                 newAmount = Double.parseDouble(amount.getText().toString()) * 1.10;
                                             } else {
                                                 newAmount = Double.parseDouble(amount.getText().toString());
                                             }

                                             if (disc.trim().length() != 0) {
                                                 newAmount = newAmount * (1 - Double.parseDouble(disc) / 100);
                                                 if (cash.isChecked()) {
                                                     totalAmnt.setText("Total Bill: $" + String.format("%.2f", newAmount));
                                                     int num = Integer.parseInt(people.getText().toString());
                                                     if (num == 1) {
                                                         eachpay.setText("Each Pays: $" + newAmount + " in cash");
                                                     } else {
                                                         eachpay.setText("Each Pays: $" + String.format("%.2f", newAmount / num) + " in cash");
                                                     }
                                                 } else {
                                                     totalAmnt.setText("Total Bill: $" + String.format("%.2f", newAmount));
                                                     int num = Integer.parseInt(people.getText().toString());
                                                     if (num == 1) {
                                                         eachpay.setText("Each Pays: $" + newAmount + " via Paynow To 912345678");
                                                     } else {
                                                         eachpay.setText("Each Pays: $" + String.format("%.2f", newAmount / num) + " via Paynow To 912345678");
                                                     }
                                                 }
                                             }
                                         }
                                     }
                                 });



        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                people.setText("");
                discount.setText("");
                svs.setChecked(false);
                gst.setChecked(false);

            }
        });
    }
}