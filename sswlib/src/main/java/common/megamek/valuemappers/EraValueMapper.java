package common.megamek.valuemappers;

import common.megamek.IValueMapper;

public class EraValueMapper implements IValueMapper<Integer, Integer> {
    @Override
    public Integer MapValue(Integer input) {
        if (input <= 2780) {
            // AGE OF WAR / STAR LEAGUE
            return 0;
        }
        else if (input >= 2781 && input <= 3049) {
            // SUCCESSION WARS
            return 1;
        }
        else if (input >= 3050 && input <= 3061) {
            // CLAN INVASION
            return 2;
        }
        else if (input >= 3062) {
            // DARK AGE
            return 3;
        }
        else {
            // DEFAULT ALL
            return 4;
        }
    }
}
