CREATE TABLE public."Users" (
    "Email" character varying(30) NOT NULL,
    "TeamID" integer NOT NULL,
    "FirstName" character varying(20) NOT NULL,
    "LastName" character varying(20) NOT NULL,
    "Password" character varying(255) NOT NULL,
    "Picture" character varying(255)
);


ALTER TABLE public."Users" OWNER TO postgres;


insert into public."Users" VALUES('admin@example.com', 1, 'Admin', 'Example', 'Secret', 'https://example.com/image.png');
