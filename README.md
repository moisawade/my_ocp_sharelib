# my_ocp_sharelib
shared-library/
│── vars/            # Fonctions accessibles directement dans les pipelines
│   ├── myFunction.groovy  # Définition d'une fonction réutilisable
│── src/             # Code Groovy partagé sous forme de classes
│   ├── com/company/MyClass.groovy  # Classe Groovy réutilisable
│── resources/       # Fichiers de ressources (ex: templates, JSON, YAML)
│── build.gradle     # (Optionnel) Pour gérer les dépendances
│── README.md        # Documentation
