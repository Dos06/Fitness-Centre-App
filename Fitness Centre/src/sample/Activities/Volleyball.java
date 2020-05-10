package sample.Activities;

public class Volleyball extends Activity {
    protected boolean kneePads;

    public Volleyball(boolean kneePads) {
        this.name = "Volleyball";
        this.kneePads = kneePads;
    }
    public Volleyball(int price, int period, boolean kneePads) {
        super(price, period);
        this.name = "Volleyball";
        this.kneePads = kneePads;
    }
    public Volleyball(String name, int price, int period, boolean kneePads) {
        super(name, price, period);
        this.name = "Volleyball";
        this.kneePads = kneePads;
    }

    public boolean getKneePads() {
        return kneePads;
    }
    public void setKneePads(boolean kneePads) {
        this.kneePads = kneePads;
    }

    @Override
    public String toString() {
        return "Volleyball{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", period=" + period +
                ", kneePads=" + kneePads +
                '}';
    }
}
