package technomad.api.server.technomad.core.analyze;
import lombok.Getter;
import org.datavec.api.io.labels.ParentPathLabelGenerator;
import org.datavec.api.split.FileSplit;
import org.datavec.image.recordreader.ImageRecordReader;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.*;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ImageAnalyzeModel{
    @Getter
    private MultiLayerNetwork model;
    @Getter
    private final int HEIGHT = 28;  // 이미지 높이
    @Getter
    private final int WIDTH = 28;  // 이미지 넓이
    @Getter
    private final int CHANNELS = 3;  // 이미지 채널 수(RGB, Grayscale ...)
    @Getter
    private final int BATCH_SIZE = 32;  // 한번에 처리하는 데이터 수 - i7의 경우 코어(쓰레드) 수와 클럭 속도에 따라 처리 속도가 크게 차이납니다. 하드웨어 환경마다 다를 수 있지만, 대부분의 경우 GPU를 사용하지 않는 경우에는 batchSize 값을 32 또는 64 정도로 설정하면 적당합니다.
    private final int NUM_LABELS = 5; // 학습 시킬 이미지 수
    private final int OUTPUT_NUM = 5; // number of labels (classes)
    private final int SEED = 123; // seed for repeatability

    public ImageAnalyzeModel() throws IOException {
        // 1. 학습 및 Validation Check 용 이미지 데이터셋 생성
        String trainImgDirPath = "{/train/directory}";
        String validationImgDirPath = "{/validation/directory}";
        DataSetIterator trainData = getImgData(trainImgDirPath);
        DataSetIterator validationData = getImgData(validationImgDirPath);
        if(trainData == null || validationData == null) return;

        // 2. Convolutional Neural Network Model 생성 - CNN 모델을 생성합니다. 여기서는 ConvolutionLayer 클래스와 SubsamplingLayer 클래스, DenseLayer 클래스 등을 사용하여 모델의 레이어를 정의합니다.
        // set the hyperparameters of the CNN model
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(SEED) // seed는 난수 생성기(Random Number Generator)
                .l2(0.0005)
                .activation(Activation.RELU)
                .updater(new Adam(0.0001))
                .list()
                .layer(new ConvolutionLayer.Builder(5, 5)
                        .nIn(CHANNELS)
                        .stride(1, 1)
                        .nOut(20)
                        .activation(Activation.IDENTITY)
                        .build())
                .layer(new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
                        .kernelSize(2,2)
                        .stride(2,2)
                        .build())
                .layer(new DenseLayer.Builder().activation(Activation.RELU)
                        .nOut(500).build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nOut(OUTPUT_NUM)
                        .activation(Activation.SOFTMAX)
                        .build())
                .setInputType(InputType.convolutionalFlat(28, 28, 3)) // for input types such as images
                .build();

        // 3. Convolutional Neural Network Model 컴파일 - CNN 모델을 컴파일합니다.
        // 여기서는 NeuralNetConfiguration.Builder() 클래스를 사용하여 모델의 하이퍼파라미터를 정의하고, MultiLayerNetwork 클래스를 사용하여 모델을 컴파일합니다.
        createModel(conf);

        // 4. Convolutional Neural Network Model 훈련 - fit() 메소드를 사용하여 모델을 훈련합니다.
        // epochs - 모델의 학습 주기 회수 10이면 모든 데이터를 10번 반복학습합니다.
        trainModel(trainData);

        // 5. Convolutional Neural Network Model 검증 - Evaluation 클래스를 사용하여 모델의 정확도 등을 평가합니다.
        evaluationModel(validationData);
    }

    private DataSetIterator getImgData(String dirPath) throws IOException {
        try{
            File parentDir = new File(dirPath);
            ImageRecordReader trainImageRecordReader = new ImageRecordReader(HEIGHT, WIDTH, CHANNELS, new ParentPathLabelGenerator());
            trainImageRecordReader.initialize(new FileSplit(parentDir));
            return new RecordReaderDataSetIterator(trainImageRecordReader, BATCH_SIZE, 1, NUM_LABELS);
        }catch (Exception e){
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    private void createModel(MultiLayerConfiguration conf) {
        model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(1));
    }

    private void trainModel(DataSetIterator trainData) {
        int epochs = 50;
        for (int i = 0; i < epochs; i++){
            while (trainData.hasNext())
                model.fit(trainData.next());
            trainData.reset();
        }
    }

    private void evaluationModel(DataSetIterator validData) {
        Evaluation eval = new Evaluation(NUM_LABELS);
        while (validData.hasNext())
        {
            DataSet row = validData.next();
            INDArray features = row.getFeatures();
            INDArray label = row.getLabels();
            INDArray predicted = model.output(features, false);
            eval.eval(label, predicted);
        }
    }
}