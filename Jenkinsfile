pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'mouraaad/ms-usuarios'
        DOCKER_TAG = 'latest'
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials' // Configurar en Jenkins Credentials
        AZURE_KUBECONFIG_ID = 'azure-aks-kubeconfig' // Configurar en Jenkins Credentials
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Deploy to Azure Kubernetes (AKS)') {
            steps {
                withKubeConfig([credentialsId: AZURE_KUBECONFIG_ID]) {
                    sh 'kubectl apply -f k8s/deployment.yaml'
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
