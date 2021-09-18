package com.example.calculadoradreco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActivityHistory extends AppCompatActivity {

  public Button back;
  public Button clear;
  public TextView historyText;
  private static final String FILE_NAME = "teste.txt";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_history);

    // Text's
    historyText = (TextView) findViewById(R.id.historyText);

    // Buttons
    clear = (Button) findViewById(R.id.clear);
    back  = (Button) findViewById(R.id.back);

    load();


    // Back to other screen
    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        backScreen();
      }
    });

    clear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        clean();
      }
    });

  }

  public void backScreen() {
    Intent mainIntent = new Intent(this, MainActivity.class);
    startActivity(mainIntent);
  }

  public void load() {
    FileInputStream fis = null;

    try {
      fis = openFileInput(FILE_NAME);
      InputStreamReader isr = new InputStreamReader(fis);
      BufferedReader br = new BufferedReader(isr);
      StringBuilder sb = new StringBuilder();
      String text;

      while ((text = br.readLine()) != null) {
        sb.append(text).append("\n");
      }

      historyText.setText(sb.toString());

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void clean() {
    String text = "";
    FileOutputStream fos = null;

    try {
      fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
      fos.write(text.getBytes());

      Toast.makeText(getApplicationContext(),"Histórico Limpo", Toast.LENGTH_LONG).show();

    } catch (FileNotFoundException e) {
      e.printStackTrace();

    } catch (IOException e) {
      e.printStackTrace();

    } finally {
      if (fos != null) {

        try {
          fos.close();

        } catch (IOException e) {
          e.printStackTrace();

        }

      }

    }


    load();
  }


}