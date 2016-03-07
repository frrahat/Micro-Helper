package com.frrahat.microhelper;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class BasicInfosFragment extends Fragment {
	private static EditText basicEditText;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View basicView=inflater.inflate(R.layout.fragment_basic_infos, container, false);
		
		basicEditText=(EditText) basicView.findViewById(R.id.editText_basics);
		basicEditText.setFocusable(false);
		updateTextSize(getActivity().getBaseContext());
		setBasicTexts();
		return basicView;
	}
	
	private void setBasicTexts(){
		String basicTexts=
				"(1)**Prepare The Ports**"
				+ "\n"
				+ "\n#Work_out_Experiment_specific_control_bit_sequence"
				+ "\n#Move_this_seq_to_AL_converting_it_to_HEX"
				+ "\n\nMOV AL, <convert control bit seqs to HEX and put here>;"
				+ "\n\n#Send_this_sequence_to_the_control_word_register_of_8255_to_prepare_it"
				+ "\n#00010011b_is_the_address_of_the_control_word_register_of_8255"
				+ "\n\nOUT 19H,   AL;"
				+ "\n"
				+ "\n\n(2)**Need To Remember**"
				+ "\n"
				+ "\n#Address_of_port_A=00010000b=16H"
				+ "\n#Address_of_port_B=00010001b=17H"
				+ "\n#Address_of_port_C=00010010b=18H"
				+ "\n#Address_of_control=00010011b=19H"
				+ "\n"
				+ "\n"
				+ "\n(3)**Take Input From Port A, Output To Port B In Simple I/0 Mode : **"
				+ "\n"
				+ "\n#Set_port_A_input_and_port_B_output_(and_c_lower_to_input)"
				+ "\n\nMOV AL, 99H;"
				+ "\nOUT 19H, AL;"
				+ "\n"
				+ "\n#Transfer"
				+ "\nIN AL, 16H;"
				+ "\nOUT 17H, AL;"
				+ "\n"
				+ "\n"
				+ "\n4)**Working with the Keypad**"
				+ "\n___________________________"
				+ workingWithKeyPad
				+ "\n"
				+ "\n"
				+ "\n";
		
		basicEditText.setText(basicTexts);
	}
	
	String workingWithKeyPad=
			 "\n(source : MTS-88.C manual for Course 316 provided by BUET)"
			 + "\n\n\n"
			 + "• A - assemble: this is used to write our code. "
			 + "We have to press A and provide a starting address for our code. "
			 + "After we hit return we can start writing our code. "
			 + "As user space in RAM starts from 0400H, most users generally provide this as the starting address. "
			 + "In general as long as our full code resides within adresses 0400H and 7FFFH we should be okay. "
			 + "\n\n\n"
			 + "• G - go: once we are done writing our code we can go back to the menu by pressing F7. "
			 + "If we press G, provide the address from where code execution should begin, and hit return; "
			 + "execution should begin. "
			 + "\n\n\n"
			 + "• U - unassemble: the first time we run our code we might not get the expected result. "
			 + "In that case we should modify our code. "
			 + "Our first step would be to stop execution of the program by pressing F3. "
			 + "Now we can go back to the menu screen by pressing F7. "
			 + "If we press U, provide the address of the first line of code to be modified, and hit enter; "
			 + "we will be able to edit the line and any number of lines following it. "
			 + "We can use an up / down button situated at the lower right part of the keypad to browse "
			 + "through the lines of code. "
			 + "To start / stop editing we should press F6. "
			 + "One point to take care of is that we should hit return after we have edited our line of code. "
			 + "It might seem that the following line just vanished, but it’s actually there. "
			 + "If the edited line happens to affect the code size, we might lose the following lines of code."
			 + "\n\n\n"
			 + "• D - dump: sometimes we might need to store certain patterns in our memory. "
			 + "If we press D, provide the starting address to dump values, and hit return we can start storing values. "
			 + "\n\n\n"
			 + "• SHIFT+B: moves the cursor left while writing / editing code. "
			 + "\n\n"
			 + "• SHIFT+N: moves the cursor right while writing / editing code. "
			 + "\n\n"
			 + "• F3: stops execution. "
			 + "\n\n"
			 + "• SHIFT+F5: deletes character at cursor. "
			 + "\n\n"
			 + "• F5: leaves space at cursor. "
			 + "\n\n"
			 + "• SHIFT+F6: deletes line. "
			 + "\n\n"
			 + "• F6: starts / leaves editing mode. "
			 + "\n\n"
			 + "• F7: brings up menu screen."
			 + "\n";
	
	public static void updateTextSize(Context context){
		if(basicEditText==null)
			return;
		SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
		float FONT_SIZE=Float.parseFloat(sharedPreferences.getString(
				context.getString(R.string.key_fontSize), "17"));
		basicEditText.setTextSize(FONT_SIZE);
	}
}
