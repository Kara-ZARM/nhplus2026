# JDBC-Treiber für SQLite einbinden

## Normalfall: nichts zu tun

Der SQLite-JDBC-Treiber ist bereits als Maven-Abhängigkeit in der [`pom.xml`](../pom.xml) eingetragen (`org.xerial:sqlite-jdbc`). IntelliJ lädt ihn beim ersten Öffnen des Projekts automatisch — ihr müsst hier nichts manuell tun.

## Falls der Treiber nicht gefunden wird

Wenn beim Starten der Anwendung ein Fehler wie `ClassNotFoundException: org.sqlite.JDBC` auftaucht, liegt meist eine hängende Maven-Synchronisation vor. So holt ihr sie nach:

1. IntelliJ-Menü **File → Invalidate Caches… → Invalidate and Restart**.
2. Nach dem Neustart in der Maven-Toolbar (rechter Rand) auf das Refresh-Symbol klicken (*Reload All Maven Projects*).
3. Projekt neu bauen: **Build → Rebuild Project**.

## Notfall-Fallback: Treiber manuell als Library einbinden

Nur wenn der Maven-Weg partout nicht funktioniert (z. B. weil euer Rechner keinen Maven-Repository-Zugriff hat):

1. `F4` oder Rechtsklick auf den Projektnamen im Projektbaum → **Open Module Settings**.
2. Links **Project Settings → Libraries** auswählen.
3. Oben auf **+** klicken und **From Maven…** wählen.
4. Im Suchfeld `org.xerial:sqlite-jdbc` eingeben und die Version wählen, die in der `pom.xml` steht (aktuell `3.44.1.0`).
5. **OK** — IntelliJ lädt den Treiber herunter und bietet an, ihn einem Modul zuzuordnen.
6. Modul `NHPlus` auswählen → **OK** → **Apply** → **OK**.

Danach sollte der Treiber verfügbar sein und die Anwendung wieder starten.
