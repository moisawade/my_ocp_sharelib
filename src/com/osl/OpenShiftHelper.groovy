package com.aviro

class OpenShiftHelper {
    static void login(script, String token, String server) {
        script.sh "oc login --token=${token} --server=${server} --insecure-skip-tls-verify"
    }

    static void deploy(script, String project, String image, String appName) {
        script.sh "oc project ${project}"
        script.sh "oc set image deployment/${appName} ${appName}=${image} --namespace=${project}"
    }

    static void deployDeployment(script, String project, String image, String appName, String deploymentName) {
        script.sh "oc project ${project}"
        script.sh "oc set image deployment/${deploymentName} ${appName}=${image} --namespace=${project}"
    }

    // static boolean checkDeployment(script, String project, String appName) {
    //     return script.sh(script: "oc rollout status deployment/${appName} --namespace=${project}", returnStatus: true) == 0
    // }

    static boolean checkDeployment(script, String project, String appName, String deploymentName) {
        return script.sh(script: "oc rollout status deployment/${deploymentName} --namespace=${project}", returnStatus: true) == 0
    }
}

