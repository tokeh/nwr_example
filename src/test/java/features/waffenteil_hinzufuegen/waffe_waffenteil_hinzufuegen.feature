Feature: Waffe.Waffenteil_hinzufuegen
  Neue Meldung mit XWaffe-konformer Rückmeldung. (Afo.138, Afo.140)
  Die Nachricht bewirkt, dass ein Waffenteilobjekt einem Waffenobjekt zugeordnet wird.
  Diese Nachricht wird verwendet, um einer Waffe Waffenteile zuzuordnen, die in einer Waffe verbaut sind.
  Diese Zuordnung darf nur über die Nachrichten Waffe.Waffenteil_hinzufuegen oder Waffe.zusammenbauen erfolgen.
  Unter Verwendung der Nachricht Waffe.Waffenteil_hinzufuegen endet der Lebenszyklus einer Waffe nicht,
  auch wenn sich die Struktur der Waffe verändert hat. (FK, Kap.5.2.2.1)

  # Schritte werden vor jedem Scenario ausgeführt (für aufwendige Testdaten)
  Background:
    Given eine Waffe mit der NWR-ID W2017-01-13-1234560-A (Eine gültige Waffe mit Waffenstatus "1" oder "14")
    And eine Erlaubnis mit der NWR-ID E2017-01-13-1234560-A
    And eine Waffe mit der NWR-ID WXYZ (Eine gültige Waffe mit Waffenstatus not in ("1", "14"))
    And ein Waffenteil mit der NWR-ID T2017-01-13-1234561-B (Ein gültiges Waffenteil ohne Zuordnung "verbaut_in")
    And ein Waffenteil mit der NWR-ID T2017-01-13-1234562-C (Ein gültiges Waffenteil ohne Zuordnung "verbaut_in")
    And ein Waffenteil mit der NWR-ID XYZ (mit Zuordnung "verbaut_in")
	And alle Waffenteile und die Waffe sind der Erlaubnis E2017-01-13-1234560-A zugewiesen
	And für alle Waffenteile und die Waffe ist die gleiche Behörde zuständig

  # Happy Paths
  Scenario: Waffenteil wird einer Waffe erfolgreich hinzugefügt
    Given eine gültige Waffe im Register
    And ein gültiges Waffenteil im Register
    When Waffenteil mit der NWR-ID T2017-01-13-1234561-B der Waffe mit der NWR-ID W2017-01-13-1234560-A hinzugefügt wird
    Then erhält die absendende Behörde eine XWaffe-konforme Erfolgsmeldung (NWR-ID des neu angelegten Aktivitätsobjekts und Fehlerliste ist leer)

  Scenario: Mehrere Waffenteile werden einer Waffe erfolgreich hinzugefügt
    Given eine gültige Waffe im Register
    And mehrere Waffenteile:
      | nwr_id_waffenteil     |
      | T2017-01-13-1234561-B | 
      | T2017-01-13-1234562-C |
    When Waffenteile der Waffe mit der NWR-ID W2017-01-13-1234560-A hinzugefügt werden:
      | nwr_id_waffenteil     |
      | T2017-01-13-1234561-B |
      | T2017-01-13-1234562-C |
    Then erhält die absendende Behörde eine XWaffe-konforme Erfolgsmeldung (NWR-ID des neu angelegten Aktivitätsobjekts und Fehlerliste ist leer)

  # Fehlerfälle
  Scenario: Hinzufügen von Waffenteil schlägt fehl wegen falscher Waffen-ID
    When das Waffenteil mit der NWR-ID T2017-01-13-1234561-B der Waffe mit der Waffen-NWR-ID W2017-01-13-1234560-X hinzugefügt wird
    Then erhält die absendende Behörde eine XWaffe-konforme Fehlermeldung, dass die Waffe nicht gefunden wurde

  Scenario: Hinzufügen von Waffenteil schlägt fehl wegen falscher Waffenteil-ID
    When das Waffenteil mit der NWR-ID T2017-01-13-1234561-X der Waffe mit der Waffen-NWR-ID W2017-01-13-1234560-A hinzugefügt wird
    Then erhält die absendende Behörde eine XWaffe-konforme Fehlermeldung, dass das Waffenteil nicht gefunden wurde

  Scenario: Hinzufügen von mehreren Waffenteilen schlägt fehl, wenn mindestens ein Waffenteil gegen die Regeln verstößt
    And mehrere Waffenteile:
      | nwr_id_waffenteil     |
      | T2017-01-13-1234561-B | 
      | XYZ |
    When die Waffenteile der Waffe mit der Waffen-NWR-ID W2017-01-13-1234560-A hinzugefügt werden
    Then erhält die absendende Behörde eine XWaffe-konforme Fehlermeldung, dass das Waffenteil XYZ bereits eine Zuordnung mit "verbaut_in" enthält

  Scenario: Hinzufügen von Waffenteil schlägt fehl wegen falschem Waffenstatus

  Scenario: Hinzufügen von Waffenteil schlägt fehl wegen falscher Zuständigkeit der meldenden Behörde für die Waffe

  Scenario: Hinzufügen von Waffenteil schlägt fehl wegen falscher Zuständigkeit der meldenden Behörde für die hinzuzufügenden Waffenteile

  Scenario: Hinzufügen von Waffenteil schlägt fehl wegen vorhandenem Attribut 'Zuordnungsart' 'verbaut_in' im hinzuzufügenden Waffenteil
    Given ein Waffenteil XYZ mit einer Zuordnungsart "verbaut_in"
	When Waffenteil XYZ soll zur Waffe hinzugefügt werden
	Then erhält die absendende Behörde eine XWaffe-konforme Fehlermeldung, dass das Waffenteil bereits eine Zuordnungsart "verbaut_in" enthält

  Scenario: Hinzufügen von Waffenteil schlägt fehl, da Waffe und hinzuzufügende Waffenteile der selben Erlaubnis zugeordnet sind