package fr.noais.byt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import fr.noais.byt.R;
import fr.noais.byt.models.VideoModel;

public class DetailsVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_video);

        TextView tvTitle = findViewById(R.id.descrTitleTxt);
        TextView tvDescription = findViewById(R.id.descrDescriptionTxt);
        TextView tvUrl = findViewById(R.id.descrUrlTxt);
        ImageButton cancelBtn = findViewById(R.id.descrCancelBtn);

        // ...
        //get video from intent
        VideoModel video = getIntent().getParcelableExtra("video", VideoModel.class);
        // ...

        cancelBtn.setOnClickListener(v -> finish());

        if (video != null) {
            // ...
            tvTitle.setText(String.format("%s (%s)", video.getTitle(), video.getCategory().name()));
            tvDescription.setText(video.getDescription());
            tvUrl.setText(video.getUrl());
            // ...
        }else{
            // ...
            tvTitle.setText("?");
            tvDescription.setText("?");
            tvUrl.setText("?");
            // ...
            Snackbar.make(tvTitle, "No video found", Snackbar.LENGTH_LONG).show();
        }
    }
}