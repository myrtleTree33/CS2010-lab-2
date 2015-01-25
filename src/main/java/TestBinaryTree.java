import java.io.*;

/**
 *  Class to test the BinaryTree class
 */
public class TestBinaryTree {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BinaryTree<String> tree = BinaryTree.readBinaryTree1(br);
        System.out.println(tree);
        System.out.println("levelorder:" + tree.levelorderToString()); 
    }
}
