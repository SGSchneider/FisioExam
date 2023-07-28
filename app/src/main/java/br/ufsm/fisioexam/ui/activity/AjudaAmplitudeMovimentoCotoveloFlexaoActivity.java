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

public class AjudaAmplitudeMovimentoCotoveloFlexaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda_amplitude_movimento_cotovelo_flexao);
        inicializaButtons();
        rodaVideoEmLoop();
    }

    private void rodaVideoEmLoop() {
        StyledPlayerView video = findViewById(R.id.activity_ajuda_amplitude_movimento_cotovelo_flexao_video);

        RedimensionaPlayerVideo(video);


        ExoPlayer exoPlayer = new ExoPlayer.Builder(this).build();

        Uri videoUri = Uri.parse("asset:///videos_cotovelo/amplitude_movimento_flexao.mp4");

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
        Button sair;
        sair = findViewById(R.id.activity_ajuda_amplitude_movimento_cotovelo_flexao_button_sair);
        sair.setOnClickListener(v -> finish());
    }
}
