package sg.edu.rp.c346.id20031634.movieratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etMovie, etdescription, etYear;
    Button btnInsert, btnDisplay;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etMovie = (EditText) findViewById(R.id.etMovie);
        etdescription = (EditText) findViewById(R.id.etDescripiton);
        etYear = (EditText) findViewById(R.id.etYear);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);
        rb = findViewById(R.id.Rating);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String movie = etMovie.getText().toString().trim();
                String descripiton = etdescription.getText().toString().trim();
                if (movie.length() == 0 || descripiton.length() == 0){
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }


                String year_str = etYear.getText().toString().trim();
                int year = 0;
                try {
                    year = Integer.valueOf(year_str);
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);

                int stars = getStars();
                dbh.insertMovie(movie, descripiton, year, stars);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etMovie.setText("");
                etdescription.setText("");
                etYear.setText("");

            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

    }


    private int getStars() {
        int stars = 1;
        stars = (int) rb.getRating();
        return stars;
    }

}
