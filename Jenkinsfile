pipeline {
    agent any
	tools {
		maven 'Maven_Latest'
	}

	environment {
		PROJECT_ID = 'jenkins-362010'
        CLUSTER_NAME = 'k8s'
        LOCATION = 'us-central1-c'
        CREDENTIALS_ID = 'kubernetes'
	}

    stages {
	    stage('Scm Checkout') {
		    steps {
			    checkout scm
		    }
	    }

	    stage('Build') {
		    steps {
			    sh 'mvn clean package'
		    }
	    }

	    stage('Test') {
		    steps {
			    echo "Testing..."
			    sh 'mvn test'
		    }
	    }

	    stage('SonarQube Analysis') {
            def mvn = tool 'Maven_Latest';
            withSonarQubeEnv() {
              sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=springboot-jenkins-docker-gke"
            }
          }

	    stage('Build Docker Image') {
		    steps {
			    sh 'whoami'
			    script {
				    myimage = docker.build("stephenrajisac/jenkins-docker-kubernetes-gke:${env.BUILD_ID}")
			    }
		    }
	    }

	    stage("Push Docker Image") {
		    steps {
			    script {
				    echo "Push Docker Image"
				    withCredentials([string(credentialsId: 'dockerhub', variable: 'dockerhub')]) {
            				sh "docker login -u stephenrajisac -p ${dockerhub}"
				    }
				        myimage.push("${env.BUILD_ID}")

			    }
		    }
	    }

	    stage('Deploy to K8s') {
		    steps{
			    echo "Deployment started ..."
			    sh 'ls -ltr'
			    sh 'pwd'
			    sh "sed -i 's/tagversion/${env.BUILD_ID}/g' service.yaml"
				sh "sed -i 's/tagversion/${env.BUILD_ID}/g' deployment.yaml"
			    echo "Start deployment of service.yaml"
			    step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'service.yaml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
				echo "Start deployment of deployment.yaml"
				step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'deployment.yaml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
			    echo "Deployment Finished ..."
		    }
	    }
    }
}