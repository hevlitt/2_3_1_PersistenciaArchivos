package com.example.desk.a2_3_1_persistenciaarchivos;


import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText textBox;
    Button btns,btnl;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = (EditText) findViewById(R.id.editText);
        btnl=(Button)findViewById(R.id.btnLoad);
        btns=(Button)findViewById(R.id.btnSave);


        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = textBox.getText().toString();
                try {
                    File ruta = Environment.getExternalStorageDirectory();
                    File file = new File(ruta.getAbsolutePath(), "textfile.txt");
                    //FileOutputStream fOut = openFileOutput("textfile.txt", MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
                    //---write the string to the file---
                    try {
                        osw.write(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    osw.flush();
                    osw.close();
                    Toast.makeText(getBaseContext(), "File saved successfully! "+ruta, Toast.LENGTH_SHORT).show();
                    textBox.setText("");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });

        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File ruta = Environment.getExternalStorageDirectory();
                    File file = new File(ruta.getAbsolutePath(), "textFile.txt");
                    //FileInputStream fIn = openFileInput("textfile.txt");
                    InputStreamReader isr = new InputStreamReader(new FileInputStream (file));
                    char[] inputBuffer = new char[READ_BLOCK_SIZE];
                    String s = "";
                    int charRead;
                    while ((charRead = isr.read(inputBuffer)) > 0) {
                        //---convert the chars to a String---
                        String readString =
                                String.copyValueOf(inputBuffer, 0,
                                        charRead);
                        s += readString;
                        inputBuffer = new char[READ_BLOCK_SIZE];
                    }
                    textBox.setText(s);
                    Toast.makeText(getBaseContext(), "File loaded successfully!",
                            Toast.LENGTH_SHORT).show();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
    }
}

