--liquibase formatted sql
--changeset Katerina163:1
insert into client(id, full_name, phone, email, passport, password) values(
'39f6e3ba-d928-4137-bb2c-5f1f5ed9697a', 'Иванов Иван Иванович', 88005553535, 'ivan@gmail.com', 2957624795, 'admin123');
insert into bank(id, name, password) values('27eeb75d-4f5e-4eea-8972-41f489d92b40', 'Лучший банк', 'admin123');
--rollback truncate table client;

--changeset Katerina163:3
insert into credit(id, limit, interest_rate, bank_id) values('25aa6e95-5b1f-4f66-b586-1e3843d106ee', 5000000, 15, '27eeb75d-4f5e-4eea-8972-41f489d92b40');
insert into credit(id, limit, interest_rate, bank_id) values('2a52bec8-f867-46df-a6c9-c0a96db3ca60', 10000000, 10, '27eeb75d-4f5e-4eea-8972-41f489d92b40');
--rollback truncate table credit;

--changeset Katerina163:2
insert into loan_offer(id, client_id, credit_id, loan_amount, month) values(
'3b7219a1-623b-4fd2-ab6a-2c48e39f4aa8', '39f6e3ba-d928-4137-bb2c-5f1f5ed9697a', '25aa6e95-5b1f-4f66-b586-1e3843d106ee', 2000000, now());
--rollback truncate table loan_offer;