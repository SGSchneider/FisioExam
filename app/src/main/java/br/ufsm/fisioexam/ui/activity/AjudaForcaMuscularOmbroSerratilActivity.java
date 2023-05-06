package br.ufsm.fisioexam.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSource;

import br.ufsm.fisioexam.R;

public class AjudaForcaMuscularOmbroSerratilActivity extends AppCompatActivity {
    private ExoPlayer exoPlayerA;
    private ExoPlayer exoPlayerB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda_forca_muscular_ombro_serratil);
        InicializaExoPlayers();
        inicializaButtons();
        rodaVideoEmLoop();

    }


    private void rodaVideoEmLoop() {
        StyledPlayerView videoA = findViewById(R.id.activity_ajuda_forca_muscular_ombro_serratil_video_a);
        StyledPlayerView videoB = findViewById(R.id.activity_ajuda_forca_muscular_ombro_serratil_video_b);

        RedimensionaPlayerVideo(videoA);
        RedimensionaPlayerVideo(videoB);


        DefineExoPlayer(exoPlayerA, "asset:///videos_ombro/forca_muscular_serratil_a.mp4");
        videoA.setPlayer(exoPlayerA);


        DefineExoPlayer(exoPlayerB, "asset:///videos_ombro/forca_muscular_serratil_b.mp4");
        videoB.setPlayer(exoPlayerB);
    }

    private void InicializaExoPlayers() {
        exoPlayerA = new ExoPlayer.Builder(this).build();
        exoPlayerB = new ExoPlayer.Builder(this).build();
    }

    private void DefineExoPlayer(ExoPlayer exoPlayer, String path) {
        Uri videoUriA = Uri.parse(path);
        MediaSource mediaSourceA = new ProgressiveMediaSource.Factory(new DefaultDataSource.Factory(this)).createMediaSource(MediaItem.fromUri(videoUriA));
        exoPlayer.setMediaSource(mediaSourceA);
        exoPlayer.setRepeatMode(Player.REPEAT_MODE_ALL);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);
    }

    private void RedimensionaPlayerVideo(StyledPlayerView video) {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics size = new DisplayMetrics();
        display.getMetrics(size);
        int width = size.widthPixels;
        int height = (int) (width * (9.0f / 16.0f)); // assumindo uma proporção de aspecto de 16:9
        LayoutParams params = (LayoutParams) video.getLayoutParams();
        params.width = width;
        params.height = height;
        video.setLayoutParams(params);
    }

    private void inicializaButtons() {
        Button sair = findViewById(R.id.activity_ajuda_forca_muscular_ombro_serratil_button_sair);
        sair.setOnClickListener(v -> finalizaAjuda());
    }

    private void finalizaAjuda() {
        exoPlayerA.release();
        exoPlayerB.release();
        finish();
    }
}
