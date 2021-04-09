pipeline {
	agent any
	tools {
        maven 'Maven'
        jdk 'JDK'
    }
	stages {
		stage('Test') {
			steps {
                sh 'mvn clean test'
			}
			post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
		}
		stage('Build') {
			steps {
				sh 'mvn clean package spring-boot:repackage -DskipTests'
		    }
		}
		stage('Deploy') {
			steps {
				sh 'rm /opt/services/Dockerfile'
				sh 'rm /opt/services/PersonalWebsiteServices.jar'
				sh 'cp -a Dockerfile /opt/services/Dockerfile'
				sh 'cp -a target/PersonalWebsiteServices**.jar /opt/services/PersonalWebsiteServices.jar'
		    }
		}
	}
}
