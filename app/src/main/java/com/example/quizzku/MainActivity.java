package com.example.quizzku;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView soal, waktu;
    private Button btnNext;
    private RadioGroup pilihan;
    private RadioButton JwbA, JwbB, JwbC;
    private ImageView ivImage;
    private int nomor = 0;
    private int biji;
    private int benar = 0;
    private int salah = 0;

    private int gambar[] = {
            R.drawable.img,
            R.drawable.score,
            R.drawable.elek,
            R.drawable.gintama,
            R.drawable.haerin,
    };

    private String pertanyaan[] = {
            "1. Apakah Benar Bahwa Club diatas adalah Club terbaik?",
            "2. Berapa score akhir dari pertandingan diatas yang bertanding pada 13 januari lalu?",
            "3. Apakah Benar Bahwa Anime diatas merupakan Anime dengan animasi terbaik sepanjang masa?",
            "4. Apa Nama Anime Diatas?",
            "5. Apakah wanita diatas cocok dengan YANUAR?",
    };

    private String Option[] = {
            "Benar", "Benar Sekali", "Sangat Sangat Benar",
            "5-2", "4-0", "1-2",
            "Benar", "Sangat Benar", "Tidak",
            "One Piece", "Kimetsu no Yaiba", "Gintama",
            "Cocok", "Cocok Banget", "Luar Biasa Cocok"
    };

    private String Jawaban[] = {
            "Sangat Sangat Benar",
            "5-2",
            "Tidak",
            "Gintama",
            "Luar Biasa Cocok"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        soal = findViewById(R.id.soal);
        waktu = findViewById(R.id.waktu);
        btnNext = findViewById(R.id.btnNext);
        pilihan = findViewById(R.id.pilihan);
        JwbA = findViewById(R.id.JwbA);
        JwbB = findViewById(R.id.JwbB);
        JwbC = findViewById(R.id.JwbC);
        ivImage = findViewById(R.id.ivImage);

        // Set initial question and image
        soal.setText(pertanyaan[nomor]);
        ivImage.setImageResource(gambar[nomor]);
        JwbA.setText(Option[0]);
        JwbB.setText(Option[1]);
        JwbC.setText(Option[2]);

        // Countdown timer
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                waktu.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                waktu.setText("Waktu Habis!!");
                navigateToResult();
            }
        }.start();

        // Button click listener
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pilihan.getCheckedRadioButtonId() != -1) { // Ensure an option is selected
                    RadioButton milih_User = findViewById(pilihan.getCheckedRadioButtonId());
                    String jawab_user = milih_User.getText().toString();

                    // Check answer
                    if (jawab_user.equalsIgnoreCase(Jawaban[nomor])) {
                        benar++;
                    } else {
                        salah++;
                    }

                    nomor++;

                    // Update question or finish quiz
                    if (nomor < pertanyaan.length) {
                        soal.setText(pertanyaan[nomor]);
                        ivImage.setImageResource(gambar[nomor]);
                        JwbA.setText(Option[nomor * 3]);
                        JwbB.setText(Option[nomor * 3 + 1]);
                        JwbC.setText(Option[nomor * 3 + 2]);
                        pilihan.clearCheck(); // Reset selection
                    } else {
                        navigateToResult();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Silahkan Pilih Jawaban Terlebih Dahulu!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void navigateToResult() {
        biji = benar * 200;
        Intent next = new Intent(getApplicationContext(), HasilQuiz.class);
        next.putExtra("nilai", biji);
        next.putExtra("benar", benar);
        next.putExtra("salah", salah);
        startActivity(next);
        finish(); // Prevent returning to the quiz screen
    }
}
