package technomad.api.server.technomad.api.learing;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import technomad.api.server.technomad.core.analyze.ImageAnalyzeModel;
import technomad.api.server.technomad.core.dto.base.TechnomadResponseDto;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

@Tag(name = "Announcement Controller", description = "Announcement 관리 컨트롤러")
@RestController
@RequestMapping("/learn")
public class LearningController {
    private final ImageAnalyzeModel imageAnalyzeModel;
    public LearningController(ImageAnalyzeModel imageAnalyzeModel) {
        this.imageAnalyzeModel = imageAnalyzeModel;
    }

    @Operation(summary = "Model Leaner", description = "모델 학습을 위한 컨트롤러")
    @PostMapping("/upload")
    public ResponseEntity<TechnomadResponseDto<Double>> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        // 파일 저장 경로 설정 (저장 경로는 상황에 맞게 변경하세요.)
        BufferedImage image = ImageIO.read(file.getInputStream());
        int height = imageAnalyzeModel.getHEIGHT();
        int width = imageAnalyzeModel.getWIDTH();
        int channel = imageAnalyzeModel.getCHANNELS();
        int batchSize = imageAnalyzeModel.getBATCH_SIZE();

        // 이미지 resize
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();

        // 이미지 pixel값 추출
        ArrayList<Double> pixelValues = new ArrayList<>();
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                int[] pixel = resizedImage.getRaster().getPixel(j, i, new int[3]);
                pixelValues.add((double)pixel[0] / 255.0);
                pixelValues.add((double)pixel[1] / 255.0);
                pixelValues.add((double)pixel[2] / 255.0);
            }
        }

        // 추출한 pixel값을 INDArray 형태로 변환
        double[] pixels = new double[pixelValues.size()];
        for(int i=0; i<pixelValues.size(); i++){
            pixels[i] = pixelValues.get(i);
        }
        INDArray features = Nd4j.create(pixels, new int[]{batchSize, channel, height, width});

        // 이미지 분류
        INDArray predicted = imageAnalyzeModel.getModel().output(features, false);
        double[] probabilities = predicted.toDoubleVector();

        // 분류 결과를 백분율(%)로 변환하여 반환
        int maxIndex = -1;
        double maxProb = -1.0;
        for(int i=0; i<probabilities.length; i++){
            if(probabilities[i] > maxProb){
                maxProb = probabilities[i];
                maxIndex = i;
            }
        }
        double response = maxProb * 100.0;
        return TechnomadResponseDto.of(response);
    }

}
