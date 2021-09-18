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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

  // Variables
  Double memoryValue = 0.0;

  // Buttons
  public Button sumButton;
  public Button minusButton;
  public Button multButton;
  public Button divisionButton;
  public Button endButton;
  public Button mcButton;
  public Button mrButton;
  public Button mPlusButton;
  public Button mMinusButton;
  public Button historyButton;

  // Text's
  public EditText firstInput;
  public EditText secondInput;
  public TextView showValueMemory;
  public EditText resultInput;

  private static final String FILE_NAME = "teste.txt";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Buttons
    sumButton      = (Button) findViewById(R.id.sumButton);
    minusButton    = (Button) findViewById(R.id.minusButton);
    multButton     = (Button) findViewById(R.id.multButton);
    divisionButton = (Button) findViewById(R.id.divisionButton);
    endButton      = (Button) findViewById(R.id.endButton);
    mcButton       = (Button) findViewById(R.id.mcButton);
    mrButton       = (Button) findViewById(R.id.mrButton);
    mPlusButton    = (Button) findViewById(R.id.mPlusButton);
    mMinusButton   = (Button) findViewById(R.id.mMinusButton);
    historyButton  = (Button) findViewById(R.id.historyButton);

    // Text's
    firstInput      = (EditText) findViewById(R.id.firstInput);
    secondInput     = (EditText) findViewById(R.id.secondInput);
    resultInput     = (EditText) findViewById(R.id.resultInput);
    showValueMemory = (TextView) findViewById(R.id.showValueMemory);

    // Funcionality of sum button
    sumButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Double firstValue  = Double.parseDouble(firstInput.getText().toString());
        Double secondValue = Double.parseDouble(secondInput.getText().toString());

        Double result = firstValue + secondValue;

        resultInput.setText(result.toString());

        save(firstInput.getText().toString() + " + " + secondInput.getText().toString() + " = " + result.toString());

      }
    });

    // Funcionality of minus button
    minusButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Double firstValue  = Double.parseDouble(firstInput.getText().toString());
        Double secondValue = Double.parseDouble(secondInput.getText().toString());

        Double result = firstValue - secondValue;

        resultInput.setText(result.toString());

        save(firstInput.getText().toString() + " - " + secondInput.getText().toString() + " = " + result.toString());

      }
    });

    // Funcionality of multiplication button
    multButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Double firstValue  = Double.parseDouble(firstInput.getText().toString());
        Double secondValue = Double.parseDouble(secondInput.getText().toString());

        Double result = firstValue * secondValue;

        resultInput.setText(result.toString());

        save(firstInput.getText().toString() + " * " + secondInput.getText().toString() + " = " + result.toString());

      }
    });

    // Funcionality of division button
    divisionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Double firstValue  = Double.parseDouble(firstInput.getText().toString());
        Double secondValue = Double.parseDouble(secondInput.getText().toString());

        if (secondValue != 0.0) {
          Double result = firstValue / secondValue;
          resultInput.setText(result.toString());

          save(firstInput.getText().toString() + " / " + secondInput.getText().toString() + " = " + result.toString());

        } else {
          Toast.makeText(getApplicationContext(),"Não é possível dividir por 0!", Toast.LENGTH_LONG).show();
          resultInput.setText("");
        }

      }
    });

    // Funcionality of memory clear button
    mcButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        memoryValue = 0.0;
        showValueMemory.setText("");

        save("COMANDO DE LIMPAR MEMÓRIA");

      }
    });

    // Funcionality of memory result button
    mrButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (memoryValue != 0.0) {
          firstInput.setText(memoryValue.toString());

          save("COMANDO DE USAR MEMÓRIA PARA CONTAS");
        }

      }
    });

    // Funcionality of memory + button
    mPlusButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Double valueResult = Double.parseDouble(resultInput.getText().toString());

        if (memoryValue != 0.0) {
          memoryValue += valueResult;
        } else {
          memoryValue = 1 * valueResult;
        }

        showValueMemory.setText(memoryValue.toString());

        save("COMANDO DE ADICIONAR VALOR PARA MEMÓRIA, VALOR: " + resultInput.getText().toString());

      }
    });

    // Funcionality of memory - button
    mMinusButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Double valueResult = Double.parseDouble(resultInput.getText().toString());

        if (memoryValue != 0.0) {
          memoryValue -= valueResult;
        } else {
          memoryValue = -1 * valueResult;
        }

        showValueMemory.setText(memoryValue.toString());

        save("COMANDO DE DIMINUIR VALOR PARA MEMÓRIA, VALOR: " + resultInput.getText().toString());

      }
    });

    // Open the history activy
    historyButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        openHistory();
      }
    });

    // Finish Program
    endButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        System.exit(0);
      }
    });

  }

  // Open new screen's
  public void openHistory() {
    Intent historyIntent = new Intent(this, ActivityHistory.class);
    startActivity(historyIntent);

  }

  // Save text
  public void save(String content) {
    String text = content + '\n';
    FileOutputStream fos = null;

    try {
      fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
      fos.write(text.getBytes());

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


  }

}