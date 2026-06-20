-- ============================================================================
-- PostgreSQL Hands-on Guide — mirrors spring-data-jpa-handson Docs 1, 2, 3
-- ============================================================================
-- How to use this file:
--   1. Run sections in order, top to bottom, in psql or pgAdmin's Query Tool.
--   2. After each "-- SCREENSHOT:" line, take your screenshot of the result
--      grid/output before moving to the next section.
--   3. Column names (code/name, em_id/em_name, dp_id/dp_name, etc.) match the
--      @Column annotations in the orm-learn project's Java entities, NOT the
--      co_code/co_name names the doc's raw SQL example uses — the doc's own
--      Country.java example maps to code/name, so we use that to stay
--      consistent with the actual app.
--
-- If you'd rather not type table-by-table, you can also just run:
--   mvn spring-boot:run
-- on the orm-learn project (now configured for PostgreSQL) — Hibernate will
-- create every table below automatically and data.sql will seed the rows.
-- Then just open psql/pgAdmin afterwards and run the SELECT statements in
-- this file to produce the same screenshots without typing the CREATE/INSERT
-- statements yourself.
-- ============================================================================


-- ============================================================================
-- SETUP — run this once, before anything else (from psql connected to the
-- default 'postgres' database, or via pgAdmin's "Create > Database")
-- ============================================================================
CREATE DATABASE ormlearn;

-- Now connect to it. In psql:
--   \c ormlearn
-- In pgAdmin, just switch your Query Tool's connection to the ormlearn database.


-- ============================================================================
-- DOC 1 — Hands-on 1: Country table creation
-- ============================================================================
CREATE TABLE country (
    code VARCHAR(2) PRIMARY KEY,
    name VARCHAR(50)
);

INSERT INTO country (code, name) VALUES ('IN', 'India');
INSERT INTO country (code, name) VALUES ('US', 'United States of America');

SELECT * FROM country;
-- SCREENSHOT: "Doc 1, Hands-on 1 — country table created, two rows inserted"


-- ============================================================================
-- DOC 1 — Hands-on 5: Populate the full country list
-- ============================================================================
-- Replace the two sample rows with the complete list of world countries.
TRUNCATE country;

INSERT INTO country (code, name) VALUES ('AF', 'Afghanistan');
INSERT INTO country (code, name) VALUES ('AL', 'Albania');
INSERT INTO country (code, name) VALUES ('DZ', 'Algeria');
INSERT INTO country (code, name) VALUES ('AS', 'American Samoa');
INSERT INTO country (code, name) VALUES ('AD', 'Andorra');
INSERT INTO country (code, name) VALUES ('AO', 'Angola');
INSERT INTO country (code, name) VALUES ('AI', 'Anguilla');
INSERT INTO country (code, name) VALUES ('AQ', 'Antarctica');
INSERT INTO country (code, name) VALUES ('AG', 'Antigua and Barbuda');
INSERT INTO country (code, name) VALUES ('AR', 'Argentina');
INSERT INTO country (code, name) VALUES ('AM', 'Armenia');
INSERT INTO country (code, name) VALUES ('AW', 'Aruba');
INSERT INTO country (code, name) VALUES ('AU', 'Australia');
INSERT INTO country (code, name) VALUES ('AT', 'Austria');
INSERT INTO country (code, name) VALUES ('AZ', 'Azerbaijan');
INSERT INTO country (code, name) VALUES ('BS', 'Bahamas');
INSERT INTO country (code, name) VALUES ('BH', 'Bahrain');
INSERT INTO country (code, name) VALUES ('BD', 'Bangladesh');
INSERT INTO country (code, name) VALUES ('BB', 'Barbados');
INSERT INTO country (code, name) VALUES ('BY', 'Belarus');
INSERT INTO country (code, name) VALUES ('BE', 'Belgium');
INSERT INTO country (code, name) VALUES ('BZ', 'Belize');
INSERT INTO country (code, name) VALUES ('BJ', 'Benin');
INSERT INTO country (code, name) VALUES ('BM', 'Bermuda');
INSERT INTO country (code, name) VALUES ('BT', 'Bhutan');
INSERT INTO country (code, name) VALUES ('BO', 'Bolivia, Plurinational State of');
INSERT INTO country (code, name) VALUES ('BQ', 'Bonaire, Sint Eustatius and Saba');
INSERT INTO country (code, name) VALUES ('BA', 'Bosnia and Herzegovina');
INSERT INTO country (code, name) VALUES ('BW', 'Botswana');
INSERT INTO country (code, name) VALUES ('BV', 'Bouvet Island');
INSERT INTO country (code, name) VALUES ('BR', 'Brazil');
INSERT INTO country (code, name) VALUES ('IO', 'British Indian Ocean Territory');
INSERT INTO country (code, name) VALUES ('BN', 'Brunei Darussalam');
INSERT INTO country (code, name) VALUES ('BG', 'Bulgaria');
INSERT INTO country (code, name) VALUES ('BF', 'Burkina Faso');
INSERT INTO country (code, name) VALUES ('BI', 'Burundi');
INSERT INTO country (code, name) VALUES ('KH', 'Cambodia');
INSERT INTO country (code, name) VALUES ('CM', 'Cameroon');
INSERT INTO country (code, name) VALUES ('CA', 'Canada');
INSERT INTO country (code, name) VALUES ('CV', 'Cape Verde');
INSERT INTO country (code, name) VALUES ('KY', 'Cayman Islands');
INSERT INTO country (code, name) VALUES ('CF', 'Central African Republic');
INSERT INTO country (code, name) VALUES ('TD', 'Chad');
INSERT INTO country (code, name) VALUES ('CL', 'Chile');
INSERT INTO country (code, name) VALUES ('CN', 'China');
INSERT INTO country (code, name) VALUES ('CX', 'Christmas Island');
INSERT INTO country (code, name) VALUES ('CC', 'Cocos (Keeling) Islands');
INSERT INTO country (code, name) VALUES ('CO', 'Colombia');
INSERT INTO country (code, name) VALUES ('KM', 'Comoros');
INSERT INTO country (code, name) VALUES ('CG', 'Congo');
INSERT INTO country (code, name) VALUES ('CD', 'Congo, the Democratic Republic of the');
INSERT INTO country (code, name) VALUES ('CK', 'Cook Islands');
INSERT INTO country (code, name) VALUES ('CR', 'Costa Rica');
INSERT INTO country (code, name) VALUES ('HR', 'Croatia');
INSERT INTO country (code, name) VALUES ('CU', 'Cuba');
INSERT INTO country (code, name) VALUES ('CW', 'Curaçao');
INSERT INTO country (code, name) VALUES ('CY', 'Cyprus');
INSERT INTO country (code, name) VALUES ('CZ', 'Czech Republic');
INSERT INTO country (code, name) VALUES ('CI', 'Côte d''Ivoire');
INSERT INTO country (code, name) VALUES ('DK', 'Denmark');
INSERT INTO country (code, name) VALUES ('DJ', 'Djibouti');
INSERT INTO country (code, name) VALUES ('DM', 'Dominica');
INSERT INTO country (code, name) VALUES ('DO', 'Dominican Republic');
INSERT INTO country (code, name) VALUES ('EC', 'Ecuador');
INSERT INTO country (code, name) VALUES ('EG', 'Egypt');
INSERT INTO country (code, name) VALUES ('SV', 'El Salvador');
INSERT INTO country (code, name) VALUES ('GQ', 'Equatorial Guinea');
INSERT INTO country (code, name) VALUES ('ER', 'Eritrea');
INSERT INTO country (code, name) VALUES ('EE', 'Estonia');
INSERT INTO country (code, name) VALUES ('ET', 'Ethiopia');
INSERT INTO country (code, name) VALUES ('FK', 'Falkland Islands (Malvinas)');
INSERT INTO country (code, name) VALUES ('FO', 'Faroe Islands');
INSERT INTO country (code, name) VALUES ('FJ', 'Fiji');
INSERT INTO country (code, name) VALUES ('FI', 'Finland');
INSERT INTO country (code, name) VALUES ('FR', 'France');
INSERT INTO country (code, name) VALUES ('GF', 'French Guiana');
INSERT INTO country (code, name) VALUES ('PF', 'French Polynesia');
INSERT INTO country (code, name) VALUES ('TF', 'French Southern Territories');
INSERT INTO country (code, name) VALUES ('GA', 'Gabon');
INSERT INTO country (code, name) VALUES ('GM', 'Gambia');
INSERT INTO country (code, name) VALUES ('GE', 'Georgia');
INSERT INTO country (code, name) VALUES ('DE', 'Germany');
INSERT INTO country (code, name) VALUES ('GH', 'Ghana');
INSERT INTO country (code, name) VALUES ('GI', 'Gibraltar');
INSERT INTO country (code, name) VALUES ('GR', 'Greece');
INSERT INTO country (code, name) VALUES ('GL', 'Greenland');
INSERT INTO country (code, name) VALUES ('GD', 'Grenada');
INSERT INTO country (code, name) VALUES ('GP', 'Guadeloupe');
INSERT INTO country (code, name) VALUES ('GU', 'Guam');
INSERT INTO country (code, name) VALUES ('GT', 'Guatemala');
INSERT INTO country (code, name) VALUES ('GG', 'Guernsey');
INSERT INTO country (code, name) VALUES ('GN', 'Guinea');
INSERT INTO country (code, name) VALUES ('GW', 'Guinea-Bissau');
INSERT INTO country (code, name) VALUES ('GY', 'Guyana');
INSERT INTO country (code, name) VALUES ('HT', 'Haiti');
INSERT INTO country (code, name) VALUES ('HM', 'Heard Island and McDonald Islands');
INSERT INTO country (code, name) VALUES ('VA', 'Holy See (Vatican City State)');
INSERT INTO country (code, name) VALUES ('HN', 'Honduras');
INSERT INTO country (code, name) VALUES ('HK', 'Hong Kong');
INSERT INTO country (code, name) VALUES ('HU', 'Hungary');
INSERT INTO country (code, name) VALUES ('IS', 'Iceland');
INSERT INTO country (code, name) VALUES ('IN', 'India');
INSERT INTO country (code, name) VALUES ('ID', 'Indonesia');
INSERT INTO country (code, name) VALUES ('IR', 'Iran, Islamic Republic of');
INSERT INTO country (code, name) VALUES ('IQ', 'Iraq');
INSERT INTO country (code, name) VALUES ('IE', 'Ireland');
INSERT INTO country (code, name) VALUES ('IM', 'Isle of Man');
INSERT INTO country (code, name) VALUES ('IL', 'Israel');
INSERT INTO country (code, name) VALUES ('IT', 'Italy');
INSERT INTO country (code, name) VALUES ('JM', 'Jamaica');
INSERT INTO country (code, name) VALUES ('JP', 'Japan');
INSERT INTO country (code, name) VALUES ('JE', 'Jersey');
INSERT INTO country (code, name) VALUES ('JO', 'Jordan');
INSERT INTO country (code, name) VALUES ('KZ', 'Kazakhstan');
INSERT INTO country (code, name) VALUES ('KE', 'Kenya');
INSERT INTO country (code, name) VALUES ('KI', 'Kiribati');
INSERT INTO country (code, name) VALUES ('KP', 'Democratic People''s Republic of Korea');
INSERT INTO country (code, name) VALUES ('KR', 'Republic of Korea');
INSERT INTO country (code, name) VALUES ('KW', 'Kuwait');
INSERT INTO country (code, name) VALUES ('KG', 'Kyrgyzstan');
INSERT INTO country (code, name) VALUES ('LA', 'Lao People''s Democratic Republic');
INSERT INTO country (code, name) VALUES ('LV', 'Latvia');
INSERT INTO country (code, name) VALUES ('LB', 'Lebanon');
INSERT INTO country (code, name) VALUES ('LS', 'Lesotho');
INSERT INTO country (code, name) VALUES ('LR', 'Liberia');
INSERT INTO country (code, name) VALUES ('LY', 'Libya');
INSERT INTO country (code, name) VALUES ('LI', 'Liechtenstein');
INSERT INTO country (code, name) VALUES ('LT', 'Lithuania');
INSERT INTO country (code, name) VALUES ('LU', 'Luxembourg');
INSERT INTO country (code, name) VALUES ('MO', 'Macao');
INSERT INTO country (code, name) VALUES ('MK', 'Macedonia, the Former Yugoslav Republic of');
INSERT INTO country (code, name) VALUES ('MG', 'Madagascar');
INSERT INTO country (code, name) VALUES ('MW', 'Malawi');
INSERT INTO country (code, name) VALUES ('MY', 'Malaysia');
INSERT INTO country (code, name) VALUES ('MV', 'Maldives');
INSERT INTO country (code, name) VALUES ('ML', 'Mali');
INSERT INTO country (code, name) VALUES ('MT', 'Malta');
INSERT INTO country (code, name) VALUES ('MH', 'Marshall Islands');
INSERT INTO country (code, name) VALUES ('MQ', 'Martinique');
INSERT INTO country (code, name) VALUES ('MR', 'Mauritania');
INSERT INTO country (code, name) VALUES ('MU', 'Mauritius');
INSERT INTO country (code, name) VALUES ('YT', 'Mayotte');
INSERT INTO country (code, name) VALUES ('MX', 'Mexico');
INSERT INTO country (code, name) VALUES ('FM', 'Micronesia, Federated States of');
INSERT INTO country (code, name) VALUES ('MD', 'Moldova, Republic of');
INSERT INTO country (code, name) VALUES ('MC', 'Monaco');
INSERT INTO country (code, name) VALUES ('MN', 'Mongolia');
INSERT INTO country (code, name) VALUES ('ME', 'Montenegro');
INSERT INTO country (code, name) VALUES ('MS', 'Montserrat');
INSERT INTO country (code, name) VALUES ('MA', 'Morocco');
INSERT INTO country (code, name) VALUES ('MZ', 'Mozambique');
INSERT INTO country (code, name) VALUES ('MM', 'Myanmar');
INSERT INTO country (code, name) VALUES ('NA', 'Namibia');
INSERT INTO country (code, name) VALUES ('NR', 'Nauru');
INSERT INTO country (code, name) VALUES ('NP', 'Nepal');
INSERT INTO country (code, name) VALUES ('NL', 'Netherlands');
INSERT INTO country (code, name) VALUES ('NC', 'New Caledonia');
INSERT INTO country (code, name) VALUES ('NZ', 'New Zealand');
INSERT INTO country (code, name) VALUES ('NI', 'Nicaragua');
INSERT INTO country (code, name) VALUES ('NE', 'Niger');
INSERT INTO country (code, name) VALUES ('NG', 'Nigeria');
INSERT INTO country (code, name) VALUES ('NU', 'Niue');
INSERT INTO country (code, name) VALUES ('NF', 'Norfolk Island');
INSERT INTO country (code, name) VALUES ('MP', 'Northern Mariana Islands');
INSERT INTO country (code, name) VALUES ('NO', 'Norway');
INSERT INTO country (code, name) VALUES ('OM', 'Oman');
INSERT INTO country (code, name) VALUES ('PK', 'Pakistan');
INSERT INTO country (code, name) VALUES ('PW', 'Palau');
INSERT INTO country (code, name) VALUES ('PS', 'Palestine, State of');
INSERT INTO country (code, name) VALUES ('PA', 'Panama');
INSERT INTO country (code, name) VALUES ('PG', 'Papua New Guinea');
INSERT INTO country (code, name) VALUES ('PY', 'Paraguay');
INSERT INTO country (code, name) VALUES ('PE', 'Peru');
INSERT INTO country (code, name) VALUES ('PH', 'Philippines');
INSERT INTO country (code, name) VALUES ('PN', 'Pitcairn');
INSERT INTO country (code, name) VALUES ('PL', 'Poland');
INSERT INTO country (code, name) VALUES ('PT', 'Portugal');
INSERT INTO country (code, name) VALUES ('PR', 'Puerto Rico');
INSERT INTO country (code, name) VALUES ('QA', 'Qatar');
INSERT INTO country (code, name) VALUES ('RO', 'Romania');
INSERT INTO country (code, name) VALUES ('RU', 'Russian Federation');
INSERT INTO country (code, name) VALUES ('RW', 'Rwanda');
INSERT INTO country (code, name) VALUES ('RE', 'Réunion');
INSERT INTO country (code, name) VALUES ('BL', 'Saint Barthélemy');
INSERT INTO country (code, name) VALUES ('SH', 'Saint Helena, Ascension and Tristan da Cunha');
INSERT INTO country (code, name) VALUES ('KN', 'Saint Kitts and Nevis');
INSERT INTO country (code, name) VALUES ('LC', 'Saint Lucia');
INSERT INTO country (code, name) VALUES ('MF', 'Saint Martin (French part)');
INSERT INTO country (code, name) VALUES ('PM', 'Saint Pierre and Miquelon');
INSERT INTO country (code, name) VALUES ('VC', 'Saint Vincent and the Grenadines');
INSERT INTO country (code, name) VALUES ('WS', 'Samoa');
INSERT INTO country (code, name) VALUES ('SM', 'San Marino');
INSERT INTO country (code, name) VALUES ('ST', 'Sao Tome and Principe');
INSERT INTO country (code, name) VALUES ('SA', 'Saudi Arabia');
INSERT INTO country (code, name) VALUES ('SN', 'Senegal');
INSERT INTO country (code, name) VALUES ('RS', 'Serbia');
INSERT INTO country (code, name) VALUES ('SC', 'Seychelles');
INSERT INTO country (code, name) VALUES ('SL', 'Sierra Leone');
INSERT INTO country (code, name) VALUES ('SG', 'Singapore');
INSERT INTO country (code, name) VALUES ('SX', 'Sint Maarten (Dutch part)');
INSERT INTO country (code, name) VALUES ('SK', 'Slovakia');
INSERT INTO country (code, name) VALUES ('SI', 'Slovenia');
INSERT INTO country (code, name) VALUES ('SB', 'Solomon Islands');
INSERT INTO country (code, name) VALUES ('SO', 'Somalia');
INSERT INTO country (code, name) VALUES ('ZA', 'South Africa');
INSERT INTO country (code, name) VALUES ('GS', 'South Georgia and the South Sandwich Islands');
INSERT INTO country (code, name) VALUES ('SS', 'South Sudan');
INSERT INTO country (code, name) VALUES ('ES', 'Spain');
INSERT INTO country (code, name) VALUES ('LK', 'Sri Lanka');
INSERT INTO country (code, name) VALUES ('SD', 'Sudan');
INSERT INTO country (code, name) VALUES ('SR', 'Suriname');
INSERT INTO country (code, name) VALUES ('SJ', 'Svalbard and Jan Mayen');
INSERT INTO country (code, name) VALUES ('SZ', 'Swaziland');
INSERT INTO country (code, name) VALUES ('SE', 'Sweden');
INSERT INTO country (code, name) VALUES ('CH', 'Switzerland');
INSERT INTO country (code, name) VALUES ('SY', 'Syrian Arab Republic');
INSERT INTO country (code, name) VALUES ('TW', 'Taiwan, Province of China');
INSERT INTO country (code, name) VALUES ('TJ', 'Tajikistan');
INSERT INTO country (code, name) VALUES ('TZ', 'Tanzania, United Republic of');
INSERT INTO country (code, name) VALUES ('TH', 'Thailand');
INSERT INTO country (code, name) VALUES ('TL', 'Timor-Leste');
INSERT INTO country (code, name) VALUES ('TG', 'Togo');
INSERT INTO country (code, name) VALUES ('TK', 'Tokelau');
INSERT INTO country (code, name) VALUES ('TO', 'Tonga');
INSERT INTO country (code, name) VALUES ('TT', 'Trinidad and Tobago');
INSERT INTO country (code, name) VALUES ('TN', 'Tunisia');
INSERT INTO country (code, name) VALUES ('TR', 'Turkey');
INSERT INTO country (code, name) VALUES ('TM', 'Turkmenistan');
INSERT INTO country (code, name) VALUES ('TC', 'Turks and Caicos Islands');
INSERT INTO country (code, name) VALUES ('TV', 'Tuvalu');
INSERT INTO country (code, name) VALUES ('UG', 'Uganda');
INSERT INTO country (code, name) VALUES ('UA', 'Ukraine');
INSERT INTO country (code, name) VALUES ('AE', 'United Arab Emirates');
INSERT INTO country (code, name) VALUES ('GB', 'United Kingdom');
INSERT INTO country (code, name) VALUES ('US', 'United States');
INSERT INTO country (code, name) VALUES ('UM', 'United States Minor Outlying Islands');
INSERT INTO country (code, name) VALUES ('UY', 'Uruguay');
INSERT INTO country (code, name) VALUES ('UZ', 'Uzbekistan');
INSERT INTO country (code, name) VALUES ('VU', 'Vanuatu');
INSERT INTO country (code, name) VALUES ('VE', 'Venezuela, Bolivarian Republic of');
INSERT INTO country (code, name) VALUES ('VN', 'Viet Nam');
INSERT INTO country (code, name) VALUES ('VG', 'Virgin Islands, British');
INSERT INTO country (code, name) VALUES ('VI', 'Virgin Islands, U.S.');
INSERT INTO country (code, name) VALUES ('WF', 'Wallis and Futuna');
INSERT INTO country (code, name) VALUES ('EH', 'Western Sahara');
INSERT INTO country (code, name) VALUES ('YE', 'Yemen');
INSERT INTO country (code, name) VALUES ('ZM', 'Zambia');
INSERT INTO country (code, name) VALUES ('ZW', 'Zimbabwe');
INSERT INTO country (code, name) VALUES ('AX', 'Åland Islands');

SELECT COUNT(*) FROM country;
-- SCREENSHOT: "Doc 1, Hands-on 5 — full country list populated"


-- ============================================================================
-- DOC 2 — Hands-on 1: Query Methods on country table
-- (These mirror the three Query Methods the doc asks you to write in Java;
--  running the equivalent SQL here gives you the same result set to screenshot.)
-- ============================================================================

-- (a) Countries whose name CONTAINS "ou"
SELECT * FROM country WHERE name ILIKE '%ou%';
-- SCREENSHOT: "Doc 2, Hands-on 1a — countries containing 'ou'"

-- (b) Same search, sorted ascending by name
SELECT * FROM country WHERE name ILIKE '%ou%' ORDER BY name ASC;
-- SCREENSHOT: "Doc 2, Hands-on 1b — same search, sorted ascending"

-- (c) Countries STARTING WITH the letter 'Z'
SELECT * FROM country WHERE name ILIKE 'Z%';
-- SCREENSHOT: "Doc 2, Hands-on 1c — countries starting with Z"


-- ============================================================================
-- DOC 2 — Hands-on 3: Department, Skill, Employee schema + data
-- (payroll.sql from the course platform isn't available, so this uses
--  realistic generated sample data with the exact column names the docs use.)
-- ============================================================================
CREATE TABLE department (
    dp_id   INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    dp_name VARCHAR(50)
);

CREATE TABLE skill (
    sk_id   INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    sk_name VARCHAR(50)
);

CREATE TABLE employee (
    em_id             INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    em_name           VARCHAR(50),
    em_salary         NUMERIC(10,2),
    em_permanent      BOOLEAN,
    em_date_of_birth  DATE,
    em_dp_id          INTEGER REFERENCES department(dp_id)
);

CREATE TABLE employee_skill (
    es_em_id INTEGER REFERENCES employee(em_id),
    es_sk_id INTEGER REFERENCES skill(sk_id),
    PRIMARY KEY (es_em_id, es_sk_id)
);

-- Departments (dp_id auto-generated: 1, 2, 3)
INSERT INTO department (dp_name) VALUES ('Engineering');
INSERT INTO department (dp_name) VALUES ('Human Resources');
INSERT INTO department (dp_name) VALUES ('Finance');

-- Skills (sk_id auto-generated: 1..6)
INSERT INTO skill (sk_name) VALUES ('Java');
INSERT INTO skill (sk_name) VALUES ('SQL');
INSERT INTO skill (sk_name) VALUES ('Spring Boot');
INSERT INTO skill (sk_name) VALUES ('Communication');
INSERT INTO skill (sk_name) VALUES ('Excel');
INSERT INTO skill (sk_name) VALUES ('Project Management');

-- Employees (em_id auto-generated: 1..6)
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES ('Arun Kumar', 65000.00, true, '1990-04-12', 1);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES ('Priya Sharma', 52000.00, true, '1988-09-23', 2);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES ('Karthik Raj', 48000.00, false, '1995-01-30', 2);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES ('Divya Menon', 58000.00, true, '1992-07-15', 3);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES ('Suresh Babu', 61000.00, true, '1985-11-02', 3);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES ('Lakshmi Iyer', 45000.00, false, '1998-03-19', 3);

-- employee_skill (many-to-many join rows)
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (2, 2);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 1);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 3);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (4, 4);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (4, 5);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (5, 5);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (5, 6);
-- Employee 3 (Karthik) and Employee 6 (Lakshmi) intentionally have NO skills yet —
-- used later to demonstrate "add skill to employee".

