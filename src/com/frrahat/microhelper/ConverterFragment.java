package com.frrahat.microhelper;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ConverterFragment extends Fragment {
	
	TextView binTextView;
	TextView hexTextView;
	Button delButton;
	Button zeroButton;
	Button oneButton;
	
	String binString;
	
	final int MAX_DIGIT=16;
	final int HEX_MAP[]={8,4,2,1};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View converterView = inflater.inflate(R.layout.fragment_converter, container, false);		
		
		binTextView=(TextView) converterView.findViewById(R.id.textView_binary);
		hexTextView=(TextView) converterView.findViewById(R.id.textView_hex);
		
		binTextView.setTypeface(MicroHelperMainActivity.getDigitalTypeFace(0));
		//hexTextView.setTypeface(MicroHelperMainActivity.getDigitalTypeFace(1));
		
		binString="";
		
		delButton=(Button) converterView.findViewById(R.id.button_del);
		zeroButton=(Button) converterView.findViewById(R.id.button_zero);
		oneButton=(Button) converterView.findViewById(R.id.button_one);
		
		setButtonActions();
		return converterView;
	}
	
	private void updateTextViews(){
		String binDisplayString="";
		String hexDisplayString="";
		
		if(binString.length()>0){
			char k=binString.charAt(0);
			int hexVal=(k-48)*HEX_MAP[0];
			
			binDisplayString+=k;
			hexDisplayString="";
			
			for(int i=1;i<binString.length();i++){
				k=binString.charAt(i);
				if(i%4==0){
					binDisplayString+=" "+k;
					hexDisplayString+=getHexChar(hexVal);
					hexVal=(k-48)*HEX_MAP[0];
				}else{
					binDisplayString+=k;
					hexVal+=(k-48)*HEX_MAP[i%4];
				}
			}
			hexDisplayString+=getHexChar(hexVal);
		}
		
		int inCompleteBit=(4-binString.length()%4)%4;
		for(int i=0;i<inCompleteBit;i++){
			binDisplayString+="_";
		}
		
		binTextView.setText(binDisplayString);
		hexTextView.setText(hexDisplayString);
	}
	
	private void setButtonActions(){
		delButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(binString.length()!=0){
					binString=binString.substring(0,binString.length()-1);
					updateTextViews();
				}
			}
		});
		
		delButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				binString="";
				updateTextViews();
				return true;
			}
		});
		
		zeroButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(binString.length()<MAX_DIGIT){
					binString+="0";
					updateTextViews();
				}	
			}
		});
		
		oneButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(binString.length()<MAX_DIGIT){
					binString+="1";
					updateTextViews();
				}
			}
		});
	}
	
	/*
	 * value should be less than 16
	 */
	public static char getHexChar(int val){
		if(val<10){
			char c= (char) (val+48); 
			return c;
		}
		char c= (char) ('A'+val-10);
		return c;
	}
}
