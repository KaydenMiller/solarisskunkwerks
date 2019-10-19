package common.megamek;

import common.DataFactory;
import common.megamek.valuemappers.EraValueMapper;
import common.megamek.valuemappers.ProductionEraValueMapper;
import common.megamek.valuemappers.SourceValueMapper;
import common.megamek.valuemappers.TechBaseValueMapper;
import components.Mech;
import components.MegaMechBattlemech;

import javax.xml.transform.Source;

public class MegaMekToMechConverter {
    private final int BALLISTIC = 0,
            ENERGY = 1,
            MISSILE = 2,
            PHYSICAL = 3,
            EQUIPMENT = 4,
            AMMUNITION = 6,
            SELECTED = 7,
            ARTILLERY = 5;

    private static Object[][] Equipment = new Object[7][];

    private static void Init() {
        Equipment[0] = new
    }

    public static Mech ConvertToSSWMech(MegaMechBattlemech megaMek) {
        Init();

        Mech mech = new Mech();

        SetMetaInfo(mech, megaMek);
        SetChassisInfo(mech, megaMek);

        return mech;
    }

    private static Mech SetMetaInfo(Mech mech, MegaMechBattlemech megaMek) {
        mech.SetSource(new SourceValueMapper().MapValue(megaMek.source));
        mech.SetRulesLevel(megaMek.rulesLevel);
        mech.SetEra(new EraValueMapper().MapValue(megaMek.era));
        mech.SetTechBase(new TechBaseValueMapper().MapValue(megaMek.techBase));
        mech.SetProductionEra(new ProductionEraValueMapper().MapValue(megaMek.era));
        mech.SetYear(megaMek.era, true);

        return mech;
    }

    private static Mech SetChassisInfo(Mech mech, MegaMechBattlemech megaMek) {
        mech.SetName(megaMek.name);
        mech.SetModel(megaMek.variant);
        SetMechConfig(mech, megaMek);
        SetEngineInfo(mech, megaMek);
        mech.SetTonnage(megaMek.mass);

        return mech;
    }

    private static Mech AddComponents(Mech mech, MegaMechBattlemech megaMek) {
        DataFactory equipmentData;

        try {
            equipmentData = new DataFactory(mech);
        }
        catch (Exception ex) {

        }

        equipmentData.GetEquipment()

        return mech;
    }

    private static Mech SetEngineInfo(Mech mech, MegaMechBattlemech megaMek) {
        String[] engineInfo = megaMek.engine.split(" ");

        try {
            mech.SetEngineRating(Integer.valueOf(engineInfo[0]));
            mech.SetWalkMP(megaMek.walkMp);
        }
        catch (Exception ex) {
            System.err.format("Cannot set walking movement points: %s", ex.getMessage());
            ex.printStackTrace();
        }

        return mech;
    }

    private static Mech SetMechConfig(Mech mech, MegaMechBattlemech megaMek) {
        switch (megaMek.config.toLowerCase()) {
            case "biped":
                mech.SetBiped();
                break;
            case "quad":
                mech.SetQuad();
                break;
        }

        return mech;
    }
}
