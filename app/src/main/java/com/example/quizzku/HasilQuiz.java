package com.example.quizzku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HasilQuiz extends AppCompatActivity {

    private TextView Nilai, Massage, hasil;
    private Button ulang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_quiz);

        // Initialize views
        Nilai = findViewById(R.id.Nilai);
        Massage = findViewById(R.id.Massage);
        hasil = findViewById(R.id.hasil);
        ulang = findViewById(R.id.ulang);

        // Get data from intent
        int nilai = getIntent().getExtras().getInt("nilai");
        int benar = getIntent().getExtras().getInt("benar");
        int salah = getIntent().getExtras().getInt("salah");

        // Set the score in the Nilai TextView
        Nilai.setText(String.valueOf(nilai));

        // Display the message with correct and incorrect answers
        Massage.setText("Jawaban Benar: " + benar + ", Jawaban Salah: " + salah);

        // Provide feedback based on the score
        if (nilai == 1000) {
            hasil.setText("Kamu punya IQ di atas rata-rata!");
        } else {
            hasil.setText("HEDEHHHHH.....BAKA");
        }

        // Retry button to restart the quiz
        ulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });
    }
}
