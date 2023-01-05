package main;

import domaine.Trader;
import domaine.Transaction;

import java.util.Arrays;
import java.util.List;

public class ExercicesOptional {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

        ExercicesOptional main = new ExercicesOptional(transactions);
        main.run();
    }

    /**
     * La liste de base de toutes les transactions.
     */
    private List<Transaction> transactions;

    /**
     * Crée un objet comprenant toutes les transactions afin de faciliter leur usage pour chaque point de l'énoncé
     *
     * @param transactions la liste des transactions
     */
    public ExercicesOptional(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Exécute chaque point de l'énoncé
     */
    public void run() {
        this.optional1();
        this.optional2();

    }

    private void optional1() {
        System.out.println("reduce1");
        var s  = transactions.stream().map(Transaction::getValue).reduce(Integer::max).orElse(Integer.MIN_VALUE);
        System.out.println(s);
    }

    private void optional2() {
        System.out.println("reduce2");
        var s = transactions.stream().reduce((a, b) -> {
            if (a.getValue() < b.getValue())
                return a;
            return b;
        });

        if (s.isPresent())
            System.out.println(s.get());
        else
            System.out.println("No transactions found");
    }

}