package com.app.mticalc;

import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Cost extends Activity implements OnClickListener {


    private String treevolume;
    private String treedbh;
    private String treeheight;
    private String treebasal;
    private String treespecies;
    private Double finalprice;
    private int myprice;
   
	//private EditText editTextMainScreen;
	final Context context = this;
	private DecimalFormat format;

	
private EditText edit_treeheight,edit_tree_dbh,edit_treevolume,edit_tree_basal, edit_price,edit_species,final_price;
    Button calculte_button,btn;
    @SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        final View v = getLayoutInflater().inflate(R.layout.activity_cost, null);
        v.setKeepScreenOn(true);
        setContentView(v);
       
        format=new DecimalFormat("#0.00");
        
        
       
	
        
        
        // Show the Up button in the action bar.
        setUpElements();
        disable();
        treespecies=this.getIntent().getStringExtra("treespecies");
        treedbh=this.getIntent().getStringExtra("treedbh" );
        treeheight=this.getIntent().getStringExtra("treeheight");
        treebasal=this.getIntent().getStringExtra("treebasal");
        treevolume=this.getIntent().getStringExtra("treevolume");
        myprice=this.getIntent().getIntExtra("myprice", 0); //getting from spinnerclass
        finalprice=this.getIntent().getDoubleExtra("finalprice", 0);
        edit_species.setText(String.valueOf(treespecies));
        edit_treeheight.setText(String.valueOf( treeheight));
        edit_tree_dbh.setText( String.valueOf(treedbh));
        edit_treevolume.setText(String.valueOf( treevolume));
        edit_tree_basal.setText(String.valueOf( treebasal));
        final_price.setText(String.valueOf("KShs. " +format.format(finalprice)));
        edit_price.setText(String.valueOf(myprice)); // to obtain the value automatically
        calculte_button.setOnClickListener(this);
        btn.setOnClickListener(this);
       
        
       
    


    }
    public void disable(){
    	edit_species.setEnabled(false);
        edit_treeheight.setEnabled(false);
        edit_tree_dbh.setEnabled(false);
        edit_treevolume.setEnabled(false);
        edit_tree_basal.setEnabled(false);
        final_price.setEnabled(false);

    }
      public void setUpElements(){
    	  edit_species=(EditText)findViewById(R.id.edit_species);
          edit_treeheight=(EditText)findViewById(R.id.Edit_Height);
          edit_tree_dbh=(EditText)findViewById(R.id.edit_dbh);
          edit_tree_basal=(EditText)findViewById(R.id.Edit_basal);
          edit_treevolume=(EditText)findViewById(R.id.Edit_volume);
          calculte_button=(Button)findViewById(R.id.calc);
          btn = (Button) findViewById(R.id.btn_price);
          edit_price=(EditText)findViewById(R.id.prices);
          final_price=(EditText)findViewById(R.id.final_price);
          
          

      }
	

    @Override
    public void onClick(View view) {
    	
    	
    

switch (view.getId()){
    case R.id.btn_price:
    	//startActivity(new Intent(this, Prices.class));

    	

		btn.setOnClickListener(new OnClickListener() {

			//@Override
			@Override
			public void onClick(View view) {
				

				// get prompts.xml view
				
				LayoutInflater layoutInflater = LayoutInflater.from(context);
				
				
				
				View promptView = layoutInflater.inflate(R.layout.price, null);			;

			

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

				// set prompts.xml to be the layout file of the alertdialog builder
				alertDialogBuilder.setView(promptView);

				

				// setup a dialog window
				alertDialogBuilder
						.setCancelable(false)
						.setPositiveButton("Back", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int id) {
										// get user input and set it to result
										dialog.cancel();
									}
								/*})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,	int id) {
										dialog.cancel();
									}*/
								});

				// create an alert dialog
				AlertDialog alertD = alertDialogBuilder.create();

				alertD.show();

			}
		});
	
       break;
    case R.id.calc:  
    	final_price = (EditText) findViewById(R.id.final_price);
    	
    	EditText edit_price = (EditText) findViewById(R.id.prices);
    	if(!edit_price.getText().toString().equals("")){
    	 //if(!(editTextA.equals(""))){
    		int price = Integer.parseInt(edit_price.getText().toString());
		 final 	double cost;
	    	double vol;
	    	vol=Double.parseDouble(treevolume);
	    	cost=price*vol;
    	
    	 AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle("Price Estimate");
         builder.setMessage("KShs. " +format.format(cost));
                 // Set the action buttons
                 builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int id) {
                    	 final_price.setText("KShs. " + format.format(cost)); //  Your code when user clicked on OK
                         //  You can write the code  to save the selected item here
                         // Therefore store data in db as well as clear all fields

                     }
                 });

         AlertDialog dialog = builder.create();//AlertDialog dialog; create like this outside onClick
         dialog.show();
    	}else{
    		 AlertDialog.Builder builder = new AlertDialog.Builder(this);
             //builder.setTitle("Error!");
             builder.setMessage("Enter Price per volume!" );
                     // Set the action buttons
                     builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int id) {
                             //  Your code when user clicked on OK
                             //  You can write the code  to save the selected item here
                             // Therefore store data in db as well as clear all fields

                         }
                     });

             AlertDialog dialog = builder.create();//AlertDialog dialog; create like this outside onClick
             dialog.show();
    		 
}
    	break;
}
    	}
    
    

   
@Override
public void onResume()
{
	super.onResume();
	

}
@Override
protected void onPause() {
super.onPause();
//finish();

}
}