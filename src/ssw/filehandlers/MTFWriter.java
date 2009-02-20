/*
Copyright (c) 2008~2009, Justin R. Bengtson (poopshotgun@yahoo.com)
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice,
        this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright notice,
        this list of conditions and the following disclaimer in the
        documentation and/or other materials provided with the distribution.
    * Neither the name of Justin R. Bengtson nor the names of contributors may
        be used to endorse or promote products derived from this software
        without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package ssw.filehandlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import ssw.*;
import ssw.components.*;

public class MTFWriter {
    // writes the given mech to an MTF file supported by MegaMek.

    private Mech CurMech;

    public MTFWriter( Mech m ) {
        CurMech = m;
    }

    public void WriteMTF( String filename ) throws IOException {
        BufferedWriter FR = new BufferedWriter( new FileWriter( filename ) );

        // first block for vesioning and name
        FR.write( "Version:1.1" );
        FR.newLine();
        FR.write( CurMech.GetName() );
        FR.newLine();
        if( CurMech.IsOmnimech() ) {
            if( CurMech.GetModel().equals( "" ) ) {
                FR.write( CurMech.GetLoadout().GetName() );
            } else {
                FR.write( CurMech.GetModel() + " " + CurMech.GetLoadout().GetName() );
            }
        } else {
            FR.write( CurMech.GetModel() );
        }
        FR.newLine();

        // second block handles general mech stuff
        FR.newLine();
        if( CurMech.IsQuad() ) {
            if( CurMech.IsOmnimech() ) {
                FR.write( "Config:Quad Omnimech" );
            } else {
                FR.write( "Config:Quad" );
            }
        } else {
            if( CurMech.IsOmnimech() ) {
                FR.write( "Config:Biped Omnimech" );
            } else {
                FR.write( "Config:Biped" );
            }
        }
        FR.newLine();
        if( CurMech.IsClan() ) {
            FR.write( "TechBase:Clan" );
        } else {
            FR.write( "TechBase:Inner Sphere" );
        }
        FR.newLine();
        FR.write( "Era:" + CurMech.GetYear() );
        FR.newLine();
        FR.write( "Rules Level:" + CurMech.GetMegaMekLevel() );
        FR.newLine();

        // third block for mech specifics
        FR.newLine();
        FR.write( "Mass:" + CurMech.GetTonnage() );
        FR.newLine();
        FR.write( "Engine:" + CurMech.GetEngine().GetRating() + " " + CurMech.GetEngine().GetCritName() );
        FR.newLine();
        FR.write( "Structure:" + CurMech.GetIntStruc().GetMMName( false ) );
        FR.newLine();
        // check for a wierd gyro config, since MM always wants to see "Gyro" in
        // the crit slots.
        if( ! CurMech.GetGyro().GetCritName().matches( "Gyro" ) ) {
            FR.write( "Gyro:" + CurMech.GetGyro().GetCritName() );
            FR.newLine();
        }
        // check for a small cockpit.
        if( ! CurMech.GetCockpit().GetCritName().equals( "Cockpit") ) {
            FR.write( "Cockpit:" + CurMech.GetCockpit().GetCritName() );
            FR.newLine();
        }
        FR.write( "Myomer:" + CurMech.GetPhysEnhance().GetMMName( false ) );
        FR.newLine();

        // fourth block for movement and heat
        FR.newLine();
        if( CurMech.GetHeatSinks().IsDouble() ) {
            FR.write( "Heat Sinks:" + CurMech.GetHeatSinks().GetNumHS() + " Double" );
        } else {
            FR.write( "Heat Sinks:" + CurMech.GetHeatSinks().GetNumHS() + " Single" );
        }
        FR.newLine();
        if( CurMech.IsOmnimech() ) {
            FR.write( "Base Chassis Heat Sinks: " + CurMech.GetLoadout().GetHeatSinks().GetBaseLoadoutNumHS() );
            FR.newLine();
        }
        FR.write( "Walk MP:" + CurMech.GetWalkingMP() );
        FR.newLine();
        FR.write( "Jump MP:" + CurMech.GetJumpJets().GetNumJJ() );
        FR.newLine();

        // fifth block for armor information
        FR.newLine();
        FR.write( "Armor:" + CurMech.GetArmor().GetMMName( false ) );
        FR.newLine();
        FR.write( "LA Armor:" + CurMech.GetArmor().GetLocationArmor( Constants.LOC_LA) );
        FR.newLine();
        FR.write( "RA Armor:" + CurMech.GetArmor().GetLocationArmor( Constants.LOC_RA) );
        FR.newLine();
        FR.write( "LT Armor:" + CurMech.GetArmor().GetLocationArmor( Constants.LOC_LT) );
        FR.newLine();
        FR.write( "RT Armor:" + CurMech.GetArmor().GetLocationArmor( Constants.LOC_RT) );
        FR.newLine();
        FR.write( "CT Armor:" + CurMech.GetArmor().GetLocationArmor( Constants.LOC_CT) );
        FR.newLine();
        FR.write( "HD Armor:" + CurMech.GetArmor().GetLocationArmor( Constants.LOC_HD) );
        FR.newLine();
        FR.write( "LL Armor:" + CurMech.GetArmor().GetLocationArmor( Constants.LOC_LL) );
        FR.newLine();
        FR.write( "RL Armor:" + CurMech.GetArmor().GetLocationArmor( Constants.LOC_RL) );
        FR.newLine();
        FR.write( "RTL Armor:" + CurMech.GetArmor().GetLocationArmor( Constants.LOC_LTR) );
        FR.newLine();
        FR.write( "RTR Armor:" + CurMech.GetArmor().GetLocationArmor( Constants.LOC_RTR) );
        FR.newLine();
        FR.write( "RTC Armor:" + CurMech.GetArmor().GetLocationArmor( Constants.LOC_CTR) );
        FR.newLine();

        // sixth block for weapon information.  Get the loadout directly as this
        // will make things much easier
        ifLoadout l = CurMech.GetLoadout();
        FR.newLine();
        Vector v = l.GetNonCore();
        // now we have to split up the vector into equipment and ammo
        Vector eq = new Vector();
        Vector ammo = new Vector();
        for( int i = 0; i < v.size(); i++ ) {
            if( v.get( i ) instanceof Ammunition ) {
                ammo.add( v.get( i ) );
            } else {
                eq.add( v.get( i ) );
            }
        }
        FR.write( "Weapons:" + eq.size() );
        FR.newLine();
        for( int i = 0; i < eq.size(); i++ ) {
            // each piece of equipment has it's own line.  If it's a weapon,
            // we'll have to find out how much ammo it has as well
            abPlaceable p = (abPlaceable) eq.get( i );
            if( p instanceof ifWeapon ) {
                if( ((ifWeapon) p).HasAmmo() ) {
                    // since we're removing ammo as we add it, ignore the ammo
                    // index if it doesn't exist in the vector
                    int ammoindex = ((ifWeapon) p).GetAmmoIndex();
                    int ammoamount = 0;
                    for( int j = ammo.size() - 1; j >= 0; j-- ) {
                        // we're using the literal ammo lot size, not what the ammo says.
                        if( ((Ammunition) ammo.get( j )).GetAmmoIndex() == ammoindex ) {
                            ammoamount += ((ifWeapon) p).GetAmmo();
                            ammo.remove( j );
                        }
                    }
                    // check for a rear-facing weapon
                    String rear = "";
                    if( p.IsMountedRear() ) {
                        rear = " (R)";
                    }
                    // now that we have the amount, add the line in
                    if( ammoamount > 0 ) {
                        FR.write( "1 " + p.GetMMName( false ) + ", " + Constants.Locs[l.Find( p )] + rear + ", Ammo:" + ammoamount );
                    } else {
                        FR.write( "1 " + p.GetMMName( false ) + ", " + Constants.Locs[l.Find( p )] + rear );
                    }
                } else {
                    // check for a rear-facing weapon
                    String rear = "";
                    if( p.IsMountedRear() ) {
                        rear = " (R)";
                    }
                    // no ammo checking needed
                    FR.write( "1 " + p.GetMMName( false ) + ", " + Constants.Locs[l.Find( p )] + rear );
                }
                FR.newLine();
            } else {
                // not a weapon so no ammo checking.  Add it to the file
                FR.write( "1 " + p.GetMMName( false ) + ", " + Constants.Locs[l.Find( p )] );
                FR.newLine();
            }
            // format is:
            // "1 <weapon lookupname>, <location>, Ammo:<total lot size>
        }

        // start internals.
        FR.newLine();
        FR.write( "Left Arm:" );
        FR.newLine();
        for( int i = 0; i < l.GetLACrits().length; i++ ) {
            FR.write( GetMMName( l.GetLACrits()[i] ) );
            FR.newLine();
        }

        FR.newLine();
        FR.write( "Right Arm:" );
        FR.newLine();
        for( int i = 0; i < l.GetRACrits().length; i++ ) {
            FR.write( GetMMName( l.GetRACrits()[i] ) );
            FR.newLine();
        }

        FR.newLine();
        FR.write( "Left Torso:" );
        FR.newLine();
        for( int i = 0; i < l.GetLTCrits().length; i++ ) {
            FR.write( GetMMName( l.GetLTCrits()[i] ) );
            FR.newLine();
        }

        FR.newLine();
        FR.write( "Right Torso:" );
        FR.newLine();
        for( int i = 0; i < l.GetRTCrits().length; i++ ) {
            FR.write( GetMMName( l.GetRTCrits()[i] ) );
            FR.newLine();
        }

        FR.newLine();
        FR.write( "Center Torso:" );
        FR.newLine();
        for( int i = 0; i < l.GetCTCrits().length; i++ ) {
            FR.write( GetMMName( l.GetCTCrits()[i] ) );
            FR.newLine();
        }

        FR.newLine();
        FR.write( "Head:" );
        FR.newLine();
        for( int i = 0; i < l.GetHDCrits().length; i++ ) {
            FR.write( GetMMName( l.GetHDCrits()[i] ) );
            FR.newLine();
        }

        FR.newLine();
        FR.write( "Left Leg:" );
        FR.newLine();
        for( int i = 0; i < l.GetLLCrits().length; i++ ) {
            FR.write( GetMMName( l.GetLLCrits()[i] ) );
            FR.newLine();
        }

        FR.newLine();
        FR.write( "Right Leg:" );
        FR.newLine();
        for( int i = 0; i < l.GetRLCrits().length; i++ ) {
            FR.write( GetMMName( l.GetRLCrits()[i] ) );
            FR.newLine();
        }

        // all done
        FR.close();
    }

    private String GetMMName( abPlaceable p ) {
        if( p.IsArmored() ) {
            return p.GetMMName( p.IsMountedRear() ) + " (armored)";
        } else {
            return p.GetMMName( p.IsMountedRear() );
        }
    }
}
