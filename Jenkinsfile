def git_repo_url = 'https://github.com/hellenmarashilian/docker.git' // Public GitHub repo URL
def git_branch = 'main' // Branch to clone
def docker_registry = '192.168.1.157:8083/repository/hellz-docker' // Nexus repository URL
def image_name = 'http_get' // Docker image name
def image_tag = 'latest' // Docker image tag
def nexus_username = 'admin' // Nexus username
def nexus_password = '2188' // Nexus password

pipeline {
    agent any
    stages {
        stage('Checkout Code') {
            echo 'Cloning repository...'
            git branch: 'main', url: 'https://github.com/hellenmarashilian/docker.git'
        }

        stage('Run Build Script') {
            echo 'Loading build.groovy script...'
            // Load the build.groovy script from the correct path (assumes it's in the root directory)
            bat 'dir'
            def buildScript = load "build.groovy"
            if (buildScript == null){
                echo 'buildscript is null'
            }
            echo 'Calling build script...'
            def map = [git_repo_url: git_repo_url, git_branch: git_branch, docker_registry: docker_registry, image_name: image_name, image_tag: image_tag, nexus_username: nexus_username, nexus_password: nexus_password]
            buildScript.building(map)
        }
    }
}
