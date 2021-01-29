public final class Runner {

  public static final Runner ROOT = new Runner();

  private Effect effect;

  private Runner() {}

  private void run(Effect effect) {
    if (effect == null) {
      if (this.effect == null) {
        ;
      } else {
        this.effect.destroyState();
      }
    } else {
      if (this.effect == null) {
        effect.createState();
      } else {
        this.effect.updateState(effect);
      }
    }
    this.effect = effect;
  }

  public void run(EffectBuilder builder) {
    run(builder.build());
  }
}
