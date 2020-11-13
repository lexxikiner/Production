public enum MonitorType {

  LCD("LCD"),
  LED("LED");

  public final String code;

  MonitorType(String code) {
    this.code = code;
  }

  public String code() {
    return code;
  }

}
