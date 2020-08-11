pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }

	options {
		timestamp()
		
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
                	withSonarQubeEnv("Sonar_Scanner") {
                		bat "mvn sonar:sonar"
                	}
            }
        }
        stage('Upload to Artifactory') {
        	steps {
        		rtMavenDeployer {
        			id : 'deployer',
        			serverId: 'artifactory',
        			releaseRepo: 'maven-pipeline-repository',
        			snapshotRepo: 'maven-pipeline-repository-snapshot'
        		}
        		rtMavenRun {
        			pom: 'pom.xml',
        			goals : 'clean install',
        			deployerId : 'deployer'
        		}
        		rtPublishBuildInfo {
        			serverId : 'artifactory'
        		}
        	}
        }
    }
}