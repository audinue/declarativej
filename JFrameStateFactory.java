import java.util.Map;
import java.util.List;

public final class JFrameStateFactory extends StateFactory {

  public static final JFrameStateFactory INSTANCE = new JFrameStateFactory();

  private JFrameStateFactory() {}

  public State create(Map<String, Object> properties, List<State> children) {
    return new JFrameState(properties, children);
  }
}
