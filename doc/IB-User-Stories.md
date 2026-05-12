# Informationsblatt: User Stories

## 1) User Stories allgemein

User Stories sind ein Werkzeug aus der agilen Softwareentwicklung, um Anforderungen an eine Software zu formulieren. Der Begriff „User Story" stammt aus dem Englischen und beschreibt im wörtlichen Sinne eine Geschichte (Story) eines Anwenders (User). Eine User Story beschreibt also eine gewünschte Funktionalität eines Systems aus Sicht des Anwenders.

Dabei bietet eine User Story vor allem drei Vorteile:

- Sie ist leicht zu verstehen und vermittelt die Wünsche der Anwender.
- Sie ist schnell erstellt und erleichtert die Schätzung des Aufwands zur Realisierung.
- Sie lässt sich schrittweise detaillieren und unterstützt so die iterative Entwicklung.

Die Anforderung wird dabei in einem Satz und mit einfachen Worten beschrieben. So ist sichergestellt, dass alle an der Entwicklung beteiligten Kollegen sie auch ohne technischen Hintergrund verstehen. User Stories formulieren das **WER**, **WAS** und **WARUM** einer Funktionalität — also wer möchte was von einem System, um welchen Nutzen davon zu haben. *Wie* die gewünschte Funktionalität später umgesetzt wird, ist für eine User Story unerheblich.

### Aufbau einer User Story

> **Als** \<User\> **möchte ich** \<Funktionalität\>, **um** \<Nutzen\> **zu erreichen.**

Zu jeder User Story gehören **Akzeptanzkriterien**. Sie legen fest, wann eine User Story vollständig implementiert wurde. Die Akzeptanzkriterien sind wiederum die Grundlage, um:

1. die **Aufgaben (Tasks)** festzulegen, die nötig sind, um die User Story umzusetzen, und
2. die **Testfälle** abzuleiten, um die Implementierung der Story zu testen.

In der Praxis werden User Stories heute meist in digitalen Tools verwaltet (z.B. Kanban-Boards, GitHub Issues oder Jira). Die Akzeptanzkriterien, Tasks und Testfälle werden dabei direkt der Story zugeordnet.

### Häufige Fehler

User Stories beschreiben eine Funktionalität **aus Anwendersicht** und benennen einen **echten Nutzen**. Die folgenden fünf Fehlerbilder tauchen regelmäßig auf:

#### F1 — Entwicklersicht statt Anwendersicht

| So nicht | Besser |
|---|---|
| *Als Entwickler möchte ich eine Datenbanktabelle für Patienten anlegen.* | *Als Wohnbereichsleiter möchte ich die Daten meiner Patienten verwalten können.* |

Die erste Variante beschreibt eine technische Aufgabe — das gehört in die **Tasks**, nicht in die User Story.

#### F2 — Rolle zu generisch

| So nicht | Besser |
|---|---|
| *Als **User** möchte ich Patienten anlegen können…* | *Als **Wohnbereichsleiter** möchte ich Patienten anlegen können…* |

„User" verrät nichts über Motivation und Berechtigung. Pflegekraft, Wohnbereichsleiter, Administrator und Patient haben unterschiedliche Bedürfnisse und Rechte — die Rolle muss das abbilden.

#### F3 — UI-Element statt Funktionalität

| So nicht | Besser |
|---|---|
| *…möchte ich einen **Button zum Exportieren**, um Daten exportieren zu können.* | *…möchte ich die Patientendaten als CSV exportieren können, um sie an das Abrechnungssystem zu übergeben.* |

Ein Button ist ein Lösungsvorschlag, keine Anforderung. Die Story beschreibt *was* erreicht werden soll — ob das später ein Button, ein Menüpunkt oder ein Tastenkürzel wird, entscheidet die Implementierung.

#### F4 — Nutzen trivial oder tautologisch

| So nicht | Besser |
|---|---|
| *…um die Funktion **nutzen zu können**.* | *…um bei Schichtwechsel schnell zu erkennen, welche Patienten heute noch behandelt werden müssen.* |
| *…um Patienten zu **verwalten**.* | *…um die Zimmerbelegung gemäß der Pflegestufen vornehmen zu können.* |

Wenn der Nutzen nur die Funktionalität umformuliert, fehlt der Business Value. Fragt euch: *Warum sollte ein Unternehmen Geld dafür ausgeben?*

