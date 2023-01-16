package com.example.actividadprefluca;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    SeekBar mibarra;
    EditText recuento;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main2);

        mibarra = (SeekBar) findViewById(R.id.seekBar);
        mibarra.setMax(10);
        mibarra.setProgress(0);
        recuento = (EditText) findViewById(R.id.editText);


        mibarra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(mibarra.getProgress()<5) {
                    Toast toast = Toast.makeText(MainActivity2.this, "ABAJO", Toast.LENGTH_SHORT);
                    toast.show();
                } else if(mibarra.getProgress()==5){
                    Toast toast = Toast.makeText(MainActivity2.this, "CENTRO", Toast.LENGTH_SHORT);
                    toast.show();
                } else if(mibarra.getProgress()>5){
                    Toast toast = Toast.makeText(MainActivity2.this, "ARRIBA", Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });
        recuento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Toast toast = Toast.makeText(MainActivity2.this, recuento.getText(), Toast.LENGTH_LONG);
                toast.show();

            }
        });
    }
}
