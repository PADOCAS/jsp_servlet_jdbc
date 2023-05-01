CREATE TABLE public.arroz (
    no_arroz character varying(50) NOT NULL,
    CONSTRAINT arroz_pk PRIMARY KEY (no_arroz)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.arroz
  OWNER TO postgres;