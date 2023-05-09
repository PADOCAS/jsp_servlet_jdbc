CREATE TABLE public.login (
    login character varying(20) NOT NULL,
    senha character varying(20) NOT NULL,
    email character varying(100) NOT NULL,
    nome character varying(100) NOT NULL,
    admin boolean DEFAULT false NOT NULL,
    usuario_login character varying(20) DEFAULT 'admin'::character varying NOT NULL,
    perfil character varying(30),
    sexo character varying(1) DEFAULT 'M'::character varying NOT NULL,
    foto_user text,
    extensao_foto_user character varying(10),
    cep character varying(12) NOT NULL,
    logradouro character varying(100) NOT NULL,
    bairro character varying(60) NOT NULL,
    localidade character varying(60) NOT NULL,
    uf character varying(2) NOT NULL,
    numero character varying(15) NOT NULL,
    data_nascimento date,
    renda_mensal numeric(22,2) DEFAULT 0 NOT NULL
);


CREATE SEQUENCE public.telefone_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE public.telefone (
    login character varying(20) NOT NULL,
    id bigint DEFAULT nextval('public.telefone_seq'::regclass) NOT NULL,
    numero character varying(15) NOT NULL,
    usuario_login character varying(20) DEFAULT 'admin'::character varying NOT NULL
);



CREATE TABLE public.usuario (
    login character varying(20) NOT NULL,
    senha character varying(20) NOT NULL,
    nome character varying(50) NOT NULL
);




CREATE SEQUENCE public.versao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




CREATE TABLE public.versao (
    id bigint DEFAULT nextval('public.versao_seq'::regclass) NOT NULL,
    arquivo_sql character varying(50) NOT NULL,
    data_exec timestamp without time zone NOT NULL
);