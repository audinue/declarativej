import java.util.Map;
import java.util.List;
import java.util.function.Supplier;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;

@SuppressWarnings("unchecked")
public final class JButtonState extends ComponentState {

  private final JButton button;
  private Supplier<EffectBuilder> onClick = () -> null;

  public JButtonState(Map<String, Object> properties, List<State> children) {
    button = new JButton();
    button.addActionListener(e -> Runner.ROOT.run(onClick.get()));
    button.setText((String) properties.get("text"));
    onClick = (Supplier<EffectBuilder>) properties.get("onClick");
  }

  public void setProperty(String name, Object value, Object oldValue) {
    if (name.equals("text")) {
      button.setText((String) value);
    } else if (name.equals("onClick")) {
      onClick = (Supplier<EffectBuilder>) value;
    }
  }

  public Component getComponent() {
    return button;
  }
}
