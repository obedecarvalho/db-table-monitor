
--Tabelas
select table_schema, table_name
from information_schema.tables
where table_schema not in ('pg_catalog', 'information_schema');

--PK
select tc.table_schema, tc.table_name, tc.constraint_type, tc.constraint_name, kcu.column_name, kcu.ordinal_position
from information_schema.table_constraints tc
inner join information_schema.key_column_usage kcu on kcu.constraint_name = tc.constraint_name and kcu.constraint_schema = tc.constraint_schema
where tc.table_schema not in ('pg_catalog', 'information_schema')
	and tc.constraint_type = 'PRIMARY KEY';

--colunas
select table_schema, table_name, column_name, ordinal_position, data_type, is_nullable
from information_schema."columns"
where table_schema not in ('pg_catalog', 'information_schema')