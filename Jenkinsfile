pipeline {
    agent none
    options { skipDefaultCheckout() }
    stages {
        stage("Build") {
            agent {
                docker {
                    image 'openjdk:8-jdk-slim'
                    args '-v /root/.gradle:/root/.gradle'
                }
            }
            steps {
                checkout scm
                sh './gradlew clean compileJava'
                sh './gradlew clean test'
                junit '**/test-results/test/*.xml'
                sh './gradlew clean customFatJar'
                stash name: 'dockerfile', includes: '.build/Dockerfile'
                stash name: 'docker-compose-stack', includes: '.build/docker-compose-stack.yml'
                dir('build/libs') {
                    stash name: 'jar', includes: 'cd_demo_movie_sevice-all-1.0-SNAPSHOT.jar'
                }
            }
        }

        stage("Package") {
                agent {
                    docker { 
                        image 'docker:18.09'
                        args '-v /var/run/docker.sock:/var/run/docker.sock'
                        }
                }            
                steps {
                    unstash 'dockerfile'
                    unstash 'jar'
                    sh "ls - la"
                    sh "docker build . -t movie-service:${env.BUILD_ID}"
                    echo 'push image to internal nexus image repository'
                }
        }

        stage("Deplo2QA") {
            agent any
            steps {
                sh 'ls -la'
                echo 'deploy to qa'
            }
        }

        stage("ComponentTests") {
            failFast true
            parallel {
                stage('Api Tests') {
                    agent any
                    steps {
                        echo 'postman tests'
                    }
                }

                stage('CDC Tests') {
                    agent any
                    steps {
                        echo 'consumer driven contract tests'
                    }
                }
            }
        }

        stage("Deploy2Prod") {
            agent any
            steps {
                echo 'deploy to prod'
            }
        }
    }
}