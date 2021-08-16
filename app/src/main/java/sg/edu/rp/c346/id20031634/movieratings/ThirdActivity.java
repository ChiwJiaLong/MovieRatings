package sg.edu.rp.c346.id20031634.movieratings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etMovie, etDescription, etYear;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar rb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etMovie = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescriptions);
        etYear = (EditText) findViewById(R.id.etYear);
        rb = findViewById(R.id.ratingBarStar);

        Intent i = getIntent();
        final Movie currentMovie = (Movie) i.getSerializableExtra("movie");

        etID.setText(currentMovie.getId()+"");
        etMovie.setText(currentMovie.getMovie());
        etDescription.setText(currentMovie.getDescription());
        etYear.setText(currentMovie.getYearReleased()+"");
        rb.setRating(currentMovie.getStars());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentMovie.setMovie(etMovie.getText().toString().trim());
                currentMovie.setDescription(etDescription.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentMovie.setYearReleased(year);

//                int selectedRB = rg.getCheckedRadioButtonId();
//                RadioButton rb = (RadioButton) findViewById(selectedRB);
//                currentSong.setStars(Integer.parseInt(rb.getText().toString()));
                currentMovie.setStars((int) rb.getRating());
                int result = dbh.updateMovie(currentMovie);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Movie updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete this Movie? \n " + etMovie.getText().toString());
                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final DBHelper dbh = new DBHelper(ThirdActivity.this);
                        int result = dbh.deleteMovie(currentMovie.getId());
                        if (result > 0) {
                            Toast.makeText(ThirdActivity.this, "Movie deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                myBuilder.setPositiveButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();


            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                myBuilder.setPositiveButton("Do not Discard", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

    }



}