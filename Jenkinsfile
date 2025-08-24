pipeline {
    agent any
    environment {
        STAGING_USER = 'staging'
        STAGING_HOST = 'servidor-stagin'
        STAGING_PORT = '2222'
        ARTIFACT_NAME = 'demo-0.0.1-SNAPSHOT.jar'
        SSH_KEY = '/var/jenkins_home/.ssh/id_rsa'
        STAGING_PATH = '/home/staging/'
    }
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/AlejandroCalzadilla/servidor_stagin_spring.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
         stage('Code Quality') {
            steps {
                sh 'mvn checkstyle:check'
            }
        } 
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
            }
        }
        stage('Deploy to Staging') {
            steps {
                sh 'scp -o StrictHostKeyChecking=no -i $SSH_KEY -P $STAGING_PORT target/${ARTIFACT_NAME} $STAGING_USER@$STAGING_HOST:$STAGING_PATH'
                sh 'ssh -o StrictHostKeyChecking=no -i $SSH_KEY -p $STAGING_PORT $STAGING_USER@$STAGING_HOST "nohup java -jar $STAGING_PATH${ARTIFACT_NAME} > $STAGING_PATH/app.log 2>&1 &"'
            }
        }
        stage('Validate Deployment') {
            steps {
                sh 'sleep 10'
                sh 'curl --fail http://$STAGING_HOST:8080/health'
            }
        }
    }
}
