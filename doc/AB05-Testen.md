# AB 05 — Testen

> **Thema:** Qualitätssicherung durch manuelle und automatisierte Tests
>
> **Zeitrahmen:** ~4 Unterrichtsstunden

**Nach Bearbeitung dieses Arbeitsblatts könnt ihr:**
- Testfälle systematisch durchführen und dokumentieren
- zwischen manuellem und automatisiertem Testen unterscheiden
- einen JUnit-Test für eine DAO-Klasse schreiben
- die Qualität eurer Implementierung anhand der Akzeptanzkriterien überprüfen
- OO-Entwurfsprinzipien aus der Zuul-Lernsituation an eurem eigenen Code reflektieren

## Aufgabe 1: Manuelle Tests durchführen

Führt die Testfälle durch, die ihr in [AB 03](AB03-User-Stories-Akzeptanzkriterien-Tasks-Testfaelle.md) definiert habt. Dokumentiert die Ergebnisse in [`abgabe/AB05.md`](../abgabe/AB05.md) — das Template enthält eine vorbereitete Tabelle.

Für jeden **nicht bestandenen** Testfall:
- Beschreibt das tatsächliche Verhalten.
- Behebt den Fehler im Code.
- Führt den Test erneut durch und dokumentiert das Ergebnis.

## Aufgabe 2: Weitere Tests

Testet auch Szenarien, die über eure Testfälle aus AB 03 hinausgehen:

- **Grenzwerte:** Was passiert bei leeren Eingabefeldern? Bei sehr langen Texten?
- **Zusammenspiel:** Funktioniert das Löschen eines Patienten, der Behandlungen hat?
- **Konsistenz:** Werden Änderungen sofort in der Datenbank gespeichert?

Dokumentiert diese Tests ebenfalls in [`abgabe/AB05.md`](../abgabe/AB05.md).

## Aufgabe 3: JUnit-Tests

Schreibt mindestens **zwei JUnit-Tests** für eine eurer DAO-Klassen. Testet z.B.:
- Einen neuen Datensatz anlegen und wieder auslesen (`create` + `read`)
- Einen Datensatz löschen und prüfen, dass er nicht mehr existiert (`deleteById` + `read`)

Als Vorlage dient die Datei `src/test/java/de/hitec/nhplus/datastorage/PatientDaoTest.java`. Dort seht ihr, wie ein DAO-Test aufgebaut wird.

> **Hinweis:** JUnit 5 ist bereits als Dependency in der `pom.xml` eingebunden. Erstellt eure Testklassen im Verzeichnis `src/test/java/` in der gleichen Package-Struktur wie der zu testende Code.

## Aufgabe 4: Code-Reflexion — OO-Entwurfsprinzipien

In der **vorangegangenen Zuul-Lernsituation** habt ihr Prinzipien wie **Kopplung**, **Kohäsion / SRP**, **Kapselung (Geheimnisprinzip)** und **DRY** kennengelernt. Wendet diese jetzt auf euren eigenen NHPlus-Code an. Schaut bei Unsicherheit in eure Zuul-Unterlagen zurück.

Findet in eurem neu geschriebenen Code (Pfleger-Modul und Login-System):

1. Eine Stelle, an der ihr bewusst **hohe Kohäsion / SRP** erreicht habt — welche Klasse, welche Verantwortung?
2. Eine Stelle, an der ihr **DRY** angewendet habt — was habt ihr nicht dupliziert, obwohl ihr es hättet tun können?
3. Eine Stelle, an der ihr **Kapselung** gezielt eingesetzt habt — welche Felder sind privat, und warum?
4. Eine Stelle, an der ihr **Kopplung gering gehalten** habt — welche zwei Klassen hättet ihr enger koppeln können, habt es aber nicht getan?

Beschreibt jede Stelle in 2–3 Sätzen in [`abgabe/AB05.md`](../abgabe/AB05.md) und verweist auf Klasse und Methode.

## Aufgabe 5: Retrospektive

Beantwortet als Gruppe die folgenden Fragen und haltet die Antworten in [`abgabe/AB05.md`](../abgabe/AB05.md) fest:

1. **Was lief gut** bei der Umsetzung der Lernsituation?
2. **Was war die größte Hürde?**
3. **Überraschungen:** Welche Story oder welcher Workstream war überraschend aufwändig oder leicht — und warum?
4. **Was würdet ihr beim nächsten Mal anders machen?**

---

## Relevante Ressourcen

- [AB 03 — User Stories & Testfälle](AB03-User-Stories-Akzeptanzkriterien-Tasks-Testfaelle.md) — eure definierten Testfälle
- [AB 04 — Implementierung](AB04-Implementierung.md) — der zu testende Code

---

*Zurück zur [Projekt-Übersicht](../README.md)*
