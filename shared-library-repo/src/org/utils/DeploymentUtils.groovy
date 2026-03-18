package org.utils
class DeploymentUtils implements Serializable {
  def steps
  DeploymentUtils(steps) {
    this.steps = steps
  }
  def deployMessage(String appName) {
    steps.echo "Deploying application: ${appName}"
  }
  def verifyDeployment() {
    steps.echo "Deployment verified SUCCESSFULLY."
  }
}

