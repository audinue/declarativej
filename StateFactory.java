import java.util.Map;
import java.util.List;

public abstract class StateFactory {

  public abstract State create(
    Map<String, Object> properties,
    List<State> children
  );
}
