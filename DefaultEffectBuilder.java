import java.util.HashMap;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.function.Function;

public final class DefaultEffectBuilder extends EffectBuilder {

  private final StateFactory factory;
  private final HashMap<String, Object> properties = new HashMap<>();
  private final ArrayList<EffectBuilder> children = new ArrayList<>();

  public DefaultEffectBuilder(StateFactory factory) {
    this.factory = factory;
  }

  public DefaultEffectBuilder put(String key, Object value) {
    properties.put(key, value);
    return this;
  }

  public DefaultEffectBuilder put(String key, Supplier value) {
    properties.put(key, value);
    return this;
  }

  public DefaultEffectBuilder put(String key, Function value) {
    properties.put(key, value);
    return this;
  }

  public DefaultEffectBuilder add(EffectBuilder child) {
    children.add(child);
    return this;
  }

  public Effect build() {
    ArrayList<Effect> children = new ArrayList<>();
    for (EffectBuilder child : this.children) {
      children.add(child.build());
    }
    return new Effect(factory, properties, children);
  }
}
