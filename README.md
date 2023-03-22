# credit-card

## Setup docker postgre

```shell
sudo docker run --rm \
--name payment-db \
-e POSTGRES_DB=payment \
-e POSTGRES_USER=paymentuser \
-e POSTGRES_PASSWORD=YgKH3Qh8wenMNPfDdaAa2gcGKt7pB4pf6jzzVaQe \
-e PGDATA=/var/lib/postgresql/data/pgdata \
-v "$PWD/payment-user-data:/var/lib/postgresql/data" \
-p 5432:5432 \
postgres:15
```

## Login postgre

```shell
psql -h 127.0.0.1 -U paymentuser payment
```