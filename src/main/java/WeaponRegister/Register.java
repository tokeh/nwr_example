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

    public Waffe getWeapon(final String id) {
        return this.weapons.get(id);
    }

    public boolean addWeapon(final String licenseId, final Waffe weapon) {
        if (this.weapons.containsKey(weapon)) {
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
