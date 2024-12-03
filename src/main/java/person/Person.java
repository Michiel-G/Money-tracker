package person;

public class Person {
    private final String name;
    private int walletAmount;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(int walletAmount) {
        this.walletAmount = walletAmount;
    }
}
