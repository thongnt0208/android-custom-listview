package com.example.lab3customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvFruit;
    ArrayList<Fruit> arrayFruits;
    FruitListAdapter fruitAdapter;
    Toolbar toolbar;
    EditText etTitle, etDescription, etImageLink;
    Button btnAddFruit, btnReset, btnUpdateFruit;

    private boolean checkInput() {
        if (TextUtils.isEmpty(etTitle.getText().toString())) {
            etTitle.setError("Require");
            return false;
        }
        if (TextUtils.isEmpty(etDescription.getText().toString())) {
            etDescription.setError("Require");
            return false;
        }
        return true;
    }

    private void clearInputs() {
        etTitle.setText("");
        etDescription.setText("");
        etImageLink.setText("");
        btnAddFruit.setVisibility(View.VISIBLE);
        btnUpdateFruit.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.showOverflowMenu();
        //1. Mapping
        mapping();

        //2. Set Adapter
        fruitAdapter = new FruitListAdapter(this, R.layout.list_item, arrayFruits);
        lvFruit.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvFruit.setAdapter(fruitAdapter);

        //remove an item
        lvFruit.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String tmp = arrayFruits.get(position).getName();
                arrayFruits.remove(position);
                fruitAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Deleted " + tmp, Toast.LENGTH_SHORT).show();
                clearInputs();
                return false;
            }
        });

        //add an item
        btnAddFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    arrayFruits.add(new Fruit(etTitle.getText().toString(), etDescription.getText().toString(), etImageLink.getText().toString()));
                    Toast.makeText(MainActivity.this, "Added " + etTitle.getText().toString(), Toast.LENGTH_SHORT).show();
                    clearInputs();
                    fruitAdapter.notifyDataSetChanged();
                }
            }
        });

        //update an item
        lvFruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit currentFruit = arrayFruits.get(position);
                etTitle.setText(currentFruit.getName());
                etDescription.setText(currentFruit.getDescription());
                if (currentFruit.getImageLink() != null) {
                    etImageLink.setText((currentFruit.getImageLink()));
                } else {
                    etImageLink.setText("");
                }

                //hide add button and display update button
                btnAddFruit.setVisibility(View.INVISIBLE);
                btnUpdateFruit.setVisibility(View.VISIBLE);

                btnUpdateFruit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkInput()) {
                            currentFruit.setName(etTitle.getText().toString());
                            currentFruit.setDescription(etDescription.getText().toString());
                            currentFruit.setImageLink(etImageLink.getText().toString());

                            Toast.makeText(MainActivity.this, "Edited! ", Toast.LENGTH_SHORT).show();
                            fruitAdapter.notifyDataSetChanged();
                            clearInputs();
                        }
                    }
                });

            }
        });

        //reset edit text
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputs();
            }
        });
    }

    private void mapping() {
        lvFruit = findViewById(R.id.lvFruits);
        arrayFruits = new ArrayList<>();
        etTitle = findViewById(R.id.etTile);
        etDescription = findViewById(R.id.etDescription);
        btnAddFruit = findViewById(R.id.btnAdd);
        btnReset = findViewById(R.id.btnReset);
        btnUpdateFruit = findViewById(R.id.btnUpdate);
        etImageLink = findViewById(R.id.etImageLink);

        String[] names = new String[]{"Apple", "Banana", "Cherry", "Durian", "Elderberry", "Fig", "Grapes", "Honeydew", "Jackfruit"};
        String[] descriptions = new String[]{"A sweet and crunchy fruit that comes in various colors.", "A curved, yellow fruit with a thick peel.", "A small, round fruit with a tart taste.", "A spiky fruit with a strong odor and a custard-like texture.", "A small, dark purple fruit known for its antioxidant properties.", "A small, pear-shaped fruit with a soft, sweet flesh.", "Small, juicy fruits that grow in clusters and come in various colors.", "A large, round fruit with a green rind and sweet, juicy flesh.", "A large, tropical fruit with a distinct, sweet flavor."};
        int[] imagesSrc = new int[]{R.drawable.apple, R.drawable.bananas, R.drawable.cherry, R.drawable.durian, R.drawable.elderberry, R.drawable.fig, R.drawable.grapes, R.drawable.honeydew, R.drawable.jackfruit};

        for (int i = 0; i < Math.min(Math.min(names.length, descriptions.length), imagesSrc.length); i++) {
            arrayFruits.add(new Fruit(names[i], descriptions[i], imagesSrc[i] != 0 ? imagesSrc[i] : R.drawable.ic_launcher_background));
        }
    }
}