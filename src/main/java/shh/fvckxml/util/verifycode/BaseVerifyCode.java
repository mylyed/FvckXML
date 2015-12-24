package shh.fvckxml.util.verifycode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BaseVerifyCode implements IVerifyCode {
    private static BaseVerifyCode baseVerifyCode;

    public static BaseVerifyCode getInstance() {
        if (baseVerifyCode == null) {
            synchronized (BaseVerifyCode.class) {
                BaseVerifyCode t = baseVerifyCode;
                if (t == null) {
                    t = new BaseVerifyCode();
                    baseVerifyCode = t;
                }
            }
        }
        return baseVerifyCode;
    }

    /**
     * 图片宽度
     */
    private static int width = 70;
    /**
     * 图片高度
     */
    private static int height = 22;
    /**
     * 验证码个数
     */
    private static int codeNum = 4;
    static Random r = new Random();

    /**
     * 获取颜色-- 需要颜色值的范围
     * 
     * @param low 最低的色度
     * @param high 最高的色
     * @return
     */
    public static Color getColor(int low, int high) {
        return new Color(low + r.nextInt(high - low) + 1, low + r.nextInt(high - low) + 1,
                low + r.nextInt(high - low) + 1);
    }

    private static final char[] seeds =
            "QWERTYUIPASDFGHJKLZXCVBNMqwertyuipasdfghjklzxcvbnm1234578".toCharArray();

    public ImageAndCode getImageAndCode() {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图像的处理接口
        Graphics2D graphics = (Graphics2D) bi.getGraphics();

        // 创建图片背景色
        Color color = getColor(100, 155);
        // 设置图片颜色
        graphics.setColor(color);
        // 设置背景颜色
        graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());

        int interference = r.nextInt(2);
        switch (interference) {
            case 0:
                diawLines(graphics);
                break;
            case 1:
                fillOval(graphics);
                break;
            default:
                fillOval(graphics);
        }

        int len = seeds.length;
        StringBuffer sb = new StringBuffer();
        int index = -1;
        for (int i = 0; i < codeNum; i++) {
            // 为了不连续出现相同的字母
            int ti;
            do {
                ti = r.nextInt(len);
            } while (ti == index);
            index = ti;
            // 生成单个验证码
            String code = String.valueOf(seeds[index]);

            Font font = new Font("宋体", Font.BOLD, 19 + r.nextInt(7));
            graphics.setFont(font);
            graphics.setColor(getColor(0, 100));
            graphics.drawString(code, 15 * i, 20);
            sb.append(code);
        }


        ImageAndCode imageAndCode = new ImageAndCode();
        imageAndCode.code = sb.toString();
        imageAndCode.image = bi;
        return imageAndCode;
    }

    // 画干扰线
    private static void diawLines(Graphics graphics) {
        for (int i = 0; i < 5 + r.nextInt(30); i++) {
            graphics.setColor(getColor(0, 255));
            graphics.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width),
                    r.nextInt(height));
        }
    }

    // 画干扰圈
    private static void fillOval(Graphics graphics) {
        for (int i = 0; i < 20; i++) {
            graphics.setColor(getColor(150, 255));
            graphics.fillOval(r.nextInt(width), r.nextInt(height), r.nextInt(width),
                    r.nextInt(height));
        }
    }
}
