import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// Node class for Huffman Tree
class HuffmanNode implements Comparable<HuffmanNode> {
    int frequency;
    char symbol;
    HuffmanNode left, right;

    HuffmanNode(char symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
        this.symbol = '\0'; // non-leaf node
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.frequency, other.frequency);
    }
}

public class HuffmanCoding {
    // Build the Huffman tree
    private static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencies) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            priorityQueue.add(parent);
        }

        return priorityQueue.poll();
    }

    // Generate the Huffman codes
    private static void generateHuffmanCodes(HuffmanNode node, String prefix, Map<Character, String> codeMap) {
        if (node == null) {
            return;
        }

        // If it's a leaf node
        if (node.symbol != '\0') {
            codeMap.put(node.symbol, prefix);
        } else {
            generateHuffmanCodes(node.left, prefix + "0", codeMap);
            generateHuffmanCodes(node.right, prefix + "1", codeMap);
        }
    }

    // Encode the given text using the Huffman codes
    public static String encode(Map<Character, String> codeMap, String text) {
        StringBuilder encodedText = new StringBuilder();
        for (char symbol : text.toCharArray()) {
            encodedText.append(codeMap.get(symbol));
        }
        return encodedText.toString();
    }

    // Decode the encoded text using the Huffman tree
    public static String decode(HuffmanNode root, String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode currentNode = root;

        for (char bit : encodedText.toCharArray()) {
            if (bit == '0') {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }

            if (currentNode.left == null && currentNode.right == null) {
                decodedText.append(currentNode.symbol);
                currentNode = root;
            }
        }

        return decodedText.toString();
    }

    // Main function demonstrating the Huffman Coding
    public static void main(String[] args) {
        // Example input frequencies from the slides
        Map<Character, Integer> frequencies = new HashMap<>();
        frequencies.put('A', 30);
        frequencies.put('B', 10);
        frequencies.put('C', 25);
        frequencies.put('D', 60);
        frequencies.put('E', 70);
        frequencies.put('F', 15);
        frequencies.put('G', 5);

        // Build the Huffman tree
        HuffmanNode root = buildHuffmanTree(frequencies);

        // Generate the Huffman codes
        Map<Character, String> codeMap = new HashMap<>();
        generateHuffmanCodes(root, "", codeMap);

        // Display the Huffman codes
        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : codeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Encode a sample text
        String text = "ABCDE";
        String encodedText = encode(codeMap, text);
        System.out.println("\nEncoded Text: " + encodedText);

        // Decode the encoded text
        String decodedText = decode(root, encodedText);
        System.out.println("Decoded Text: " + decodedText);
    }
}