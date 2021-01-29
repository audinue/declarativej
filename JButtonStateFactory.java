import java.util.Map;
import java.util.List;

public final class JButtonStateFactory extends StateFactory {

  public static final JButtonStateFactory INSTANCE = new JButtonStateFactory();

  private JButtonStateFactory() {}

  public State create(Map<String, Object> properties, List<State> children) {
    return new JButtonState(properties, children);
  }
}
