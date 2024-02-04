package fr.noais.byt.adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.*;

import java.util.List;

import fr.noais.byt.R;
import fr.noais.byt.database.VideoDatabase;
import fr.noais.byt.models.VideoModel;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    private final List<VideoModel> videos;

    public VideoAdapter(List<VideoModel> videos) {
        this.videos = videos;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoModel v = videos.get(position);
        VideoDatabase db = VideoDatabase.getDb(holder.itemView.getContext());
        //----------------
        holder.tvTitle.setText(v.getTitle());

        holder.btnDelete.setOnClickListener(v1 -> {
            db.videoDao().delete(v);
            videos.remove(v);
            notifyDataSetChanged();
        });

        holder.btnDetails.setOnClickListener(v1 -> {
            // ...
        });

        holder.btnPlay.setOnClickListener(v1 -> {
            startActivity(v1.getContext(), new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("http://www.youtube.com/watch?v=%s", v.getUrl()))), null);
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;

        public ImageButton btnDelete;
        public ImageButton btnDetails;
        public ImageButton btnPlay;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);

            btnDelete = itemView.findViewById(R.id.deleteBtn);
            btnDetails = itemView.findViewById(R.id.descriptionBtn);
            btnPlay = itemView.findViewById(R.id.playBtn);
        }
    }

}
