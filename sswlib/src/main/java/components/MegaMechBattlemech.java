package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class MegaMechBattlemech {
    public float mtfVersion;
    public String name;
    public String variant;

    public String config;
    public String techBase;
    public int era;
    public String source;
    public int rulesLevel;

    public float mass;
    public String engine;
    public String structure;
    public String myomer;

    public String heatSinks;
    public int walkMp;
    public int jumpMp;

    public String armorType;
    public Map<String, Integer> armor = new HashMap<String, Integer>();

    public int weaponCount;
    public Map<String, String> weaponAndLocation = new HashMap<String, String>();

    public String[] head = new String[12];
    public String[] leftArm = new String[12];
    public String[] rightArm = new String[12];
    public String[] leftTorso = new String[12];
    public String[] centerTorso = new String[12];
    public String[] rightTorso = new String[12];
    public String[] leftLeg = new String[12];
    public String[] rightLeg = new String[12];

    public static MegaMechBattlemech parseFromFile(String filePath) {
        List<String> megaMechFileLines = LoadFile(filePath);

        String strVersion = GetMegaMechMTFVersion(megaMechFileLines);

        switch (strVersion) {
            case "1.0":
                return ParseVersion1(megaMechFileLines);
            default:
                // Pretend its a version 1?
                System.err.format("Could not parse MegaMech file.");
                return new MegaMechBattlemech();
        }
    }

    private static String GetMegaMechMTFVersion(List<String> lines) {
        return GetValueFromKvP(lines, "Version");
    }

    private static MegaMechBattlemech ParseVersion1(List<String> lines) {
        MegaMechBattlemech mech = new MegaMechBattlemech();

        mech.mtfVersion = 1.0f;
        mech.name = GetValueFromFileRow(lines, 1);
        mech.variant = GetValueFromFileRow(lines, 2);

        mech.config = GetValueFromKvP(lines, "Config");
        mech.techBase = GetValueFromKvP(lines, "TechBase");
        String strEra = GetValueFromKvP(lines, "era");
        mech.era = Integer.parseInt(strEra != null ? strEra : "-1");
        mech.source = GetValueFromKvP(lines, "Source");
        String strRules = GetValueFromKvP(lines, "Rules Level");
        mech.rulesLevel = Integer.parseInt(strRules != null ? strRules : "-1");

        String strMass = GetValueFromKvP(lines, "Mass");
        mech.mass = Float.parseFloat(strMass != null ? strMass : "-1");
        mech.engine = GetValueFromKvP(lines, "Engine");
        mech.structure = GetValueFromKvP(lines, "Structure");
        mech.myomer = GetValueFromKvP(lines, "Myomer");

        mech.heatSinks = GetValueFromKvP(lines, "Heat Sinks");
        String strWalk = GetValueFromKvP(lines, "Walk MP");
        mech.walkMp = Integer.parseInt(strWalk != null ? strWalk : "-1");
        String strJump = GetValueFromKvP(lines, "Jump MP");
        mech.jumpMp = Integer.parseInt(strJump != null ? strJump : "-1");

        return mech;
    }

    private static String GetValueFromKvP(List<String> lines, String key) {
        for (String line : lines) {
            if (line.toLowerCase().contains(key)) {
                int valueStart = line.indexOf(":");
                int endOfLine = line.indexOf('\n');

                String value = line.substring(valueStart + 1, endOfLine - 1);
                return value;
            }
        }

        return null;
    }

    private static String GetValueFromFileRow(List<String> lines, int row) {
        return lines.get(row);
    }

    private static List<String> LoadFile(String filePath) {
        List<String> lines = new ArrayList<String>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
            return lines;
        }
        catch (Exception ex) {
            System.err.format("Exception occurred trying to load MegaMech file: %s", filePath);
            ex.printStackTrace();
            return lines;
        }
    }
}
