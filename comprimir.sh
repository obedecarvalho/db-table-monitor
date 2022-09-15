#!/bin/bash
zip --symlinks -r dbtablemonitor$(date +'-%Y%m%d-%H%M').zip .git/ .settings/ outros/ src/ .gitignore pom.xml comprimir.sh subir.sh