#### F5 — Story zu groß („Epic")

| So nicht | Besser (aufgesplittet) |
|---|---|
| *…möchte ich **das komplette Pflegekraft-Modul** haben…* | 1. *…Pflegekräfte in einer Übersicht sehen…* <br> 2. *…eine neue Pflegekraft anlegen…* <br> 3. *…eine Pflegekraft löschen…* |

Eine Story, die mehrere Tage Arbeit erfordert und viele Akzeptanzkriterien sammelt, ist ein **Epic**. Splittet in Stories, die sich einzeln umsetzen und abnehmen lassen (siehe INVEST unten).

### Qualitätscheck: INVEST

Nach dem Schreiben einer User Story prüft ihr sie gegen die **INVEST-Kriterien** (Bill Wake, 2003). Eine gute Story erfüllt alle sechs — sonst umschreiben oder splitten.

| Buchstabe | Kriterium | Prüffrage | Typischer Verstoß |
|---|---|---|---|
| **I** | **I**ndependent — unabhängig | Könnte diese Story allein umgesetzt werden, ohne auf eine andere zu warten? | *„Als Pfleger möchte ich mich einloggen"* setzt die Story zum Anlegen von Pflegern voraus → Reihenfolge dokumentieren oder zusammenlegen. |
| **N** | **N**egotiable — verhandelbar | Ist die Story offen für Diskussion, oder wurde schon die Implementierung festgeschrieben? | *„…über ein Dropdown mit max. 5 Einträgen, Font Arial 12pt…"* → gehört in Akzeptanzkriterien/Design, nicht in die Story. |
| **V** | **V**aluable — wertstiftend | Liefert die Umsetzung einen erkennbaren Nutzen für den Anwender oder das Unternehmen? | *„…um die Funktion zu nutzen"* → trivialer Nutzen, F4. |
| **E** | **E**stimable — schätzbar | Könnt ihr den Aufwand grob in Stunden schätzen? | Wenn ihr antwortet *„keine Ahnung, hängt von zu vielen Dingen ab"* → Story ist zu unklar oder zu groß, splitten. |
| **S** | **S**mall — klein genug | Passt die Umsetzung realistisch in 1–2 Arbeitstage pro Person? | Mehr als 5–7 Akzeptanzkriterien ist ein starkes Indiz für ein Epic, F5. |
| **T** | **T**estable — testbar | Lassen sich aus den Akzeptanzkriterien konkrete Testfälle ableiten? | *„…soll benutzerfreundlich sein"* → nicht testbar. Besser: *„…maximal 3 Eingabefelder, Fehlermeldung bei leerem Feld"*. |

**So wendet ihr den Check an:** Geht Story für Story durch. Wenn *ein* Kriterium nicht erfüllt ist, überarbeitet die Story — entweder konkretisieren (E, T), splitten (S, I) oder den Nutzen schärfen (V). Eine Story zu verwerfen ist kein Fehler, sondern Teil der Planung.

### Akzeptanzkriterien: Nutzer-sichtbar, nicht implementierungs-technisch

Akzeptanzkriterien beschreiben **beobachtbares Verhalten**, nicht Code-Interna:

| So nicht | Besser |
|---|---|
| *Die Methode `savePatient()` gibt `true` zurück.* | *Nach Klick auf „Speichern" erscheint der Patient in der Übersicht und ist nach Neustart der Anwendung noch vorhanden.* |
| *Das Passwort wird mit `MessageDigest` gehasht.* | *Nach dem Speichern eines neuen Nutzers ist das Passwort in der Datenbank nicht mehr im Klartext lesbar.* |

Regel: Ein Akzeptanzkriterium soll für den Product Owner / Auftraggeber verständlich und nachprüfbar sein — auch ohne Blick in den Quellcode.

### Tasks: Das richtige Abstraktionsniveau

Tasks beschreiben **Arbeitspakete**, nicht die konkrete Lösung. Sie benennen, *was* zu tun ist, ohne die Implementierung vorwegzunehmen:

| Zu spezifisch (vorweggenommen) | Angemessen (Arbeitspaket) |
|---|---|
| Modelklasse `Patient` erstellen, abgeleitet von `Person`, Konstruktoren mit und ohne ID | Modelklasse für Patienten erstellen |
| `AllPatientView` erstellen: TableView mit Textfeldern, Button zum Hinzufügen und Löschen | Übersichts-View für Patienten erstellen |

