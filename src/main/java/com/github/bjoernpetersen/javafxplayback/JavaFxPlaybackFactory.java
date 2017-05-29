package com.github.bjoernpetersen.javafxplayback;

import com.github.bjoernpetersen.jmusicbot.InitStateWriter;
import com.github.bjoernpetersen.jmusicbot.config.Config;
import com.github.bjoernpetersen.jmusicbot.config.Config.Entry;
import com.github.bjoernpetersen.jmusicbot.playback.Playback;
import com.github.bjoernpetersen.jmusicbot.playback.PlaybackFactory;
import com.github.bjoernpetersen.jmusicbot.playback.included.WavePlaybackFactory;
import com.github.bjoernpetersen.mp3Playback.Mp3PlaybackFactory;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.sound.sampled.UnsupportedAudioFileException;

public class JavaFxPlaybackFactory implements Mp3PlaybackFactory, WavePlaybackFactory {

  public JavaFxPlaybackFactory() {
  }

  @Nonnull
  @Override
  public List<Entry> initializeConfigEntries(@Nonnull Config config) {
    return Collections.emptyList();
  }

  @Override
  public void initialize(@Nonnull InitStateWriter stateWriter) {
  }

  @Override
  public void destructConfigEntries() {
  }

  @Nonnull
  @Override
  public Playback createPlayback(@Nonnull File inputFile)
      throws UnsupportedAudioFileException, IOException {
    return new JavaFxPlayback(inputFile);
  }

  @Nonnull
  @Override
  public Collection<Class<? extends PlaybackFactory>> getBases() {
    Set<Class<? extends PlaybackFactory>> bases = new HashSet<>();
    bases.add(Mp3PlaybackFactory.class);
    bases.add(WavePlaybackFactory.class);
    // theoretically more are possible. Just need to create marker interfaces.
    return bases;
  }

  @Override
  public void close() throws IOException {
  }
}
