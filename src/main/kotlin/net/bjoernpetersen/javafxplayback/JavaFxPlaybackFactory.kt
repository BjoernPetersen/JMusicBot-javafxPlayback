package net.bjoernpetersen.javafxplayback

import javafx.scene.media.MediaException
import net.bjoernpetersen.musicbot.api.config.Config
import net.bjoernpetersen.musicbot.spi.plugin.Playback
import net.bjoernpetersen.musicbot.spi.plugin.management.InitStateWriter
import net.bjoernpetersen.musicbot.spi.plugin.predefined.Mp3PlaybackFactory
import net.bjoernpetersen.musicbot.spi.plugin.predefined.UnsupportedAudioFileException
import net.bjoernpetersen.musicbot.spi.plugin.predefined.WavePlaybackFactory
import java.io.File
import java.io.IOException

class JavaFxPlaybackFactory : Mp3PlaybackFactory, WavePlaybackFactory {

    override val name: String = "JavaFX"
    override val description: String = "Plays MP3 or Wave songs using JavaFX's included feature"

    override fun createConfigEntries(config: Config): List<Config.Entry<*>> {
        return emptyList()
    }

    override fun createSecretEntries(secrets: Config): List<Config.Entry<*>> {
        return emptyList()
    }

    override fun createStateEntries(state: Config) {}

    override fun initialize(initStateWriter: InitStateWriter) {}

    @Throws(UnsupportedAudioFileException::class, IOException::class)
    override fun createPlayback(inputFile: File): Playback {
        try {
            return JavaFxPlayback(inputFile)
        } catch (e: MediaException) {
            if (e.type == MediaException.Type.MEDIA_UNSUPPORTED) {
                throw UnsupportedAudioFileException(e.message)
            } else {
                throw IOException(e)
            }
        }
    }

    override fun close() {}
}
