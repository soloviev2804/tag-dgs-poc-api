--liquibase formatted sql

--changeset vsolovyev:sc-5591-data
insert into accounts
values ('00000000-0000-0000-0000-000000000000', 'account 1', 100000.00);

insert into cards
values ('2f1c9a32-0532-416e-bac1-9d0555295b27', 'card 1 account 1', '00000000-0000-0000-0000-000000000000');

insert into payment_limits
values ('22ad9807-b258-43b0-ac85-9d276788b86e', 'DAILY', '2f1c9a32-0532-416e-bac1-9d0555295b27', 1000);
insert into payment_limits
values ('fba79d14-523f-430c-a24e-ab94ddd84076', 'WEEKLY', '2f1c9a32-0532-416e-bac1-9d0555295b27', 10000);

insert into cards
values ('d1407e56-9c53-4f9a-bdeb-4930450009d6', 'card 2 account 1', '00000000-0000-0000-0000-000000000000');

insert into accounts
values ('5d29cf52-eb78-45cc-bcc8-580450877c9c', 'account 2', 500000.00);

insert into cards
values ('69b49290-d0ea-4664-939d-38dae0999518', 'card 1 account 2', '5d29cf52-eb78-45cc-bcc8-580450877c9c');

insert into cards
values ('b761a5d5-704d-425a-af25-d696088ea91f', 'card 2 account 2', '5d29cf52-eb78-45cc-bcc8-580450877c9c');