Die konkreten Klassen, Vererbungshierarchien und GUI-Elemente sind **Architekturentscheidungen**, die bei der Implementierung getroffen werden — nicht bei der Planung.

---

## 2) User Stories aus NHPlus

> **Hinweis:** Die folgenden User Stories beschreiben die bereits implementierten Module von NHPlus. Sie dienen als Vorlage für eure eigenen User Stories in [AB 03](AB03-User-Stories-Akzeptanzkriterien-Tasks-Testfaelle.md). Beachtet, dass die Tasks in Story 1 auch einmalige Architektur-Aufgaben enthalten (DAO-Schicht, Datenbankverbindung), die für spätere Module nicht erneut anfallen.

### User Story 1: Patientenverwaltung

> **Als** Wohnbereichsleiter **möchte ich** die persönlichen Daten der Pflegepatienten verwalten können, **um** die Zimmerbelegung gemäß der Pflegestufen der Patienten vornehmen zu können.

#### Akzeptanzkriterien

| Nr. | Kriterium |
|-----|-----------|
| A_1 | Die persönlichen Daten eines Patienten bestehen aus der PatientenID, dem Nachnamen, Vornamen, Geburtsdatum, der Pflegestufe, Raumnummer im Pflegeheim und dem Vermögensstand. |
| A_2 | Alle Feldbelegungen sind verpflichtend. |
| A_3 | Alle Patienten werden mit ihren vollständigen Daten in einer tabellarischen Übersicht dargestellt. |
| A_4 | Jede Information zu einem Patienten kann aus der Übersicht heraus geändert werden. |
| A_5 | Es können neue Patienten hinzugefügt werden. |
| A_6 | Ein ausgewählter Patient kann gelöscht werden. |
| A_7 | Jede getätigte Änderung — Anlegen eines Patienten, Ändern der Stammdaten und das Löschen eines Patienten — wird in der Datenbank abgebildet. |

#### Tasks

| Nr. | Task |
|-----|------|
| T_1 | Modelklasse für Patienten erstellen. |
| T_2 | Datenzugriffsschicht (DAO) aufbauen: allgemeines DAO-Interface mit CRUD-Methoden, Basisimplementierung, Datenbankverbindung und Factory. Konkretes DAO für Patienten erstellen. |
| T_3 | Datenbanktabelle für die Stammdaten der Patienten anlegen. |
| T_4 | Übersichts-View für Patienten erstellen: tabellarische Anzeige aller Patientendaten mit Möglichkeit zum Anlegen, Ändern und Löschen. |
| T_5 | Controller für die Patienten-Übersicht erstellen. |

#### Testfälle (Auszug)

**TF1: Alle Patienten anzeigen**

| | |
|---|---|
| **Vorbedingung** | Der Nutzer hat im Hauptfenster der Anwendung die Option *Patienten/innen* ausgewählt. |
| **Testschritte** | — |
| **Erwartetes Ergebnis** | Es werden alle Patienten, die in der Datenbank gespeichert sind, mit ihren vollständigen Daten (ID, Nachname, Vorname, Geburtsdatum, Pflegestufe, Raumnummer, Vermögensstand) in einer Übersicht angezeigt. |

**TF2: Patientendaten ändern**

| | |
|---|---|
| **Vorbedingung** | Der Nutzer hat im Hauptfenster der Anwendung die Option *Patienten/innen* ausgewählt. |
| **Testschritte** | 1. Der User wählt per Doppelklick auf einen Nachnamen einen Patienten aus. 2. Der User ändert den Nachnamen. 3. Der User beendet die Eingabe mit der Entertaste. |
| **Erwartetes Ergebnis** | 1. Der geänderte Nachname wird in der Übersicht angezeigt. 2. Der geänderte Nachname wurde in der Datenbank gespeichert. |

**TF3: Patient anlegen**

