FROM registry.access.redhat.com/ubi7/ubi:7.8

RUN yum -y install https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm && \
    yum-config-manager --enable rhel-server-rhscl-7-rpms && \
    yum -y clean all && \
    rm -rf /var/cache/yum

RUN yum -y install java-latest-openjdk \
                   java-latest-openjdk-devel && \
    yum -y clean all && \
    rm -rf /var/cache/yum

COPY tester/oracle_instantclient_7.repo /etc/yum.repos.d/oracle_instantclient_7.repo

RUN curl -s -L https://yum.oracle.com/RPM-GPG-KEY-oracle-ol7 -o /etc/pki/rpm-gpg/RPM-GPG-KEY-oracle && \
    gpg --quiet --with-fingerprint /etc/pki/rpm-gpg/RPM-GPG-KEY-oracle

RUN yum -y install oracle-instantclient19.6-basic \
	oracle-instantclient19.6-devel \
	oracle-instantclient19.6-jdbc \
	oracle-instantclient19.6-odbc \
	oracle-instantclient19.6-sqlplus \
	oracle-instantclient19.6-tools && \
    yum -y clean all && \
    rm -rf /var/cache/yum

RUN mkdir /service

COPY build/libs /service

COPY tester/entrypoint /entrypoint
COPY tester/execute_query /execute_query
COPY tester/ojdbc.properties /etc/ojdbc.properties

ENTRYPOINT ["/entrypoint"]
CMD ["select 1 from dual"]

ENV TNS_ADMIN=/etc
