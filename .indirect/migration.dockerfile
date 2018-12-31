FROM mysql:latest

COPY .indirect/testrail/testrail.sql ./testrail.sql
COPY .indirect/testrail/migrate.sh ./migrate.sh
CMD ["./migrate.sh"]