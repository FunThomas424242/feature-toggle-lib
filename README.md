# feature-toggle-lib

[![Build Status](https://travis-ci.org/FunThomas424242/feature-toggle-lib.svg?branch=master)](https://travis-ci.org/FunThomas424242/feature-toggle-lib)

Java Bibliothek um Feature per Token zu aktivieren und zu deaktivieren (wie togglz). 

Ziel: Es sollen massiv parallel laufende Tests z.B. in Kombination von spring-boot und Restassured unterstützt werden.

Sonstige Features:
* Annotationen basieren auf @Feature(ENABLE|DISABLE) @IntegrationToggle
* toggle aktivierbar über Annotation (es soll nicht dynamisch in Produktion einschaltbar sein)
* eingecheckte Toggle Zustände können per Systemproperties überschrieben werden (via Kommandozeile)
* Eine @IntegrationToggle Annotation soll eine dynamische Aktivierung in Tests unterstützen

Status: on hold

Begründung:
Die Realisierung scheitert aktuell daran, dass zum laufenden Testfall nicht die ThreadID des entsprechenden Servers rechtzeitig ermittelt werden kann. Die ThreadId des Servers wird der Testrule leider erst mitgeteilt, wenn der Test bereits durchgeführt wurde. Das liegt daran, dass ein Listener beim Server registriert wird dessen erstes Event den Shutdown registriert. Da vorher der Server null ist kann der Listener nicht früher registriert werden. 

Lösung: Evtl. mit AOP möglich oder aber mittels spring Mechanismen welche dem Autor aktuell unbekannt sind.

Daher warten auf Idee :(

Letzter Stand im branch parallel