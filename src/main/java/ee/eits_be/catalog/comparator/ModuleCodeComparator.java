package ee.eits_be.catalog.comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This custom comparator is needed due to fact that top levels and sub levels in String comparison do not work same as
 * numeric type conversion. Example: DER.1 ... DER.10 String type conversion will result in DER.10, DER.1 ...
 */
public class ModuleCodeComparator implements Comparator<String> {

    @Override
    public int compare(String code1, String code2) {
        List<Integer> levels1 = parseCodeToLevels(code1);
        List<Integer> levels2 = parseCodeToLevels(code2);

        int maxLength = Math.max(levels1.size(), levels2.size());

        for (int i = 0; i < maxLength; i++) {
            int level1 = (i < levels1.size()) ? levels1.get(i) : 0;
            int level2 = (i < levels2.size()) ? levels2.get(i) : 0;

            if (level1 != level2) {
                return Integer.compare(level1, level2);
            }
        }
        return 0;
    }

    private List<Integer> parseCodeToLevels(String code) {
        return Arrays.stream(code.split("\\."))
                .map(part -> {
                    try {
                        return Integer.parseInt(part.replaceAll("[^0-9]", ""));
                    } catch (NumberFormatException e) {
                        return 0; // Default to 0 if parsing fails
                    }
                })
                .toList();
    }
}