package Z.Z01;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.stream.Stream;

public class Futil {
    public static void processDir(String startDir, String outputFileName) {
        Stream.Builder<File> files = Stream.builder();
        try {
            Files.walkFileTree(Path.of(startDir), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    files.add(file.toFile());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        files.build()
                .map(f -> {
                    try (var fis = new FileInputStream(f)) {
                        var fc = fis.getChannel();
                        var byteBuffer = ByteBuffer.allocate(1024);
                        fc.read(byteBuffer);
                        return byteBuffer;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(bb->{
                    bb.flip();
                    var cb = CharBuffer.allocate(bb.remaining());
                    var decoder = Charset.forName("Cp1250").newDecoder();
                    decoder.decode(bb, cb, true);
                    cb.flip();
                    return cb;
                })
                .map(cb->{
                    var encoder = StandardCharsets.UTF_8.newEncoder();
                    var bb = ByteBuffer.allocate(cb.remaining());
                    encoder.encode(cb, bb, true);
                    bb.flip();
                    return bb;
                })
                .forEach(byteBuffer -> {
                    try (var fos = new FileOutputStream(outputFileName, true)) {
                        var fc = fos.getChannel();
                        fc.write(byteBuffer);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

    }
}
