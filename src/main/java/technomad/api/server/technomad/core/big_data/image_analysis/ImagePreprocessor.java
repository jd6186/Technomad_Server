package technomad.api.server.technomad.core.big_data.image_analysis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 이미지 데이터셋 전처리
 */
@Component
public class ImagePreprocessor {
    @Value("upload.learning_img")
    private String learningImagePath;
    @Value("upload.validation_img")
    private String validationImagePath;
    @Value("upload.learning_preprocessed_img")
    private String learningPreprocessedImagePath;
    @Value("upload.validation_preprocessed_img")
    private String validationPreprocessedImagePath;

    private File[] learningImages;
    private File[] validationImages;
    private final ImageLoader imageLoader;
    public ImagePreprocessor(ImageLoader imageLoader) throws IOException {
        this.imageLoader = imageLoader;
//        // 1. 훈련 이미지 데이터셋 주입
//        this.learningImages = this.imageLoader.getImageFiles(learningImagePath);
//        // 2. 검증 이미지 데이터셋 주입
//        this.validationImages = this.imageLoader.getImageFiles(validationImagePath);
        // 3. 기본적으로 가지고 있는 이미지 전처리
//        preprocessMultiImage();
    }

    /**
     * 서비스 실행 시 기본적으로 가지고 있던 모든 이미지 파일 전처리 작업 진행
     */
    private void preprocessMultiImage() throws IOException {
        int targetWidth = 300;
        int targetHeight = 300;
        for(File file : learningImages){
            preprocessImage(file, learningPreprocessedImagePath, targetWidth, targetHeight);
        }
        for(File file : validationImages){
            preprocessImage(file, validationPreprocessedImagePath, targetWidth, targetHeight);
        }
    }

    /**
     * 이미지 전처리 작업 진행
     * TODO - 이미지 검증 시 반드시 이 Method를 거친 이미지를 검증하도록 로직 구현 필요
     */
    public static void preprocessImage(File imageFile, String saveFilePath, int targetWidth, int targetHeight) throws IOException {
        // 이미지 로드
        BufferedImage originalImage = ImageIO.read(imageFile);

        // 이미지 크기 조정
        Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage processedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = processedImage.createGraphics();
        graphics.drawImage(resizedImage, 0, 0, null);
        graphics.dispose();

        // 색 공간 변환 (예시로 그레이스케일로 변환)
        BufferedImage grayscaleImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D grayscaleGraphics = grayscaleImage.createGraphics();
        grayscaleGraphics.drawImage(processedImage, 0, 0, null);
        grayscaleGraphics.dispose();

        // 전처리된 이미지 저장 (예시로 "_preprocessed" suffix를 파일명에 추가하여 저장)
        String outputPath = saveFilePath + File.separator + imageFile.getName().replaceFirst("[.][^.]+$", "_preprocessed.jpg");
        File outputImageFile = new File(outputPath);
        ImageIO.write(grayscaleImage, "jpg", outputImageFile);
    }
}