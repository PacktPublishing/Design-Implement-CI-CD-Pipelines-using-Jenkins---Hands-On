
# Section 7 – Advanced Jenkins Pipelines

---

## 7.2 Parameterized Pipeline

```groovy
pipeline {
    agent any

    parameters {
        choice(
            name: 'ENVIRONMENT',
            choices: ['dev', 'qa', 'prod'],
            description: 'Select the target environment'
        )
        string(
            name: 'APP_VERSION',
            defaultValue: '1.0.0',
            description: 'Application version'
        )
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "Checking out source code"
            }
        }

        stage('Build') {
            steps {
                echo "Building application version ${params.APP_VERSION}"
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying application to ${params.ENVIRONMENT} environment"
                echo "Deployment completed successfully"
            }
        }
    }

    post {
        success {
            echo "Pipeline executed successfully"
        }
        failure {
            echo "Pipeline execution failed"
        }
    }
}
```

---

## 7.4 Credentials Handling

### 1️⃣ Username & Password (Basic Usage)

```groovy
pipeline {
    agent any

    stages {
        stage('Username Password Demo') {

            environment {
                SERVICE_CREDS = credentials('linux-agent-ssh-cred-new')
            }

            steps {
                sh 'echo "Service user is $SERVICE_CREDS_USR"'
                sh 'echo "Service password is $SERVICE_CREDS_PSW"'
            }
        }
    }
}
```

---

### 2️⃣ SSH Login Using withCredentials (Best Practice)

```groovy
pipeline {
    agent any

    stages {
        stage('SSH Into Linux Server') {

            steps {
                withCredentials([sshUserPrivateKey(
                    credentialsId: 'linux-agent-ssh-cred-new',
                    keyFileVariable: 'SSH_KEY',
                    usernameVariable: 'SSH_USER'
                )]) {

                    sh '''
                    echo "Connecting to remote server..."
                    ssh -i $SSH_KEY -o StrictHostKeyChecking=no $SSH_USER@ip hostname
                    '''
                }
            }
        }
    }
}
```

---

### 3️⃣ GitHub Token Using Environment Variable

```groovy
pipeline {
    agent any

    stages {
        stage('Example Secret Text') {

            environment {
                GITHUB_TOKEN = credentials('github-creds')
            }

            steps {
                sh 'printenv | grep GITHUB_TOKEN'
                sh '''
                echo "Testing GitHub authentication..."
                curl -H "Authorization: token $GITHUB_TOKEN" https://api.github.com/users/madhuri75jha
                '''
            }
        }
    }
}
```

---

### 4️⃣ GitHub Token Using withCredentials (Best Practice)

```groovy
pipeline {
    agent any

    stages {
        stage('GitHub Secure Access') {

            steps {
                withCredentials([
                    string(credentialsId: 'github-creds', variable: 'API_TOKEN')
                ]) {

                    sh '''
                    echo "Calling GitHub API securely..."
                    curl -H "Authorization: token $API_TOKEN" https://api.github.com/user
                    '''
                }
            }
        }
    }
}
```

---

## 7.6 Conditional Execution Example

```groovy
pipeline {
    agent any

    parameters {
        string(name: 'DEPLOY_ENV', defaultValue: 'dev', description: 'Environment to deploy')
    }

    stages {
        stage('Build') {
            steps {
                echo "Building the application..."
            }
        }

        stage('Run Unit Tests') {
            steps {
                echo "Running unit tests..."
            }
        }

        stage('Deploy to Production') {
            when {
                branch 'main'
            }
            steps {
                echo "Deploying application to production..."
            }
        }

        stage('Manual Deployment') {
            when {
                expression { params.DEPLOY_ENV == 'prod' }
            }
            steps {
                echo "Running deployment based on parameter..."
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check logs.'
        }
    }
}
```

---

## 7.8 Parallel Stages

```groovy
pipeline {
    agent any

    stages {
        stage('Parallel Tasks') {
            parallel {
                stage('Build Stage') {
                    steps {
                        echo 'Building the application...'
                    }
                }
                stage('Unit Tests Stage') {
                    steps {
                        echo 'Running unit tests...'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check logs.'
        }
    }
}
```

---

## 7.10 Manual Approval Stage

```groovy
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building the application...'
            }
        }

        stage('Approval') {
            steps {
                input message: 'Do we want to proceed to deployment?',
                      ok: 'Yes, Deploy',
                      submitter: 'admin'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                echo "Simulating deployment"
                echo "Application deployed successfully"
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed or approval rejected.'
        }
    }
}
```

---

## 7.11 Shared Library Usage

```groovy
@Library('shared-lib-demo') _
import org.utils.DeploymentUtils

pipeline {
    agent any

    stages {
        stage('Deployment Utils Demo') {
            steps {
                script {
                    def utils = new DeploymentUtils(this)
                    utils.deployMessage('DemoApp')
                    utils.verifyDeployment()
                }
            }
        }
    }
}
```
