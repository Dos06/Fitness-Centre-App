package sample.Activities;

public class SwimmingPool extends Activity {
    protected boolean fins;
    protected boolean paddles;

    public SwimmingPool(boolean fins, boolean paddles) {
        this.name = "SwimmingPool";
        this.fins = fins;
        this.paddles = paddles;
    }
    public SwimmingPool(int price, int period, boolean fins, boolean paddles) {
        super(price, period);
        this.name = "SwimmingPool";
        this.fins = fins;
        this.paddles = paddles;
    }
    public SwimmingPool(String name, int price, int period, boolean fins, boolean paddles) {
        super(name, price, period);
        this.name = "SwimmingPool";
        this.fins = fins;
        this.paddles = paddles;
    }

    public boolean getFins() {
        return fins;
    }
    public void setFins(boolean fins) {
        this.fins = fins;
    }
    public boolean getPaddles() {
        return paddles;
    }
    public void setPaddles(boolean paddles) {
        this.paddles = paddles;
    }

    @Override
    public String toString() {
        return "SwimmingPool{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", period=" + period +
                ", fins=" + fins +
                ", paddles=" + paddles +
                '}';
    }
}
