import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    BinaryTree<String> bTree = new BinaryTree<String>();
    String[] filePathStrings = {"a.txt", "b.txt"};
    
    for (String filepath : filePathStrings) {
      try {
        bTree.readBinaryTree1(new BufferedReader(new FileReader(filepath)));
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
   
  }

}
