FROM adoptopenjdk:11-hotspot-bionic

RUN mkdir /service

COPY build/libs /service

COPY context/entrypoint /entrypoint
COPY context/execute_query /execute_query
COPY context/ojdbc.properties /etc/ojdbc.properties

ENTRYPOINT ["/entrypoint"]
CMD ["select 1 from dual"]

ENV TNS_ADMIN=/etc
