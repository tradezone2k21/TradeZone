package com.example.everythingstore.buyer.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.everythingstore.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Register2BuyerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register2BuyerFragment extends Fragment {
    EditText addressText,cityText,pincodeText,stateText;
    Button signUpButton;
    TextView error_box;
    String address,city,pincode,state,name,phone;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    boolean flag;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Register2BuyerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Register2BuyerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Register2BuyerFragment newInstance(String param1, String param2) {
        Register2BuyerFragment fragment = new Register2BuyerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_register2_buyer, container, false);
        addressText=view.findViewById(R.id.add_buy_register);
        cityText=view.findViewById(R.id.city_buy_register);
        pincodeText=view.findViewById(R.id.pincode_buy_register);
        stateText=view.findViewById(R.id.state_buy_register);
        signUpButton=view.findViewById(R.id.signup_buyer_register);
        error_box=view.findViewById(R.id.error_box_buy_register);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                address=addressText.getText().toString().trim();
                city=cityText.getText().toString().trim();
                pincode=pincodeText.getText().toString().trim();
                state=stateText.getText().toString().trim();
                //retrieving the seller name and phone from the prev fragment after swiping
                name= Prefs.getString(getString(R.string.PrefsBuyerNameRegister),"ERROR Name").trim();
                phone=Prefs.getString(getString(R.string.PrefsBuyerPhoneRegister),"ERROR Phone").trim();

                BuyerSignUpModel buyerSignUpModel=new BuyerSignUpModel(name,phone,address,city,pincode,state);
//                Log.d("testFragmentOnclick", "onClick: "+name+" "+phone+" "+address+" "+city+" "+pincode+" "+state);



                if(areDetailsValid()  && !userExisting()){
                    Toast.makeText(getContext(), "Registration Done!", Toast.LENGTH_SHORT).show();
                    rootNode=FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app");
                    reference=rootNode.getReference("Buyers");
                    reference.child(phone).setValue(buyerSignUpModel);


                    //this is to goto sign in page after registering
                    getActivity().finish();

                }
                else{
                    if(areDetailsValid())
                        Toast.makeText(getContext(),"User already existing!",Toast.LENGTH_SHORT).show();
                }


            }
        });

        return view;
    }

    private boolean userExisting(){

        DatabaseReference reference=FirebaseDatabase.getInstance("https://everything-store-2137d-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Buyers");
        Query checkUser=reference.orderByChild("phone").equalTo(phone);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("Firebase", "checking if user exists> "+"FDB test usernameExist?="+snapshot.child("pincode")+" "+snapshot.toString());
                if(snapshot.exists()){

                    flag=true;
                }
                else
                    flag=false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return flag;
    }

    private boolean areDetailsValid() {
        boolean allValid=true;
        if(!pincode.matches("^[1-9]{1}[0-9]{2}[0-9]{3}$")){
            pincodeText.setError(getString(R.string.Enter_valid_pincode));
            allValid=false;
        }
        if(!phone.matches("^[9]{1}[1]{1}[0-9]{10}$")){
            error_box.setText(getString(R.string.Check_phone_number));
            allValid=false;
        }
        else error_box.setText("");
        if(!name.toString().matches("^[a-zA-Z][a-zA-Z ]*$")) {
            error_box.setText(getString(R.string.Check_name));
        }else error_box.setText("");

        if(address.length()<5){
            addressText.setError(getString(R.string.Check_Address));
            allValid=false;
        }

        if(!state.matches("^[A-Za-z ]{3,}$")){
            stateText.setError(getString(R.string.Check_State));
            allValid=false;
        }

        if(!city.matches("^[A-Za-z]{3,}$")){
            cityText.setError(getString(R.string.Check_city));
            allValid=false;
        }
        return allValid;
    }
}