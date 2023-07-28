package br.ufsm.fisioexam.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowMetrics;
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

public class AjudaAmplitudeMovimentoOmbroAducaoHorizontalActivity extends AppCompatActivity {
    private ExoPlayer exoPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda_amplitude_movimento_ombro_aducao_horizontal);
        inicializaButtons();
        rodaVideoEmLoop();

    }


    private void rodaVideoEmLoop() {
        StyledPlayerView video = findViewById(R.id.activity_ajuda_amplitude_movimento_ombro_aducao_horizontal_video);

        RedimensionaPlayerVideo(video);

        exoPlayer = new ExoPlayer.Builder(this).build();

        Uri videoUri = Uri.parse("asset:///videos_ombro/amplitude_movimento_aducao_horizontal.mp4");

        MediaSource mediaSource = new ProgressiveMediaSource.Factory(new DefaultDataSource.Factory(this)).createMediaSource(MediaItem.fromUri(videoUri));

        exoPlayer.setMediaSource(mediaSource);
        exoPlayer.setRepeatMode(Player.REPEAT_MODE_ALL);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);

        video.setPlayer(exoPlayer);


    }

    private void RedimensionaPlayerVideo(StyledPlayerView video) {
        WindowMetrics size;
        size = getWindowManager().getMaximumWindowMetrics();
        int width = size.getBounds().width();
        int height = (int) (width * (9.0f / 16.0f)); // assumindo uma proporção de aspecto de 16:9
        ViewGroup.LayoutParams params = video.getLayoutParams();
        params.width = width;
        params.height = height;
        video.setLayoutParams(params);
    }

    private void inicializaButtons() {
        Button sair = findViewById(R.id.activity_ajuda_amplitude_movimento_ombro_aducao_horizontal_button_sair);
        sair.setOnClickListener(v -> finalizaAjuda());
    }

    private void finalizaAjuda() {
        exoPlayer.release();
        finish();
    }
}
