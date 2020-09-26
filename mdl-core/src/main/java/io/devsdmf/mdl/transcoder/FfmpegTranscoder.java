package io.devsdmf.mdl.transcoder;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.VideoAttributes;

import java.io.File;

public class FfmpegTranscoder implements Transcoder {

    private final Encoder encoder;

    public FfmpegTranscoder(Encoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public File transcode(File source, File target) {
        try {
            AudioAttributes audioAttributes = new AudioAttributes();


            VideoAttributes videoAttributes = new VideoAttributes();

            EncodingAttributes attrs = new EncodingAttributes();
            encoder.encode(new MultimediaObject(source),target,attrs);
        } catch (EncoderException e) {
            // TODO: Log exception
            return null;
        }

        return null;
    }
}
