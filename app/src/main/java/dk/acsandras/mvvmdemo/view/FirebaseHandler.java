package dk.acsandras.mvvmdemo.view;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dk.acsandras.mvvmdemo.model.Observable;
import dk.acsandras.mvvmdemo.model.Observer;

public class FirebaseHandler implements Observer {
    DatabaseReference myRef;
    ViewModel viewModel;

    public FirebaseHandler(final ViewModel viewModel) {
        this.viewModel = viewModel;
        myRef = FirebaseDatabase.getInstance().getReference("string");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                if(!value.equals("")) {
                    if (!value.equals(viewModel.getA().getValue())) {
                        viewModel.setA(value);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        /*myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        myRef.addValueEventListener(valueEventListener);
        viewModel.addObserverForDatabase(this);
    }

    @Override
    public void update(Observable observable) {
        myRef.setValue(viewModel.getA().getValue());
    }

}
