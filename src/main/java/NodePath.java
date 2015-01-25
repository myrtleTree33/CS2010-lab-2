/**
 * 
 */

/**
 * @author A0108165J
 *
 */
public class NodePath {
  
  public NodePath() {
    this.data = "";
    this.path = "";
  }
  
  public NodePath(String data, String path) {
    this.data = data;
    this.path = path;
  }
  
  public String toString() {
    return this.data + " " + this.path;
  }
  
  public String data;
  public String path;
}
