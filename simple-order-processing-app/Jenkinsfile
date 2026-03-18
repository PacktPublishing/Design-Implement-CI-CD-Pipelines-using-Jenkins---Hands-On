pipeline {
  agent any
  tools {
    maven 'maven_3'
  }
  stages {
    stage('Checkout') {
      steps {
        git(branch: 'main', url: 'https://github.com/madhuri75jha/simple-order-processing-app.git')
      }
    }

    stage('Build') {
      steps {
        sh '''
        echo "Building application pipeline..."
        mvn clean package
        '''
      }
    }

  stage('Deploy Application') {
      steps {
        echo 'Starting Application Deployment...'
        sh '''
        echo "Creating deployment directory..."
        mkdir -p /tmp/orderapp-deploy/

        echo "Copying JAR to deployment directory"
        cp target/*.jar /tmp/orderapp-deploy/

        echo "Listing deployed files"
        ls -l /tmp/orderapp-deploy/
        '''
      }
    }

  }
  
  post {
    success {
      echo 'Pipeline Executed Successfully. Application DEPLOYED.'
    }

    failure {
      echo 'Pipeline Failed. Please Check LOGS.'
    }

  }
}
