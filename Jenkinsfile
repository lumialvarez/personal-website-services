pipeline {
	agent any
	tools {
        maven 'Maven'
        jdk 'JDK'
    }
	environment {
		DATASOURCE_URL = credentials("DATASOURCE_URL")
	}
	stages {
		stage('Prepare') {
			steps {
				sh 'cp -r src/main/resources/template.properties src/main/resources/application.properties'
				sh 'cat src/main/resources/application.properties'
				sh 'sed \'s/{DATASOURCE_URL}/$DATASOURCE_URL/g\' src/main/resources/application.properties'
				sh 'cat src/main/resources/application.properties'
			}
		}
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
				
				sh 'sudo docker build -t \'personal-website-services\' .'

				sh 'sudo docker run --name \'personal-website-services\' --add-host=lmalvarez.com:$(ip route | awk \'/docker0 /{print $9}\') -p 9191:9191 -d --restart unless-stopped personal-website-services:latest'
		    }
		}
	}
}