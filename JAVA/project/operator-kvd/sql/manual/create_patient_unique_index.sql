CREATE UNIQUE INDEX IF NOT EXISTS ux_patients_fio_birthdate
    ON patients (
    last_name,
    first_name,
    COALESCE(middle_name, ''),
    birth_date
    );