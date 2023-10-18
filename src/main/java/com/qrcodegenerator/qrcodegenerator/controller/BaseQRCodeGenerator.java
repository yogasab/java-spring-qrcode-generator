package com.qrcodegenerator.qrcodegenerator.controller;

import java.io.IOException;

import com.google.zxing.WriterException;

public abstract class BaseQRCodeGenerator {
    public abstract void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException;

    public abstract byte[] getQRCodeImageByte(String text, int width, int height) throws WriterException, IOException;
}