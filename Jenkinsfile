pipeline {
    agent {
        label 'demo-agent'
    }

    tools {
        maven 'maven'
    }

    environment {
        IMAGE_NAME = 'ductm2205/demo-spring'
        IMAGE_TAG = "${GIT_COMMIT.take(7)}"
        DEPLOY_HOST = 'ec2-user@10.0.1.253'
    }

    stages {
        // stage('Checkout') {
        //     steps {
        //         checkout scm
        //     }
        // }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker image') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
            }
        }

        stage('Publish to Registry') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-pat',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                      echo "${DOCKER_PASS}" | docker login -u "${DOCKER_USER}" --password-stdin
                      docker push ${IMAGE_NAME}:${IMAGE_TAG}
                    '''
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent(['agent-server']) {
                    sh """
                      ssh -o StrictHostKeyChecking=no ${DEPLOY_HOST} '
                      cd /opt/app &&
                      git pull main &&
                        export IMAGE_TAG=${IMAGE_TAG} &&
                        docker compose pull &&
                        docker compose up -d
                      '
                    """
                }
            }
        }
     
    }
    
    post {
        success {
            echo "Deployment successful: ${IMAGE_NAME}:${IMAGE_TAG}"
        }
        failure {
            echo "Pipeline failed"
        }
    }
}