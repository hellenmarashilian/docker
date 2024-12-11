def call(){
  pipeline {
    agent any
    stages {
        stage('Checkout Code') {
            steps{
                script{
                echo 'Cloning repository...'
                git branch: 'main', url: 'https://github.com/hellenmarashilian/docker.git'
                }
            }
        }
    }
}
}
return this