SELECT * FROM department;
SELECT * FROM skill;
SELECT * FROM employee;
SELECT * FROM employee_skill;
-- SCREENSHOT: "Doc 2, Hands-on 3 — department/skill/employee/employee_skill tables created and seeded"


-- ============================================================================
-- DOC 2 — Hands-on 4: @ManyToOne — Employee joined with Department
-- ============================================================================
SELECT e.em_id, e.em_name, e.em_salary, e.em_permanent, e.em_date_of_birth,
       d.dp_id, d.dp_name
FROM employee e
LEFT JOIN department d ON e.em_dp_id = d.dp_id
WHERE e.em_id = 1;
-- SCREENSHOT: "Doc 2, Hands-on 4 — employee 1 fetched with department (testGetEmployee)"


-- ============================================================================
-- DOC 2 — Hands-on 5: @OneToMany — Department joined with its Employees
-- ============================================================================
SELECT d.dp_id, d.dp_name, e.em_id, e.em_name
FROM department d
LEFT JOIN employee e ON e.em_dp_id = d.dp_id
WHERE d.dp_id = 3;
-- SCREENSHOT: "Doc 2, Hands-on 5 — department 3 fetched with its employee list (testGetDepartment)"


-- ============================================================================
-- DOC 2 — Hands-on 6: @ManyToMany — Employee joined with Skills
-- ============================================================================
SELECT e.em_id, e.em_name, s.sk_id, s.sk_name
FROM employee e
LEFT JOIN employee_skill es ON e.em_id = es.es_em_id
LEFT JOIN skill s ON es.es_sk_id = s.sk_id
WHERE e.em_id = 2;
-- SCREENSHOT: "Doc 2, Hands-on 6a — employee 2 fetched with skills"

