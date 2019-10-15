package common.megamek;

import common.megamek.valuemappers.SourceValueMapper;
import common.megamek.valuemappers.TechBaseValueMapper;
import components.Mech;
import components.MegaMechBattlemech;

import javax.xml.transform.Source;

public class MegaMekToMechConverter {

    public static Mech ConvertToSSWMech(MegaMechBattlemech megaMek) {
        Mech mech = new Mech();

        SetMetaInfo(mech, megaMek);
        SetChassisInfo(mech, megaMek);

        return mech;
    }

    private static Mech SetMetaInfo(Mech mech, MegaMechBattlemech megaMek) {
        mech.SetSource(new SourceValueMapper().MapValue(megaMek.source));
        mech.SetRulesLevel(megaMek.rulesLevel);
        //mech.SetEra(megaMek.era);
        mech.SetTechBase(new TechBaseValueMapper().MapValue(megaMek.techBase));

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
