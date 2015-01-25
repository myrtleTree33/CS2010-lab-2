import java.io.*;
import java.util.*;

import javax.swing.text.Element;
import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import org.junit.Ignore;

/**
 * Class for a binary tree that stores type E objects. Node is a public class.
 **/
public class BinaryTree<E> implements Serializable {

  // Data Field
  /** The root of the binary tree */
  protected Node<E> root;

  /** Construct an empty BinaryTree */
  public BinaryTree() {
    root = null;
  }

  /**
   * Construct a BinaryTree with a specified root. Should only be used by
   * subclasses.
   * 
   * @param root
   *          The node that is the root of the tree.
   */
  protected BinaryTree(Node<E> root) {
    this.root = root;
  }

  /**
   * Constructs a new binary tree with data in its root,leftTree as its left
   * subtree and rightTree as its right subtree.
   */
  public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
    root = new Node<E>(data);
    if (leftTree != null) {
      root.left = leftTree.root;
    } else {
      root.left = null;
    }
    if (rightTree != null) {
      root.right = rightTree.root;
    } else {
      root.right = null;
    }
  }

  /**
   * Return the left subtree.
   * 
   * @return The left subtree or null if either the root or the left subtree is
   *         null
   */
  public BinaryTree<E> getLeftSubtree() {
    if (root != null && root.left != null) {
      return new BinaryTree<E>(root.left);
    } else {
      return null;
    }
  }

  /**
   * Return the right sub-tree
   * 
   * @return the right sub-tree or null if either the root or the right subtree
   *         is null.
   */
  public BinaryTree<E> getRightSubtree() {
    if (root != null && root.right != null) {
      return new BinaryTree<E>(root.right);
    } else {
      return null;
    }
  }

  /**
   * Return the data field of the root
   * 
   * @return the data field of the root or null if the root is null
   */
  public E getData() {
    if (root != null) {
      return root.data;
    } else {
      return null;
    }
  }

  /**
   * Determine whether this tree is a leaf.
   * 
   * @return true if the root has no children
   */
  public boolean isLeaf() {
    return (root == null || (root.left == null && root.right == null));
  }

  public int height() {
    return height(root);
  }

  public int height(Node<E> r) {
    if (r == null)
      return 0;
    return 1 + Math.max(height(r.left), height(r.right));
  }

  public int size() {
    return size(root);
  }

  public int size(Node<E> r) {
    if (r == null)
      return 0;
    return 1 + size(r.left) + size(r.right);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    preOrderTraverse(root, 1, sb);
    return sb.toString();
  }

  /**
   * Perform a preorder traversal.
   * 
   * @param node
   *          The local root
   * @param depth
   *          The depth
   * @param sb
   *          The string buffer to save the output
   */
  private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
    for (int i = 1; i < depth; i++) {
      sb.append("  ");
    }
    if (node == null) {
      sb.append("null\n");
    } else {
      sb.append(node.toString());
      sb.append("\n");
      preOrderTraverse(node.left, depth + 1, sb);
      preOrderTraverse(node.right, depth + 1, sb);
    }
  }

  /*
   * Implement the following method to read the path encodings and create the
   * nodes.
   * 
   * Sample Input
   * 
   * (11,LL) (7,LLL) (8,R) (5,) (4,L) (13,RL) (2,LLR) (1,RRR) (4,RR) ()
   */
  public static BinaryTree<String> readBinaryTree1(BufferedReader bR)
      throws Exception {
    StringBuilder sBuilder = new StringBuilder();
    String currLine = "";
    while ((currLine = bR.readLine()) != null) {
      sBuilder.append(" ");
      sBuilder.append(currLine);
    }
    
    ArrayList<NodePath> paths = _convertStrToTreeMap(sBuilder.toString());
    BinaryTree<String> binaryTree = new BinaryTree<String>(new Node<String>("-1"));
    for (int i = 0; i < paths.size(); i++) {
      NodePath curr = paths.get(i);
      System.out.println(curr);
      
      if (i == 0) {
        if (curr.path.length() == 0) {
          binaryTree = new BinaryTree<String>(new Node<String>(curr.data));
        }
      }
      insertData(curr.data, curr.path, binaryTree.root);
    }
    _printDspStatements(binaryTree);
    return new BinaryTree<String>();
  }
  
  public static void _printDspStatements(BinaryTree<String> binaryTree) {
    Boolean isCorrect = isValid(binaryTree);
    if (!isCorrect) {
      System.out.println("not complete");
    }
    System.out.println(binaryTree.levelorderToString());
    
  }
  
  public static Boolean isValid(BinaryTree<String> bTree) {
    if (bTree.root == null) {
      return false;
    }
    return _checkValid(bTree.root);
  }
  
  private static Boolean _checkValid(Node<String> node) {
    if (node.data.equals("-1")) {
      return false;
    }
    if (node.left != null) {
      _checkValid(node.left);
    }

    if (node.right!= null) {
      _checkValid(node.right);
    }
    
    return true;
  }
  
  private static void insertData(String data, String path, Node<String> currNode) {
    if (path.length() == 0) {
      currNode.data = data;
      return;
    }
    if (path.charAt(0) == 'L') {
      if (currNode.left == null) {
        currNode.left = new Node<String>("-1");
      }
      insertData(data, path.substring(1), currNode.left);

    } else if (path.charAt(0) == 'R') {
      if (currNode.right == null) {
        currNode.right = new Node<String>("-1");
      }
      insertData(data, path.substring(1), currNode.right);
    }
  }

  private static ArrayList<NodePath> _convertStrToTreeMap(String dataStr) {
    String raw = dataStr.replaceAll("\\(", "")
                        .replaceAll("\\)", "");
    List<String> nodesList = Arrays.asList(raw.split(" "));
    ArrayList<NodePath> paths = new ArrayList<NodePath>();
    for (int i = 0; i < nodesList.size(); i++) {
      if (nodesList.get(i)
                   .trim()
                   .length() > 0) {
        String[] elem = nodesList.get(i)
                                 .split(",");
        String data = elem[0].trim();
        String path;
        if (elem.length > 1) {
          path = elem[1].trim();
        } else {
          path = "";
        }
        paths.add(new NodePath(data, path));
      }
    }
    _sortTreeMapByPath(paths);
    return paths;
  }


  private static void _sortTreeMapByPath(ArrayList<NodePath> np) {
    Collections.sort(np, new Comparator<NodePath>() {
      public int compare(NodePath p1, NodePath p2) {
        return p1.path.length() - p2.path.length();
      }
    });
  }

  /**
   * Method to return the preorder traversal of the binary tree as a sequence of
   * strings each separated by a space.
   * 
   * @return A preorder traversal as a string
   */
  public String preorderToString() {
    StringBuilder stb = new StringBuilder();
    preorderToString(stb, root);
    return stb.toString();
  }

  private void preorderToString(StringBuilder stb, Node<E> root) {
    stb.append(root);
    if (root.left != null) {
      stb.append(" ");
      preorderToString(stb, root.left);
    }
    if (root.right != null) {
      stb.append(" ");
      preorderToString(stb, root.right);
    }
  }

  /**
   * Method to return the postorder traversal of the binary tree as a sequence
   * of strings each separated by a space.
   * 
   * @return A postorder traversal as a string
   */
  public String postorderToString() {
    StringBuilder stb = new StringBuilder();
    postorderToString(stb, root);
    return stb.toString();
  }

  private void postorderToString(StringBuilder stb, Node<E> root) {
    if (root.left != null) {
      postorderToString(stb, root.left);
      stb.append(" ");
    }
    if (root.right != null) {
      postorderToString(stb, root.right);
      stb.append(" ");
    }
    stb.append(root);
  }

  /**
   * A method to display the inorder traversal of a binary tree placeing a left
   * parenthesis before each subtree and a right parenthesis after each subtree.
   * For example the expression tree shown in Figure 6.12 would be represented
   * as (((x) + (y)) * ((a) / (b))).
   * 
   * @return An inorder string representation of the tree
   */
  public String inorderToString() {
    StringBuilder stb = new StringBuilder();
    inorderToString(stb, root);
    return stb.toString();
  }

  private void inorderToString(StringBuilder stb, Node<E> root) {
    if (root.left != null) {
      stb.append("(");
      inorderToString(stb, root.left);
      stb.append(") ");
    }
    stb.append(root);
    if (root.right != null) {
      stb.append(" (");
      inorderToString(stb, root.right);
      stb.append(")");
    }
  }

  public String levelorderToString() {
    StringBuilder stb = new StringBuilder();
    levelorderToString(stb, root);
    return stb.toString();
  }

  private void levelorderToString(StringBuilder stb, Node<E> root) {
    if (root == null) {
      stb.append("An empty tree");
      return;
    }
    Queue<Node<E>> q = new LinkedList<Node<E>>();
    q.offer(root);
    while (!q.isEmpty()) {
      Node<E> curr = q.poll();
      stb.append(curr);
      stb.append(" ");
      if (curr.left != null) {
        q.offer(curr.left);
      }
      if (curr.right != null) {
        q.offer(curr.right);
      }
    }
  }
}