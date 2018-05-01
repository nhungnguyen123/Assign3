pipeline{
	agent{
	dockerfile true
	}
	stages{
		stage('Example'){
		steps{
		echo 'Hello Word'
		sh 'echo myCustomEnvVar = $myCustomEnvVar'
		}
	}
	}
}