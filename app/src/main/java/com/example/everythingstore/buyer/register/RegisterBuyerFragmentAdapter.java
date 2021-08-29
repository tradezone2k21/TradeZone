package com.example.everythingstore.buyer.register;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.everythingstore.buyer.register.Register1BuyerFragment;
import com.example.everythingstore.buyer.register.Register2BuyerFragment;

public class RegisterBuyerFragmentAdapter extends FragmentStateAdapter {
    public RegisterBuyerFragmentAdapter(@NonNull @org.jetbrains.annotations.NotNull FragmentManager fragmentManager, @NonNull @org.jetbrains.annotations.NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new Register2BuyerFragment();

        }

        return new Register1BuyerFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
