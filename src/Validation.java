/**
 * Oprettet af LarsKramer den 19-04-2019.
 */
public class Validation {
  private String lineEnd;

  public Validation() {
    this.lineEnd = "\n";
  }

  public Validation(String lineEnd) {
    this.lineEnd = lineEnd;
  }

  public String isPresent(String value, String name) {
    String message = "";
    if (value.isEmpty()) {
      message = name + "is required." + lineEnd;
    }
    return message;
  }

  public String isInteger(String value, String name) {
    String message = "";
    try {
      Integer.parseInt(value);
    } catch (NumberFormatException e) {
      message = name + " must be an integer" + lineEnd;
    }
    return message;
  }

  public String isDouble(String value, String name) {
    String message = "";
    try {
      Double.parseDouble(value);
    } catch (NumberFormatException e) {
      message = name + "must be a valid number" + lineEnd;
    }
    return message;
  }
}