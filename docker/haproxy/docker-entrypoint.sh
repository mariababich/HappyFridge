#!/bin/sh
#
#

set -e

# first arg is `-f` or `--some-option`
if [ "${1#-}" != "$1" ]; then
	set -- haproxy "$@"
fi

if [ "$1" = 'haproxy' ]; then
	shift # "haproxy"
	#   -W  -- "master-worker mode"
	#   -db -- disables background mode
#	set -- haproxy -W -db "$@"
	set -- haproxy -db "$@"
fi

/sbin/syslogd -O /proc/1/fd/1   # <--- link to docker's stdout

exec "$@"
