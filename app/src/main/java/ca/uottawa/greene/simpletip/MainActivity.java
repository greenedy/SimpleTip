package ca.uottawa.greene.simpletip;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    private Dialog rateDialog;
    private RatingBar ratingBar;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button btnSuggestTip = (Button) findViewById(R.id.btnSuggestTip);

        getSharePref();
        //when Suggest Button is pushed displays rating bar dialogue
        btnSuggestTip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Create and show dialogue
                rateDialog = new Dialog(MainActivity.this, R.style.Dialog);
                rateDialog.setContentView(R.layout.suggest);
                rateDialog.setCancelable(true);
                ratingBar = (RatingBar) rateDialog.findViewById(R.id.ratingBar);
                rateDialog.show();

                //When the rating bar is clicked on then a delay of half a second and then the dialogue closes
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        //Tip percentage is calculated based on rating from user
                        int x = (int) ratingBar.getRating();
                        x = 10 + (x * 2);
                        final int y=x;
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Tip text area is updated with the calculated tip
                                EditText tip = (EditText) findViewById(R.id.txtTipPercentage);
                                tip.setText( String.valueOf(y));
                                rateDialog.dismiss();
                            }
                        }, 500);

                    }


                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //When the settings is clicked open the preferences activity
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, PreferencesActivity.class);

            //Intent intent = new Intent(this, PreferencesActivity.class);
            startActivityForResult(intent,1);
            getSharePref();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       getSharePref();
    }
    public void getSharePref() {

        // PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        // sharedPref = this.getSharedPreferences("preferences",MODE_PRIVATE);

        String spDefaultTip = sharedPref.getString("defaultTip", "15");
        String spDefaultCurrency = sharedPref.getString("defaultCurrency", "1");



        EditText txtBillAmount = (EditText) findViewById(R.id.txtBillAmount);
        TextView lblBillAmount = (TextView) findViewById(R.id.lblBillTotal);
        EditText txtTipPercentage = (EditText) findViewById(R.id.txtTipPercentage);
        EditText txtNumOfPeople = (EditText) findViewById(R.id.txtNumOfPeople);

        if(spDefaultCurrency.equals("1")){
            lblBillAmount.setText("Bill Total: $");

        }
        else if(spDefaultCurrency.equals("2")){
            lblBillAmount.setText("Bill Total: €");

        }
        else if(spDefaultCurrency.equals("3")){
            lblBillAmount.setText("Bill Total: £");

        }

        txtTipPercentage.setText(spDefaultTip);


    }

    public void plusOne(View view) {
        EditText txtBillAmount = (EditText) findViewById(R.id.txtBillAmount);
        double billAmount = Double.parseDouble(txtBillAmount.getText().toString());
        billAmount=billAmount+1.00;
        txtBillAmount.setText(String.format("%.2f",billAmount));
    }
    public void minusOne(View view) {
        EditText txtBillAmount = (EditText) findViewById(R.id.txtBillAmount);
        double billAmount = Double.parseDouble(txtBillAmount.getText().toString());
        if(billAmount-1.00<0){
        }
        else{
            billAmount=billAmount-1.00;
            txtBillAmount.setText(String.format("%.2f",billAmount));
        }

    }
    public void plusTen(View view) {
        EditText txtBillAmount = (EditText) findViewById(R.id.txtBillAmount);
        double billAmount = Double.parseDouble(txtBillAmount.getText().toString());
        billAmount=billAmount+0.10;
        txtBillAmount.setText(String.format("%.2f",billAmount));
    }
    public void minusTen(View view) {
        EditText txtBillAmount = (EditText) findViewById(R.id.txtBillAmount);
        double billAmount = Double.parseDouble(txtBillAmount.getText().toString());
        if(billAmount-0.10<0){
        }
        else{
            billAmount=billAmount-0.10;
            txtBillAmount.setText(String.format("%.2f",billAmount));
        }
    }

    public void plus(View view) {
        EditText txtNumOfPeople = (EditText) findViewById(R.id.txtNumOfPeople);
        int numPeople = Integer.parseInt(txtNumOfPeople.getText().toString());
        numPeople++;
        txtNumOfPeople.setText(Integer.toString(numPeople));
    }
    public void minus(View view) {
        EditText txtNumOfPeople = (EditText) findViewById(R.id.txtNumOfPeople);
        int numPeople = Integer.parseInt(txtNumOfPeople.getText().toString());
        if(numPeople==1||numPeople==0) {
        }
        else{
            numPeople--;
        }
        txtNumOfPeople.setText(Integer.toString(numPeople));

    }

    //Checks inputs and opens summary activity
    public void calculate(View view) {

        EditText txtBillAmount = (EditText) findViewById(R.id.txtBillAmount);
        EditText txtTipPercentage = (EditText) findViewById(R.id.txtTipPercentage);
        EditText txtNumOfPeople = (EditText) findViewById(R.id.txtNumOfPeople);

        //Check to make sure inputs are present and valid
        if (txtBillAmount.getText().toString().equals("")||txtBillAmount.getText().toString().equals("0.00")||txtBillAmount.getText().toString().equals("0")||txtBillAmount.getText().toString().equals("0.0")){
            Toast.makeText(MainActivity.this,"Please input a Bill Amount", Toast.LENGTH_LONG).show();
        }
        else if(txtTipPercentage.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Please input a Tip Percentage", Toast.LENGTH_LONG).show();
        }
        else if(txtNumOfPeople.getText().toString().equals("")||txtNumOfPeople.getText().toString().equals("0")){
            Toast.makeText(MainActivity.this,"Please input a valid Number of People", Toast.LENGTH_LONG).show();
        }
        else {
            //get inputs and put in a bundle in an intent
            Intent intent = new Intent(this, SummaryActivity.class);
            double passBillAmount = Double.parseDouble(txtBillAmount.getText().toString());
            double passTipPercentage = Double.parseDouble(txtTipPercentage.getText().toString());
            int passNumOfPeople = Integer.parseInt(txtNumOfPeople.getText().toString());

            Bundle b = new Bundle();
            b.putDouble("EXTRA_BillAmount", passBillAmount);
            b.putDouble("EXTRA_TipPercentage", passTipPercentage);
            b.putInt("EXTRA_NumOfPeople", passNumOfPeople);
            intent.putExtras(b);

            startActivity(intent);
        }
    }
}
