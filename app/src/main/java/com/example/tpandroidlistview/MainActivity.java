package com.example.tpandroidlistview;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tpandroidlistview.controller.DaoPerson;
import com.example.tpandroidlistview.model.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LIST_PERSONS = "LIST_PERSONS";

    EditText nomEditText, prenomEditText;
    Person selectedPerson;

    // On déclare un lancer pour un appel préalablement déclaré (intent) pour démarrer une processus d'éxécution d'un ActivityResultContract.
    ActivityResultLauncher<Intent> intentActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomEditText = findViewById(R.id.nomEditText);
        prenomEditText = findViewById(R.id.prenomEditText);

        // On déclare un lancer pour un appel préalablement déclaré (intent) pour démarrer une processus d'éxécution d'un ActivityResultContract.
        intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult activityResult) {
                        if (activityResult.getResultCode() == Activity.RESULT_OK) {
                            Intent data = activityResult.getData();
                            selectedPerson = (Person) data.getSerializableExtra(ListViewActivity.PERSON);
                            nomEditText.setText(selectedPerson.getNom());
                            prenomEditText.setText(selectedPerson.getPrenom());
                        }
                    }
                });
    }

    public void createPerson(View view) {

        // On récupère la liste des personnes et on créé une nouvelle Personne
        String nom = nomEditText.getText().toString();
        String prenom = prenomEditText.getText().toString();
        Person person = new Person(nom, prenom);

        // On ajoute la personne
        DaoPerson.addPerson(person);

        displayListView(view);
    }

    public void modifyPerson(View view) {
        String nom = nomEditText.getText().toString();
        String prenom = prenomEditText.getText().toString();
        DaoPerson.updatePerson(selectedPerson, new Person(nom, prenom));
        displayListView(view);
    }

    public void deletePerson(View view) {
        DaoPerson.deletePerson(selectedPerson);
        displayListView(view);
    }

    public void displayListView(View view) {
        Intent listViewIntent = new Intent(this, ListViewActivity.class);
        listViewIntent.putExtra(MainActivity.LIST_PERSONS, DaoPerson.getAllPersons());
        intentActivityResultLauncher.launch(listViewIntent);
    }
}