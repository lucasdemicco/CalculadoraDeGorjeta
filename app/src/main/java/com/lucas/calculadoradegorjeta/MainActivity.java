package com.lucas.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtValor;
    private TextView txtResultSeek, txtResultGorjeta, txtResultTotal;
    private SeekBar seekBar;

    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtValor = findViewById(R.id.txtValor);
        txtResultSeek = findViewById(R.id.txtResultSeek);
        txtResultGorjeta = findViewById(R.id.txtResultGorjeta);
        txtResultTotal = findViewById(R.id.txtResultTotal);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress;
                txtResultSeek.setText(Math.round(porcentagem) + "%");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calcular(){
        String valorRecuperado = txtValor.getText().toString();
        if(valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(
                    getApplicationContext(),
                    "Digite um valor primeiro",
                    Toast.LENGTH_LONG
            ).show();
        }else{

            //Converter String para Double
            double valorDigitado = Double.parseDouble(valorRecuperado);

            //Calcular gorjeta total
            double gorjeta = valorDigitado * (porcentagem/100);

            //Exibir gorjeta e total
            txtResultGorjeta.setText("R$: " + Math.round(gorjeta));

            //Exibir total
            double total = valorDigitado + gorjeta;
            txtResultTotal.setText("R$: %.2f" + total);

        }
    }
}