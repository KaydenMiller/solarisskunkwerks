package components;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.net.Inet4Address;
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
    public Map<String, List<String>> weaponAndLocation;

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

        mech.armorType = GetValueFromKvP(lines, "Armor");
        mech.armor.put("HD", Integer.valueOf(GetValueFromKvP(lines, "HD Armor")));
        mech.armor.put("LA", Integer.valueOf(GetValueFromKvP(lines, "LA Armor")));
        mech.armor.put("RA", Integer.valueOf(GetValueFromKvP(lines, "RA Armor")));
        mech.armor.put("LT", Integer.valueOf(GetValueFromKvP(lines, "LT Armor")));
        mech.armor.put("RT", Integer.valueOf(GetValueFromKvP(lines, "RT Armor")));
        mech.armor.put("CT", Integer.valueOf(GetValueFromKvP(lines, "CT Armor")));
        mech.armor.put("LL", Integer.valueOf(GetValueFromKvP(lines, "LL Armor")));
        mech.armor.put("RL", Integer.valueOf(GetValueFromKvP(lines, "RL Armor")));
        mech.armor.put("RLT", Integer.valueOf(GetValueFromKvP(lines, "RTL Armor")));
        mech.armor.put("RCT", Integer.valueOf(GetValueFromKvP(lines, "RTC Armor")));
        mech.armor.put("RRT", Integer.valueOf(GetValueFromKvP(lines, "RTR Armor")));

        mech.weaponCount = Integer.valueOf(GetValueFromKvP(lines, "Weapons"));
        mech.weaponAndLocation = GetWeaponComponentArray(lines, mech.weaponCount);

        mech.leftArm = GetComponentArray(lines, "Right Arm:");
        mech.rightArm = GetComponentArray(lines, "Left Arm:");
        mech.leftTorso = GetComponentArray(lines, "Left Torso:");
        mech.rightTorso = GetComponentArray(lines, "Right Torso:");
        mech.centerTorso = GetComponentArray(lines, "Center Torso:");
        mech.leftLeg = GetComponentArray(lines, "Left Leg:");
        mech.rightLeg = GetComponentArray(lines, "Right Leg:");
        mech.head = GetComponentArray(lines, "Head:");

        return mech;
    }

    private static String GetValueFromKvP(List<String> lines, String key) {
        for (String line : lines) {
            if (line.toLowerCase().contains(key.toLowerCase())) {
                int valueStart = line.indexOf(":");
                int endOfLine = line.length();

                String value;
                try {
                    value = line.substring(valueStart + 1, endOfLine);
                }
                catch (Exception ex) {
                    System.err.format("Exception occurred trying to get KVP value from MegaMek file.");
                    value = "";
                }

                return value;
            }
        }

        return null;
    }

    private static HashMap<String, List<String>> GetWeaponComponentArray(List<String> lines, int weaponCount) {
        HashMap<String, List<String>> weapons = new HashMap<String, List<String>>();

        int arrayKeyIndex = GetIndexOfKey(lines, "Weapons");
        int fileLineStart = arrayKeyIndex + 1;

        for (int i = fileLineStart; i < fileLineStart + weaponCount; i++) {
            String[] values = lines.get(i).split(",");

            if (weapons.containsKey(values[1])) {
                weapons.get(values[1]).add(values[0]);
            }
            else {
                weapons.put(values[1], new ArrayList<>());
                weapons.get(values[1]).add(values[0]);
            }
        }

        return weapons;
    }

    private static String[] GetComponentArray(List<String> lines, String arrayKey) {
        String[] components = new String[12];

        int arrayKeyIndex = GetIndexOfKey(lines, arrayKey);
        int fileLineStart = arrayKeyIndex + 1;

        for (int i = fileLineStart; i < fileLineStart + 12; i++) {
            components[i - fileLineStart] = lines.get(i);
        }

        return components;
    }

    private static String GetValueFromFileRow(List<String> lines, int row) {
        return lines.get(row);
    }

    private static int GetIndexOfKey(List<String> lines, String key) {
        int index = 0;

        for (String line : lines) {
            if (line.toLowerCase().contains(key.toLowerCase())) {
                return index;
            }

            ++index;
        }

        return -1;
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
            System.err.format("Exception occurred trying to load MegaMek file: %s", filePath);
            ex.printStackTrace();
            return lines;
        }
    }
}
