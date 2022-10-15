pipeline {
  agent {
          docker {
              image 'maven:3.8.1-adoptopenjdk-11'
              args '-v /root/.m2:/root/.m2'
          }
      }
  stages {
    stage('Build') {
      when {
        expression {
          BRANCH_NAME != 'master'
        }
      }
      steps {
        sh 'mvn clean install -DskipTests'
      }
    }
    stage('Build & Tests') {
      when {
        branch "US*"
      }
      steps {
        sh 'mvn clean install '
      }
    }
  }
}
