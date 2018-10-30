package com.example.rodrigosantos.androidstudioprojects.agendacomfirebaseccp3anbua;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaDeContatosActivity extends AppCompatActivity {
    private ListView contatosListView;
    private ArrayAdapter <Contato> contatosAdapter;
    private List<Contato> contatos;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference contatosReference;


    private void configuraFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        contatosReference = firebaseDatabase.getReference("contatos");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_contatos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        configuraFirebase();
        contatosListView = findViewById(R.id.contatosListView);
        contatos = new ArrayList<>();

        contatosAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contatos);

        contatosListView.setAdapter(contatosAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adicionarContatoIntent =
                      new Intent(ListaDeContatosActivity.this,AdicionaContatosActivity.class);
                startActivity(adicionarContatoIntent);
            }
        });
        configuraObserverLongClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        contatosReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contatos.clear();
                for (DataSnapshot json : dataSnapshot.getChildren()){
                    Contato contato = json.getValue(Contato.class);
                    contatos.add(contato);
                    contato.setId(json.getKey());
                }
                contatosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListaDeContatosActivity.this,
                        getString(R.string.erro_firebase),
                        Toast.LENGTH_SHORT).show();
                databaseError.toException().printStackTrace();
            }
        });
    }

    private void configuraObserverLongClick(){
        contatosListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           final View view, final int position, long id) {

                AlertDialog.Builder dBuilder =
                        new AlertDialog.Builder(ListaDeContatosActivity.this);
                dBuilder.setPositiveButton(
                        getString(R.string.deletar_contato),

                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int wich) {
                                Contato contato = contatos.get(position);
                                contatosReference.child(contato.getId()).removeValue();
                                Toast.makeText(ListaDeContatosActivity.this,
                                        getString(R.string.contato_removido),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton(getString(
                                R.string.atualizar_contato),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final AlertDialog.Builder dBuilder =
                                        new AlertDialog.Builder(ListaDeContatosActivity.this);
                                View raiz =
                                        LayoutInflater.from(ListaDeContatosActivity.this).
                                                inflate(R.layout.activity_adiciona_contatos,null);
                                final Contato contato = contatos.get(position);
                                final EditText nomeEditText =
                                        raiz.findViewById(R.id.nomeEditText);
                                final EditText foneEditText =
                                        raiz.findViewById(R.id.foneEditText);
                                foneEditText.setText(contato.getFone());
                                final EditText emailEditText =
                                        raiz.findViewById(R.id.emailEditText);

                                nomeEditText.setText(contato.getNome());
                                foneEditText.setText(contato.getFone());
                                emailEditText.setText(contato.getEmail());


                                final FloatingActionButton fab =
                                        raiz.findViewById(R.id.fab);
                                final AlertDialog dialogView = dBuilder.setView(raiz).create();
                                dialogView.show();
                                fab.setOnClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view){
                                        String nome =
                                                nomeEditText.getEditableText().toString();
                                        String fone =
                                                foneEditText.getEditableText().toString();
                                        String email =
                                                emailEditText.getEditableText().toString();
                                        contato.setNome(nome);
                                        contato.setFone(fone);
                                        contato.setEmail(email);

                                        contatosReference.child(contato.getId()).setValue(contato);
                                        Toast.makeText(ListaDeContatosActivity.this, "Ok",
                                                Toast.LENGTH_SHORT).show();
                                        dialogView.cancel();

                                    }


                                });


                            }
                        }).create();
                dBuilder.show();
                return false;
            }
        });
    }
}
