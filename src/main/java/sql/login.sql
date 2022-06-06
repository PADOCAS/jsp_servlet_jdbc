-- Criação da Tabela:
CREATE TABLE public.login
(
  login character varying(20) NOT NULL,
  senha character varying(20) NOT NULL,
  CONSTRAINT login_pk PRIMARY KEY (login)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.login
  OWNER TO postgres;

-- Insert:
INSERT INTO public.login (login, senha) values ('admin', 'admin');