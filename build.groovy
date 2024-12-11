def call(Map input_values) {
    def default_values = [
        git_repo_url: git_repo_url, 
        git_branch: git_branch, 
        docker_registry: docker_registry, 
        image_name: image_name, 
        image_tag: image_tag, 
        nexus_username: nexus_username, 
        nexus_password: nexus_password
    ]
    def map_to_apply = default_values + input_values

    pipeline {
        agent any
        stages {
            stage('Checkout Code') {
                steps {
                    script {
                        echo 'Cloning repository...'
                        git branch: map_to_apply.git_branch, url: map_to_apply.git_repo_url
                    }
                }
            }

            stage('Build Docker Image') {
                steps {
                    script {
                        echo 'Building Docker image...'
                        bat "docker build -t ${map_to_apply.image_name}:${map_to_apply.image_tag} ./${map_to_apply.image_name}"
                    }
                }
            }

            stage('Push to Nexus Repository') {
                steps {
                    script {
                        echo 'Pushing Docker image to Nexus...'
                        bat """
                            docker login ${map_to_apply.docker_registry} -u ${map_to_apply.nexus_username} -p ${map_to_apply.nexus_password}
                            docker tag ${map_to_apply.image_name}:${map_to_apply.image_tag} ${map_to_apply.docker_registry}/${map_to_apply.image_name}:${map_to_apply.image_tag}
                            docker push ${map_to_apply.docker_registry}/${map_to_apply.image_name}:${map_to_apply.image_tag}
                        """
                    }
                }
            }
        }
    }
}
