#!/usr/bin/env bash

for i in $(seq 1 60); do
    output=$(mysqladmin status -hdb -uroot -p1234)
    status=$?
    if [[ "${status}" != "0" ]]; then
        echo No connection established. Mysqladmin status is ${status}.
        sleep 1;
        continue
    fi
    echo Connection established.
    echo ${output}
    echo Load the testrail.sql dump
    mysql --host=db -uroot -p1234<testrail.sql
    break
done