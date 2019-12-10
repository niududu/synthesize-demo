package com.example.demo1.utils.code;

import com.example.demo1.utils.StringUtils;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Description: 生成条形码工具类
 * @Author: LiuZW
 * @CreateDate: 2019/11/5/005 15:52
 * @Version: 1.0
 */
public class BarCodeUtil {


    public static void main(String[] args) {
        String msg = "911187093187";
        String base64 = generateBase64(msg);
        System.out.println(base64);
    }
    /**
     * 生成文件
     *
     * @param msg
     * @param path
     * @return
     */
    public static File generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    /**
     * 生成字节
     *
     * @param msg
     * @return
     */
    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        return ous.toByteArray();
    }

    public static String generateBase64(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        String base64 = generate(msg, ous);
        return base64;
    }

    /**
     * 生成到流
     *
     * @param msg
     * @param ous
     */
    public static String generate(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return "";
        }
        Code128Bean bean = new Code128Bean();

        // 精细度
        final int dpi = 512;
        // module宽度
        //final double moduleWidth = UnitConv.in2mm(1.0f / dpi);

        // 配置对象
        bean.setModuleWidth(0.3D);
        //bean.setWideFactor(3);
        // 设置两侧是否留白
        bean.doQuietZone(true);
        bean.setBarHeight(10.0D);
        String format = "image/png";
        try {

            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);

            // 生成条形码
            bean.generateBarcode(canvas, msg);

            // 结束绘制
            canvas.finish();
            BufferedImage bufferedImage = canvas.getBufferedImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos); // 第二个参数“jpg”不需要修改
            BASE64Encoder encoder = new BASE64Encoder();
            byte[] bytes = baos.toByteArray();
            String base64String = encoder.encodeBuffer(bytes).trim();
            base64String = base64String.replaceAll("\n", "").replaceAll("\r", "");// 删除 \r\n
            return base64String;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
