package WeaponRegister;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register {
    private final Map<String, Erlaubnis> licenses;
    private final Map<String, Waffe> weapons;
    private final Map<String, Waffenteil> weaponParts;

    public Register() {
        this.licenses = new HashMap<String, Erlaubnis>();
        this.weapons = new HashMap<String, Waffe>();
        this.weaponParts = new HashMap<String, Waffenteil>();
    }

    public Waffe getWeapon(final String weaponId) {
        return this.weapons.get(weaponId);
    }

    public boolean addLicense(final Erlaubnis license) {
        if (this.licenses.containsKey(license.getId())) {
            return false;
        } else {
            this.licenses.put(license.getId(), license);
            return true;
        }
    }

    public boolean addWeapon(final Waffe weapon) {
        if (this.weapons.containsKey(weapon.getId())) {
            return false;
        } else {
            this.weapons.put(weapon.getId(), weapon);
            return true;
        }
    }

    public boolean addWeaponPart(final Waffenteil weaponPart) {
        if (this.weaponParts.containsKey(weaponPart.getId())) {
            return false;
        } else {
            this.weaponParts.put(weaponPart.getId(), weaponPart);
            return true;
        }
    }

    public boolean addWeaponPartToWeapon(final String weaponId, final String weaponPartId) {
        Waffe weapon = this.weapons.get(weaponId);
        Waffenteil part = this.weaponParts.get(weaponPartId);

        return weapon.addWeaponPart(part);
    }
}
