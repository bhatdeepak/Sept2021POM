pipeline{

	agent any
	
	tools{
		maven 'maven'
		}
		
	stages{
		stage("Build"')
		{
			steps
			{
				git 'https://github.com/jglick/simple-maven-project-with-tests.git'
				sh "mvn -Dmaven.test.failure.ignore=true clean package"
			}
			post
			{
				success
				{
					junit '**/target/surefire-reports/TEST-*.xml'
					archiveArtifacts 'target/*.jar'
				}
			}
		}
		
		stage('Regression automation test')
		{
			Steps
			{
				catchError(buildResult: 'Success', stageResult: 'Failure'){
					git 'https://github.com/bhatdeepak/Sept2021POM.git'
					sh "mvn clean install"
				}
			}
		}
		
		stage('Publish allure reports')
		{
			steps
			{
				script{
					allure([
						includeProperties: false,
						jdk: '',
						properties: [],
						reportBuildPolicy: 'ALWAYS',
						results: [[path: '/allure-results']]	
						])
					}
			}
		}			
						
		stage('publish extent report')
		{
			steps{
					publishHTML([allowMissing: false,
								alwaysLinkToLastBuild: false,
								keepAll: false,
								reportDir: 'build',
								reportFiles: 'TestExecutionReport.html',
								reportName: 'HTML Extent Report'
								reportTitles: ''])
				}
		}
		
	}
}										 				