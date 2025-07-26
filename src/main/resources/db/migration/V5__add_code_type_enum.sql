ALTER TABLE verification_codes
    ADD verification_code_type VARCHAR(255);

ALTER TABLE verification_codes
    ALTER COLUMN verification_code_type SET NOT NULL;