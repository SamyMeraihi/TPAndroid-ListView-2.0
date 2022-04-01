package com.example.tpandroidlistview.model;

import java.io.Serializable;
import java.util.Objects;


// On serialise l'objet Person, ça veux dire qu'il est converti en chaine pour être envoyé à l'autre Activity, qui va le déserialiser en objet.
public class Person implements Serializable {

    // Attributes
    private int id;
    private String nom, prenom;

    // Constructors
    public Person(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }

    // Setters
    public void setId(int ID) {
        this.id = ID;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getNom(), person.getNom()) && Objects.equals(getPrenom(), person.getPrenom());
    }
}
