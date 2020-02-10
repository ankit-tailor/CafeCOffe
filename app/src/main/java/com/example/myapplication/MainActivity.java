package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int numberOfCoffees = 1;
    public void submitOrder(View view) {
//        displayMessage(str);
        int price = calculate();
        String str = cretaeOrderSummary(price);
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:tailorankit3345@gmail.com")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
            intent.putExtra(Intent.EXTRA_TEXT, str);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
    }

    /*
    This method will check for whipped cream topping
     */
    public boolean hasWhippedCream() {
        CheckBox c = (CheckBox) findViewById(R.id.whippedCream_checkbox_view);
        if(c.isChecked() == true) {
            return true;
        } else {
            return false;
        }
    }
    /*
    This method will check for chocolate topping
     */
    public boolean hasChocolate() {
        CheckBox c = (CheckBox) findViewById(R.id.chocolate_checkbox_view);
        if(c.isChecked() == true) {
            return true;
        } else {
            return false;
        }
    }
    /*
    This method will calculate the price
     */
    public int calculate() {
        int totalPrice = numberOfCoffees * 5;
        boolean hasWhippedCream = hasWhippedCream();
        boolean hasChocolate = hasChocolate();

        if(hasChocolate){
            totalPrice += numberOfCoffees * 2;
        }
        if(hasWhippedCream){
            totalPrice += numberOfCoffees;
        }
        return totalPrice;
    }
    /*
    This method is used to grab input words i.e name field
     */
    public String inputTexts(){
        EditText name = (EditText)findViewById(R.id.name_view);
        String str = name.getText().toString();
        return str;
    }

    public String cretaeOrderSummary(int price){
        String summary = "";
        String str = inputTexts();
        boolean hasWhippedCream = hasWhippedCream();
        boolean hasChocolate = hasChocolate();
        if(hasWhippedCream) {
            if(hasChocolate) {
                summary = "Name: "+ str + "\nAdd Whipped Cream? Yes" +"\nAdd Chocolate? Yes"+ "\nQuantity: " + numberOfCoffees + "\nTotal: $" + price + "\nThank you!";
            }
            else{
                summary = "Name: "+ str + "\nAdd Whipped Cream? Yes"+"\nAdd Chocolate? No"+"\nQuantity: " + numberOfCoffees + "\nTotal: $" + price + "\nThank you!";
            }
        }
        else{
            if(hasChocolate) {
                summary = "Name: "+ str + "\nAdd Whipped Cream? No" + "\nAdd Chocolate? Yes" + "\nQuantity: " + numberOfCoffees + "\nTotal: $" + price + "\nThank you!";
            }else{
                summary = "Name: "+ str + "\nAdd Whipped Cream? No"+"\nAdd Chocolate? No"+"\nQuantity: " + numberOfCoffees + "\nTotal: $" + price + "\nThank you!";
            }
        }
        return summary;
    }
    /*
    increment number of cups by 1

    * */
    public void increment(View view) {
        if(numberOfCoffees < 100)
        displayQuantity(++numberOfCoffees);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have more than 100 cup of coffees";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
//        displayPrice(numberOfCoffees * 5);
    }
    public void decrment(View view) {
        if(numberOfCoffees>1) {
            displayQuantity(--numberOfCoffees);
//            displayPrice(numberOfCoffees * 5);
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have less than 1 cup of coffee";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}

