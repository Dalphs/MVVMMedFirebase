package dk.acsandras.mvvmdemo.model;

public class Model implements Observable {

    // TODO (2) Konverter dine datafelter fra primitive variable som f.eks. double til LiveData
    // Det vil gøre, at denne data member vil sende nootifikationer til alle de klasser, som observerer
    // dette felt, og de klasser vil så kunne opdatere data automatiskt.
    // Se dette forklaret i https://developer.android.com/topic/libraries/architecture/livedata
    private String a = new String(); // Før var det "private String a;"
    private ObserverManager observerManager = new ObserverManager(this);

    // TODO (3) Lav getters og setters og evt. en constructor for din data
    public Model() {
    }

    public String getA() {
        if (a == null) {
            a = new String();
        }
        return a;
    }

    public void setA(String a) {
        this.a = a;
        observerManager.notifyObservers();
    }

    @Override
    public void observe(Observer observer) {
        observerManager.addObserver(observer);
    }

    @Override
    public void unobserve(Observer observer) {
        observerManager.deleteObserver(observer);
    }

}
