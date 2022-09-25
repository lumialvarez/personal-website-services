def APP_VERSION
pipeline {
	agent any
	tools {
		maven 'Maven'
		jdk 'JDK'
	}
	environment {
		SSH_MAIN_SERVER = credentials("SSH_MAIN_SERVER")
	
		DATASOURCE_URL = credentials("DATASOURCE_URL")
		DATASOURCE_USERNAME = credentials("DATASOURCE_USERNAME")
		DATASOURCE_PASSWORD = credentials("DATASOURCE_PASSWORD")
		JWT_SECRET = credentials("JWT_SECRET")
		
		DATASOURCE_URL_PRUEBAS = credentials("DATASOURCE_URL_PRUEBAS")
		DATASOURCE_USERNAME_PRUEBAS = credentials("DATASOURCE_USERNAME_PRUEBAS")
		DATASOURCE_PASSWORD_PRUEBAS = credentials("DATASOURCE_PASSWORD_PRUEBAS")
		JWT_SECRET_PRUEBAS = credentials("JWT_SECRET_PRUEBAS")
	}
	stages {
		stage('Get Version') {
			steps {
				script {
					APP_VERSION = sh (
						script: "mvn help:evaluate -Dexpression=project.version -q -DforceStdout",
						returnStdout: true
					).trim()
				}
				script {
					currentBuild.displayName = "#" + currentBuild.number + " - v" + APP_VERSION
				}
			}
		}
		stage('Test') {
			steps {
				sh 'rm src/main/resources/application.properties || true'
				sh 'java ReplaceSecrets.java DATASOURCE_URL $DATASOURCE_URL_PRUEBAS'
				sh 'java ReplaceSecrets.java DATASOURCE_USERNAME $DATASOURCE_USERNAME_PRUEBAS'
				sh 'java ReplaceSecrets.java DATASOURCE_PASSWORD $DATASOURCE_PASSWORD_PRUEBAS'
				sh 'java ReplaceSecrets.java JWT_SECRET $JWT_SECRET_PRUEBAS'
				sh 'cat src/main/resources/application.properties'
			
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
				sh 'rm src/main/resources/application.properties || true'
				sh 'java ReplaceSecrets.java DATASOURCE_URL $DATASOURCE_URL'
				sh 'java ReplaceSecrets.java DATASOURCE_USERNAME $DATASOURCE_USERNAME'
				sh 'java ReplaceSecrets.java DATASOURCE_PASSWORD $DATASOURCE_PASSWORD'
				sh 'java ReplaceSecrets.java JWT_SECRET $JWT_SECRET'
				sh 'cat src/main/resources/application.properties'
				
				sh 'mvn clean package spring-boot:repackage -DskipTests'

				sh "cd target ; docker build . -t lmalvarez/personal-website-services:${APP_VERSION}"
			}
		}
		stage('Deploy') {
			steps {
				//script_internal_ip.sh -> ip route | awk '/docker0 /{print $9}'
				script {
					INTERNAL_IP = sh (
						script: "ssh ${SSH_MAIN_SERVER} 'sudo bash script_internal_ip.sh'",
						returnStdout: true
					).trim()
				}

				sh "docker rm -f personal-website-services &>/dev/null && echo \'Removed old container\' "

				sh "docker run --name personal-website-services --net=backend-services --add-host=lmalvarez.com:${INTERNAL_IP} -p 9292:9292 -d --restart unless-stopped lmalvarez/personal-website-services:${APP_VERSION}"
			}
		}
		stage('Push') {
            steps {
                sh '''echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin '''

                sh "docker push lmalvarez/personal-website-services:${APP_VERSION}"
            }
        }
	}
}