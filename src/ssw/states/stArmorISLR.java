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

package ssw.states;

import ssw.Constants;
import ssw.components.*;

public class stArmorISLR implements ifArmor, ifState {
    boolean locked = false;
    private final static AvailableCode AC = new AvailableCode( false, 'E', 'X', 'X', 'F',
        3058, 0, 0, "LA", "", false, false, 3055, true, "FC", Constants.EXPERIMENTAL, Constants.EXPERIMENTAL  );

    public String GetLookupName() {
        return "Laser-Reflective";
    }

    public String GetCritName() {
        return "Laser-Reflective";
    }

    public String GetMMName() {
        return "Reflective";
    }

    public boolean IsClan() {
        return false;
    }

    public boolean Place( Armor a, ifLoadout l ) {
        // Simply place the armor into the loadout queue
        l.AddToQueue( a );
        return true;
    }

    public boolean Place( Armor a, ifLoadout l, LocationIndex[] Locs ) {
        // not implemented yet, just place as normal
        return Place( a, l );
    }

    public int NumCrits() {
        return 10;
    }

    public float GetAVMult() {
        return 1.0f;
    }

    public boolean IsStealth() {
        return false;
    }

    public float GetCostMult() {
        return 30000.0f;
    }

    public float GetBVTypeMult() {
        return 1.5f;
    }

    public int GetBAR() {
        return 10;
    }

    public boolean LocationLocked() {
        return locked;
    }

    public void SetLocked( boolean l ) {
        locked = l;
    }

    public MechModifier GetMechModifier() {
        return null;
    }

    public AvailableCode GetAvailability() {
        return AC;
    }
}
