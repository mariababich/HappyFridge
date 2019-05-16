FROM haproxy:1.8.1-alpine

COPY docker-entrypoint.sh /docker-entrypoint.sh
COPY haproxy.cfg /usr/local/etc/haproxy/haproxy.cfg
RUN chmod +x /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]

CMD ["haproxy", "-f", "/usr/local/etc/haproxy/haproxy.cfg", "-db"]
