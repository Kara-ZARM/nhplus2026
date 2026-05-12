# Bewertungsraster — Lernsituation NHPlus

Dieses Raster zeigt euch, woran eure Arbeit gemessen wird. Nutzt es zur Selbstkontrolle.

> **Abgabeort:** Eure Ergebnisse zu den fünf Arbeitsblättern tragt ihr in [`abgabe/AB01.md`](../abgabe/AB01.md) – [`abgabe/AB05.md`](../abgabe/AB05.md) ein.

## Gesamtbewertung

| Bereich | Gewichtung | Beschreibung |
|---|---|---|
| **Architekturverständnis** (AB 01) | 10% | Verständnis von DAO, MVP, Singleton; Nachvollzug einer Modul-Erweiterung; erste eigene Code-Änderung durchgeführt |
| **DSGVO-Analyse** (AB 02) | 10% | Vollständigkeit der gefundenen Verstöße; korrekte Zuordnung zu DSGVO-Artikeln; Ableitung technischer Anforderungen |
| **Planung** (AB 03) | 20% | Qualität der User Stories, Akzeptanzkriterien, Tasks und Testfälle; begründete Priorisierung der DSGVO-Fixes |
| **Implementierung** (AB 04) | 45% | Funktionsfähigkeit, Code-Qualität, Einhaltung der Architektur |
| **Testen & Reflexion** (AB 05) | 15% | Testdokumentation, JUnit-Tests, **Qualität der Code-Reflexion** zu OO-Entwurfsprinzipien (treffsichere Belegstellen, korrekte Begriffsverwendung, Bewusstsein für Trade-offs — die Code-Substanz selbst wird in AB 04 bewertet, hier zählt die Reflexionsleistung), Retrospektive |

## Bestehens-Schwelle für AB 04

Vor der differenzierten Bewertung in der Tabelle unten müssen die folgenden **Pflichtelemente** lauffähig vorliegen — andernfalls ist AB 04 nicht bestanden, unabhängig von den übrigen Kriterien:

- Pfleger-Modul mit **allen vier CRUD-User-Stories** lauffähig (Anlegen, Anzeigen, Ändern, Löschen)
- Login-System mit `PasswordUtil` (Salt + Hash, kein Klartext-Passwort in der DB)

Oberhalb dieser Schwelle beschreiben „Ausreichend" / „Sehr gut" die **Qualität** der Umsetzung — nicht den Umfang.

## Detailkriterien Implementierung (AB 04)

| Kriterium | Sehr gut | Ausreichend | Nicht bestanden |
|---|---|---|---|
| **Pfleger-Modul** | Alle vier CRUD-User-Stories vollständig, alle Akzeptanzkriterien erfüllt, sauberes Verhalten an den Rändern (leere Eingaben, Validierung, Fehlerdialoge) | Alle vier CRUD-User-Stories lauffähig, vereinzelte Akzeptanzkriterien nicht erfüllt oder Fehlerverhalten unscharf | Pflicht-Schwelle verfehlt: weniger als die vier CRUD-Operationen lauffähig oder Modul nicht startbar |
| **Login-System** | `PasswordUtil` korrekt integriert (Salt und Hash werden beim Anlegen gespeichert, Prüfung über `verify`), Fehlermeldung bei falschem Login, Leseauftrag zu `PasswordUtil` (Fragen 1–3) vollständig und fachlich korrekt beantwortet | Login funktioniert grundsätzlich, aber Fehlermeldung fehlt oder einzelne Antworten des Leseauftrags sind lückenhaft | Pflicht-Schwelle verfehlt: Kein Login, Passwort im Klartext gespeichert oder `PasswordUtil` durch eigenes, fehlerhaftes Hashing ersetzt |
| **Architekturkonformität** | Alle Patterns korrekt angewandt (DAO, MVP, Singleton) | Patterns erkennbar, kleinere Abweichungen | Patterns ignoriert, Code unstrukturiert |
| **Code-Qualität** | Javadoc vollständig, sinnvolle Benennung, lesbarer Code | Javadoc vorhanden, Code lesbar | Kein Javadoc, unverständlicher Code |
| **OO-Entwurfsprinzipien** *(anknüpfend an die Zuul-Lernsituation)* | **Geringe Kopplung** zwischen den DAO/MVP-Schichten (kein SQL im Controller, keine FXML-Typen im Model), **hohe Kohäsion / SRP** (jede neue Klasse hat eine klare Verantwortung), **saubere Kapselung** (Felder privat, Zugriff über Methoden), **DRY** (keine duplizierte Logik zwischen Patient- und Caregiver-Modul) | Prinzipien erkennbar angewandt, vereinzelte Verstöße (eine Klasse mit zwei Aufgaben, doppelte Logik an 1–2 Stellen) | Klare Verstöße: öffentliche Felder, Controller greift direkt auf die DB zu, dupliziertes DAO-Grundgerüst, „Gott-Klasse" |
| **Team-Workflow** | Feature-Branches pro Aufgabenpaket, saubere Pull Requests, Kopplungspunkte sichtbar abgestimmt; **gewählte Aufteilung** (Default-Vorschlag oder begründete Abweichung) ist im Abgabe-Template nachvollziehbar dokumentiert | Commits von allen Mitgliedern vorhanden, aber direkt auf `main` oder PR-Prozess lückenhaft, oder Aufteilung nicht reflektiert | Keine oder nur ein Mitglied committet, kein erkennbarer Workflow |
| **Zusätzliche DSGVO-Umsetzung** *(optional — Bonus)* | Eine der beiden frei gewählten DSGVO-Stories zusätzlich funktionsfähig implementiert, Akzeptanzkriterien erfüllt | Umsetzung angefangen, aber nicht vollständig | *kein Punktabzug* — nicht nötig fürs Bestehen; reine Planung in AB 03 reicht für die Grundbewertung, eine vollständige Umsetzung kann die Implementierungsnote nach oben schieben |

## Hinweise

- Das Bewertungsraster dient der **Transparenz** — ihr wisst von Anfang an, worauf es ankommt.
- Die **Gruppenarbeit** wird durch die Git-Historie und den Team-Workflow nachvollzogen. Jedes Mitglied muss in der Git-Historie als Autor **substanzieller Code-Beiträge** sichtbar sein — reine Merge-Commits, Whitespace-Korrekturen oder Typo-Fixes zählen nicht als Eigenanteil.
- Der **Workstream-Vorschlag in AB 04** ist ein Default für den Erstkontakt mit Team-Git — kein Korsett. Eine **begründete Abweichung** (im Abgabe-Template dokumentiert) wird gleichwertig bewertet. Nicht belohnt wird blinde Befolgung des Vorschlags ebenso wie unreflektiertes Abweichen.
- **DSGVO-User-Stories**, die ihr plant aber nicht umsetzt, fließen positiv in die Planungsnote in AB 03 ein. Zusätzliche, über den Pflichtumfang hinaus umgesetzte DSGVO-Stories werten die Implementierungsnote nach oben (siehe Zeile „Zusätzliche DSGVO-Umsetzung" in der Tabelle oben).
