#!/usr/bin/env groovy
pipeline {
    agent any
    tools {
        jdk 'OpenJDK-15.0.2'
    }

    stages {
         stage('Setup') {
            steps {
                git url:'http://10.250.10.2:8929/root/hello-selenium-java.git', branch: 'main'
            }
            post {
                success {
                    archiveArtifacts 'build/libs/*.jar'
                }
            }
        }
        
        //Probar las pruebas, haciendo los waits en los test
        stage('Test') {
            steps {
                withGradle {
                    sh './gradlew clean test'
                    sh './gradlew -Dgeb.env=firefoxHeadless iT'
                    sh './gradlew codenarcTest'

                }
            }
            post {
                always {
                    junit 'build/test-results/test/TEST-*.xml'
                    publishHTML (
                        target: [
                            allowMissing : false,
                            alwaysLinkToLastBuild : false,
                            keepAll : true,
                            reportDir: "build/reports/codenarc",
                            reportFiles: "*.html",
                            reportName : "Codenarc Report"
                        ]
                    )

                }
            }
        }  
        
    }
}
