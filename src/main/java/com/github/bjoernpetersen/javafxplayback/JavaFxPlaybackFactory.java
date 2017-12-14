package com.github.bjoernpetersen.javafxplayback;

import com.github.bjoernpetersen.jmusicbot.InitStateWriter;
import com.github.bjoernpetersen.jmusicbot.config.Config;
import com.github.bjoernpetersen.jmusicbot.config.Config.Entry;
import com.github.bjoernpetersen.jmusicbot.platform.Platform;
import com.github.bjoernpetersen.jmusicbot.platform.Support;
import com.github.bjoernpetersen.jmusicbot.playback.Playback;
import com.github.bjoernpetersen.jmusicbot.playback.PlaybackFactory;
import com.github.bjoernpetersen.jmusicbot.playback.included.Mp3PlaybackFactory;
import com.github.bjoernpetersen.jmusicbot.playback.included.WavePlaybackFactory;
import com.github.zafarkhaja.semver.Version;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.media.MediaException;
import javax.annotation.Nonnull;
import javax.sound.sampled.UnsupportedAudioFileException;

public class JavaFxPlaybackFactory implements Mp3PlaybackFactory, WavePlaybackFactory,
    com.github.bjoernpetersen.mp3Playback.Mp3PlaybackFactory {

  public JavaFxPlaybackFactory() {
  }

  @Nonnull
  @Override
  public String getReadableName() {
    return "JavaFX PlaybackFactory";
  }

  @Nonnull
  @Override
  public Support getSupport(@Nonnull Platform platform) {
    switch (platform) {
      case LINUX:
      case WINDOWS:
        return Support.YES;
      case UNKNOWN:
        return Support.MAYBE;
      case ANDROID:
      default:
        return Support.NO;
    }
  }

  @Nonnull
  @Override
  public List<Entry> initializeConfigEntries(@Nonnull Config config) {
    return Collections.emptyList();
  }

  @Override
  public void initialize(@Nonnull InitStateWriter stateWriter) {
  }

  @Nonnull
  @Override
  public List<? extends Entry> getMissingConfigEntries() {
    return Collections.emptyList();
  }

  @Override
  public void destructConfigEntries() {
  }

  @Nonnull
  @Override
  public Version getMinSupportedVersion() {
    return Version.forIntegers(0, 14, 0);
  }

  @Nonnull
  @Override
  public Playback createPlayback(@Nonnull File inputFile) throws UnsupportedAudioFileException, IOException {
    try {
      return new JavaFxPlayback(inputFile);
    } catch (MediaException e) {
      if (e.getType() == MediaException.Type.MEDIA_UNSUPPORTED) {
        throw new UnsupportedAudioFileException(e.getMessage());
      } else {
        throw new IOException(e);
      }
    }
  }

  @Nonnull
  @Override
  public Collection<Class<? extends PlaybackFactory>> getBases() {
    Set<Class<? extends PlaybackFactory>> bases = new HashSet<>();
    bases.add(Mp3PlaybackFactory.class);
    bases.add(WavePlaybackFactory.class);
    bases.add(com.github.bjoernpetersen.mp3Playback.Mp3PlaybackFactory.class);
    // theoretically more are possible. Just need to create marker interfaces.
    return bases;
  }

  @Override
  public void close() {
  }
}
