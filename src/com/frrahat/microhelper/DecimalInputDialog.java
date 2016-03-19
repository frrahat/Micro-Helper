package com.frrahat.microhelper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class DecimalInputDialog extends DialogFragment {
	
	private EditText decimalInputEditText;
	private DecimalInputListener inputListener;
	private static String hintText;
	
	public DecimalInputDialog() {
		DecimalInputDialog.hintText="";
	}
	
	@SuppressLint("InflateParams")
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View pickerView=inflater.inflate(R.layout.dialog_decimal_input_picker, null);
        decimalInputEditText=(EditText) pickerView.findViewById(R.id.decimalInputEditText);
        decimalInputEditText.setHint(hintText);
        //refreshing hintText for next entry
        hintText="";
        
        builder.setView(pickerView)
        // Add action buttons
               .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                	   if(inputListener!=null)
                	   inputListener.inputGiven(decimalInputEditText.getText().toString());
                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       DecimalInputDialog.this.getDialog().cancel();
                   }
               });   
              
        return builder.create();
    }
	
	public static interface DecimalInputListener{
		public void inputGiven(String inputString);
	}
	
	
	public void setInputListener(DecimalInputListener listener){
		this.inputListener=listener;
	}
	
	public void setHintText(String text){
		DecimalInputDialog.hintText=text;
	}
}

