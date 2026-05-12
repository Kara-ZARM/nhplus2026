# AB 03 — User Stories, Akzeptanzkriterien, Tasks, Testfälle

> **Thema:** Planung der Erweiterung von NHPlus
>
> **Zeitrahmen:** ~4-5 Unterrichtsstunden

**Nach Bearbeitung dieses Arbeitsblatts könnt ihr:**
- User Stories mit Akzeptanzkriterien formulieren
- aus User Stories konkrete Entwicklungs-Tasks ableiten
- Testfälle aus Akzeptanzkriterien ableiten

> **Hinweis:** Zum Aufbau und zur Formulierung von User Stories siehe das [Informationsblatt: User Stories](IB-User-Stories.md). Dort findet ihr auch vollständige Beispiele zu den bereits implementierten Modulen (Patienten, Behandlungen).

## Aufgabenstellung

Plant innerhalb eurer Gruppe die Erweiterung von NHPlus. Dabei sind **zwei Bereiche** zu bearbeiten:

### 1. Pfleger-Verwaltung

NHPlus soll um ein vollständiges Modul zur Verwaltung von Pflegekräften erweitert werden (vgl. AB01, Aufgabe 2). Erstellt dafür User Stories, die mindestens das Anlegen, Anzeigen, Ändern und Löschen von Pflegekräften abdecken.

### 2. Datenschutz-Fixes

Wählt aus euren DSGVO-Verstößen in [AB 02](AB02-Datenschutzanalyse.md) **drei besonders relevante** aus. Eine davon **muss** das fehlende Login sein (Zugangsbeschränkung); die anderen zwei wählt ihr frei. Erstellt pro Verstoß eine User Story, die ihn behebt.

Begründet eure Auswahl in 2–3 Sätzen: Warum habt ihr gerade diese drei Verstöße priorisiert (Schadensrisiko, Aufwand, rechtliches Gewicht)? Priorisierung ist eine Kernkompetenz agiler Arbeit — nicht jede gute Idee wird auch umgesetzt.

---

Für jede User Story:

1. Formuliert die **User Story** im Format: *Als \<Rolle\> möchte ich \<Funktionalität\>, um \<Nutzen\> zu erreichen.*
2. Definiert **Akzeptanzkriterien**, die festlegen, wann die User Story fertig implementiert ist.
3. Leitet **Tasks** ab — welche Klassen müssen neu erstellt, welche bestehenden Klassen angepasst werden?
4. Leitet aus den Akzeptanzkriterien **Testfälle** ab.

**Jedes Gruppenmitglied formuliert mindestens eine User Story mit.**

---

## Qualitätscheck vor Abgabe

Prüft jede User Story gegen die **INVEST-Kriterien** und die fünf typischen Fehlerbilder (F1–F5) aus dem [Informationsblatt User Stories](IB-User-Stories.md#qualitätscheck-invest). Tragt pro Story kurz ein, wie ihr die Kriterien erfüllt — oder begründet, warum ihr bewusst abweicht.

> **Mini-Peer-Review:** Lasst eine andere Gruppe *eine* eurer User Stories gegenlesen (F1–F5, INVEST). Notiert, was zurückkam und was ihr angepasst habt. 10 Minuten, hoher Lerneffekt.

---

## Abgabe

Tragt eure Ergebnisse in [`abgabe/AB03.md`](../abgabe/AB03.md) ein. Das Template enthält vorbereitete Blöcke für User Stories, Akzeptanzkriterien, Tasks, Testfälle sowie die INVEST-Tabelle.

---

## Relevante Ressourcen

- [Informationsblatt: User Stories](IB-User-Stories.md) — Erklärung und vollständige Beispiele
- [AB 01 — Analyse der Architektur](AB01-Analyse-der-Architektur.md) — technischer Kontext und Architekturverständnis
- [AB 02 — Datenschutzanalyse](AB02-Datenschutzanalyse.md) — eure identifizierten Verstöße und Anforderungen

---

*Weiter mit [AB 04 — Implementierung](AB04-Implementierung.md)*
