FROM gitpod/workspace-mysql

ARG JAVA_VERSION=11.0.17-amzn
RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && sdk install java ${JAVA_VERSION} && sdk default java ${JAVA_VERSION} && sdk install maven"
RUN bash -c "curl 'https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip' -o 'awscliv2.zip' && unzip awscliv2.zip && sudo ./aws/install"
RUN bash -c "curl -Ls 'https://github.com/aws/aws-sam-cli/releases/latest/download/aws-sam-cli-linux-x86_64.zip' -o '/tmp/aws-sam-cli-linux-x86_64.zip' && unzip '/tmp/aws-sam-cli-linux-x86_64.zip' -d '/tmp/sam-installation' && sudo '/tmp/sam-installation/install' && sam --version"
