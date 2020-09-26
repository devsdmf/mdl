package io.devsdmf.mdl.transcoder;

import java.io.File;

public interface Transcoder {

    File transcode(File source, File target);
}
