package common.megamek.valuemappers;

import common.megamek.IValueMapper;

public class SourceValueMapper implements IValueMapper<String, String> {
    @Override
    public String MapValue(String input) {
        return GetOutput(input.toLowerCase());
    }

    private String GetOutput(String input) {
        switch (input) {
            case "tro 3039 - star league":
                return "RS3039u";
            default:
                return "unknown";
        }
    }
}
