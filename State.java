public abstract class State {

  public void setProperty(String key, Object value, Object oldValue) {}

  public void unsetProperty(String key, Object oldValue) {}

  public void addChild(int index, State child) {}

  public void removeChild(int index, State child) {}

  public void destroy() {}
}
