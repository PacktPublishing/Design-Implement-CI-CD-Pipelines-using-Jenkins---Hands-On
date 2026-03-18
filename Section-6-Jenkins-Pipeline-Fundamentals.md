
# Design and Implement CI/CD Pipelines using Jenkins
## Section 6 – Jenkins Pipeline Fundamentals

---
## 6.5 Git Checkout Pipeline
```groovy
pipeline {
    agent any
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/madhuri75jha/simple-order-processing-app.git'
                echo "Git repository successfully checked out"
            }
        }
    }
}
```

---

## 6.8 Java CI Pipeline
```groovy
pipeline {
    agent any
    environment {
        APP_NAME = "simple-order-app"
        BUILD_VERSION = "1.0.${BUILD_NUMBER}"
        MAVEN_HOME = "/opt/maven"
        PATH = "/opt/maven/bin:${env.PATH}"
    }
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/madhuri75jha/simple-order-processing-app.git'
            }
        }
        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Build Java Application') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
    }
}
```

