package com.frrahat.microhelper;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ValueSetterFragment extends Fragment {
	
	ArrayList<Spinner> spinnerList;
	ArrayList<ArrayAdapter<String>> arrayAdapterList;
	
	TextView binaryValueTextView;
	TextView hexValueTextView;
	final int HEX_MAP[]={8,4,2,1};
	
	String spinnerItemStrings[][]={
			{"I/0 Mode", "Memory Mode"},
			{"Mode 0 (Simple I/0)","Mode 1 (Sngl HndShk)","Mode 2 (Dbl HndShk)"},
			{"Input","Output","X"},
			{"Input","Output","X"},
			{"Mode 0","Mode 1","X"},
			{"Input","Output","X"},
			{"Input","Output","X"}};
	
	String spinnerItemValues[][]={
			{"1", "0"},
			{"00","01","1X"},
			{"1","0","X"},
			{"1","0","X"},
			{"0","1","X"},
			{"1","0","X"},
			{"1","0","X"}};
	
	int valueIndices[];
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View valueSetterView = inflater.inflate(R.layout.fragment_value_setter, container, false);
		
		binaryValueTextView=(TextView) valueSetterView.findViewById(R.id.textView_binaryValueBits);
		hexValueTextView=(TextView) valueSetterView.findViewById(R.id.textView_hexValueString);
		
		spinnerList = new ArrayList<>();
		//IOmodeSpinner
		spinnerList.add((Spinner) valueSetterView.findViewById(R.id.spinner1));
		
		//GrpAmodeSpinner
		spinnerList.add((Spinner) valueSetterView.findViewById(R.id.spinner2));
		
		//GrpAportASpinner
		spinnerList.add((Spinner) valueSetterView.findViewById(R.id.spinner3));
		
		//GrpAportCuSpinner
		spinnerList.add((Spinner) valueSetterView.findViewById(R.id.spinner4));
		
		//GrpBmodeSpinner
		spinnerList.add((Spinner) valueSetterView.findViewById(R.id.spinner5));
		
		//GrpBportBSpinner
		spinnerList.add((Spinner) valueSetterView.findViewById(R.id.spinner6));
		
		//GrpBportClSpinner
		spinnerList.add((Spinner) valueSetterView.findViewById(R.id.spinner7));
		
		setSpinnerItems();
		
		
		//set initial value string
		valueIndices=new int[spinnerItemStrings.length];
		for(int i=0;i<spinnerItemValues.length;i++){
			valueIndices[i]=0;
		}
		
		updateTextViews();
		
		return valueSetterView;
	}

	private void setSpinnerItems() {
		
		ArrayAdapter<String>arrayAdapter0=new ArrayAdapter<String>(getActivity().getBaseContext(),
				android.R.layout.simple_spinner_item, spinnerItemStrings[0]);
		
		ArrayAdapter<String>arrayAdapter1=new ArrayAdapter<String>(getActivity().getBaseContext(),
				android.R.layout.simple_spinner_item, spinnerItemStrings[1]);
		
		ArrayAdapter<String>arrayAdapter2=new ArrayAdapter<String>(getActivity().getBaseContext(),
				android.R.layout.simple_spinner_item, spinnerItemStrings[2]);
		
		ArrayAdapter<String>arrayAdapter3=new ArrayAdapter<String>(getActivity().getBaseContext(),
				android.R.layout.simple_spinner_item, spinnerItemStrings[3]);
		
		ArrayAdapter<String>arrayAdapter4=new ArrayAdapter<String>(getActivity().getBaseContext(),
				android.R.layout.simple_spinner_item, spinnerItemStrings[4]);
		
		ArrayAdapter<String>arrayAdapter5=new ArrayAdapter<String>(getActivity().getBaseContext(),
				android.R.layout.simple_spinner_item, spinnerItemStrings[5]);
		
		ArrayAdapter<String>arrayAdapter6=new ArrayAdapter<String>(getActivity().getBaseContext(),
				android.R.layout.simple_spinner_item, spinnerItemStrings[6]);
		
		
		arrayAdapterList=new ArrayList<>(7);
		arrayAdapterList.add(arrayAdapter0);
		arrayAdapterList.add(arrayAdapter1);
		arrayAdapterList.add(arrayAdapter2);
		arrayAdapterList.add(arrayAdapter3);
		arrayAdapterList.add(arrayAdapter4);
		arrayAdapterList.add(arrayAdapter5);
		arrayAdapterList.add(arrayAdapter6);
		
		
		for(int i=0;i<spinnerList.size();i++){
			final int index[]=new int[2];
			index[0]=i;
			arrayAdapterList.get(i).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerList.get(i).setAdapter(arrayAdapterList.get(i));
			spinnerList.get(i).setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					valueIndices[index[0]]=position;
					updateTextViews();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					//do nothing
				}
			});
		}

	}
	
	private void updateTextViews(){
		String displayBinBitString="";
		for(int i=0;i<valueIndices.length;i++){
			displayBinBitString+=spinnerItemValues[i][valueIndices[i]]+" ";
		}
		
		binaryValueTextView.setText(displayBinBitString);
		String bitString=displayBinBitString.replace("X", "0").replace(" ", "");
		String hexString="";
		int hexValue=(bitString.charAt(0)-48)*HEX_MAP[0];
		for(int i=1;i<bitString.length();i++){
			int k=bitString.charAt(i)-48;
			if(i%4==0){
				hexString+=Integer.toHexString(hexValue);
				hexValue=k*HEX_MAP[i%4];
			}else{
				hexValue+=k*HEX_MAP[i%4];
			}
		}
		hexString+=Integer.toHexString(hexValue);
		
		hexValueTextView.setText(hexString.toUpperCase(Locale.getDefault()));
	}
}
