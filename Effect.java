import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public final class Effect {

  private final StateFactory factory;
  private final Map<String, Object> properties;
  private final List<Effect> children;
  private State state;

  public Effect(
    StateFactory factory,
    Map<String, Object> properties,
    List<Effect> children
  ) {
    this.factory = factory;
    this.properties = properties;
    this.children = children;
  }

  public StateFactory getFactory() {
    return factory;
  }

  public Map<String, Object> getProperties() {
    return properties;
  }

  public List<Effect> getChildren() {
    return children;
  }

  void createState() {
    List<State> children = new ArrayList<>();
    for (Effect child : this.children) {
      child.createState();
      children.add(child.state);
    }
    state = factory.create(properties, children);
  }

  void destroyState() {
    state.destroy();
  }

  void updateState(Effect next) {
    if (next.factory != factory) {
      next.createState();
      state.destroy();
    } else {
      for (Entry<String, Object> entry : next.properties.entrySet()) {
        Object oldValue = properties.get(entry.getKey());
        if (!Objects.equals(oldValue, entry.getValue())) {
          state.setProperty(entry.getKey(), entry.getValue(), oldValue);
        }
      }
      for (Entry<String, Object> entry : properties.entrySet()) {
        if (!next.properties.containsKey(entry.getKey())) {
          state.unsetProperty(entry.getKey(), entry.getValue());
        }
      }
      int a = children.size();
      int b = next.children.size();
      if (a < b) {
        for (int i = a; i < b; i++) {
          Effect child = next.children.get(i);
          child.createState();
          state.addChild(i, child.state);
        }
      } else if (b < a) {
        for (int i = b; i < a; i++) {
          Effect child = children.get(i);
          state.removeChild(i, child.state);
          child.state.destroy();
        }
      }
      int c = Math.min(a, b);
      for (int i = 0; i < c; i++) {
        children.get(i).updateState(next.children.get(i));
      }
      next.state = state;
    }
  }
}
