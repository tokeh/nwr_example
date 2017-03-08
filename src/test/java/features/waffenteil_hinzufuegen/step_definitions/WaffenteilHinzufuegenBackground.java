package features.waffenteil_hinzufuegen.step_definitions;

import WeaponRegister.Erlaubnis;
import WeaponRegister.Register;
import WeaponRegister.Waffe;
import WeaponRegister.Waffenteil;
import cucumber.api.java.en.Given;

import static org.assertj.core.api.Assertions.assertThat;

public class WaffenteilHinzufuegenBackground {

  final String administrationBW = "Baden-Wuerttemberg";
  Register register;
  Waffe validWeapon;
  Waffe invalidWeapon;
  Erlaubnis license;
  Waffenteil validWeaponPartWithoutAssignmentA;
  Waffenteil validWeaponPartWithoutAssignmentB;
  Waffenteil validWeaponPartWithAssignment;
  boolean returnMessage;

  // Background
  @Given("^eine Waffe mit der NWR-ID (.+) \\(Eine gültige Waffe mit Waffenstatus \"(\\d+)\" oder \"(\\d+)\"\\)$")
  public void validWeapon(final String id, final int statusCode1, final int statusCode2) {
    this.validWeapon = new Waffe(id, this.administrationBW, statusCode1);
    this.register = new Register();
  }

  @Given("^eine Erlaubnis mit der NWR-ID (.+)$")
  public void validLicense(final String id) {
    this.license = new Erlaubnis(id);
    this.register.addLicense(this.license);
  }

  @Given("^eine Waffe mit der NWR-ID (.+) \\(Eine gültige Waffe mit Waffenstatus not in \\(\"(\\d+)\", \"(\\d+)\"\\)\\)$")
  public void invalidWeapon(final String id, final int statusCode1, final int statusCode2) {
    this.invalidWeapon = new Waffe(id, this.administrationBW, statusCode1 + 1);
  }

  @Given("^ein Waffenteil mit der NWR-ID (.+) \\(Ein gültiges Waffenteil ohne Zuordnung \"verbaut_in\"\\)$")
  public void validWeaponPartWithoutAssignment(final String id) {
    if (this.validWeaponPartWithoutAssignmentA == null) {
      this.validWeaponPartWithoutAssignmentA = new Waffenteil(id, this.administrationBW);
    } else {
      this.validWeaponPartWithoutAssignmentB = new Waffenteil(id, this.administrationBW);
    }
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
    this.license.addWeaponPart(this.validWeaponPartWithoutAssignmentA);
    this.license.addWeaponPart(this.validWeaponPartWithAssignment);
  }

  @Given("^für alle Waffenteile und die Waffe ist die gleiche Behörde zuständig$")
  public void weaponAndWeaponPartsHaveSameAdministration() {
    assertThat(this.validWeapon.getAdministration().equals(
        this.validWeaponPartWithoutAssignmentA.getAdministration())).isTrue();
    assertThat(this.validWeapon.getAdministration().equals(
        this.validWeaponPartWithAssignment.getAdministration())).isTrue();
    assertThat(this.validWeaponPartWithoutAssignmentA.getAdministration().equals(
        this.validWeaponPartWithAssignment.getAdministration())).isTrue();
  }
}
