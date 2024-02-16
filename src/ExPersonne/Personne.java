package ExPersonne;

import java.io.Serializable;

public class Personne implements Serializable {
    private int age;
    private String nom;

    public Personne(int age, String nom) {
        this.age = age;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "ExPersonne.Personne{" +
                "age=" + age +
                ", nom='" + nom + '\'' +
                '}';
    }
}
