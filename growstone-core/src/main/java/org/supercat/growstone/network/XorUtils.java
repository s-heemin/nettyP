package org.supercat.growstone.network;

public class XorUtils {
        private static final byte XOR_KEY = (byte)0x87;

        public static byte[] xorEncode(byte[] data) {
            byte[] encoded = new byte[data.length];
            for (int i = 0; i < data.length; i++) {
                encoded[i] = (byte) (data[i] ^ XOR_KEY);
            }
            return encoded;
        }

        public static byte[] xorDecode(byte[] data) {
            return xorEncode(data);
        }

}
