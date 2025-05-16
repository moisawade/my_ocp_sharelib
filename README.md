# my_ocp_sharelib
shared-library/
│── vars/            # Fonctions accessibles directement dans les pipelines
│   ├── myFunction.groovy  # Définition d'une fonction réutilisable
│── src/             # Code Groovy partagé sous forme de classes
│   ├── com/company/MyClass.groovy  # Classe Groovy réutilisable
│── resources/       # Fichiers de ressources (ex: templates, JSON, YAML)
│── build.gradle     # (Optionnel) Pour gérer les dépendances
│── README.md        # Documentation


# Install Openshift Client (oc)
# 1-) Dowload 
wget https://downloads-openshift-console.apps.rm3.7wse.p1.openshiftapps.com/amd64/linux/oc.tar
# 2-)Unpack the archive
tar xvf oc.tar
# 3-) Place the bbinary in your PATH, try echo $PATH to get your path directory
sudo cp oc /usr/local/bin/
# 4-) Test the binary 
oc help