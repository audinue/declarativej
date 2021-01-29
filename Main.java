public final class Main {

  public static DefaultEffectBuilder JFrame() {
    return new DefaultEffectBuilder(JFrameStateFactory.INSTANCE);
  }

  public static DefaultEffectBuilder JButton() {
    return new DefaultEffectBuilder(JButtonStateFactory.INSTANCE);
  }

  public static EffectBuilder Counter(int count) {
    return JFrame()
      .put("text", "Counter")
      .put("width", 200)
      .put("height", 100)
      .add(
        JButton()
          .put("text", "Count: " + count)
          .put("onClick", () -> Counter(count + 1))
      );
  }

  public static void main(String[] args) {
    Runner.ROOT.run(Counter(0));
  }
}
