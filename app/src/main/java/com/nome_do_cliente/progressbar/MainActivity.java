package com.nome_do_cliente.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int progress = 0, kwHora = 0, contatorDias = 0;
    private int max = 10, min = 0;
    private int progressoAtual = 0, limiteProgresso = 0;
    public static final double tarifa = 0.39;
    private double totalContaAtual = 0, totalProjecao = 0;


    Random geradorKw = new Random();
    DecimalFormat df = new DecimalFormat("#.##");


    private ProgressBar progressBar;
    private TextView textViewProgress,txtAtual, txtProjecao, txtDiaMes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        textViewProgress = findViewById(R.id.text_view_progress);
        txtAtual = findViewById(R.id.txtConsumoAtual);
        txtProjecao = findViewById(R.id.txtPrevisao);
        txtDiaMes = findViewById(R.id.txtDiaMes);

        progressBar.setProgress(0);
        textViewProgress.setText("0%");
    }
    public void onClickUp(View view)
    {
        if (progress <= 60)
        {
            progress += 2;
            updateProgressBar();
        }
    }

    public void onClickLow(View view)
    {
        if (progress >= 10)
        {
            progress -= 5;
            updateProgressBar();
        }
    }

    private void updateProgressBar()
    {
        progressBar.setProgress(progress);

        kwHora = kwHora + geradorKw.nextInt(max + 1 - min) + min;

        totalContaAtual = kwHora * tarifa;

        progressoAtual = progressoAtual+1;
        limiteProgresso = 31;
        totalProjecao = ((totalContaAtual/progressoAtual)*limiteProgresso);

        contatorDias++;

        textViewProgress.setText(kwHora + " Kw/h");
        txtDiaMes.setText("Dia " + contatorDias);
        txtAtual.setText("R$ " + df.format(totalContaAtual));
        txtProjecao.setText("R$ " + df.format(totalProjecao));
    }

}