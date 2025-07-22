ALTER TABLE users
    ADD is_verified BOOLEAN;

ALTER TABLE users
DROP
COLUMN is_email_verified;

ALTER TABLE email_verification_tokens
DROP
COLUMN is_verified;