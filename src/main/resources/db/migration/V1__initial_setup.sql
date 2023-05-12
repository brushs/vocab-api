CREATE TABLE vocabulary
(
  id                        INTEGER PRIMARY KEY,
  name_en                   VARCHAR(64) NOT NULL,
  name_fr                   VARCHAR(64) NOT NULL,
  description_en            VARCHAR(512),
  description_fr            VARCHAR(512),
  active_ind                BOOL NOT NULL,
  created_by_user_id        VARCHAR(64) NOT NULL,
  created_date              DATE NOT NULL,
  last_updated_user_id      VARCHAR(64) NOT NULL,
  last_updated_date         DATE NOT NULL
);

INSERT INTO vocabulary VALUES (1, 'GEOSCAN', 'GEOSCAN*', '', '', true, 'system', current_timestamp, 'system', current_timestamp);
INSERT INTO vocabulary VALUES (2, 'GC Common', 'GC Common*', '', '', true, 'system', current_timestamp, 'system', current_timestamp);

CREATE TABLE term
(
  id                        INTEGER PRIMARY KEY,
  name_en                   VARCHAR(64) NOT NULL,
  name_fr                   VARCHAR(64) NOT NULL,
  description_en            VARCHAR(512),
  description_fr            VARCHAR(512),
  active_ind                BOOL NOT NULL,
  vocabulary_id             INTEGER NOT NULL,
  parent_term_id            INTEGER,
  created_by_user_id        VARCHAR(64) NOT NULL,
  created_date              DATE NOT NULL,
  last_updated_user_id      VARCHAR(64) NOT NULL,
  last_updated_date         DATE NOT NULL
);

INSERT INTO term VALUES (1, 'Geology', 'Geology*', '', '', true, 1, null, 'system', current_timestamp, 'system', current_timestamp);
INSERT INTO term VALUES (2, 'Geology', 'Geology*', '', '', true, 2, null, 'system', current_timestamp, 'system', current_timestamp);
INSERT INTO term VALUES (3, 'Biology', 'Biology*', '', '', true, 2, null, 'system', current_timestamp, 'system', current_timestamp);
INSERT INTO term VALUES (4, 'Hard Rocks', 'Hard Rocks*', '', '', true, 2, 2, 'system', current_timestamp, 'system', current_timestamp);
INSERT INTO term VALUES (5, 'Soft Rocks', 'Soft Rocks*', '', '', true, 2, 2, 'system', current_timestamp, 'system', current_timestamp);
INSERT INTO term VALUES (6, 'Really Hard Rocks', 'Really Hard Rocks*', '', '', true, 2, 4, 'system', current_timestamp, 'system', current_timestamp);
