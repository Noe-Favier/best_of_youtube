package fr.noais.byt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import fr.noais.byt.R;
import fr.noais.byt.adapters.VideoAdapter;
import fr.noais.byt.database.VideoDatabase;
import fr.noais.byt.models.VideoModel;

public class MainActivity extends AppCompatActivity {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------
        Button addVideoBtn = findViewById(R.id.addVideoBtn);
        //------
        ActivityResultLauncher<Intent> newVideoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        VideoModel video = data.getParcelableExtra("video", VideoModel.class);
                        // ...
                        CompletableFuture.runAsync(() -> {
                            VideoDatabase.getDb(getApplicationContext()).videoDao().add(video);
                        }).thenRun(() -> {
                            Snackbar.make(findViewById(R.id.addVideoBtn), "Video added", Snackbar.LENGTH_SHORT).show();
                            refreshVideoList();
                        }).exceptionally(e -> {
                            Log.e("MainActivity", "Error adding video", e);
                            Snackbar.make(findViewById(R.id.addVideoBtn), "Error adding video", Snackbar.LENGTH_SHORT).show();
                            return null;
                        });
                    }
                });
        //------

        addVideoBtn.setOnClickListener(v -> {
            newVideoLauncher.launch(new Intent(MainActivity.this, NewVideoActivity.class));
        });

        refreshVideoList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }

    protected void refreshVideoList() {
        CompletableFuture.runAsync(() -> {
            List<VideoModel> vl = VideoDatabase.getDb(getApplicationContext()).videoDao().list();
            runOnUiThread(() -> {
                RecyclerView rvVideoList = findViewById(R.id.videoList);
                TextView noContent = findViewById(R.id.noContentTextView);
                if (vl.isEmpty()) {
                    rvVideoList.setVisibility(RecyclerView.GONE);
                    noContent.setVisibility(TextView.VISIBLE);
                } else {
                    noContent.setVisibility(TextView.GONE);
                    rvVideoList.setVisibility(RecyclerView.VISIBLE);

                    rvVideoList.setAdapter(new VideoAdapter(vl));
                    rvVideoList.setLayoutManager(new LinearLayoutManager(this));
                }
            });
        });
    }
}
