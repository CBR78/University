pipeline {
    agent any

    stages {
        stage('BuildTestAndPackage') {
            steps {
                sh 'chmod +x ./mvnw'
                sh './mvnw clean package'
                junit 'target/surefire-reports/*.xml'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}