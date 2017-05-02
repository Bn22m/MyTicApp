--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

-- Started on 2017-05-02 01:25:50

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE myticapp;
--
-- TOC entry 2158 (class 1262 OID 16399)
-- Name: myticapp; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE myticapp WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_South Africa.1252' LC_CTYPE = 'English_South Africa.1252';


ALTER DATABASE myticapp OWNER TO postgres;

\connect myticapp

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 16400)
-- Name: tblmessage; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tblmessage (
    id integer DEFAULT 0 NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    message text NOT NULL,
    processing boolean DEFAULT false NOT NULL,
    done boolean DEFAULT false NOT NULL,
    "user" character varying(20),
    staff character varying(20),
    comment text,
    picture character varying(100) DEFAULT 'img/site'::character varying
);


ALTER TABLE tblmessage OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 16411)
-- Name: tblproduct; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tblproduct (
    "ID" character varying(30) NOT NULL,
    "DESCRIPTION" character varying(50) NOT NULL,
    "QUANTITY" integer DEFAULT 0 NOT NULL,
    "STATS" integer DEFAULT 0 NOT NULL,
    "MINIMUM" integer DEFAULT 10 NOT NULL,
    "DATE1" date NOT NULL,
    "DATE2" date NOT NULL
);


ALTER TABLE tblproduct OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16447)
-- Name: tblstaff; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tblstaff (
    "USERNAME" character varying(20) NOT NULL,
    "PASSWORD" character varying(20) NOT NULL,
    "COMPANYID" character varying(20) NOT NULL
);


ALTER TABLE tblstaff OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16408)
-- Name: tbluser; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tbluser (
    "USERNAME" character varying(20) NOT NULL,
    "PASSWORD" character varying(20) NOT NULL,
    "COMPANY" character varying(45) NOT NULL,
    "ADDRESS" character varying(100) NOT NULL,
    "PHONE" character varying(15),
    "MOBILE" character varying(15),
    "FAX" character varying(15),
    "EMAIL" character varying(35)
);


ALTER TABLE tbluser OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16461)
-- Name: tblvisitor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tblvisitor (
    ticket integer DEFAULT 63 NOT NULL,
    "time" timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE tblvisitor OWNER TO postgres;

--
-- TOC entry 2027 (class 2606 OID 16418)
-- Name: tblmessage tblmessage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblmessage
    ADD CONSTRAINT tblmessage_pkey PRIMARY KEY (id);


--
-- TOC entry 2031 (class 2606 OID 16420)
-- Name: tblproduct tblproduct_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblproduct
    ADD CONSTRAINT tblproduct_pkey PRIMARY KEY ("ID");


--
-- TOC entry 2033 (class 2606 OID 16451)
-- Name: tblstaff tblstaff_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblstaff
    ADD CONSTRAINT tblstaff_pkey PRIMARY KEY ("USERNAME");


--
-- TOC entry 2029 (class 2606 OID 16422)
-- Name: tbluser tbluser_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbluser
    ADD CONSTRAINT tbluser_pkey PRIMARY KEY ("USERNAME");


--
-- TOC entry 2035 (class 2606 OID 16467)
-- Name: tblvisitor tblvisitor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblvisitor
    ADD CONSTRAINT tblvisitor_pkey PRIMARY KEY (ticket);


--
-- TOC entry 2036 (class 2606 OID 16452)
-- Name: tblmessage tblmessage_STAFF_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tblmessage
    ADD CONSTRAINT "tblmessage_STAFF_fkey" FOREIGN KEY (staff) REFERENCES tblstaff("USERNAME");


-- Completed on 2017-05-02 01:25:50

--
-- PostgreSQL database dump complete
--

