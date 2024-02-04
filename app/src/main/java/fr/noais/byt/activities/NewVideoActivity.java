package fr.noais.byt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.android.material.snackbar.Snackbar;

import fr.noais.byt.R;
import fr.noais.byt.models.Category;
import fr.noais.byt.models.VideoModel;

public class NewVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_video);

        EditText title = findViewById(R.id.titleTxt);
        EditText description = findViewById(R.id.descriptionTxa);
        EditText url = findViewById(R.id.urlTxt);
        Switch isFavorite = findViewById(R.id.favoriteSwt);
        Spinner category = findViewById(R.id.categorySpn);

        ImageButton saveBtn = findViewById(R.id.addBtn);
        ImageButton cancelBtn = findViewById(R.id.cancelBtn);

        category.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Category.getNames()));

        for (EditText editText : new EditText[]{title, description, url}) {
            editText.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    if (editText.getText().toString().isEmpty()) {
                        editText.setError("This field is required");
                    }
                }
            });
        }

        cancelBtn.setOnClickListener(v -> {
            finish();
        });

        saveBtn.setOnClickListener(v -> {
            boolean error = false;
            if (title.getText().toString().isEmpty()) {
                title.setError("This field is required");
                error = true;
            }
            if (description.getText().toString().isEmpty()) {
                description.setError("This field is required");
                error = true;
            }
            if (url.getText().toString().isEmpty()) {
                url.setError("This field is required");
                error = true;
            }

            if (error) {
                Snackbar.make(v, "Please fill all the fields", Snackbar.LENGTH_LONG).show();
                return;
            }

            VideoModel videoModel = new VideoModel();
            videoModel.setTitle(title.getText().toString());
            videoModel.setDescription(description.getText().toString());
            videoModel.setUrl(url.getText().toString());
            videoModel.setFavorite(isFavorite.isChecked());
            videoModel.setCategory(Category.fromName(category.getSelectedItem().toString()));


            Intent intent = new Intent();
            intent.putExtra("video", videoModel);
            setResult(RESULT_OK, intent);
            Log.d("NOE", "onCreate: video added");
            finish();
        });
    }
}