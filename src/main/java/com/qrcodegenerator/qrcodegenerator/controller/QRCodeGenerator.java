package com.qrcodegenerator.qrcodegenerator.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

enum QRCodeFormat {
    PNG, JPG, GIF
}

@FunctionalInterface
interface QRCodeImageGenerator {
    void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException;
}
// Inheritance
class QRCodeGenerator extends BaseQRCodeGenerator {
    private final QRCodeImageGenerator imageGenerator;

    public QRCodeGenerator() {
        // Lambda expression
        this.imageGenerator = (text, width, height, filePath) -> {
            try {
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
                Path path = FileSystems.getDefault().getPath(filePath);

                String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1).toUpperCase();

                switch (QRCodeFormat.valueOf(fileExtension)) {
                    case PNG:
                        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
                        break;
                    case JPG:
                        MatrixToImageWriter.writeToPath(bitMatrix, "JPG", path);
                        break;
                    case GIF:
                        MatrixToImageWriter.writeToPath(bitMatrix, "GIF", path);
                        break;
                    default:
                        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
                        break;
                }
            } catch (WriterException | IOException e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        imageGenerator.generateQRCodeImage(text, width, height, filePath);
    }

    @Override
    public byte[] getQRCodeImageByte(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig config = new MatrixToImageConfig(0xFF000002, 0xFFFFC041);

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, config);
        return pngOutputStream.toByteArray();
    }
}
