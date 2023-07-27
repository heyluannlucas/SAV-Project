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

        String algorithm = getString("a", "algorithm", "b").toUpperCase();
        if (!isValidAlgorithm(algorithm)) {
            addError("Algoritmo de ordenação inválido. Use a=b para Bubble Sort");
            isValid = false;
        }

        String listType = getString("t", "listtype", "n").toUpperCase();
        if (!isValidListType(listType)) {
            addError(getListTypeMessage(listType));
            isValid = false;
        }

        String sortOrder = getString("o", "sortorder", "za").toUpperCase();
        if (!isValidSortOrder(sortOrder)) {
            addError("Tipo de ordenamento inválido.");
            isValid = false;
        }

        String listValueType = getString("in", "listvaluetype", "r").toUpperCase();
        if (!isValidListValueType(listValueType)) {
            addError("Tipo de valor da lista inválido.");
            isValid = false;
        }

        int delay = getInt("s", "delay", 100);
        if (!isValidDelay(delay)) {
            addError("O valor de \"s\" deve estar entre 100 e 1000.");
            isValid = false;
        }

        int randomCount = getInt("r", "randomcount", 15);
        if (!isValidRandomCount(randomCount)) {
            addError("O valor de \"r\" deve estar entre 1 e 40.");
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

    // Novo método para verificar se o tipo de lista é válido
    public boolean isValidListType(String listType) {
        return listType.equalsIgnoreCase("N") || listType.equalsIgnoreCase("C");
    }

    // Novo método para obter mensagem específica do tipo de lista
    public String getListTypeMessage(String listType) {
        if (listType.equalsIgnoreCase("C")) {
            return "Lista de caracteres ainda não implementada. Use -t=n para executar o programa com uma lista numérica.";
        }
        return "Tipo de lista inválido. Use -t=n para lista numérica ou -t=c para lista de caracteres.";
    }

    // Método para validar o valor de "r"
    public boolean isValidRandomCount(int randomCount) {
        return randomCount >= 1 && randomCount <= 40;
    }

    // Método para validar o valor de "s"
    public boolean isValidDelay(int delay) {
        return delay >= 100 && delay <= 1000;
    }


    public List<String> getErrors() {
        return errors;
    }

    // Método para adicionar erros à lista de erros
    private void addError(String error) {
        errors.add(error);
    }
}
