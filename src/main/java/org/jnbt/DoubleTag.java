package org.jnbt;

public final class DoubleTag extends Tag {
  private final double value;
  
  public DoubleTag(String name, double value) {
    super(name);
    this.value = value;
  }
  
  public Double getValue() {
    return Double.valueOf(this.value);
  }
  
  public String toString() {
    String name = getName();
    String append = "";
    if (name != null && !"".equals(name))
      append = "(\"" + getName() + "\")"; 
    return "TAG_Double" + append + ": " + this.value;
  }
}
