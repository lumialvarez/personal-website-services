pipeline {
	agent any
	tools {
        maven 'Maven'
        jdk 'JDK'
    }
	environment {
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
		    }
		}
		stage('Deploy') {
			steps {
				script {
    			    REMOTE_HOME = sh (
                        script: "ssh centos@lmalvarez.com 'pwd'",
                        returnStdout: true
                    ).trim()
    			}
    			//script_internal_ip.sh -> ip route | awk '/docker0 /{print $9}'
    			script {
    			    INTERNAL_IP = sh (
                        script: "ssh centos@lmalvarez.com 'sudo bash script_internal_ip.sh'",
                        returnStdout: true
                    ).trim()
    			}
				
				sh "ssh centos@lmalvarez.com 'sudo rm -rf ${REMOTE_HOME}/tmp_jenkins/${JOB_NAME}'"
    			sh "ssh centos@lmalvarez.com 'sudo mkdir -p -m 777 ${REMOTE_HOME}/tmp_jenkins/${JOB_NAME}'"
				
				sh "scp -r ${WORKSPACE}/BUILD_TAG.txt centos@lmalvarez.com:${REMOTE_HOME}/tmp_jenkins/${JOB_NAME}"
    			sh "scp -r ${WORKSPACE}/Dockerfile centos@lmalvarez.com:${REMOTE_HOME}/tmp_jenkins/${JOB_NAME}"
				sh "scp -r ${WORKSPACE}/target centos@lmalvarez.com:${REMOTE_HOME}/tmp_jenkins/${JOB_NAME}"
				
			
				sh "ssh centos@lmalvarez.com 'sudo docker rm -f personal-website-services &>/dev/null && echo \'Removed old container\''"
				
				sh "ssh centos@lmalvarez.com 'sudo docker build -t personal-website-services ${WORKSPACE}/Dockerfile'"

				sh "ssh centos@lmalvarez.com 'sudo docker run --name personal-website-services --add-host=lmalvarez.com:${INTERNAL_IP} -p 9191:9191 -d --restart unless-stopped personal-website-services:latest'"
		    }
		}
	}
}