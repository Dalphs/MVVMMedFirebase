package dk.acsandras.mvvmdemo.view.android;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dk.acsandras.mvvmdemo.R;
import dk.acsandras.mvvmdemo.view.FirebaseHandler;
import dk.acsandras.mvvmdemo.view.ViewModel;

public class AndroidView extends AppCompatActivity {

    private ViewModel viewModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        FirebaseHandler firebaseHandler = new FirebaseHandler(viewModel);

        // TODO (8) Inflater en textView og en editText, som kommer til at holde vores data
        textView = findViewById(R.id.textViewA);
        final EditText editText = findViewById(R.id.editTextA);

        // TODO (9) Vi tilføjer en listener, som reagerer på hver gang tekstfeltet ændres
        // Jeg har hentet denne snippet fra nettet
        // https://www.google.com/search?q=android+onchange+listener
        // Tak for tippet, Sainsh!
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s != null)
                    viewModel.setA(s.toString());
            }
        });

        // TODO (10) Observing the View Model


        // TODO (11) Create the observer which updates the UI.
        final Observer<String> stringObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String a) {
                // Update the UI, in this case, a TextView.
                textView.setText(a);
                if (!editText.getText().toString().equals(a))
                    editText.setText(a);
            }
        };

        // TODO (12) Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        // Delegate assignment of observer to ViewModel
        viewModel.observeA(this, stringObserver);//getA().observe(this, stringObserver);
    }

    // Det her er bare menuen i app'en, bruges ikke pt.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Det her er bare menuen i app'en, bruges ikke pt.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
