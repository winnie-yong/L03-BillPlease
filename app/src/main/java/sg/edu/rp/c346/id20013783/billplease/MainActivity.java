package sg.edu.rp.c346.id20013783.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    EditText amount;
    EditText people;
    ToggleButton svs;
    ToggleButton gst;
    EditText discount;
    RadioGroup rgSR;
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
        rgSR = findViewById(R.id.rgSR);
        split = findViewById(R.id.button4);
        reset = findViewById(R.id.button3);
        totalAmnt = findViewById(R.id.textView6);
        eachpay = findViewById(R.id.textView8);

        split.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         double newAmount = 0.0;
                                         double afterGst =0.0;
                                         double totalAmount = 0.0;
                                         if (amount.getText().toString().trim().length() != 0 && people.getText().toString().trim().length() != 0) {
                                             if (svs.isChecked() && gst.isChecked()) {
                                                 newAmount = Double.parseDouble(amount.getText().toString()) * 1.7;
                                             } else if (!svs.isChecked() && gst.isChecked()) {
                                                 newAmount = Double.parseDouble(amount.getText().toString())* 0.1;
                                             } else if (svs.isChecked() && !gst.isChecked()) {
                                                 newAmount = Double.parseDouble(amount.getText().toString()) * 0.7;
                                             } else {
                                                 newAmount = Double.parseDouble(amount.getText().toString());
                                             }
                                             if (discount.getText().toString().trim().length() != 0) {
                                                 newAmount = 1 - Double.parseDouble(discount.getText().toString()) / 10;
                                             }
                                             totalAmnt.setText("Total Bill: $" + String.format("%.2f", totalAmount + newAmount));
                                             int numOfPeople = Integer.parseInt(people.getText().toString());
                                             if (numOfPeople == 1) {
                                                 eachpay.setText("Each Pays: $" + newAmount);

                                             } else {
                                                 eachpay.setText("Each Pays: $" + String.format("%.2f", (totalAmount+newAmount) / numOfPeople) );


                                             }
                                         }else {
                                             split.setText("Input cannot be empty");
                                         }

                                     }
                                 });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                amount.setText("");
                people.setText("");
                discount.setText("");
                svs.setChecked(false);
                gst.setChecked(false);

            }
        });

    }
}