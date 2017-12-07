package com.yaho.facelapse;

import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.NIOUtils;
import org.jcodec.common.SeekableByteChannel;

import java.io.File;
import java.io.IOException;

public class Video extends FileHandler implements FileOperation {

    public static void main(String[] args) throws IOException {

        SeekableByteChannel out = null;
        try {

            out = NIOUtils.writableFileChannel("/tmp/output.mp4"); // ?

            // AndroidSequenceEncoder
            AWTSequenceEncoder encoder = new AWTSequenceEncoder(new File(".mp4"));

            int numOfFrames = 30;
            for (int i = 1; i<numOfFrames; i++) {

                // Generate the image
                BufferedImage image = ImageIO.read(new File(""));

                // Encode the image
                encoder.encodeImage(image);
            }

            // Finalize the encoding
            encoder.finish();

        } finally {
            NIOUtils.closeQuietly(out); // ?
        }
    }
}
