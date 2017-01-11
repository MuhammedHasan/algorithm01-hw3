import edu.princeton.cs.algs4.*;

import java.util.Comparator;
import java.util.Objects;

public class StockMarket {

    private In inputFile = new In("StockDealsInput.txt");
    private Out outputFile = new Out("TransitionOutput.txt");

    private MaxPQ<Person> buyers = new MaxPQ<>();
    private MinPQ<Person> sellers = new MinPQ<>();

    private void readFiles() {
        for (String s : inputFile.readAllLines()) {
            Person p = Person.fromString(s);
            if (p.type.equals("S")) sellers.insert(p);
            else buyers.insert(p);
        }
    }

    private void sell(Person buyer, Person seller) {
        int amount;
        if (buyer.amount > seller.amount) amount = seller.amount;
        else amount = buyer.amount;
        buyer.amount -= amount;
        seller.amount -= amount;

        this.outputFile.print("S");
        this.outputFile.print(seller.id);
        this.outputFile.print(" sells B");
        this.outputFile.print(buyer.id);
        this.outputFile.print(" in size of ");
        this.outputFile.println(amount);
    }

    private void run() {
        while (!(buyers.isEmpty() || sellers.isEmpty())) {
            Person buyer = this.buyers.max();
            Person seller = this.sellers.min();
            this.sell(buyer, seller);

            if (buyer.amount == 0) this.buyers.delMax();
            if (seller.amount == 0) this.sellers.delMin();
        }
    }

    public static void main(String[] args) {
        StockMarket market = new StockMarket();
        market.readFiles();
        market.run();
    }
}

class Person implements Comparable<Person> {
    int id;
    String type;
    int price;
    int amount;

    static Person fromString(String s) {
        String[] fields = s.split(" ");
        Person p = new Person();
        p.id = Integer.parseInt(fields[1]);
        p.type = fields[0];
        p.price = Integer.parseInt(fields[3]);
        p.amount = Integer.parseInt(fields[2]);
        return p;
    }

    @Override
    public int compareTo(Person person) {
        return this.price - person.price;
    }
}