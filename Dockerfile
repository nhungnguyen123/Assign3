# Base image
FROM ubuntu

# Do image configuration
RUN /bin/bash -c 'echo this it checking'
ENV myCustomEnvVar="this is a sample"\
	otherEnvVar="this is also a sample."