package dk.acsandras.mvvmdemo.view;


import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import dk.acsandras.mvvmdemo.model.Model;
import dk.acsandras.mvvmdemo.model.Observable;

public class ViewModel extends androidx.lifecycle.ViewModel implements dk.acsandras.mvvmdemo.model.Observer {

    // TODO (4) ViewModel skal også bruge LiveData, som de forskellige activities kan observere
    // ViewModel er også løst koblet på modellen, dvs. modellen har ikke kendskab til ViewModel
    private MutableLiveData<String> currentA = new MutableLiveData<>();
    private Model model = new Model();

    public ViewModel() {
        model.observe(this);
        currentA.setValue("");
        // TODO (5) Det her illustrerer, at vi får data fra modellen
        // Giver "Startdata fra Model"
        // currentA.setValue("Startdata fra ViewModel");
    }

    public MutableLiveData<String> getA() {
        // TODO (6) Hent data fra modellen
        return currentA;
    }

    public void setA(String a) {
        // TODO (7) Skriv data til modellen
        model.setA(a);
    }

    public void observeA(LifecycleOwner lifeCycleOwner, Observer<String> stringObserver) {
        currentA.observe(lifeCycleOwner, stringObserver);
    }

    @Override
    public void update(Observable observable) {
        //if (arg == model)
        currentA.setValue(model.getA());
    }

    public void addObserverForDatabase(dk.acsandras.mvvmdemo.model.Observer observer){
        model.observe(observer);
    }
}
