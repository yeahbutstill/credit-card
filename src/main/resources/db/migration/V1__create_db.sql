drop table if exists credit_card;
create table credit_card (
    id bigserial NOT NULL,
    credit_card_number varchar(20) NOT NULL,
    cvv varchar(4) NOT NULL,
    expiration_date varchar(7) NOT NULL,
    primary key(id)
);