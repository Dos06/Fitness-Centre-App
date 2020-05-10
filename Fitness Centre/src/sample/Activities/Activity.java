package sample.Activities;

public abstract class Activity {
    protected String name;
    protected int price;
    protected int period;

    public Activity() {}
    public Activity(int price, int period) {
        this.price = price;
        this.period = period;
    }
    public Activity(String name, int price, int period) {
        this.name = name;
        this.price = price;
        this.period = period;
    }
}
