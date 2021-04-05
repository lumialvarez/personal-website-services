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
				sh 'sudo systemctl stop personal-website-services'
				sh 'rm /opt/services/PersonalWebsiteServices.jar'
				sh 'cp -a target/PersonalWebsiteServices**.jar /opt/services/PersonalWebsiteServices.jar'
				sh 'sudo systemctl start personal-website-services'
		    }
		}
	}
}
