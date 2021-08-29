package com.example.everythingstore.seller.register;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.everythingstore.seller.register.Register1SellerFragment;
import com.example.everythingstore.seller.register.Register2SellerFragment;

import org.jetbrains.annotations.NotNull;

public class RegisterSellerFragmentAdapter extends FragmentStateAdapter {
    public RegisterSellerFragmentAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new Register2SellerFragment();

        }

        return new Register1SellerFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
