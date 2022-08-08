pipeline {
  agent any
  tools {
    maven 'MAVEN_HOME'
    jdk 'JAVA_HOME'
  }
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
