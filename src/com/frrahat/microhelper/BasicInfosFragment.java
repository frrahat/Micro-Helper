package com.frrahat.microhelper;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class BasicInfosFragment extends Fragment {
	EditText basicEditText;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View basicView=inflater.inflate(R.layout.fragment_basic_infos, container, false);
		
		basicEditText=(EditText) basicView.findViewById(R.id.editText_basics);
		basicEditText.setFocusable(false);
		setBasicTexts();
		return basicView;
	}
	
	private void setBasicTexts(){
		String basicTexts=
				"1)Prepare_the_ports:"
				+ "\n"
				+ "\n#Work_out_Experiment_specific_control_bit_sequence"
				+ "\n#Move_this_seq_to_AL_converting_it_to_binary"
				+ "\n\nMOV AL, <convert control bit seqs to HEX and put here>;"
				+ "\n\n#Send_this_srquence_to_the_control_of_8255_to_prepare_it"
				+ "\n#00010011b_is_the_address_of_the_control_of_8255"
				+ "\n\nOUT 19H,   AL;"
				+ "\n"
				+ "\n\n2)Need To Remember:"
				+ "\n"
				+ "\n#Address_of_port_A=00010000b=16H"
				+ "\n#Address_of_port_B=00010001b=17H"
				+ "\n#Address_of_port_C=00010010b=18H"
				+ "\n#Address_of_control=00010011b=19H"
				+ "\n"
				+ "\n"
				+ "\n3)Take Input From Port A, Output To Port B In Simple I/0 Mode :"
				+ "\n"
				+ "\n#Set_port_A_input_and_port_B_output_(and_c_lower_to_input)"
				+ "\n\nMOV AL, 99H;"
				+ "\nOUT 19H, AL;"
				+ "\n"
				+ "\n#Transfer"
				+ "\nIN AL, 16H;"
				+ "\nOUT 17H, AL;";
		
		basicEditText.setText(basicTexts);
	}
}
