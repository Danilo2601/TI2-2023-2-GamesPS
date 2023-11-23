

CREATE SEQUENCE public."id-users"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000
    CACHE 1;


ALTER TABLE public."id-users" OWNER TO ti2cc;

SET default_tablespace = '';

SET default_table_access_method = heap;


CREATE TABLE public.users (
    id integer DEFAULT nextval('public."id-users"'::regclass) NOT NULL,
   	usuario text NOT NULL,
	senha text NOT NULL,
	nome text NOT NULL,
	email text NOT NULL,
	descricao text,
	dataNascimento date NOT NULL,
	gerenciador bool NOT NULL
);


ALTER TABLE public.users OWNER TO ti2cc;


ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
  