def git_repo_url = 'https://github.com/hellenmarashilian/docker.git' // Public GitHub repo URL
def git_branch = 'main' // Branch to clone
def docker_registry = '192.168.1.157:8083/repository/hellz-docker' // Nexus repository URL
def image_name = 'http_get' // Docker image name
def image_tag = 'latest' // Docker image tag
def nexus_username = 'admin' // Nexus username
def nexus_password = '2188' // Nexus password

node {
    stage('Checkout Code') {
        echo 'Cloning repository...'
        git branch: 'main', url: 'https://github.com/hellenmarashilian/docker.git'
    }

    stage('Run Build Script') {
        echo 'Running build.groovy script...'
        // Load the build.groovy script from the correct path (assumes it's in the root directory)
        def buildScript = load 'build.groovy'
        buildScript([GIT_REO_URL: git_repo_url, GIT_BRANCH: git_branch, DOCKER_REGISTERY: docker_registry, IMAGE_NAME: image_name, IMAGE_TAG: image_tag, NEXUS_USERNAME: nexus_username, NEXUS_PASSWORD: nexus_password])
    }
}
