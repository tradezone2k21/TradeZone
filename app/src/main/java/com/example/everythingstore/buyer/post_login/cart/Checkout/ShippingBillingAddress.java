package com.example.everythingstore.buyer.post_login.cart.Checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.everythingstore.R;

public class ShippingBillingAddress extends AppCompatActivity {
CheckBox same_address_checkbox;
Button continue_next;
EditText shippingName,shippingPhone,shippingPincode,shippingHouse,shippingColony,shippingCity,shippingState,
         billingName,billingPhone,billingPincode,billingHouse,billingColony,billingCity,billingState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_billing_address);
        same_address_checkbox=findViewById(R.id.address_same_checkbox);
        continue_next=findViewById(R.id.shipping_billing_continue_button);
        shippingName=findViewById(R.id.shipping_name);
        shippingPhone=findViewById(R.id.shipping_phone_no);
        shippingPincode=findViewById(R.id.shipping_pincode);
        shippingHouse=findViewById(R.id.shipping_house_no);
        shippingColony=findViewById(R.id.shipping_colony);
        shippingCity=findViewById(R.id.shipping_city);
        shippingState=findViewById(R.id.shipping_state);

        billingName=findViewById(R.id.billing_name);
        billingPhone=findViewById(R.id.billing_phone_no);
        billingPincode=findViewById(R.id.billing_pincode);
        billingHouse=findViewById(R.id.billing_house_no);
        billingColony=findViewById(R.id.billing_colony);
        billingCity=findViewById(R.id.billing_city);
        billingState=findViewById(R.id.billing_state);


        same_address_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    copy_shipping_address_to_billing_address();
                else{
                    billingName.setText("");
                    billingPhone.setText("");
                    billingPincode.setText("");
                    billingHouse.setText("");
                    billingColony.setText("");
                    billingCity.setText("");
                    billingState.setText("");
                }
            }
        });

        copy_shipping_address_to_billing_address();
    }

    private void copy_shipping_address_to_billing_address() {
        billingName.setText(shippingName.getText());
        billingPhone.setText(shippingPhone.getText());
        billingPincode.setText(shippingPincode.getText());
        billingHouse.setText(shippingHouse.getText());
        billingColony.setText(shippingColony.getText());
        billingCity.setText(shippingCity.getText());
        billingState.setText(shippingState.getText());
    }


}