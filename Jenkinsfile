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
                dir('.build') {
                    stash name: 'dockerfile', includes: 'Dockerfile'
                    stash name: 'docker-compose-stack', includes: 'docker-compose-stack.yml'
                }
                dir('build/libs') {
                    stash name: 'jar', includes: 'cd_demo_movie_sevice-all-1.0-SNAPSHOT.jar'
                }
            }
        }

        stage("Package") {
                agent {
                    docker { 
                        image 'ugurgungor/docker-with-sudo:latest'
                        args '-u root:root -v /var/run/docker.sock:/var/run/docker.sock'
                        }
                }            
                steps {
                    unstash 'dockerfile'
                    unstash 'jar'
                    sh "ls -la"
                    sh "docker build . -t 51.15.240.50:8082/movie-service:${env.BUILD_ID}"
                    sh "sudo docker login -u admin -p admin123 51.15.240.50:8082"
                    sh "sudo docker push 51.15.240.50:8082/movie-service:${env.BUILD_ID}"
                }
        }

        stage("Deplo2QA") {
            agent {label 'swarm-qa'}
            steps {
                unstash 'docker-compose-stack'
                sh 'ls -la'
                sh "sudo docker login -u admin -p admin123 51.15.240.50:8082"
                sh "sudo docker pull 51.15.240.50:8082/movie-service:${env.BUILD_ID}"
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