pipeline {
	environment {
	    registry = "amitagrahari2512/docker-test"
	    registryCredential = 'DockerRegistry'
	    dockerImage = ''
  	}
  	
    agent any

    tools {
        // Install the Maven version configured as "Maven" and add it to the path.
        maven "Maven"
    }

	options {
		timestamps()
		
		timeout(time: 1, unit:'HOURS')
		
		//Do not automatically checkout the SCM on every stage 
		skipDefaultCheckout()
		
		buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
	
		//To Avoid Concurrent build
		disableConcurrentBuilds()
	}
    stages {
        stage('checkout') {
            steps {
                echo "Checkout in master branch"
                	checkout scm
            }
        }
        stage('Build') {
            steps {
                echo "build in master branch"
                	bat "mvn install"
            }
        }
        stage('Unit Testing') {
            steps {
                echo "Unit test in master branch"
                	bat "mvn test"
            }
        }
        stage('Sonar Analysis') {
            steps {
                echo "Run Sonar Qube in master branch"
                	withSonarQubeEnv("SonarQube") {
                		bat "mvn sonar:sonar"
                	}
            }
        }
        stage('Upload to Artifactory') {
        	steps {
        		rtMavenDeployer (
        			id : 'deployer',
        			serverId: 'artifactory',
        			releaseRepo: 'maven-pipeline-repository',
        			snapshotRepo: 'maven-pipeline-repository-snapshot'
        		)
        		rtMavenRun (
        			pom: 'pom.xml',
        			goals : 'clean install',
        			deployerId : 'deployer'
        		)
        		rtPublishBuildInfo (
        			serverId : 'artifactory'
        		)
        	}
        }
        stage ('Docker Image') {
        	steps{
		        script {
		          dockerImage = docker.build registry + ":$BUILD_NUMBER"
		        }
      		}
        }
        stage ('Deploy Push Image to Docker Hub') {
        	steps{
		        script {
		          docker.withRegistry( '', registryCredential ) {
		            dockerImage.push()
		          }
		       }
        	}
        }
        stage ('Stop Running Containers') {
        	steps {
        		bat 'docker container stop devops_application'
				bat 'docker container rm devops_application'
        	}
        }
        
        stage ('Docker Deployment') {
        	steps {
        		bat docker container run --name devops_application -d -p 7000:8081 registry + ":$BUILD_NUMBER"
        	}
        }
	}
}
