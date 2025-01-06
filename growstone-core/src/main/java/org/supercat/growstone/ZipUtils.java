package org.supercat.growstone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public final class ZipUtils {
    private static final Logger logger = LoggerFactory.getLogger(ZipUtils.class);

    /**
     * Shovels all data from an input stream to an output stream.
     */
    private static void shovelInToOut(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1000];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }

    /**
     * Compresses a file with zlib compression.
     */
    public static void compressFile(File raw, File compressed) throws IOException {
        InputStream in = new FileInputStream(raw);
        OutputStream out = new DeflaterOutputStream(new FileOutputStream(compressed));
        shovelInToOut(in, out);
        in.close();
        out.close();
    }

    /**
     * Decompresses a zlib compressed file.
     */
    public static void decompressFile(File compressed, File raw) throws IOException {
        InputStream in = new InflaterInputStream(new FileInputStream(compressed));
        OutputStream out = new FileOutputStream(raw);
        shovelInToOut(in, out);
        in.close();
        out.close();
    }


    /**
     * Compresses a file with zlib compression.
     */
    public static byte[] compressBytes(byte[] raw) throws IOException {
        InputStream in = new ByteArrayInputStream(raw);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream out = new DeflaterOutputStream(baos);
        shovelInToOut(in, out);
        in.close();
        out.close();
        return baos.toByteArray();
    }

    public static byte[] tryCompressBytes(byte[] raw) {
        try {
            return compressBytes(raw);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Decompresses a zlib compressed file.
     */
    public static byte[] decompressBytes(byte[] raw) throws IOException {
        InputStream in = new InflaterInputStream(new ByteArrayInputStream(raw));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        shovelInToOut(in, out);
        in.close();
        out.close();
        return out.toByteArray();
    }

    public static byte[] tryDecompressBytes(byte[] raw) {
        try {
            return decompressBytes(raw);
        } catch (Exception e) {
        }

        return null;
    }
}
