package features.waffenteil_hinzufuegen.step_definitions;

import static org.assertj.core.api.Assertions.*;

import WeaponRegister.Erlaubnis;
import WeaponRegister.Register;
import WeaponRegister.Waffe;
import WeaponRegister.Waffenteil;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Map;

public class WaffenteilHinzufuegen {

    // Für Dependency Injection mit PicoContainer
    private WaffenteilHinzufuegenBackground background;

    public WaffenteilHinzufuegen(WaffenteilHinzufuegenBackground background) {
        this.background = background;
    }

    // Steps der Szenarien
    @Given("^eine gültige Waffe im Register$")
    public void validWeapon() {
        assertThat(background.register.addWeapon(background.validWeapon)).isTrue();
        assertThat(background.register.getWeapon(background.validWeapon.getId())).isNotNull();
    }

    @Given("^ein gültiges Waffenteil im Register$")
    public void validWeaponPart() {
        assertThat(background.register.addWeaponPart(background.validWeaponPartWithoutAssignmentA)).isTrue();
    }

    @When("^Waffenteil mit der NWR-ID (.+) der Waffe mit der NWR-ID (.+) hinzugefügt wird$")
    public void weaponPartAssignedToWeapon(final String partId, final String weaponId) {
        background.returnMessage = background.register.addWeaponPartToWeapon(weaponId, partId);
    }

    @Then("^erhält die absendende Behörde eine XWaffe-konforme Erfolgsmeldung \\(NWR-ID des neu angelegten Aktivitätsobjekts und Fehlerliste ist leer\\)$")
    public void returnMessage() {
        assertThat(background.returnMessage).isTrue();
    }

    @And("^mehrere Waffenteile:$")
    public void multipleWeaponParts(final DataTable weaponParts) {
        for (Map<String, String> row : weaponParts.asMaps(String.class, String.class)) {
            String partId = row.get("nwr_id_waffenteil");
            assertThat(background.register.addWeaponPart(new Waffenteil(partId, background.administrationBW))).isTrue();
            assertThat(background.register.getWeaponPart(partId)).isNotNull();
        }
    }

    // Tagged Hooks @Before/@After
    @Before
    public void beforeScenario() {
        System.err.println("before scenario");
    }

    @After
    public void afterScenario() {
        System.err.println("after scenario");
    }

    @Before("@happy_path")
    public void beforeTag() {
        System.err.println("before tag happy_path");
    }

    @After("@happy_path")
    public void afterTag() {
        System.err.println("after tag happy_path");
    }
}
