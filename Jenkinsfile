pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'chmod +x ./mvnw'
                sh './mvnw compile'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }
        stage('Package') {
              steps {
                sh './mvnw package -DskipTests'
              }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}