package common.megamek.valuemappers;

import common.megamek.IValueMapper;

public class ProductionEraValueMapper implements IValueMapper<Integer, Integer> {
    @Override
    public Integer MapValue(Integer input) {
        if (input <= 2550) {
            // AGE OF WAR
            return 0;
        }
        else if (input >= 2551 && input <= 2780) {
            // STAR LEAGUE
            return 1;
        }
        else if (input >= 2781 && input <= 3049) {
            // SUCCESSION WARS
            if (input >= 2915 && input <= 3049)
            {
                // LATE SUCCESSION WARS
                return 3;
            }
            return 2;
        }
        else if (input >= 3050 && input <= 3061) {
            // CLAN INVASION
            return 4;
        }
        else if (input >= 3062 && input <= 3067) {
            // CIVIL WAR
            return 5;
        }
        else if (input >= 3068 && input <= 3080) {
            // JIHAD
            return 6;
        }
        else if (input >= 3081) {
            // REPUBLIC OF THE SPHERE
            return 7;
        }
        else if (input >= 3181) {
            // DARK AGE
            return 8;
        }
        else {
            // DEFAULT (SUCCESSION WARS)
            return 1;
        }
    }
}
