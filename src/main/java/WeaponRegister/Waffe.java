package WeaponRegister;

import java.util.HashMap;
import java.util.Map;

public class Waffe {
    private final String id;
    private final String administration;
    private final int statusCode;
    private final Map<String, Waffenteil> weaponParts;

    public Waffe(final String id, final String administration, final int statusCode) {
        this.id = id;
        this.administration = administration;
        this.statusCode = statusCode;
        this.weaponParts = new HashMap<String, Waffenteil>();
    }

    public String getId() {
        return this.id;
    }

    public String getAdministration() {
        return this.administration;
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
