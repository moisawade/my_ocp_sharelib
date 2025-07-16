Jenkins Pipeline for OpenShift Deployment & Trivy Scan
Overview
This Jenkins pipeline automates the following tasks:

Security scanning of Docker images using Trivy

Optional publishing and emailing of Trivy scan reports

Login and deployment to OpenShift

Exposing the application route if needed

Verifying the deployment rollout

It uses a shared library (my_ocp_sharelib) to encapsulate OpenShift helper logic.

Requirements
Jenkins with:

Access to a build node with oc, podman, and docker.io/aquasec/trivy

Installed Publish HTML Reports plugin

Configured credentials:

openshift-musa-token-ID: OpenShift token (Secret Text)

Optional: Email Extension Plugin configured

Environment Variables
Variable	Description
IMAGE_NAME	Docker image name (e.g., docker.io/my/image)
IMAGE_TAG	Image tag (e.g., latest)
OC_TOKEN	OpenShift token (from Jenkins credentials)
OC_SERVER	OpenShift API server URL
PROJECT	OpenShift project (namespace)
APP_NAME	Name of the application/deployment
APP_DEPLOYMENT	Name used in rollout verification

Pipeline Stages
1. Préparer le scan Trivy
Creates the output directory for the Trivy report.

2. Scan Trivy (non-bloquant)
Runs a security scan on the Docker image with Trivy:

Continues even if CRITICAL/HIGH vulnerabilities are found

Report generated as HTML

3. Publish the Trivy report (optional)
Publishes the scan result as an HTML report in Jenkins.
Triggered only if confirmBool == true.

4. Send Scan Report (optional)
Sends the Trivy report by email.
Triggered only if confirmBool == true.

5. Login to OpenShift
Authenticates to the OpenShift cluster and selects the target project.

6. Deploy Application
If the deployment exists:

Updates the image and rolls out

If the deployment doesn't exist:

Creates the app using oc new-app

Exposes a service with oc expose svc/...

7. Expose Application
Ensures the OpenShift route is created (skips if it already exists).

8. Check Deployment Status
Verifies the rollout status of the deployment using your Shared Library.

Parameters
Parameter	Type	Description
confirmBool	boolean	Whether to publish and email the Trivy report

Trivy Report Output
Report directory: trivy-report

HTML report file: trivy-report.html

Accessible via Jenkins HTML Publisher (if enabled)

Email Example
When enabled, the recipient receives an email with:

Subject: Image scan report with Trivy

Attachment: trivy-report.html

Extensibility Ideas
Add support for multi-environment deployment (dev, staging, prod)

Use oc apply -f with YAMLs instead of oc new-app

Add a diff step using oc diff for deployment previews

Use versioned deployments + image digests for auditability

Add Slack or MS Teams notifications