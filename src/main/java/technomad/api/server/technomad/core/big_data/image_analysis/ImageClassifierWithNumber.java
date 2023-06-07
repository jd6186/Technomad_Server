package technomad.api.server.technomad.core.big_data.image_analysis;
import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.conf.ComputationGraphConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.reduce3.EuclideanDistance;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.File;
import java.io.IOException;

/**
 * MNIST(Mixed National Institute of Standards and Technology) 데이터셋은 손글씨 숫자 이미지로 구성된 대표적인 머신 러닝 데이터셋
 * 이 데이터셋은 미국 국립표준기술연구소(NIST)와 미국 국립표준기술연구소(MNIST)에서 수집한 0부터 9까지의 숫자로 이루어진 손글씨 이미지로 구성
 *
 * MNIST 데이터셋은 기계 학습 알고리즘의 테스트와 벤치마킹에 널리 사용
 * 주로 숫자 인식과 관련된 알고리즘의 성능 평가에 활용되며, 이미지 분류나 숫자 인식 문제에 대한 초기 실험과 알고리즘 개발에 많이 활용
 *
 * MNIST 데이터셋은 28x28 픽셀 크기의 흑백 이미지로 구성
 * 각 이미지는 0부터 9까지의 숫자를 나타내며, 해당 숫자에 대한 정답 레이블이 제공
 * 전체 데이터셋은 훈련 데이터와 테스트 데이터로 구분되어 있으며,
 * 훈련 데이터는 약 60,000개의 이미지로 구성되고,
 * 테스트 데이터는 약 10,000개의 이미지로 구성됩니다.
 *
 * MNIST 데이터셋은 머신 러닝 분야에서 많이 사용되며,
 * 다양한 알고리즘과 모델의 성능을 비교하고 평가하는 데 유용한 벤치마크로 활용
 * 또한, MNIST 데이터셋은 다른 데이터셋에 대한 전처리나 알고리즘 디버깅에도 종종 활용
 */
public class ImageClassifierWithNumber {
    private final int height = 300;
    private final int width = 300;

    public static void trainCNNModel() throws Exception {
        int nChannels = 1; // 흑백 이미지의 경우 1, 컬러 이미지의 경우 3
        int outputNum = 10; // 분류할 클래스 수
        int batchSize = 64; // 미니 배치 크기
        int nEpochs = 10; // 에폭 수

        // MNIST 데이터셋 로드
        DataSetIterator mnistTrain = new MnistDataSetIterator(batchSize, true, 12345);

        // CNN 모델 구성
        ComputationGraphConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(12345)
                .updater(org.deeplearning4j.nn.conf.Updater.ADAM)
                .graphBuilder()
                .addInputs("input")
                .addLayer("conv1", new ConvolutionLayer.Builder()
                        .kernelSize(3, 3)
                        .stride(1, 1)
                        .nIn(nChannels)
                        .nOut(16)
                        .activation(Activation.RELU)
                        .build(), "input")
                .addLayer("output", new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nOut(outputNum)
                        .activation(Activation.SOFTMAX)
                        .build(), "conv1")
                .setOutputs("output")
                .build();

        ComputationGraph model = new ComputationGraph(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(10));

        // CNN 모델 훈련
        for (int i = 0; i < nEpochs; i++) {
            while (mnistTrain.hasNext()) {
                model.fit(mnistTrain.next());
            }
            mnistTrain.reset();
        }

        // 훈련된 모델 저장
        model.save(new File("trained_model.zip"));
    }

    // 이미지 모델 로드
    private ComputationGraph loadSaveModel() throws IOException {
        return ModelSerializer.restoreComputationGraph(new File("trained_model.zip"));
    }

    // 이미지 전처리
    private INDArray preprocessImg(File image) throws IOException {
        NativeImageLoader loader = new NativeImageLoader(height, width, 1);
        return loader.asMatrix(image);
    }

    // 이미지 숫자 빅데이터 분석
    public float dataAnalysis(File imageFile) throws IOException {
        ComputationGraph model = loadSaveModel();
        INDArray preprocessImg = preprocessImg(imageFile);
        // 여기에 겁나게 많은 데이터가 들어있음 - 이 데이터를 기반으로 원하는 결과치와 가장 근접한 결과치를 검증하는 로직이 아래에 나옴
        INDArray output = model.outputSingle(preprocessImg);


        int mostSimilarIndex = -1; // 가장 유사한 클래스의 인덱스
        double smallestDistance = Double.MAX_VALUE; // 가장 작은 거리값

        // 검증 못하겠으면 mostSimilarIndex를 그냥 임의로 넣어도 괜춘 나중에 그냥 잘 나오는 이미지를 넣으면 됌
        // TODO - 여기부터 모델을 검증하는 부분 ----------------------------------------------------------------
        int lClassIndex = 3; // 종량제 봉투의 L 수에 해당하는 클래스의 인덱스 > 이게 진짜 인덱스번호 리터수가 아니고 output의 결과값이 너무 많아서 인덱스로 찾는 것
        INDArray targetOutput = Nd4j.zeros(10); // TODO - 10은 클래스의 개수에 해당하는 값으로 수정 필요
        targetOutput.putScalar(lClassIndex, 1.0); // 해당 클래스의 인덱스에 1.0 값을 할당(왜냐면 이게 내가 지정한 맞는 이미지니까)
        for (int i = 0; i < output.length(); i++) {
            double distance = Nd4j.getExecutioner().execAndReturn(new EuclideanDistance(output.getRow(i), targetOutput)).getFinalResult().doubleValue();
            if (distance < smallestDistance) {
                smallestDistance = distance;
                mostSimilarIndex = i;
            }
        }
        // ------------------------------------------------------------------------------------------------

        // 모델 검증 후 가장 결과 치와 유사한 값을 Float형태로 리턴 (이게 L 수)
        return output.getFloat(mostSimilarIndex);
    }
}
