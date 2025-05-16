def call(String project, String image, String appName, String ocToken, String ocServer, String ocDeployment) {
    script {
        echo "Déploiement de l'application '${appName}' sur OpenShift..."

        // Connexion à OpenShift
        sh "oc login --token=${ocToken} --server=${ocServer}"

        // Sélection du projet OpenShift
        sh "oc project ${project}"

        //Création du deploiement
        sh "oc new-app -name=appName ${appName}=${image} --namespace=${project}"

        // Mise à jour de l'image dans le déploiement
        //sh "oc set image deployment/${ocDeployment} ${appName}=${image} --namespace=${project}"

        // Vérification du statut du déploiement
        def status = sh(script: "oc rollout status deployment/${ocDeployment} --namespace=${project}", returnStatus: true)

        if (status != 0) {
            error " Échec du déploiement sur OpenShift !"
        } else {
            echo "Déploiement réussi !"
        }
    }
}
