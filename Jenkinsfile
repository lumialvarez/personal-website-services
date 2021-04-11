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
				sh 'sudo docker stop personal-website-services || true'
				sh 'sudo docker rm personal-website-services || true'
				
				sh 'sudo docker build -t 'personal-website-services' .'

				sh 'INTERNAL_IP=$(ip route | awk \'/docker0 /{print $9}\')'
				sh 'sudo docker run --name \'personal-website-services\' --add-host=lmalvarez.com:$INTERNAL_IP -p 9191:9191 -d personal-website-services:latest'
		    }
		}
	}
}