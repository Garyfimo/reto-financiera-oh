pipeline {
    agent any
    stages {

        stage('Start') {
            steps {
                 notifyBuild('STARTED')
             }
        }

        stage('build') {
         steps {
             checkout scm
           }
        }

        stage('sonar') {
         steps {
             sh "./gradlew clean sonarqube"
           }
        }
    }
}


def notifyBuild(String buildStatus = 'STARTED') {
    // default build status in case is not passed as parameter
    buildStatus = buildStatus ?: 'SUCCESS'

    // variables and constants
    def colorName = 'RED'
    def colorCode = '#FF0000'
    def from = 'jenkins@belcorp.biz'
    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def summary = "${subject} (${env.RUN_DISPLAY_URL})"
    def details = "<p>${buildStatus}: Job <a href='${env.RUN_DISPLAY_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>"

    // override default values based on build status
    if (buildStatus == 'STARTED') {
        color = 'YELLOW'
        colorCode = '#FFFF00'
    } else if (buildStatus == 'SUCCESS') {
        color = 'GREEN'
        colorCode = '#00FF00'
    } else {
        color = 'RED'
        colorCode = '#FF0000'
    }

    // send notifications
    slackSend (
        color: colorCode,
        message: summary,
        channel: '#jenkins',
        teamDomain: 'armonica',
        tokenCredentialId: 'slack-token')
}