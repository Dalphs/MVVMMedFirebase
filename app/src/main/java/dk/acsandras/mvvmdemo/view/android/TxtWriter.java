package dk.acsandras.mvvmdemo.view.android;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import dk.acsandras.mvvmdemo.view.ViewModel;

public class TxtWriter {
    ViewModel viewModel;

    public TxtWriter(ViewModel viewModel) {
        this.viewModel = viewModel;

        final Observer<String> stringObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String a) {
                // Update the UI, in this case, a TextView.
                System.out.println("ændret");
            }
        };

        // TODO (12) Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        // Delegate assignment of observer to ViewModel
        //viewModel.observeA(this, stringObserver);
    }

    public void writeToFile(String a){

    }
}
