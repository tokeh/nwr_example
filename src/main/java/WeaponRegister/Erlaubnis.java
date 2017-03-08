package WeaponRegister;

import java.util.HashMap;
import java.util.Map;

public class Erlaubnis {
    private final String id;
    private final Map<String, Waffe> weapons;
    private final Map<String, Waffenteil> weaponParts;

    public Erlaubnis(final String id) {
        this.id = id;
        this.weapons = new HashMap<String, Waffe>();
        this.weaponParts = new HashMap<String, Waffenteil>();
    }

    public String getId() {
        return this.id;
    }

    public boolean containsWeapon(final String id) {
        return this.weapons.containsKey(id);
    }

    public boolean containsWeaponPart(final String id) {
        return this.weaponParts.containsKey(id);
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
}
