def call(MAP input_values){

  def default_values= [GIT_REPO_URL:GIT_REPO_URL, GIT_BRANCH:GIT_BRANCH, DOCKER_REGISTRY:DOCKER_REGISTRY, IMAGE_NAME:IMAGE_NAME, IMAGE_TAG:IMAGE_TAG, NEXUS_USERNAME:NEXUS_USERNAME, NEXUS_PASSWORD:NEXUS_PASSWORD]
  def map_to_apply=default_values+input_values

pipeline {
  stages{
    stage('Checkout Code') {
        echo 'Cloning repository...'
        git branch: GIT_BRANCH, url: GIT_REPO_URL
    }

    stage('Build Docker Image') {
        echo 'Building Docker image...'
        bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ./${IMAGE_NAME}"
    }

    stage('Push to Nexus Repository') {
        echo 'Pushing Docker image to Nexus...'
        bat """
            docker login ${DOCKER_REGISTRY} -u ${NEXUS_USERNAME} -p ${NEXUS_PASSWORD}
            docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}
            docker push ${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}
        """
    }
  }
}  
}
