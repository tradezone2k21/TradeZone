package com.example.everythingstore.seller.register;

import android.content.ContextWrapper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.everythingstore.R;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Register1SellerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Register1SellerFragment extends Fragment {
    EditText phoneEditText;
    EditText nameEditText;
    String phoneNo,nameSeller;
    @Override
    public void onPause() {
        super.onPause();

         phoneNo=phoneEditText.getText().toString();
         nameSeller=nameEditText.getText().toString();
        //when ever the user swipes right to the address details fragment these name and phone will be stored in sharedprefs for access from that fragment.
        Prefs.putString(getString(R.string.PrefsSellerNameRegister),nameSeller);
        Prefs.putString(getString(R.string.PrefsSellerPhoneRegister),phoneNo);
//        Toast.makeText(getActivity(), "data sent to Sharedprefs", Toast.LENGTH_SHORT).show();
    }



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Register1SellerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Register1SellerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Register1SellerFragment newInstance(String param1, String param2) {
        Register1SellerFragment fragment = new Register1SellerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        phoneEditText=getView().findViewById(R.id.phone_sell_register);
//        nameEditText=getView().findViewById(R.id.name_sell_register);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        new Prefs.Builder()
                .setContext(this.getContext())
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getActivity().getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment and atach variables if any.
        View view=inflater.inflate(R.layout.fragment_register1_seller, container, false);
        phoneEditText=view.findViewById(R.id.phone_sell_register);
        nameEditText=view.findViewById(R.id.name_sell_register);
        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().matches("^[9]{1}[1]{1}[0-9]{10}$")){
                    phoneEditText.setError(getString(R.string.Check_phone_number));
                }
            }
        });
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().matches("^[a-zA-Z][a-zA-Z ]*$")){
                    nameEditText.setError(getString(R.string.Check_name));
                }
            }
        });
        return view;
    }
}