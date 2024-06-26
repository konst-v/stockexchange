CREATE USER stockexchange WITH password 'stockexchange';
ALTER USER stockexchange WITH SUPERUSER;
CREATE SCHEMA stockexchange;
GRANT USAGE ON SCHEMA stockexchange TO stockexchange;
ALTER ROLE stockexchange SET search_path = stockexchange;