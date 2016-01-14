package io.github.mylyed.gravy.util.verifycode;

import java.awt.image.BufferedImage;

public interface IVerifyCode {

    ImageAndCode getImageAndCode();

   public static class ImageAndCode {
        public BufferedImage image;
        public String code;
    }
}
