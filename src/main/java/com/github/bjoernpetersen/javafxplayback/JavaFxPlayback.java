package com.github.bjoernpetersen.javafxplayback;

import com.github.bjoernpetersen.jmusicbot.playback.AbstractPlayback;
import java.io.File;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javax.annotation.Nonnull;

class JavaFxPlayback extends AbstractPlayback {

  @Nonnull
  private final MediaPlayer player;

  JavaFxPlayback(@Nonnull File file) throws IOException, MediaException {
    this.player = new MediaPlayer(new Media(file.toURI().toURL().toExternalForm()));
    player.setOnEndOfMedia(this::markDone);
  }

  @Override
  public void play() {
    player.play();
  }

  @Override
  public void pause() {
    player.pause();
  }

  @Override
  public void close() throws Exception {
    player.dispose();
    super.close();
  }
}