| | |
|---|---|
| **Vorbedingung** | Der Nutzer hat im Hauptfenster der Anwendung die Option *Patienten/innen* ausgewählt. |
| **Testschritte** | 1. Der User hat alle Stammdaten des anzulegenden Patienten in die dafür vorgesehenen Felder eingegeben. 2. Der User hat den Button *Hinzufügen* betätigt. |
| **Erwartetes Ergebnis** | 1. Der neue Patient wird mit allen eingetragenen Daten sowie einer automatisch erstellten ID als letzter Eintrag in der Übersicht angezeigt. 2. Der neue Patient wurde in der Datenbank gespeichert. |

---

### User Story 2: Behandlungsdokumentation

> **Als** Pflegekraft **möchte ich** die Behandlungen meiner Patienten dokumentieren und die Dokumentationen vergangener Behandlungen einsehen können, **um** geeignete Folgemaßnahmen ergreifen zu können.

#### Akzeptanzkriterien

| Nr. | Kriterium |
|-----|-----------|
| A_1 | Eine Behandlung besitzt eine ID, einen Patienten, ein Datum, Beginn, Ende, eine Kurz- und eine Langbeschreibung. |
| A_2 | Alle Behandlungen werden in einer tabellarischen Übersicht mit ihrer ID, der PatientenID, Datum, Beginn, Ende und Kurzbeschreibung angezeigt. |
| A_3 | Die Anzeige der Behandlungen kann durch Auswahl eines Patienten auf alle Behandlungen dieses Patienten eingeschränkt werden. Diese Einschränkung soll durch die Option „alle" wieder aufgehoben werden können. |
| A_4 | Durch Klick auf einen Button soll in einem neuen Fenster eine neue Behandlung angelegt werden können. |
| A_5 | Wurde beim Anlegen einer neuen Behandlung kein Patient ausgewählt, erscheint eine Fehlermeldung. |
| A_6 | Eine ausgewählte Behandlung soll gelöscht werden können. |
| A_7 | Jede Behandlung kann durch Auswahl in einem weiteren Fenster geöffnet werden, um nähere Informationen zu erfahren (Patient, Pflegestufe, Datum, Beginn, Ende, Kurzbeschreibung und Langbeschreibung). Es soll eine Möglichkeit zum Ändern der Daten und zum Verlassen des Fensters über die Option *Abbruch* geben. |

#### Tasks

| Nr. | Task |
|-----|------|
| T_1 | Modelklasse für Behandlungen erstellen. |
| T_2 | DAO für Behandlungen implementieren, einschließlich Methoden zum Filtern und Löschen nach Patient. |
| T_3 | Datenbanktabelle für Behandlungen anlegen (mit Fremdschlüssel auf die Patienten-Tabelle). |
| T_4 | Übersichts-View für Behandlungen erstellen: tabellarische Anzeige mit Filtermöglichkeit nach Patient. |
| T_5 | View zum Anlegen einer neuen Behandlung erstellen. |
| T_6 | Detail-View für eine Behandlung erstellen: Anzeige und Bearbeitung aller Behandlungsdaten. |
| T_7 | Beim Löschen eines Patienten dessen Behandlungen ebenfalls löschen. |
| T_8 | Controller für die Behandlungs-Views erstellen. |

#### Testfälle (Auszug)

**TF1: Behandlungen eines Patienten anzeigen**

| | |
|---|---|
| **Vorbedingung** | Der Nutzer hat im Hauptfenster die Option *Behandlungen* ausgewählt. |
| **Testschritte** | Der User wählt in der Auswahlliste einen Patienten aus. |
| **Erwartetes Ergebnis** | Es werden nur die Behandlungen des ausgewählten Patienten in der Übersicht angezeigt. Bei Auswahl von „alle" werden wieder alle Behandlungen angezeigt. |

**TF2: Behandlung ohne Patientenauswahl anlegen**

| | |
|---|---|
| **Vorbedingung** | Der Nutzer hat im Hauptfenster die Option *Behandlungen* ausgewählt. Kein Patient ist in der Auswahlliste ausgewählt. |
| **Testschritte** | Der User klickt auf den Button zum Anlegen einer neuen Behandlung. |
| **Erwartetes Ergebnis** | Es erscheint eine Fehlermeldung, die darauf hinweist, dass zuerst ein Patient ausgewählt werden muss. |

---

*Siehe auch: [AB 03 — User Stories, Akzeptanzkriterien, Tasks, Testfälle](AB03-User-Stories-Akzeptanzkriterien-Tasks-Testfaelle.md)*
