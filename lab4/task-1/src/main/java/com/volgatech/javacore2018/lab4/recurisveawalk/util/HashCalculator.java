package com.volgatech.javacore2018.lab4.recurisveawalk.util;

import java.io.InputStream;
import java.math.BigInteger;

public interface HashCalculator {
    BigInteger calculate(InputStream fileInputStream);

    BigInteger computeHashOfBlock(byte[] data, BigInteger hash);
}
