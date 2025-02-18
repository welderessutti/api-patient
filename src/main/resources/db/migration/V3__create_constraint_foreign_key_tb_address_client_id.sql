ALTER TABLE IF EXISTS tb_address
    ADD CONSTRAINT tb_address_patient_id_fk
    FOREIGN KEY (patient_id)
    REFERENCES tb_patient (id)
;