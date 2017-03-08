package WeaponRegister;

import java.util.HashMap;
import java.util.Map;

public class Waffenteil {
    private final String id;
    private final String administration;
    private final Map<String, Object> assignment;

    public Waffenteil(final String id, final String administration) {
        this.id = id;
        this.administration = administration;
        this.assignment = new HashMap<String, Object>();
    }

    public String getId() {
        return this.id;
    }

    public String getAdministration() {
        return this.administration;
    }

    public boolean containsAssignment(final String assignment) {
        return this.assignment.containsKey(assignment);
    }

    public boolean addAssignment(final String assignment, final Object object) {
        if (this.assignment.containsKey(assignment)) {
            return false;
        } else {
            this.assignment.put(assignment, object);
            return true;
        }
    }
}