-- Add a skill to employee 3 (who had none), then re-check:
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (3, 6);

SELECT e.em_id, e.em_name, s.sk_id, s.sk_name
FROM employee e
LEFT JOIN employee_skill es ON e.em_id = es.es_em_id
LEFT JOIN skill s ON es.es_sk_id = s.sk_id
WHERE e.em_id = 3;
-- SCREENSHOT: "Doc 2, Hands-on 6b — skill added to employee 3 (testAddSkillToEmployee)"


-- ============================================================================
-- DOC 3 — Hands-on 2: HQL "fetch join" equivalent — all permanent employees
-- with department AND skills in a single query
-- ============================================================================
SELECT e.em_id, e.em_name, e.em_permanent, d.dp_name, s.sk_name
FROM employee e
LEFT JOIN department d ON e.em_dp_id = d.dp_id
LEFT JOIN employee_skill es ON e.em_id = es.es_em_id
LEFT JOIN skill s ON es.es_sk_id = s.sk_id
WHERE e.em_permanent = true;
-- SCREENSHOT: "Doc 3, Hands-on 2 — getAllPermanentEmployees() HQL fetch-join equivalent"


-- ============================================================================
-- DOC 3 — Hands-on 4: AVG() aggregate — average salary
-- ============================================================================
-- Overall average salary
SELECT AVG(em_salary) FROM employee;
-- SCREENSHOT: "Doc 3, Hands-on 4a — getAverageSalary()"

-- Average salary filtered by department id (e.g. department 3)
SELECT AVG(em_salary) FROM employee WHERE em_dp_id = 3;
-- SCREENSHOT: "Doc 3, Hands-on 4b — getAverageSalary(id) for department 3"


-- ============================================================================
-- DOC 3 — Hands-on 5: Native Query — get all employees
-- ============================================================================
SELECT * FROM employee;
-- SCREENSHOT: "Doc 3, Hands-on 5 — getAllEmployeesNative()"


-- ============================================================================
-- SKIPPED (matches the project README — source files were never provided):
--   Doc 2, Hands-on 2 — stock table (needs stock-data.csv)
--   Doc 3, Hands-on 3 — quiz attempt schema (needs quiz.mwb)
--   Doc 3, Hands-on 6 — Criteria Query (doc only links an external tutorial,
--                        no fixed code/data spec to screenshot)
-- ============================================================================
