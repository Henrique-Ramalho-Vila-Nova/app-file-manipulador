package com.example.apparquivo;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    private EditText fileContentEditText;
    private MultiAutoCompleteTextView fileContentEdit;
    private String fileName = "meuarquivo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileContentEditText = findViewById(R.id.fileContentEditText);
        fileContentEdit = findViewById(R.id.fileContentEdit);

        Button createButton = findViewById(R.id.createButton);
        Button viewButton = findViewById(R.id.viewButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = fileContentEditText.getText().toString();
                writeToFile(fileName, content);
                Toast.makeText(MainActivity.this, "Arquivo criado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = readFile(fileName);
                fileContentEdit.setText(content);
                Toast.makeText(MainActivity.this, "Visualizado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(fileName);

                Toast.makeText(MainActivity.this, "Arquivo apagado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void writeToFile(String fileName, String content) {
        try {
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            FileInputStream fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private boolean deleteFile() {
        return deleteFile(null);
    }

    private boolean deleteMyFile(String fileName) {
        try {
            File file = new File(getFilesDir(), fileName);
            if (file.exists()) {
                if (file.delete()) {
                    Toast.makeText(this, "Arquivo apagado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Falha ao apagar o arquivo", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Arquivo não encontrado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}



