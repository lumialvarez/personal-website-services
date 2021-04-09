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
				sh 'sudo rm -rf /opt/services/*'
				sh 'sudo cp -a Dockerfile /opt/services/Dockerfile'
				sh 'sudo cp -a target/PersonalWebsiteServices**.jar /opt/services/PersonalWebsiteServices.jar'
		    }
		}
	}
}
