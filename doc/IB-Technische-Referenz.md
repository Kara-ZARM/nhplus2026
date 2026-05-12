# Technische Referenz

Dieses Dokument enthält Anleitungen zu den verwendeten Werkzeugen und Technologien. Schlagt hier nach, wenn ihr bei der Einrichtung oder Nutzung Hilfe braucht.

## Datenbank in IntelliJ einsehen

1. Klicke rechts oben auf das Datenbanksymbol. Die Datenbank `nursingHome.db` sollte angezeigt werden. Klappe die Pfeile auf, um die Tabellen zu sehen.

Falls die Tabellen nicht angezeigt werden:
1. Rechtsklick auf die Datenbank → *Tools → Manage Shown Schemas...*
2. Wähle *All Schemas* aus.

Falls die Datenbank nicht sichtbar ist:
1. Klicke im Datenbankbereich auf das `+`-Symbol → *Data Source → SQLite*.
2. Gib unter *File* den Pfad zur Datenbank an: `db/nursingHome.db` (im Projektverzeichnis).
3. Klicke auf *Test Connection*, dann *Apply* und *OK*.

## SQL-Statements absetzen

1. Klicke rechts oben auf das Datenbank-Symbol.
2. Klicke auf *Jump to Query Console...* in der Symbolleiste.
3. Trage den SQL-Befehl ein (z.B. `SELECT * FROM patient`).
4. Klicke auf den Ausführen-Pfeil.

## SQLite

Für die Erweiterung von NHPlus müsst ihr Tabellen erstellen (`CREATE TABLE`), ändern (`ALTER TABLE`), Daten einfügen (`INSERT INTO`), aktualisieren (`UPDATE`), löschen (`DELETE`) und abfragen (`SELECT`).

SQLite-Dokumentation: https://www.sqlite.org/lang.html

## module-info.java

Die Datei `module-info.java` im Hauptpackage steuert, welche Abhängigkeiten das Projekt nutzt und welche Pakete nach außen sichtbar sind.

**Wichtig für eure Erweiterungen:**

- `requires` — Abhängigkeiten, die das Modul benötigt (z.B. `javafx.controls`, `java.sql`).
- `opens <Paket> to javafx.fxml` — Erlaubt JavaFX, per Reflexion auf das Paket zuzugreifen (nötig für FXML-Views und deren Controller).
- `opens <Paket> to javafx.base` — Erlaubt JavaFX-Property-Binding (nötig für Model-Klassen mit `SimpleStringProperty` etc.).
- `exports <Paket>` — Macht das Paket für andere Module verfügbar.

**Wenn ihr ein neues Paket erstellt**, müsst ihr es hier eintragen:

```java
// Beispiel: Neues Paket für Pfleger
opens de.hitec.nhplus.pfleger to javafx.fxml;
exports de.hitec.nhplus.pfleger;
```

Ohne diesen Eintrag kann JavaFX nicht auf eure Controller und Views zugreifen.

## SceneBuilder

Der SceneBuilder ist ein grafischer Editor für FXML-Dateien. Download: https://gluonhq.com/products/scene-builder/#download

**In IntelliJ einbinden (optional):**

1. *File → Settings → Languages & Frameworks → JavaFX*
2. Unter *Path to SceneBuilder* den Installationspfad eintragen.
3. Danach können FXML-Dateien per Rechtsklick → *Open In SceneBuilder* geöffnet werden.

FXML-Dateien können alternativ auch direkt im SceneBuilder geöffnet werden (ohne IntelliJ-Integration).

**Beim Erstellen einer neuen View:**

1. Erstellt die FXML-Datei im Ordner `src/main/resources/de/hitec/nhplus/`.
2. Tragt links unten unter *Controller* die zugehörige Controller-Klasse ein (z.B. `controller.AllCaregiverController`).
3. Vergebt für Steuerelemente, auf die der Controller zugreifen soll, eine `fx:id` im Bereich *Code* (rechts).
4. Tragt bei Buttons unter *On Action* den Namen der Handler-Methode ein (z.B. `handleAdd`).

## Anwendung starten

Über den Run-Button in IntelliJ oder auf der Kommandozeile:

```bash
mvn clean javafx:run
```

> **Wichtig:** Die App nutzt einen **relativen Pfad** zur Datenbank (`db/nursingHome.db`). Startet die Anwendung deshalb immer aus dem **Projekt-Root** — sonst findet sie die DB nicht und legt eine leere neben dem aktuellen Arbeitsverzeichnis an. Beim Start aus IntelliJ ist das automatisch der Fall.

## Tests ausführen

```bash
mvn test
```

Ein Beispiel-Test findet sich in `src/test/java/de/hitec/nhplus/datastorage/PatientDaoTest.java`.
