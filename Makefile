COMPOSE_FILE = docker-compose.yml

up:
	docker-compose -f $(COMPOSE_FILE) up -d

down:
	docker-compose -f $(COMPOSE_FILE) down

restart: down up

logs:
	docker-compose -f $(COMPOSE_FILE) logs -f mariadb

maria-cli:
	docker exec -it mariadb-webflux mysql -u webfluxuser -p