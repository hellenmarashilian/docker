def git_repo_url = 'https://github.com/hellenmarashilian/docker.git' // Public GitHub repo URL
def git_branch = 'main' // Branch to clone
def docker_registry = '192.168.1.157:8083/repository/hellz-docker' // Nexus repository URL
def image_name = 'http_get' // Docker image name
def image_tag = 'latest' // Docker image tag
def nexus_username = 'admin' // Nexus username
def nexus_password = '2188' // Nexus password
node {
    echo 'Loading getfiles.groovy...'
    def loadrepo = load 'getfiles.groovy'
    echo "loadrepo is: ${loadrepo}"
    //loadrepo.call()
    def map = [git_repo_url: git_repo_url, git_branch: git_branch, docker_registry: docker_registry, image_name: image_name, image_tag: image_tag, nexus_username: nexus_username, nexus_password: nexus_password]
    echo 'Loading getfiles.groovy...'
    def push = load 'gf.groovy'
    echo "push is: ${push}"
    push.call(map)
}

