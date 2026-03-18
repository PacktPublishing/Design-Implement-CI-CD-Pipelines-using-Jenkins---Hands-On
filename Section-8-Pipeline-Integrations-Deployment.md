
# Section 8 – Pipeline Integrations & Deployment Automation

---

## 8.2 Application Deployment Pipeline

```groovy
pipeline {

    agent any

    tools {
        maven 'maven_3'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/madhuri75jha/simple-order-processing-app.git'
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy Application') {
            steps {
                echo 'Starting application deployment...'

                sh '''
                echo "Creating deployment directory"
                mkdir -p /tmp/orderapp-deploy

                echo "Copying JAR to deployment directory"
                cp target/*.jar /tmp/orderapp-deploy/

                echo "Listing deployed files"
                ls -l /tmp/orderapp-deploy
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully. Application deployed.'
        }
        failure {
            echo 'Pipeline failed. Please check logs.'
        }
    }
}
```

---

## 8.3 Ansible Deployment

### deploy.yaml (Ansible Playbook)

```yaml
---
- hosts: web
  become: yes

  tasks:
    - name: Install Apache
      apt:
        name: apache2
        state: present
        update_cache: yes

    - name: Start Apache service
      service:
        name: apache2
        state: started
        enabled: yes
```

---

### Jenkins Pipeline Script for Ansible Deployment

```groovy
pipeline {
    agent any

    stages {
        stage('Deploy Apache using Ansible') {
            steps {
                sh 'ansible-playbook -i /var/lib/jenkins/ansible-demo1/hosts /var/lib/jenkins/ansible-demo1/deployApache.yml'
            }
        }
    }
}
```
