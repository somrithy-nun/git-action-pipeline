pipeline {
    agent any

    tools {
        // Install the Nodejs for npm
        nodejs "nodejs-18.12.1"
    }
    stages {
        stage('Get Source Code From GitHub') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/Nun-SomRithy/pipline_with_react.git'
                sh 'ls -ltr'
            }
        }
        stage('Build'){
            steps{
                echo 'Build Image prepare to deploy'
                sh 'node --version'
                sh 'npm --version'
                sh 'npm install'
                sh 'npm run build'
                sh "ls -ltr"
                sh 'docker build -t react .'
            }
        }
        stage('Deploy') {
            steps{
                sh 'docker run -d -p 8083:80 --name reactjs  react '
            }
        }
    }
}
