INSERT INTO akila_db.currency (id, code, name)
VALUES (UUID_TO_BIN('ff7057c2-cda9-4f6b-b94f-227b259a94d3'), 'VND', 'Viet Nam'),
       (UUID_TO_BIN('cf7057c2-cda9-4f6b-b94f-227b259a94d3'), 'USD', 'USA'),
       (UUID_TO_BIN('af7057c2-cda9-4f6b-b94f-227b259a94d3'), 'CNY', 'China');

INSERT INTO akila_db.account (id, created_date, date_of_birth, email, username)
VALUES (UUID_TO_BIN('37166790-4470-4484-bfbb-66364e0ff807'), '2020-01-04 10:10:10', '1995-01-10', 'a.nguyen@gmail.com', 'a.nguyen'),
       (UUID_TO_BIN('47166790-4470-4484-bfbb-66364e0ff807'), '2020-01-04 10:10:10', '1994-01-10', 'b.nguyen@gmail.com', 'b.nguyen'),
       (UUID_TO_BIN('57166790-4470-4484-bfbb-66364e0ff807'), '2020-01-04 10:10:10', '1996-01-10', 'c.nguyen@gmail.com', 'c.nguyen');

INSERT INTO akila_db.wallet (id, name, total_amount, account_id, currency_id)
VALUES (UUID_TO_BIN('9f05eb60-2fa7-4c32-a90a-64371961cb9d'), 'A.Nguyen-VND', 50000000,
        UUID_TO_BIN('37166790-4470-4484-bfbb-66364e0ff807'),
        UUID_TO_BIN('ff7057c2-cda9-4f6b-b94f-227b259a94d3')),
       (UUID_TO_BIN('1f05eb60-2fa7-4c32-a90a-64371961cb9d'), 'A.Nguyen-USD', 10000000,
        UUID_TO_BIN('37166790-4470-4484-bfbb-66364e0ff807'),
        UUID_TO_BIN('cf7057c2-cda9-4f6b-b94f-227b259a94d3')),
       (UUID_TO_BIN('2f05eb60-2fa7-4c32-a90a-64371961cb9d'), 'B.Nguyen-VND', 90000000,
        UUID_TO_BIN('47166790-4470-4484-bfbb-66364e0ff807'),
        UUID_TO_BIN('ff7057c2-cda9-4f6b-b94f-227b259a94d3'));