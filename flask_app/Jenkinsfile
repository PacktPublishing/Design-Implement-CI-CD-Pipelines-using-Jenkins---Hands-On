pipeline{
  agent { label 'linux-agent-new' }
  environment { 
    VENV = 'venv'
  }
  stages {
    stage('Checkout Code') {
      steps {
        git branch: 'main', url: 'https://github.com/madhuri75jha/flask_app.git'
      }
    }
    stage('Setup Python Environment') {
      steps {
        sh '''
        sudo apt update
        sudo apt install -y python3-venv
        python3 -m venv $VENV
        . $VENV/bin/activate
        pip install --upgrade pip
        '''
        }
    }
    stage('Install Dependencies') {
      steps {
        sh '''
        . $VENV/bin/activate
        pip install -r requirements.txt
        '''
      }
    }
     stage('RUN TESTS') {
      steps {
        sh '''
        . $VENV/bin/activate
        echo 'NO Tests defined yet - SKIPPING'
        '''
      }
    }
      stage('RUN FLASK APPLICATION') {
      steps {
        sh '''
        . $VENV/bin/activate
        echo 'STARTING FLASK APP...'
        python app.py
        '''
      }
    }
  }
  post {
    success {
      echo 'FLASK CI/CD pipeline executed SUCCESSFULLY'
    }
    failure {
      echo 'PIPELINE FAILED!!! CHECK LOGS'
    }
  }
}
