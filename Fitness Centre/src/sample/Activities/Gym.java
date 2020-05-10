package sample.Activities;

public class Gym extends Activity {
    protected boolean grips;

    public Gym(boolean grips) {
        this.name = "Gym";
        this.grips = grips;
    }
    public Gym(int price, int period, boolean grips) {
        super(price, period);
        this.name = "Gym";
        this.grips = grips;
    }
    public Gym(String name, int price, int period, boolean grips) {
        super(name, price, period);
        this.name = "Gym";
        this.grips = grips;
    }

    public boolean getGrips() {
        return grips;
    }
    public void setGrips(boolean grips) {
        this.grips = grips;
    }

    @Override
    public String toString() {
        return "Gym{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", period=" + period +
                ", grips=" + grips +
                '}';
    }
}
