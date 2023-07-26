import java.util.HashMap;
import java.util.Map;

public class ArgumentParser {
    private final Map<String, String> argsMap = new HashMap();

    public ArgumentParser(String[] args) {
        this.parseArgs(args);
    }

    private void parseArgs(String[] args) {
        String[] var2 = args;
        int var3 = args.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String arg = var2[var4];
            String[] parts = arg.split("=");
            if (parts.length == 2) {
                String paramName = parts[0].toLowerCase();
                String paramValue = parts[1];
                this.argsMap.put(paramName, paramValue);
            }
        }

    }

    public String getString(String shortName, String longName, String defaultValue) {
        String value = (String)this.argsMap.get(shortName.toLowerCase());
        if (value == null) {
            value = (String)this.argsMap.get(longName.toLowerCase());
        }

        return value != null ? value : defaultValue;
    }

    public int getInt(String shortName, String longName, int defaultValue) {
        String value = this.getString(shortName, longName, (String)null);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    public int[] getIntArray(String shortName, String longName, int[] defaultValue) {
        String value = this.getString(shortName, longName, (String)null);
        if (value == null) {
            return defaultValue;
        } else {
            String[] values = value.split(",");
            int[] array = new int[values.length];

            for(int i = 0; i < values.length; ++i) {
                array[i] = Integer.parseInt(values[i].trim());
            }

            return array;
        }
    }
}
