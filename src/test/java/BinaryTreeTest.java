import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author A0108165J
 *
 */
public class BinaryTreeTest {

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
   * Test method for {@link BinaryTree#readBinaryTree1(java.io.BufferedReader)}.
   */
  @Test
  public void testReadBinaryTree1() {
    BinaryTree<String> bTree;
    String[] filePathStrings = {"in1", "in2", "in3", "in4", "in5", "in6", "in7"};
    
    for (String filepath : filePathStrings) {
      try {
        bTree = BinaryTree.readBinaryTree1(new BufferedReader(new FileReader("in/" + filepath + ".txt")));
        _writeToFile("out/out" + filepath.substring(2) + ".txt", bTree);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
   
  }

  public static void _writeToFile(String filename, BinaryTree<String> binaryTree) throws IOException {
    FileWriter fWriter = new FileWriter(filename, false);
    BufferedWriter out = new BufferedWriter(fWriter);
    
    Boolean isCorrect = BinaryTree.isValid(binaryTree);
    if (!isCorrect) {
      out.write("not complete\n");
    }
    out.write(binaryTree.levelorderToString());
    out.close();
  }

}
