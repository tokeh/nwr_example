package features.steps.waffe;

import static org.assertj.core.api.Assertions.*;

import WeaponRegister.Erlaubnis;
import WeaponRegister.Register;
import WeaponRegister.Waffe;
import WeaponRegister.Waffenteil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class waffenteil_hinzufuegen {

    private final String administrationBW = "Baden-Wuerttemberg";
    private Register register;
    private Waffe validWeapon;
    private Waffe invalidWeapon;
    private Erlaubnis license;
    private Waffenteil validWeaponPartWithoutAssignment;
    private Waffenteil validWeaponPartWithAssignment;

    @Given("^eine Waffe mit der NWR-ID (.+) \\(Eine gültige Waffe mit Waffenstatus \"(\\d+)\" oder \"(\\d+)\"\\)$")
    public void validWeapon(final String id, final int statusCode1, final int statusCode2) {
        this.validWeapon = new Waffe(id, this.administrationBW, statusCode1);
        this.register = new Register();
    }

    @Given("^eine Erlaubnis mit der NWR-ID (.+)$")
    public void validLicense(final String id) {
        this.license = new Erlaubnis(id);
    }

    @Given("^eine Waffe mit der NWR-ID (.+) \\(Eine gültige Waffe mit Waffenstatus not in \\(\"(\\d+)\", \"(\\d+)\"\\)\\)$")
    public void invalidWeapon(final String id, final int statusCode1, final int statusCode2) {
        this.invalidWeapon = new Waffe(id, this.administrationBW, statusCode1 + 1);
    }

    @Given("^ein Waffenteil mit der NWR-ID (.+) \\(Ein gültiges Waffenteil ohne Zuordnung \"verbaut_in\"\\)$")
    public void validWeaponPartWithoutAssignment(final String id) {
        this.validWeaponPartWithoutAssignment = new Waffenteil(id, this.administrationBW);
    }

    @Given("^ein Waffenteil mit der NWR-ID (.+) \\(mit Zuordnung \"(.+)\"\\)$")
    public void validWeaponPartWithAssignment(String id, String assignment) {
        this.validWeaponPartWithAssignment = new Waffenteil(id, this.administrationBW);
        //assigned to validWeapon
        assertThat(this.validWeaponPartWithAssignment.addAssignment(assignment, this.validWeapon)).isTrue();
    }

    @Given("^alle Waffenteile und die Waffe sind der Erlaubnis (.+) zugewiesen$")
    public void weaponAndWeaponPartsAssignedToLicense(String id) {
        assertThat(this.license.getId()).matches(id);
        this.license.addWeapon(this.validWeapon);
        this.license.addWeaponPart(this.validWeaponPartWithoutAssignment);
        this.license.addWeaponPart(this.validWeaponPartWithAssignment);
    }

    @Given("^für alle Waffenteile und die Waffe ist die gleiche Behörde zuständig$")
    public void weaponAndWeaponPartsHaveSameAdministration() {
        assertThat(this.validWeapon.getAdministration().equals(
                this.validWeaponPartWithoutAssignment.getAdministration())).isTrue();
        assertThat(this.validWeapon.getAdministration().equals(
                this.validWeaponPartWithAssignment.getAdministration())).isTrue();
        assertThat(this.validWeaponPartWithoutAssignment.getAdministration().equals(
                this.validWeaponPartWithAssignment.getAdministration())).isTrue();
    }

    @When("^Waffenteil mit der NWR-ID (.+) der Waffe mit der NWR-ID (.+) hinzugefügt wird")
    public void weaponPartAssignedToWeapon(final String weaponId, final String partId) throws Throwable {

    }
}
