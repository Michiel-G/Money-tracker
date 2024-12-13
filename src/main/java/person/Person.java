package person;

import java.util.HashMap;
import java.util.Map;

public class Person {
    private final String name;
    private int walletAmount;
    private Map<Person, Integer> debts;

    public Person(String name) {
        this.name = name;
        this.debts = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<Person, Integer> getDebts() {
        return debts;
    }

    public void removeDebts(){
        this.debts = new HashMap<>();
    }
    public void addDebt(Person person, int amount){
        if (!person.equals(this)){
            if(debts.containsKey(person)){
                debts.replace(person, debts.get(person)+amount);
            } else{
                debts.put(person, amount);
                System.out.println("added debt to "+this.getName()+" for "+person.getName()+" of price "+amount);
            }
        }

    }

    public int getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(int walletAmount) {
        this.walletAmount = walletAmount;
    }

}
