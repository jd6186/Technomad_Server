package technomad.api.server.technomad.core.big_data.image_analysis;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;

/**
 * 사전 훈련 모델
 * 대규모 이미지 데이터셋으로 미리 훈련된 모델을 사용하는 방법도 많이 활용
 * 예를 들어, ImageNet 데이터셋으로 사전 훈련된 VGGNet, ResNet, Inception 등의 모델은 다양한 이미지 분류, 객체 감지, 세분화 작업에 사용
 */
public class PretrainedModel {
    @Value("upload.learning_preprocessed_img")
    private String learningImagePath;
    @Value("upload.validation_preprocessed_img")
    private String validationImagePath;
    private File[] learningImages;
    private File[] validationImages;

    private final ImageLoader imageLoader;
    public PretrainedModel(ImageLoader imageLoader) throws IOException {
        this.imageLoader = imageLoader;
        // 1. 전처리된 훈련 이미지 데이터셋 주입
        this.learningImages = this.imageLoader.getImageFiles(learningImagePath);
        // 2. 전처리된 검증 이미지 데이터셋 주입
        this.validationImages = this.imageLoader.getImageFiles(validationImagePath);
    }


    // TODO - 3. 이미지 검증 알고리즘 구현
    //  - 종량제 전체 L수 확인 알고리즘
    //  - 종량제 채운 L를 % 단위로 확인하는 알고리즘
    
    // TODO - 4. Model 훈련
    // TODO - 5. Model 검증
    
    // TODO - 6. Model 검증 성공 시 실제 이미지 데이터를 통해 Model 활용
}
