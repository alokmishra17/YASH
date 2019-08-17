package com.volvain.yash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.volvain.yash.DAO.Database;

public class homeFragment extends Fragment {
    Button helpButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home,null);
        helpButton= v.findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
        return v;
    }
public void sendRequest(){
        if(Global.checkInternet()==0) {
            Database db= new Database(getContext());
            if(db.checkId()){
            startActivity(new Intent(this.getContext(),HelpSync.class));}
        else { Fragment fragment = new loginFragment();
    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.fragment_container, fragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
        Toast.makeText(this.getContext(),"Please Login First",Toast.LENGTH_LONG);}
        }
         else Toast.makeText(this.getContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
    }

}

