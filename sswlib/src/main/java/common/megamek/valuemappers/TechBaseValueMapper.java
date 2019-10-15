package common.megamek.valuemappers;

import common.megamek.IValueMapper;

public class TechBaseValueMapper implements IValueMapper<String, Integer> {

    @Override
    public Integer MapValue(String input) {
        return GetOutput(input.toLowerCase());
    }

    private int GetOutput(String input) {
        switch (input) {
            case "inner sphere":
                return 0;
            case "clan":
                return 1;
            default:
                return 0;
        }
    }
}
