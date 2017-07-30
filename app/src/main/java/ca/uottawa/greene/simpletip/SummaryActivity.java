package ca.uottawa.greene.simpletip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Retrieve bundle from intent and get values stored in the bundle
        Bundle b = getIntent().getExtras();
          double billTotal = b.getDouble("EXTRA_BillAmount");
          double tipPercentage = (b.getDouble("EXTRA_TipPercentage"))/100;
          int numOfPeople = b.getInt("EXTRA_NumOfPeople");

        //Calculate other variables
        double tipTotal = billTotal*tipPercentage;
        double tipPerPerson = tipTotal/numOfPeople;
        double eachPersonPays= tipTotal/numOfPeople+ billTotal/numOfPeople;
        double totalAmount= billTotal+tipTotal;

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String spDefaultCurrency = sharedPref.getString("defaultCurrency", "1");

        TextView lblBillAmount = (TextView) findViewById(R.id.lblBillTotal);
        TextView lblTipTotal = (TextView) findViewById(R.id.lblTipTotal);
        TextView lblTipPerPerson = (TextView) findViewById(R.id.lblTipPerPerson);
        TextView lblTotalAmount = (TextView) findViewById(R.id.lblTotalAmount);
        TextView lblEachPersonPays = (TextView) findViewById(R.id.lblEachPersonPays);


        //Display Receipt

        if(spDefaultCurrency.equals("1")){
            lblBillAmount.setText("Bill Total: $"+ String.format("%.2f",billTotal));
            lblTipTotal.setText("Tip Total: $"+String.format("%.2f",tipTotal));
            lblTipPerPerson.setText("Tip per person: $"+String.format("%.2f",tipPerPerson));
            lblTotalAmount.setText("Total Amount: $"+String.format("%.2f",totalAmount));
            lblEachPersonPays.setText("Each person pays: $"+String.format("%.2f",eachPersonPays));

        }
        else if(spDefaultCurrency.equals("2")){
            lblBillAmount.setText("Bill Total: €"+ String.format("%.2f",billTotal));
            lblTipTotal.setText("Tip Total: €"+String.format("%.2f",tipTotal));
            lblTipPerPerson.setText("Tip per person: €"+String.format("%.2f",tipPerPerson));
            lblTotalAmount.setText("Total Amount: €"+String.format("%.2f",totalAmount));
            lblEachPersonPays.setText("Each person pays: €"+String.format("%.2f",eachPersonPays));
        }
        else if(spDefaultCurrency.equals("3")){
            lblBillAmount.setText("Bill Total: £"+ String.format("%.2f",billTotal));
            lblTipTotal.setText("Tip Total: £"+String.format("%.2f",tipTotal));
            lblTipPerPerson.setText("Tip per person: £"+String.format("%.2f",tipPerPerson));
            lblTotalAmount.setText("Total Amount: £"+String.format("%.2f",totalAmount));
            lblEachPersonPays.setText("Each person pays: £"+String.format("%.2f",eachPersonPays));

        }


    }

}
