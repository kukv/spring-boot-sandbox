package jp.kukv.examples;

public record ObjectKey(String value) {
  @Override
  public String toString() {
    return value;
  }
}
