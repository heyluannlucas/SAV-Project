import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgumentParser {
    private final Map<String, String> argsMap = new HashMap<>();
    private final List<String> errors = new ArrayList<>();

    public ArgumentParser(String[] args) {
        this.parseArgs(args);
    }

    private void parseArgs(String[] args) {
        for (String arg : args) {
            String[] parts = arg.split("=");
            if (parts.length == 2) {
                String paramName = parts[0].toLowerCase();
                String paramValue = parts[1];
                this.argsMap.put(paramName, paramValue);
            }
        }
    }

    public boolean validateArguments() {
        boolean isValid = true;

        String algorithm = getString("a", "algorithm", "").toUpperCase();
        if (!isValidAlgorithm(algorithm)) {
            addError("Invalid sorting algorithm. Use a=B for Bubble Sort, a=I for Insertion Sort, or a=S for Selection Sort.");
            isValid = false;
        }

        String listType = getString("t", "listtype", "").toUpperCase();
        if (!isValidListType(listType)) {
            addError(getListTypeMessage(listType));
            isValid = false;
        }

        String sortOrder = getString("o", "sortorder", "").toUpperCase();
        if (!isValidSortOrder(sortOrder)) {
            addError("Invalid sort order. Use o=AZ for ascending order or o=ZA for descending order.");
            isValid = false;
        }

        String listValueType = getString("in", "listvaluetype", "").toUpperCase();
        if (!isValidListValueType(listValueType)) {
            addError("Invalid list value type. Use in=R for random list or in=M for custom list.");
            isValid = false;
        } else {
            if (listValueType.equals("R")) {
                int randomCount = getInt("r", "randomcount", 0);
                if (!isValidRandomCount(randomCount)) {
                    addError("Invalid random count value. The value of r should be between 1 and 40.");
                    isValid = false;
                }
            } else if (listValueType.equals("M")) {
                String customValue = getString("v", "customvalue", "");
                if (customValue.isEmpty()) {
                    addError("Custom value is required when list value type is M. Use v=<value> to specify the custom value.");
                    isValid = false;
                }
            }
        }


        int delay = getInt("s", "delay", 0);
        if (!isValidDelay(delay)) {
            addError("Invalid delay value. The value of s should be between 100 and 1000.");
            isValid = false;
        }

        return isValid;
    }

    public String getString(String shortName, String longName, String defaultValue) {
        String value = this.argsMap.get(shortName.toLowerCase());
        if (value == null) {
            value = this.argsMap.get(longName.toLowerCase());
        }
        return value != null ? value : defaultValue;
    }

    public int getInt(String shortName, String longName, int defaultValue) {
        String value = this.getString(shortName, longName, null);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    public int[] getIntArray(String shortName, String longName, int[] defaultValue) {
        String value = this.getString(shortName, longName, null);
        if (value == null) {
            return defaultValue;
        } else {
            String[] values = value.split(",");
            int[] array = new int[values.length];

            for (int i = 0; i < values.length; ++i) {
                array[i] = Integer.parseInt(values[i].trim());
            }

            return array;
        }
    }

    public boolean isValidSortOrder(String sortOrder) {
        return sortOrder.equals("AZ") || sortOrder.equals("ZA");
    }

    public boolean isValidListValueType(String listValueType) {
        return listValueType.equals("R") || listValueType.equals("M");
    }

    public boolean isValidAlgorithm(String algorithm) {
        return algorithm.equals("B") || algorithm.equals("I") || algorithm.equals("S");
    }

    public boolean isValidListType(String listType) {
        return listType.equals("N") || listType.equals("C");
    }

    public String getListTypeMessage(String listType) {
        if (listType.equals("C")) {
            return "Custom list type not implemented. Use t=N to run the program with a numeric list.";
        }
        return "Invalid list type. Use t=N for numeric list or t=C for character list.";
    }

    public boolean isValidRandomCount(int randomCount) {
        return randomCount >= 1 && randomCount <= 40;
    }

    public boolean isValidDelay(int delay) {
        return delay >= 100 && delay <= 1000;
    }

    public List<String> getErrors() {
        return errors;
    }

    private void addError(String error) {
        errors.add(error);
    }
}