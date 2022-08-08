pipeline {
  agent any
  stages {
    stage('clean') {
      steps {
        sh 'mvn clean'
      }
    }

    stage('install') {
      steps {
        sh 'mvn install'
      }
    }

    stage('package') {
      steps {
        sh 'mvn package'
      }
    }

  }
}