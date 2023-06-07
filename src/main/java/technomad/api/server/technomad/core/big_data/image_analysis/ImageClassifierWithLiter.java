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
 * TODO - 몇리터나 채웠는지 % 구하는거 만들어야함 - 아래는 그냥 ImageClassifierWithNumber 내용이라 구조만 동일하게 하고 내용을 바꿔야함
 */
public class ImageClassifierWithLiter {
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
