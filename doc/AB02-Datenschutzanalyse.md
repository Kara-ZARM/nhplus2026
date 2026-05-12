# AB 02 — Datenschutz und NHPlus

> **Thema:** DSGVO im Kontext einer Pflegeheim-Anwendung
>
> **Zeitrahmen:** ~3-4 Unterrichtsstunden (inkl. ~1 UStd für die DSGVO-Webapp)

**Nach Bearbeitung dieses Arbeitsblatts könnt ihr:**
- konkrete Datenschutzverstöße in einer bestehenden Anwendung identifizieren
- Verstöße den relevanten DSGVO-Artikeln zuordnen
- technische Anforderungen für eine datenschutzkonforme Software ableiten

## Vorbereitung: DSGVO-Grundlagen erarbeiten

Bearbeitet **zuerst** die interaktive DSGVO-Webapp, um die notwendigen Grundlagen zu erarbeiten:

> **https://berndheidemann.github.io/DSGVO-LF5/**

Die dort vermittelten Inhalte bilden die Basis für die folgenden Aufgaben.

## Kontext: Besondere Kategorien personenbezogener Daten

NHPlus verwaltet Daten eines Pflegeheims. Pflegeheime verarbeiten **Gesundheitsdaten**, die laut Art. 9 DSGVO als *besondere Kategorien personenbezogener Daten* gelten. Das ist die **höchste Sensibilitätsstufe** — für die Verarbeitung gelten verschärfte Anforderungen.

Behaltet bei der folgenden Analyse im Hinterkopf: Es geht nicht nur um Namen und Adressen, sondern um psychische Zustandsbeschreibungen, Pflegestufen und medizinische Behandlungsprotokolle.

## Aufgabe 1: Verstöße in NHPlus identifizieren

Analysiert die Anwendung NHPlus und ihren Quellcode. Untersucht die Anwendung dabei systematisch in den folgenden drei Bereichen. Gebt für jeden Verstoß an: **Was** verstößt, **wogegen** (DSGVO-Artikel) und **warum** es ein Problem ist.

### a) Sicherheitsmaßnahmen (Art. 32 DSGVO)

Untersucht, welche technischen Schutzmaßnahmen vorhanden oder **nicht** vorhanden sind. Tragt eure Analyse hier ein.

### b) Grundprinzipien der Datenverarbeitung (Art. 5 DSGVO)

Untersucht, ob die Anwendung die Grundprinzipien einhält. Tragt eure Analyse hier ein.

### c) Betroffenenrechte (Art. 15–22 DSGVO)

Untersucht, ob Patienten ihre Rechte ausüben können. Tragt eure Analyse hier ein.

> **Richtwert (mit Qualitätsdimension):** Eine gründliche Analyse kommt auf **ca. 10–15 Verstöße** über alle drei Bereiche. Die Zahl ist eine Orientierung, kein Punktekonto: ein präzise belegter Verstoß mit DSGVO-Artikel und konkreter Code-/Datenmodell-Stelle ist mehr wert als drei oberflächlich aufgezählte. Wenn ihr deutlich weniger als 10 findet, schaut noch einmal genauer in den Code (Authentifizierung, Logging, Rollen, gespeicherte Felder, Löschung, Export). In AB 03 wählt ihr daraus drei priorisierte aus, die ihr als User Stories umsetzt — genug Material zum Auswählen ist also wichtig.

## Aufgabe 2: Technische Anforderungen ableiten

Leitet aus euren Erkenntnissen aus der DSGVO-Webapp und Aufgabe 1 **für jeden gefundenen Verstoß eine konkrete technische Anforderung** ab, die NHPlus erfüllen muss, um datenschutzkonform zu sein.

Formuliert die Anforderungen so, dass sie als Grundlage für User Stories in [AB 03](AB03-User-Stories-Akzeptanzkriterien-Tasks-Testfaelle.md) dienen können.

---

## Relevante Ressourcen

- [Interaktive DSGVO-Webapp](https://berndheidemann.github.io/DSGVO-LF5/) (Grundlagen für die Analyse)
- [AB 01 — Analyse der Architektur](AB01-Analyse-der-Architektur.md) (technischer Kontext)
- [AB 03 — User Stories & Planung](AB03-User-Stories-Akzeptanzkriterien-Tasks-Testfaelle.md) (eure Anforderungen fließen hier ein)

---

*Weiter mit [AB 03 — User Stories & Planung](AB03-User-Stories-Akzeptanzkriterien-Tasks-Testfaelle.md)*
