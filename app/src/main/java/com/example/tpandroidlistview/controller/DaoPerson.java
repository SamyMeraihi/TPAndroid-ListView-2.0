package com.example.tpandroidlistview.controller;

import com.example.tpandroidlistview.model.Person;

import java.util.ArrayList;

public class DaoPerson {

    // On définit une ArrayList pour stocker les personnes
    static ArrayList<Person> Personslist = new ArrayList<>();
    private static int idCounter = 0;

    public static void addPerson(Person person) {
        person.setId(++idCounter);
        Personslist.add(person);
    }
    public static void deletePerson(Person person) {
        Personslist.remove(person);
    }
    public static void updatePerson(Person selectedPerson, Person newPerson) {
        int index = Personslist.indexOf(selectedPerson);
        Personslist.get(index).setPrenom(newPerson.getPrenom());
        Personslist.get(index).setNom(newPerson.getNom());
    }

    public static void updatePerson(Person person, String firstName, String lastName) {
        // TODO: méthode sale (à changer plus tard)
        int index = Personslist.indexOf(person);
        Personslist.get(index).setPrenom(firstName);
        Personslist.get(index).setNom(lastName);
    }

    public static ArrayList<Person> getAllPersons() {
        return Personslist;
    }
}
