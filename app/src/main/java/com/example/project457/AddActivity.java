package com.example.project457;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText Id,Name,Age,Experience,Salary;
    Button btnAdd, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Id = (EditText)findViewById(R.id.txtId);
        Name = (EditText)findViewById(R.id.txtName);
        Age = (EditText)findViewById(R.id.txtAge);
        Experience = (EditText)findViewById(R.id.txtExperience);
        Salary = (EditText)findViewById(R.id.txtSalary);

        btnAdd = (Button)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    insertData();
    clearAll();
            }
        });


    }

    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("Id",Id.getText().toString());
        map.put("Name",Name.getText().toString());
        map.put("Age",Age.getText().toString());
        map.put("Experience",Experience.getText().toString());
        map.put("Salary",Salary.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Employees").push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Employee Has Been added to database", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "Couldn't Add Employee", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll(){
        Id.setText("");
        Name.setText("");
        Age.setText("");
        Experience.setText("");
        Salary.setText("");
    }
}