CREATE TABLE public.marca (
    no_marca character varying(50) NOT NULL,
    CONSTRAINT marca_pk PRIMARY KEY (no_marca)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.marca
  OWNER TO postgres;

DROP TABLE public.marca;