# AB 04 — Implementierung

> **Thema:** Umsetzung der geplanten Module
>
> **Zeitrahmen:** ~9-12 Unterrichtsstunden

**Nach Bearbeitung dieses Arbeitsblatts könnt ihr:**
- eine bestehende Anwendung eigenständig um neue Module erweitern
- die vorhandene Architektur (DAO-Pattern, MVP-Pattern) auf neue Anforderungen übertragen
- im Team an einer gemeinsamen Codebasis arbeiten

## Aufgabenstellung

Setzt die User Stories aus [AB 03](AB03-User-Stories-Akzeptanzkriterien-Tasks-Testfaelle.md) um.

### Pflichtumfang

1. **Pfleger-Modul vollständig implementieren** — alle User Stories zur Pfleger-Verwaltung müssen umgesetzt sein und die Akzeptanzkriterien erfüllen.
2. **Login-System implementieren** — die Anwendung darf nur nach erfolgreicher Anmeldung nutzbar sein.

Die beiden frei gewählten DSGVO-Stories aus AB 03 (ohne Login) bleiben als dokumentierte Planung stehen; ihre freiwillige Umsetzung wird im Bewertungsraster positiv berücksichtigt (siehe Zeile „Zusätzliche DSGVO-Umsetzung").

### Hinweis zum Login-System

Für das Login-System benötigt ihr:

> Die folgenden Vorgaben sind ein **Sicherheits-Mindeststandard** — was darüber hinausgeht (zusätzliche Spalten wie `created_at`, `role`, `last_login`; abweichende Bezeichner; mehrere Eingabefelder), entscheidet ihr selbst und dokumentiert es in eurem Datenmodell.

- Eine neue Tabelle `user` in der Datenbank mit mindestens den Spalten `username`, `password_hash` und `salt`.
- **Passwörter dürfen nicht im Klartext gespeichert werden!** Nutzt dafür die Hilfsklasse `utils/PasswordUtil.java`, die euch bereits vorbereitet wurde. Sie implementiert SHA-256 mit Salt.
- Eine neue FXML-View `LoginView.fxml` mit zwei Eingabefeldern (Benutzername, Passwort) und einem Login-Button.
- Einen `LoginController`, der die Eingabe mit `PasswordUtil.verify(...)` gegen die Datenbank prüft.
- Das Login-Fenster wird **vor** dem Hauptfenster geladen. Erst nach erfolgreicher Anmeldung wird das Hauptfenster geöffnet.

> **Leseauftrag:** Öffnet `PasswordUtil.java` und studiert den Javadoc-Kommentar sowie die drei öffentlichen Methoden. Beantwortet die drei Fragen in [`abgabe/AB04.md`](../abgabe/AB04.md) (Abschnitt „Leseauftrag PasswordUtil"):
>
> 1. Warum wird beim Anlegen eines neuen Nutzers ein zufälliger Salt erzeugt, statt einen festen Wert zu verwenden?
> 2. Was würde passieren, wenn wir den Salt weglassen und nur `hash(password, "")` aufrufen? Warum wäre das gefährlich?
> 3. Welche Alternativen zu SHA-256 nennt der Javadoc-Hinweis für Produktivsysteme, und warum sind sie für die Speicherung von Passwörtern besser geeignet als eine reine Hash-Funktion?

> **Hinweis zum Login-Flow:** Die einfachste saubere Variante ist ein **Scene-Wechsel auf derselben Stage**: `Main.start(...)` öffnet die Stage zunächst mit der `LoginView`, und der `LoginController` wechselt nach erfolgreicher Anmeldung per `stage.setScene(mainScene)` auf die `MainWindowView`. Vermeidet zwei parallele Stages — das führt schnell zu doppelten Fenstern oder `IllegalStateException: Toolkit not initialized`.

## Orientierung

Die bestehenden Klassen dienen euch als Vorlage — studiert sie, bevor ihr eigene Module entwickelt. Die Aufgaben aus [AB 01](AB01-Analyse-der-Architektur.md) haben euch darauf vorbereitet.

| Schicht | Bestehende Beispiele | Eure Aufgabe |
|---------|---------------------|--------------|
| **Model** | `Patient.java`, `Treatment.java` | Neue Modelklassen erstellen |
| **DAO** | `PatientDao.java`, `TreatmentDao.java` | Neue DAO-Klassen von `DaoImp` ableiten (siehe AB 01, Aufgabe 1) |
| **View** | `AllPatientView.fxml`, `AllTreatmentView.fxml` | Neue FXML-Views erstellen |
| **Controller** | `AllPatientController.java`, `AllTreatmentController.java` | Neue Controller erstellen (siehe AB 01, Aufgabe 4) |

> **Tipp zum Einstieg in FXML:** JavaFX und FXML haben wir nicht explizit behandelt. Öffnet `AllPatientView.fxml` im SceneBuilder und baut eure Pfleger-View analog auf — so seht ihr direkt, wie TableView, Textfelder und Buttons verdrahtet werden.

Denkt außerdem an:

- `DaoFactory` — registriert eure neuen DAOs dort.
- `module-info.java` — tragt neue Packages ein, wenn ihr welche erstellt.
- `SetUpDB` — erweitert die Klasse um Testdaten für eure neuen Tabellen. **Vergesst dabei nicht `wipeDb()`**: dort müssen eure neuen Tabellen ebenfalls per `DROP TABLE IF EXISTS …` gedroppt werden, sonst behält die Tabelle ihre alte Struktur. **Falls eure Tabelle Fremdschlüssel auf andere Tabellen hat**, muss zusätzlich auf die Reihenfolge geachtet werden: zuerst die Tabelle mit dem Fremdschlüssel droppen, dann die referenzierte — sonst scheitert das Setup an Constraint-Verletzungen. (Für `caregiver` und `user` ohne Fremdschlüssel zu anderen Tabellen ist die Reihenfolge egal.)
- `MainWindowController` — bindet eure neuen Views in die Navigation ein.

## Workstream-Vorschlag

Das Pfleger-Modul und das Login-System lassen sich in eurer 3er-Gruppe parallel entwickeln. Der folgende Schnitt ist ein **Vorschlag** für den Default-Fall — er entlastet euch von der Schnitt-Entscheidung, damit ihr die Energie ins Programmieren und in den Git-Workflow stecken könnt.

> **Abweichen ist erlaubt — und wird gleichwertig bewertet, wenn ihr begründet.** Wenn ihr einen anderen Schnitt für eure Gruppe sinnvoller findet (z. B. eine Person übernimmt das Pfleger-Modul end-to-end, zwei programmieren das Login als Pair), dokumentiert das in [`abgabe/AB04.md`](../abgabe/AB04.md), Abschnitt „Unsere Aufgabenteilung". Nicht belohnt wird **blinde Befolgung** des Vorschlags ebenso wie **unreflektiertes Abweichen** — die Begründung zählt.

| Workstream | Inhalt | Typische Dateien |
|---|---|---|
| **A: Data Layer** | Pfleger-Model + DAO + DB-Tabelle + JUnit-Tests | `Caregiver.java`, `CaregiverDao.java`, `SetUpDB.java`, `CaregiverDaoTest.java` |
| **B: UI Layer** | Pfleger-View (FXML) + Controller + Einbindung in die Navigation | `AllCaregiverView.fxml`, `AllCaregiverController.java`, Anpassungen in `MainWindowController` **und** `MainWindowView.fxml` (neuer Navigations-Button) |
| **C: Login-System** | User-Tabelle + Passwort-Hashing + LoginView + LoginController + Start-Reihenfolge | `User.java`, `UserDao.java`, `LoginView.fxml`, `LoginController.java`, Anpassungen in `Main.java` |

**Kopplungspunkte** — diese Dateien werden von mehreren Workstreams berührt. Editiert sie nicht parallel! Sprecht euch ab, wer wann einen Merge in diese Dateien setzt:

- `DaoFactory` (A und C ergänzen je eine Factory-Methode)
- `MainWindowController` **und** `MainWindowView.fxml` (B ergänzt einen Navigations-Button und den zugehörigen Handler)
- `Main.java` (C ändert die Start-Reihenfolge)
- `module-info.java` (bei neuen Packages)
- `SetUpDB` (A und C ergänzen je Testdaten)

> **Tipp:** B hängt von A ab (ohne DAO keine View mit Daten). Startet A zuerst, B kann parallel mit Stubs beginnen. C läuft komplett unabhängig — ideal für den dritten Workstream.

**Vorhandene Stubs und Helfer** — diese Dateien sind bereits im Repository und erleichtern euch den Einstieg:

- `src/main/resources/de/hitec/nhplus/AllCaregiverView.fxml` — FXML-Skelett für die Pfleger-Übersicht (Workstream B). Ergänzt `fx:controller` und die `onAction`/`onEditCommit`-Handler; orientiert euch am Header-Kommentar der Datei.
- `src/main/resources/de/hitec/nhplus/LoginView.fxml` — FXML-Skelett für das Login-Fenster (Workstream C). Ergänzt `fx:controller` und den `onAction`-Handler des Login-Buttons; orientiert euch am Header-Kommentar.
- `src/main/java/de/hitec/nhplus/utils/PasswordUtil.java` — Hilfsklasse für Passwort-Hashing mit Salt (Workstream C). Siehe Leseauftrag weiter unten.

> **Aufwands-Hinweis zu Workstream C:** Auch mit Skelett bleibt C anspruchsvoller als A oder B, weil zusätzlich der Eingriff in `Main.java` (Start-Reihenfolge: Login vor Hauptfenster) und die Schema-Änderung (`user`-Tabelle) anfallen. Plant für C entsprechend etwas mehr Zeit ein und vergebt den Workstream gezielt — oder weicht vom Vorschlag ab und programmiert C als Pair.

## Differenzierung

### Hilfen, falls ihr nicht weiterkommt

Bevor ihr fragt: prüft systematisch in dieser Reihenfolge.

**Workstream A (Data Layer)** — wenn die Tabelle nicht angelegt wird oder Daten nicht persistiert werden:
1. Ist eure neue Tabelle in `SetUpDB.setUpTables()` per `CREATE TABLE IF NOT EXISTS` deklariert?
2. Wird `setUpTables()` und eure Setup-Methode in `main` (oder beim ersten Start) aufgerufen?
3. Ist eure DAO-Klasse in `DaoFactory` über eine `create…Dao()`-Methode registriert?
4. Erbt eure DAO-Klasse von `DaoImp<EuerModel>` und implementiert alle abstrakten Methoden? (Vorlage: `PatientDao.java`)

**Workstream B (UI Layer)** — wenn die Tabelle leer bleibt oder Buttons nichts tun:
1. Ist `fx:controller="de.hitec.nhplus.controller.AllCaregiverController"` im Wurzel-Element der FXML gesetzt?
2. Sind alle `fx:id`s aus der FXML als Felder im Controller mit `@FXML` annotiert und genau gleich benannt?
3. Sind die `onAction`-Handler an den Buttons und die `onEditCommit`-Handler an den Spalten gesetzt?
4. Ruft euer Controller in `initialize()` eine `readAll()`-Methode auf der DAO auf und befüllt die `ObservableList`?

**Workstream C (Login)** — wenn das Login-Fenster nicht erscheint oder der Wechsel nicht funktioniert:
1. Lädt `Main.start()` zuerst die `LoginView` und übergibt die `Stage` an den `LoginController`?
2. Bekommt der `LoginController` die `Stage` als Feld (z.B. via `setStage()` aus `Main`), damit er später `stage.setScene(...)` aufrufen kann?
3. Ist die `MainWindowView` per `FXMLLoader` als zweite `Scene` geladen und wird im Erfolgs-Zweig per `stage.setScene(mainScene)` gesetzt?
4. Schreibt der „User anlegen"-Code wirklich Salt **und** Hash in die DB? Logging-Hilfe: in der DB direkt mit `SELECT username, salt, password_hash FROM user` prüfen.

### Vertiefungen (optional)

Wer im Pflichtumfang früh fertig wird, kann eine dieser Erweiterungen angehen. Sie zählen als „Zusätzliche DSGVO-Umsetzung" oder als Bonus zur Implementierungsnote (siehe Bewertungsraster) — keine Pflicht, aber willkommen:

| Vertiefung | Anspruch | Was ist zu tun |
|---|---|---|
| **BCrypt statt SHA-256** | Mittel | `PasswordUtil` auf `org.springframework.security.crypto.bcrypt.BCrypt` oder `org.mindrot.jbcrypt` umstellen. Begründet im Abgabe-Text, warum BCrypt für Passwörter besser geeignet ist (siehe Javadoc-Hinweis). Migrations-Pfad für Bestandsnutzer skizzieren. |
| **Logout-Button** | Niedrig | Im Hauptfenster einen Logout-Button ergänzen, der zurück zur `LoginView` wechselt und die Felder leert. Berücksichtigt: keine User-Daten dürfen im Speicher hängen bleiben. |
| **Rollen-System** | Mittel | `user`-Tabelle um Spalte `role` ergänzen (`'pfleger'` / `'admin'`). Bestimmte Buttons im Hauptfenster nach Rolle ein-/ausblenden — z.B. nur Admins dürfen Pfleger anlegen. |
| **Last-Login-Tracking** | Niedrig | Spalte `last_login_at` in `user`-Tabelle, beim erfolgreichen Login aktualisieren, im UI als „Letzter Login: …" anzeigen. Stützt Audit-Anforderungen aus AB 02. |

## Git-Workflow

Ihr arbeitet zum ersten Mal ernsthaft im Team mit Git. Damit Merges nicht ins Chaos kippen, gilt die folgende **Merge-Choreographie**:

1. **Feature-Branch pro Workstream:** `git checkout -b feature/pfleger-dao` (A), `feature/pfleger-view` (B), `feature/login` (C).
2. **Vor jedem Push:** `git pull --rebase origin main` — holt die letzten Änderungen und setzt eure Commits obendrauf.
3. **Pull Request statt direktem Merge:** Eröffnet einen PR auf GitHub. Vor dem Merge prüft jemand aus der Gruppe, ob die Anwendung noch startet.
4. **Kopplungspunkt-Regel:** Wer eine der oben genannten Kopplungs-Dateien anfasst, sagt das in der Gruppe an und merged zuerst. Erst danach ziehen die anderen den neuen Stand.
5. **Kleine Commits, frühe Merges:** Nicht tagelang an einem Branch arbeiten. Je länger ein Branch lebt, desto aufwändiger der Merge.

> **Bei Merge-Konflikt:** Nicht einfach `--theirs` oder `--ours` nehmen! Öffnet beide Versionen im IntelliJ-Merge-Tool und entscheidet bewusst, welche Änderung bleibt. Oft sind beide nötig.

## Meilensteine

Orientiert euch an folgenden Meilensteinen, um den Fortschritt im Blick zu behalten:

| Meilenstein | Zeitrahmen | Deliverable |
|---|---|---|
| **M1: Datenschicht** | UStd 1-3 | Model-Klasse + DAO + DB-Tabelle + Testdaten in SetUpDB |
| **M2: Oberfläche** | UStd 4-6 | FXML-View + Controller + Navigation im Hauptfenster |
| **M3: Login** | UStd 7-10 | Login-System funktionsfähig |
| **M4: Integration** | UStd 11-12 | Alle Module zusammengeführt, erste manuelle Tests |

## Anforderungen

- Jede implementierte User Story muss die **Akzeptanzkriterien aus AB 03** erfüllen.
- Jedes Gruppenmitglied committet seinen eigenen Code (erkennbar in der Git-Historie).
- Arbeitet nach der Merge-Choreographie aus dem Abschnitt [Git-Workflow](#git-workflow) — Feature-Branches und Pull Requests sind Pflicht.

## Javadoc

Dokumentiert jede Klasse, die ihr neu erstellt, mit Javadoc. Orientiert euch an `PatientDao.java` — dort seht ihr, wie gute Javadoc aussieht.

**Was muss dokumentiert werden?**

| Element | Javadoc nötig? | Was beschreiben? |
|---|---|---|
| Klasse | Ja | Zweck und Verantwortung der Klasse |
| Konstruktor (public) | Ja | Was initialisiert wird, `@param` für jeden Parameter |
| Methode (public) | Ja | Was sie tut, `@param`, `@return`, `@throws` |
| Getter/Setter | Nein | Nur wenn das Feld erklärungsbedürftig ist |

**Beispiel:**

```java
/**
 * Provides database access for {@link Caregiver} objects.
 * Implements CRUD operations using the DAO pattern.
 */
public class CaregiverDao extends DaoImp<Caregiver> {

    /**
     * Generates a <code>PreparedStatement</code> to persist a new caregiver.
     *
     * @param caregiver Object of class <code>Caregiver</code> to persist.
     * @return <code>PreparedStatement</code> to insert the given caregiver.
     */
    @Override
    protected PreparedStatement getCreateStatement(Caregiver caregiver) {
        // ...
    }
}
```

> **Tipp:** Wenn ihr beim Dokumentieren merkt, dass eine Klasse oder Methode mehrere Aufgaben hat — ändert es! Javadoc hilft nicht nur den Lesern, sondern auch euch, Designprobleme zu erkennen.

## Tipp

Fangt mit dem **Model** und dem **DAO** an. Wenn die Datenhaltung funktioniert, baut die **View** und den **Controller**.

---

## Relevante Ressourcen

- [AB 03 — User Stories & Planung](AB03-User-Stories-Akzeptanzkriterien-Tasks-Testfaelle.md) — eure User Stories und Tasks
- [AB 01 — Analyse der Architektur](AB01-Analyse-der-Architektur.md) — Architekturverständnis
- [MVP in JavaFX](IB-MVP-in-JavaFX.md) — Zusammenspiel von View, Model und Presenter
- [Technische Referenz](IB-Technische-Referenz.md) — Hinweise zu Datenbank, SceneBuilder, module-info

---

*Weiter mit [AB 05 — Testen](AB05-Testen.md)*
