pipeline {
    agent {
        label 'demo-agent'
    }

    tools {
        maven 'maven'
    }

    environment {
        IMAGE_NAME = 'ductm2205/demo-spring'
        IMAGE_TAG = '${GIT_COMMIT.take(7)}'
        DEPLOY_HOST = 'ec2-user@10.0.1.40'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvnw clean package -DskipTests'
            }
        }
    }
}