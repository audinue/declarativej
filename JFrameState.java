import java.util.Map;
import java.util.List;
import javax.swing.JFrame;

public final class JFrameState extends State {

  private final JFrame frame;

  public JFrameState(Map<String, Object> properties, List<State> children) {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle((String) properties.get("text"));
    frame.setSize((int) properties.get("width"), (int) properties.get("height"));
    for (State child : children) {
      frame.add(((ComponentState) child).getComponent());
    }
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
