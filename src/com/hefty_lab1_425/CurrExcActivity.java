package com.hefty_lab1_425;
import com.hefty_lab1_425.R;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class CurrExcActivity extends ActionBarActivity 
implements OnEditorActionListener, OnClickListener {
	
	// define variables for the widgets
	private EditText usdInputAmount;
	private Button pesoButton;
	private Button euroButton;
	private Button yuanButton;
	private Button dollarButton;
	private Button convertButton;
	private EditText currExc;
	private EditText prevExc;
	private EditText outputResult;
	
    // define the SharedPreferences object
    private SharedPreferences savedValues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_curr_exc);
		
		// get references to the widgets
		usdInputAmount = (EditText) findViewById(R.id.usdInputLabel);
		usdInputAmount.requestFocus();
		pesoButton = (Button) findViewById(R.id.peso);
		euroButton = (Button) findViewById(R.id.euro);
		yuanButton = (Button) findViewById(R.id.yuan);
		dollarButton = (Button) findViewById(R.id.dollar);
		convertButton = (Button) findViewById(R.id.CONVERT);
		currExc = (EditText) findViewById(R.id.currCurr);
		prevExc = (EditText) findViewById(R.id.prevCurr);
		outputResult = (EditText) findViewById(R.id.outputResult);
		
		// set the listeners
        pesoButton.setOnClickListener(this);
        euroButton.setOnClickListener(this);
        yuanButton.setOnClickListener(this);
        dollarButton.setOnClickListener(this);
        convertButton.setOnClickListener(this);
        
        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);        
	}
	
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
            actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            /*calculateAndDisplay();*/
        }        
        return false;
    }
    
	/*@Override*/
	public void onClick(View v) {
		if (!usdInputAmount.getText().toString().equals("")){
			switch (v.getId()){
			case R.id.peso: 
				if (savedValues.getString("currPeso","").equals("")){
					// no stored peso
					//System.out.println("no peso");
					currExc.setText("13.24");
					prevExc.setText("0.00");
					storeData("currPeso", currExc.getText().toString());
					storeData("prevPeso", "0.00");
				} else {
					// found stored peso
					if (savedValues.getString("currPeso",  "").equals(currExc.getText().toString())){
						// same currPeso as what is stored
						//System.out.println("same peso value found | currPeso: " + savedValues.getString("currPeso","") + " | currExc: " + currExc.getText().toString());
						currExc.setText(savedValues.getString("currPeso", ""));
						if (!savedValues.getString("prevPeso","").equals("")){
							prevExc.setText(savedValues.getString("prevPeso", ""));
						}
					} else {
						// new currPeso value
						//System.out.println("new peso value to store");
						storeData("prevPeso", savedValues.getString("currPeso",""));
						prevExc.setText(savedValues.getString("currPeso",""));
						storeData("currPeso", currExc.getText().toString());
					}
				}
				break;
			case R.id.euro:
				if (savedValues.getString("currPeso","").equals("")){
					// no stored euro
					currExc.setText("0.77");
					prevExc.setText("0.00");
					storeData("currEuro", currExc.getText().toString());
					storeData("prevEuro", "0.00");
				} else {
					// found stored euro
					if (savedValues.getString("currEuro",  "").equals(currExc.getText().toString())){
						// same currEuro as what is stored
						currExc.setText(savedValues.getString("currEuro", ""));
						if (!savedValues.getString("prevEuro","").equals("")){
							prevExc.setText(savedValues.getString("prevEuro", ""));
						}
					} else {
						// new currEuro value
						storeData("prevEuro", savedValues.getString("currEuro",""));
						prevExc.setText(savedValues.getString("currEuro",""));
						storeData("currEuro", currExc.getText().toString());
					}
				}
				break;
			case R.id.yuan:
				if (savedValues.getString("currYuan","").equals("")){
					// no stored Yuan
					//System.out.println("no Yuan");
					currExc.setText("6.14");
					prevExc.setText("0.00");
					storeData("currYuan", currExc.getText().toString());
					storeData("prevYuan", "0.00");
				} else {
					// found stored Yuan
					if (savedValues.getString("currYuan",  "").equals(currExc.getText().toString())){
						// same currYuan as what is stored
						currExc.setText(savedValues.getString("currYuan", ""));
						if (!savedValues.getString("prevYuan","").equals("")){
							prevExc.setText(savedValues.getString("prevYuan", ""));
						}
					} else {
						// new currPeso value
						storeData("prevYuan", savedValues.getString("currYuan",""));
						prevExc.setText(savedValues.getString("currYuan",""));
						storeData("currYuan", currExc.getText().toString());
					}
				}
				break;
			case R.id.dollar:
				if (savedValues.getString("currDollar","").equals("")){
					// no stored dollar
					currExc.setText("1.11");
					prevExc.setText("0.00");
					storeData("currDollar", currExc.getText().toString());
					storeData("prevDollar", "0.00");
				} else {
					// found stored dollar
					if (savedValues.getString("currDollar",  "").equals(currExc.getText().toString())){
						// same currDollar as what is stored
						currExc.setText(savedValues.getString("currDollar", ""));
						if (!savedValues.getString("prevDollar","").equals("")){
							prevExc.setText(savedValues.getString("prevDollar", ""));
						}
					} else {
						// new currDollar value
						storeData("prevDollar", savedValues.getString("currDollar",""));
						prevExc.setText(savedValues.getString("currDollar",""));
						storeData("currDollar", currExc.getText().toString());
					}
				}
				break;
			case R.id.CONVERT:
				if (!currExc.toString().equals("")){
					float output = Float.valueOf(currExc.getText().toString()) * Float.valueOf(usdInputAmount.getText().toString());
					outputResult.setText(String.valueOf(String.format("%.2f", output)));
				}
				break;
			}
		}

	}

	public void storeData(String key, String value){
		Editor editor = savedValues.edit();
    	editor.putString(key, value);
    	editor.commit();
    	System.out.println("Data stored | key: " + key + " | value: " + value);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.curr_exc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
