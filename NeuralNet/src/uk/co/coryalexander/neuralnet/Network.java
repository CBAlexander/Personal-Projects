package uk.co.coryalexander.neuralnet;

import java.util.Arrays;

/**
 * @author Cory Alexander
 */

public class Network {

    private double[][] outputs; // Stores outputs of each neuron. D1: Layer D2: Neuron
    private double[][][] weights; // Stores weights of each neuron. D1: Layer D2: Neuron D3: Neuron from prev layer
    private double[][] bias; // Stores the bias to apply to each neuron. D1: Layer D2: Neuron

    public final int[] NETWORK_LAYER_SIZES; // Array for the amount of neurons on each layer.
    public final int INPUT_SIZE; // Size of the input layer
    public final int OUTPUT_SIZE; // Size of the output layer
    public final int NETWORK_SIZE; // How many layers we have

    public Network(int... NETWORK_LAYER_SIZES) {
        this.NETWORK_LAYER_SIZES = NETWORK_LAYER_SIZES;
        this.INPUT_SIZE = NETWORK_LAYER_SIZES[0];
        this.NETWORK_SIZE = NETWORK_LAYER_SIZES.length;
        this.OUTPUT_SIZE = NETWORK_LAYER_SIZES[NETWORK_SIZE - 1]; // -1 because array begins at 0, length function starts counting from 1


        /*
            Note that I have not initialised the 2nd or 3rd dimensions on the arrays below.
            These dimensions will be initialised later, as they have differing sizes for each element of the
            1st dimension.
         */

        this.outputs = new double[NETWORK_SIZE][];
        this.weights = new double[NETWORK_SIZE][][];
        this.bias = new double[NETWORK_SIZE][];

        for(int i = 0; i < NETWORK_SIZE; i++) {
            this.outputs[i] = new double[NETWORK_LAYER_SIZES[i]]; // Here I initialise the 2nd dimension of outputs. The size of the dimension is the amount of neurons at that layer.
            this.bias[i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i], 0.3, 0.7); // Initialise the bias of each neuron to a random value between 0.3 and 0.7.

            // Here I check to see if i > 0. This is because I want to make a weight for every neuron EXCEPT those in the first layer.
            if(i > 0) {
                weights[i] = NetworkTools.createRandomArray(NETWORK_LAYER_SIZES[i], NETWORK_LAYER_SIZES[i - 1], -0.3, 0.5);
            }
        }
    }

    /**
     * Function to calculate the output of neurons.
     * @param input - Array of inputs
     * @return Values of the output layer after calculation
     */
    public double[] calculate(double... input) {
        if(input.length != this.INPUT_SIZE) return null; // If we have too few, or too many, inputs then we will return null;

        this.outputs[0] = input; // If we are on the first layer, we have no output to calculate so the output becomes the input we are given.

        /*
            Here we iterate through the layers and then the neurons. For every neuron we iterate through the neurons in the previous layer.
            We then sum up the output of the neuron at the previous layer, multiplied by the weight connecting that neuron to our current working neuron.
            We will then add the bias to the sum.

            This sum is then applied to our activation function and its output is saved in the outputs array.

            The function then returns the final layer outputs.
         */
        for(int layer = 1; layer < NETWORK_SIZE; layer++) {
            for(int neuron = 0; neuron < NETWORK_LAYER_SIZES[layer]; neuron++) {

                double sum = 0;
                for(int prevNeuron = 0; prevNeuron < NETWORK_LAYER_SIZES[layer - 1]; prevNeuron++) {
                    sum += outputs[layer - 1][prevNeuron] * weights[layer][neuron][prevNeuron];
                }

                sum += bias[layer][neuron];

                outputs[layer][neuron] = sigmoid(sum);
            }
        }

        return outputs[NETWORK_SIZE - 1];
    }

    /**
     * This function acts as the activator function for our neural network. It uses the given value x to calculate the corresponding sigmoid function.
     * @param x Value to be used in sigmoid function
     * @return Calculated value using x in the sigmoid function
     */
    private double sigmoid(double x) {
        return 1D / (1 + Math.exp(-x));
    }

    public static void main(String[] args) {
        Network network = new Network(4, 1, 3, 4);
        double[] testOut = network.calculate(0.3, 0.4, 0.6, 0.7);
        System.out.println(Arrays.toString(testOut));
    }

}
