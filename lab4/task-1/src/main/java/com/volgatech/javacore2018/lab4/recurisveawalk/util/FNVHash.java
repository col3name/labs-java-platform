package com.volgatech.javacore2018.lab4.recurisveawalk.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.logging.Logger;

public class FNVHash implements HashCalculator {
    private static final Logger LOG = Logger.getLogger(FNVHash.class.getName());
    private static final BigInteger FNV_INIT_32 = new BigInteger("811C9DC5", 16);
    private static final BigInteger PRIME_32 = new BigInteger("01000193", 16);
    private static final BigInteger MOD_32 = new BigInteger("2").pow(32);

    @Override
    public BigInteger calculate(InputStream fileInputStream) {
        byte[] data = new byte[100];
        BigInteger hash = FNV_INIT_32;

        while (true) {
            try {
                if (fileInputStream.read(data) == -1) break;
            } catch (IOException e) {
                LOG.info(e.getMessage());
            }

            hash = computeHashOfBlock(data, hash);
        }

        return hash;
    }

    @Override
    public BigInteger computeHashOfBlock(byte[] data, BigInteger hash) {
        for (byte aByte : data) {
            hash = hash.multiply(PRIME_32).pow(aByte).mod(MOD_32);
            hash = hash.xor(BigInteger.valueOf((int) aByte & 0xff));
        }

        return hash;
    }
}
